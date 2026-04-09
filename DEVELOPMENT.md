# Development Guide - Hub Manager

## Setup Completo

### Requisitos
- Java 21+
- Maven 3.8+
- PostgreSQL 16 (o Docker)

### Instalación

```bash
# macOS
brew install openjdk@21 maven postgresql@16

# Ubuntu/Debian
sudo apt-get install openjdk-21-jdk maven postgresql-16

# PostgreSQL con Docker (recomendado)
docker run --name postgres-hub -e POSTGRES_USER=hub_manager_app \
  -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=db_hub_manager_app \
  -p 5432:5432 -d postgres:16

# PostgreSQL local
createuser -P hub_manager_app  # contraseña: 123456
createdb -O hub_manager_app db_hub_manager_app
```

## Build & Testing

```bash
cd general-spring/microservices/hub-manager

# Compilar
mvn clean compile

# Tests unitarios
mvn clean test

# Smoke tests (requiere app corriendo)
mvn clean test -pl hub-manager-smoke-tests

# Build completo
mvn clean package

# Run
mvn spring-boot:run
```

### Puertos
- **8041** - Aplicación (context: /hub-manager-app)
- **8081** - Actuator endpoints (/manage/health, /manage/metrics)

### Verificación
```bash
# Health
curl http://localhost:8081/manage/health

# API
curl -X GET http://localhost:8041/hub-manager-app/api/payment-links

# Swagger UI
open http://localhost:8041/hub-manager-app/swagger-ui.html

# OpenAPI JSON
curl http://localhost:8041/hub-manager-app/v3/api-docs | jq .
```

## Code Style

### Formato automático
```bash
# Aplicar Google Java Format (AOSP)
mvn spotless:apply

# Verificar formato
mvn spotless:check
```

### Configuración IDE
- **IntelliJ**: Code Style → Scheme: GoogleStyle (automático con .editorconfig)
- **VS Code**: Editor → redhat.java + format-on-save (automático)
- **EditorConfig**: `.editorconfig` en raíz del proyecto

### Estándares
- Indentación: 4 espacios (Java), 2 espacios (XML/YAML)
- Line width: 120 caracteres máximo
- Imports organizados automáticamente
- No usar `System.out.println()` - usar SLF4J con `@Slf4j`

## Arquitectura Implementada

### TIER 1: Critical for Production
- **Global Exception Handler**: Respuestas JSON estandarizadas con códigos de error
- **Input Validation**: JSR-303 annotations (@NotBlank, @Positive, etc)
- **Comprehensive Logging**: SLF4J en todos los componentes con request tracing

### TIER 2: Architectural Improvements
- **Domain-Driven Design**: Value Objects (Amount, Description, PaymentUrl)
- **Entity Audit & Locking**: Campos createdBy/updatedBy, @Version para optimistic locking
- **Repository Pattern**: Mejoras con PaymentUrlGenerator y manejo de errores

### TIER 3: API Documentation
- **OpenAPI/Swagger**: Documentación automática con springdoc-openapi
- **Endpoints documentados**: @Operation, @ApiResponse en todos los endpoints

### Development Infrastructure
- **Spotless**: Formateo automático (Google Java Format AOSP)
- **EditorConfig**: Consistencia entre IDEs
- **Scripts**: format-code.sh, format-and-build.sh para automatización
- **IDE Config**: .idea/ (IntelliJ), .vscode/ (VS Code)

## Troubleshooting

### Error: "Tests failed"
```bash
# Verificar PostgreSQL está corriendo
docker ps | grep postgres-hub

# Si no está corriendo, iniciar
docker start postgres-hub

# Verificar conexión
psql -h localhost -U hub_manager_app -d db_hub_manager_app
```

### Error: "Port 8041 already in use"
```bash
# Encontrar proceso
lsof -i :8041

# Matar proceso (macOS/Linux)
kill -9 <PID>

# O usar puerto diferente
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8042"
```

### Error: "Spotless failed"
```bash
# Aplicar formato automáticamente
mvn spotless:apply

# Luego re-compilar
mvn clean compile
```

## Git Workflow (Fork-based)

```bash
# 1. Crear worktree
git worktree add ../improve-dev-setup -b feature/improve-architecture-and-setup

# 2. Hacer cambios
cd ../improve-dev-setup
# ... editar archivos ...

# 3. Commit
mvn spotless:apply  # Formatear primero
git add .
git commit -m "feat: descripción clara"

# 4. Push a fork
git push -u origin feature/improve-architecture-and-setup

# 5. En GitHub: Crear PR a upstream
# - Base: main (upstream)
# - Compare: tu-usuario/feature/improve-architecture-and-setup

# 6. Después de merge
git checkout main
git pull upstream main
git worktree remove ../improve-dev-setup
```

## Performance & Monitoring

### Health Check
```bash
curl http://localhost:8081/manage/health
```

Respuesta esperada:
```json
{
  "status": "UP",
  "components": {
    "db": {"status": "UP"},
    "diskSpace": {"status": "UP"},
    "ping": {"status": "UP"}
  }
}
```

### Metrics
```bash
curl http://localhost:8081/manage/metrics
curl http://localhost:8081/manage/metrics/http.server.requests
```

## Preguntas Frecuentes

**P: ¿Cómo creo un nuevo endpoint?**
A: Implementa en `PaymentLinkController.java`, añade tests en `HubManagerAppApplicationTests.java`, documenta con `@Operation` y `@ApiResponse`.

**P: ¿Debo commitear cambios de IDE?**
A: NO. Los archivos IDE (`.idea/`, `.vscode/`) están en .gitignore. Solo commitea código fuente.

**P: ¿Cómo colaboro en el proyecto?**
A: Sigue el Git Workflow arriba. Siempre crea un branch feature/, hace PR al main, y solicita review.

**P: ¿Qué versión de Java debo usar?**
A: Java 21 (especificado en pom.xml). Instala con `brew install openjdk@21`.
