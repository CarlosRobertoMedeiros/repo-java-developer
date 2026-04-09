# 🚀 Development Setup Guide - Hub Manager

Este documento describe cómo configurar tu ambiente para desarrollo en el proyecto hub-manager con **formateo automático** e **imports organizados**.

---

## 📋 Requisitos Previos

- **Java 21+** - [Descargar Amazon Corretto 21](https://corretto.aws/downloads/latest/amazon-corretto-21-x64-linux-jdk.tar.gz)
- **Maven 3.8+** - `brew install maven` o [Descargar](https://maven.apache.org/download.cgi)
- **PostgreSQL 16** - `brew install postgresql@16` o [Docker](https://www.docker.com/)
- **IDE** - IntelliJ IDEA, VS Code, o Eclipse

---

## 🔧 IDE Setup

### IntelliJ IDEA (Recomendado)

#### 1. Clonar el repositorio
```bash
git clone <repo-url>
cd repo-java-developer
```

#### 2. Abrir el proyecto
```bash
# Opción 1: Desde terminal
idea .

# Opción 2: Menu File > Open > Seleccionar directorio raíz
```

#### 3. Importar módulos Maven
- IntelliJ detectará automáticamente los módulos Maven
- Click en "Load Maven Changes" cuando aparezca

#### 4. Configurar SDK Java 21
1. **File → Project Structure → Project**
2. **SDK:** Seleccionar JDK 21
3. **Language level:** 21

#### 5. Activar Code Style Automático
1. **File → Settings → Editor → Code Style**
2. **Scheme:** GoogleStyle (ya configurado en `.idea/codeStyles/Project.xml`)
3. **Enable EditorConfig support:** ✅ Marcado

#### 6. Formateo Automático
- **Code → Reformat Code:** `Ctrl+Alt+L` (Windows/Linux) o `⌘+⌥+L` (Mac)
- **Code → Reformat Code → Rearrange code:** Organiza imports automáticamente

#### 7. Optimizar Imports
1. **Code → Optimize Imports:** `Ctrl+Alt+O` (Windows/Linux) o `⌘+⌥+O` (Mac)
2. O configurar automático:
   - **File → Settings → Editor → General → Auto Import**
   - ✅ Add unambiguous imports on the fly
   - ✅ Optimize imports on the fly

---

### VS Code

#### 1. Instalar Extensiones
```bash
# Las extensiones recomendadas están en .vscode/extensions.json
# VS Code te pedirá instalarlas automáticamente

# Si no, instalar manualmente:
code --install-extension redhat.java
code --install-extension vscjava.vscode-maven
code --install-extension pivotal.vscode-spring-boot
code --install-extension esbenp.prettier-vscode
code --install-extension eamodio.gitlens
code --install-extension sonarsource.sonarlint-vscode
```

#### 2. Configuración Automática
- VS Code usará automáticamente `settings.json` del repo
- Formateo automático al guardar: ✅ Habilitado

#### 3. Comandos Útiles
```
Ctrl+Shift+P → Format Document    # Formatear archivo
Ctrl+Shift+P → Organize Imports    # Organizar imports
```

---

## 🛠️ Maven Commands

### Compilar
```bash
# Solo compilar
mvn clean compile

# Compilar + tests
mvn clean package

# Solo un módulo
mvn clean package -pl hub-manager-app
```

### Formateo con Spotless

```bash
# Verificar formateo (sin cambios)
mvn spotless:check

# Aplicar formateo automáticamente
mvn spotless:apply

# Formateo + compilación
mvn spotless:apply clean package
```

### Tests
```bash
# Todos los tests
mvn clean test

# Solo smoke tests
mvn clean test -pl hub-manager-smoke-tests

# Tests de un módulo
mvn clean test -pl hub-manager-app

# Con logs
mvn clean test -X
```

### Ejecutar Aplicación
```bash
# En desarrollo
mvn spring-boot:run -pl hub-manager-app

# En background
mvn spring-boot:run -pl hub-manager-app &

# Con properties específicas
mvn spring-boot:run -pl hub-manager-app -Dspring-boot.run.arguments="--server.port=8042"
```

---

## 📝 Reglas de Formateo

### Código Java

**Indentación:** 4 espacios
```java
public class PaymentLink {
    private String description;  // 4 espacios
    
    public void method() {
        if (condition) {        // 8 espacios
            doSomething();      // 12 espacios
        }
    }
}
```

**Longitud máxima de línea:** 120 caracteres
```java
// ✅ Bueno - Menos de 120 chars
public PaymentLinkResponse create(@Valid @RequestBody PaymentLinkRequest request) {
    return paymentLinkService.create(request);
}

// ❌ Malo - Más de 120 chars
public PaymentLinkResponse createNewPaymentLinkWithValidRequestBodyAndReturnTheResponseDirectly(
    @Valid @RequestBody PaymentLinkRequest paymentLinkRequest) {
    return paymentLinkService.create(paymentLinkRequest);
}
```

**Organización de Imports:**
```java
// 1. java imports
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

// 2. javax imports
import javax.validation.Valid;

// 3. org imports
import org.springframework.data.domain.Page;

// 4. com imports
import com.mycompany.service.PaymentLinkService;
```

**Espacio en blanco:**
```java
// ✅ Bueno
public void method() {
    String value = calculateValue();
    if (value != null) {
        processValue(value);
    }
}

// ❌ Malo - Sin espacios
public void method(){
    String value=calculateValue();
    if(value!=null){
        processValue(value);}}
```

### XML / YAML

**Indentación:** 2 espacios
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>br.com.roberto</groupId>
  <properties>
    <java.version>21</java.version>
  </properties>
</project>
```

---

## ✅ Pre-Commit Checklist

Antes de hacer commit:

- [ ] **Formateo:** `mvn spotless:apply`
- [ ] **Tests:** `mvn clean test`
- [ ] **Build:** `mvn clean package -DskipTests`
- [ ] **Logs:** Sin `System.out.println()` - usar SLF4J
- [ ] **Imports:** Organizados (comando `spotless:apply` lo hace)
- [ ] **Sin comentarios TODOs:** Resolver o crear issue
- [ ] **Commit message:** Formato convencional (feat:, fix:, etc.)

---

## 🚦 CI/CD - Verificaciones Automáticas

En el pipeline CI se ejecutan automáticamente:

```bash
# 1. Verificar formateo
mvn spotless:check

# 2. Compilar
mvn clean compile

# 3. Tests
mvn clean test

# 4. Build
mvn clean package

# 5. Smoke tests
mvn clean test -pl hub-manager-smoke-tests
```

Si alguno falla, el PR será **rechazado automáticamente**.

---

## 🐛 Troubleshooting

### "Spotless failed" en build

```bash
# Solución: Aplicar formateo
mvn spotless:apply

# Luego verificar
mvn spotless:check
```

### Imports desorganizados en IDE

**IntelliJ:**
- `Code → Optimize Imports` (`⌘⌥O`)

**VS Code:**
- `Ctrl+Shift+P → Organize Imports`

**Maven:**
- `mvn spotless:apply`

### SDK Java no detectado

1. Descargar Java 21: https://corretto.aws/downloads/latest/
2. **File → Project Structure → SDK**
3. Click `+` → Add JDK
4. Seleccionar directorio de instalación

### Módulos no aparecen en IntelliJ

```bash
# Limpiar cache y recargar
rm -rf .idea/
mvn clean

# Abrir proyecto nuevamente
```

---

## 📚 Recursos

- **Google Java Style Guide:** https://google.github.io/styleguide/javaguide.html
- **Spotless Maven Plugin:** https://github.com/diffplug/spotless/tree/main/plugin-maven
- **EditorConfig:** https://editorconfig.org/
- **Spring Boot Guide:** https://spring.io/projects/spring-boot

---

## 🤝 Contributing

1. **Fork** el repositorio
2. Crear branch: `git checkout -b feature/mi-feature`
3. **Formatear:** `mvn spotless:apply`
4. **Tests:** `mvn clean test`
5. **Commit:** `git commit -m "feat: descripción"`
6. **Push:** `git push origin feature/mi-feature`
7. Crear **Pull Request**

---

## ❓ Preguntas?

- Crear una **issue** en GitHub
- Preguntar en el **Slack** del equipo
- Revisar `IMPROVEMENTS_SUMMARY.md` para arquitectura

**¡Happy Coding! 🎉**
