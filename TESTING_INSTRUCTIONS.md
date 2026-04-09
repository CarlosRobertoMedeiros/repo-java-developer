# 🧪 Testing Instructions - Before PR

Sigue estos pasos para probar todo antes de hacer el Pull Request.

---

## 📋 Prerequisitos

Asegurate de tener instalado:

```bash
# Java 21
java -version
# Esperado: openjdk version "21.x.x" o similar

# Maven 3.8+
mvn -version
# Esperado: Apache Maven 3.8.x or later

# PostgreSQL 16 (para tests)
psql --version
```

Si falta algo:
```bash
# macOS
brew install openjdk@21 maven postgresql@16

# Ubuntu/Debian
sudo apt-get install openjdk-21-jdk maven postgresql-16

# Windows
# Descargar desde: https://www.oracle.com/java/technologies/javase-jdk21-downloads.html
# https://maven.apache.org/download.cgi
```

---

## 🧪 Pasos de Testing

### 1. Verificar que estamos en el worktree correcto

```bash
pwd
# Debería terminar en: /improve-dev-setup

git branch
# Debería mostrar: * feature/improve-architecture-and-setup

git log --oneline -1
# Debería mostrar: config: Add code formatting and development setup infrastructure
```

### 2. Compilar el proyecto

```bash
cd /Users/fabianskier/work/repo-java-developer/improve-dev-setup

# Limpiar y compilar
mvn clean compile

# Esperado: BUILD SUCCESS
```

### 3. Ejecutar Formateo Check

```bash
mvn spotless:check

# Esperado: BUILD SUCCESS
# (Si falla, ver sección "Troubleshooting")
```

### 4. Ejecutar Tests Unitarios

```bash
mvn clean test

# Esperado: Tests run: X, Failures: 0, Errors: 0
```

### 5. Tests de Smoke

```bash
mvn clean test -pl hub-manager-smoke-tests

# Esperado: Todos los smoke tests pasan
# Verifica:
# ✅ Application running
# ✅ Create payment link
# ✅ Get all payment links
# ✅ Invalid request handling
# ✅ Swagger UI available
# ✅ OpenAPI docs available
# ✅ Health check available
```

### 6. Build Completo

```bash
mvn clean package

# Esperado: BUILD SUCCESS
# Se crea: general-spring/microservices/hub-manager/hub-manager-app/target/*.jar
```

### 7. Levantar la Aplicación (Opcional)

```bash
# Necesita PostgreSQL corriendo
# Si usas Docker:
docker run --name postgres-hub \
  -e POSTGRES_USER=hub_manager_app \
  -e POSTGRES_PASSWORD=123456 \
  -e POSTGRES_DB=db_hub_manager_app \
  -p 5432:5432 \
  postgres:16

# Luego, en otra terminal:
cd /Users/fabianskier/work/repo-java-developer/improve-dev-setup/general-spring/microservices/hub-manager/hub-manager-app

mvn spring-boot:run

# Debería ver:
# [main] o.springframework.boot.StartupInfoLogger : Started HubManagerApp in X seconds
# [main] o.s.b.w.embedded.tomcat.TomcatWebServer : Tomcat started on port(s): 8041
```

### 8. Probar API Endpoints (Con app corriendo)

```bash
# Health Check
curl -X GET http://localhost:8041/hub-manager-app/manage/health
# Esperado: {"status":"UP"}

# Create Payment Link
curl -X POST http://localhost:8041/hub-manager-app/api/payment-links \
  -H "Content-Type: application/json" \
  -d '{"description":"Test Link","amount":100.50}'
# Esperado: 201 Created con response body

# Swagger UI
open http://localhost:8041/hub-manager-app/swagger-ui.html
# Debería abrir documentación interactiva

# OpenAPI JSON
curl -X GET http://localhost:8041/hub-manager-app/v3/api-docs | jq .
# Debería retornar documentación en JSON
```

---

## ✅ Checklist Completo

Marca cada paso conforme lo hayas hecho:

```
Testing Checklist:
- [ ] Compilar (mvn clean compile)
- [ ] Formateo (mvn spotless:check)
- [ ] Tests unitarios (mvn clean test)
- [ ] Smoke tests (mvn clean test -pl hub-manager-smoke-tests)
- [ ] Build (mvn clean package)
- [ ] App levantada (mvn spring-boot:run)
- [ ] Health check OK (curl /manage/health)
- [ ] API endpoints respondiendo
- [ ] Swagger UI accesible
- [ ] OpenAPI JSON accesible

Si todo está ✅, estamos listos para PR.
```

---

## 🚨 Troubleshooting

### Error: "Spotless failed"

```bash
# Problema: Código mal formateado
# Solución:
mvn spotless:apply

# Luego verificar:
mvn spotless:check
```

### Error: "Tests failed"

```bash
# Ver detalle del error
mvn clean test -X

# Si es por PostgreSQL no conectando:
docker run --name postgres-hub \
  -e POSTGRES_USER=hub_manager_app \
  -e POSTGRES_PASSWORD=123456 \
  -e POSTGRES_DB=db_hub_manager_app \
  -p 5432:5432 \
  postgres:16
```

### Error: "Port 8041 already in use"

```bash
# Encontrar proceso en puerto 8041
lsof -i :8041

# Matar proceso
kill -9 <PID>

# O usar puerto diferente
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8042"
```

### Error: "Module not found"

```bash
# Limpiar cache Maven
rm -rf ~/.m2/repository

# Luego rebuild
mvn clean install
```

---

## 📊 Resultados Esperados

Si todo funciona, deberías ver:

```
✅ BUILD SUCCESS (compilar)
✅ BUILD SUCCESS (spotless check)
✅ BUILD SUCCESS (tests)
✅ Tests run: X, Failures: 0, Errors: 0
✅ BUILD SUCCESS (smoke tests)
✅ BUILD SUCCESS (package)
✅ Started HubManagerApp in X seconds (app)
✅ HTTP 200 (health check)
✅ Swagger UI loads (http://localhost:8041/hub-manager-app/swagger-ui.html)
✅ OpenAPI JSON accessible (http://localhost:8041/hub-manager-app/v3/api-docs)
```

---

## 🎉 Listo para PR!

Una vez que todo pase:

```bash
# Verificar status
git status
# Debería estar limpio (sin cambios)

# Ver commits en la rama
git log --oneline origin/main..
# Debería mostrar 2 commits

# Crear PR
# En GitHub: Crear PR de feature/improve-architecture-and-setup a main
```

---

## 📝 Información del PR

**Branch:** `feature/improve-architecture-and-setup`
**Base:** `main`
**Commits:** 2
**Total changes:** 31 files, 2298 insertions

**Descripción:** Comprehensive improvements implementing production-ready enhancements and development infrastructure for hub-manager project.

---

## ❓ Preguntas?

Si algo falla o tienes dudas:
1. Revisar los logs del Maven build
2. Verificar que PostgreSQL está corriendo (si haciendo tests de DB)
3. Revisar sección "Troubleshooting"
4. Consultar DEVELOPMENT_SETUP.md para más detalles

**¡Gracias por testear! 🚀**
