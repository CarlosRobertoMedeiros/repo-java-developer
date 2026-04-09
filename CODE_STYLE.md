# 📋 Code Style & Formatting Guide

Este documento describe las convenciones de código y cómo mantener el proyecto organizado.

---

## 🎯 Quick Start

### Formatear código (One-liner)
```bash
# Opción 1: Script conveniente
./scripts/format-code.sh

# Opción 2: Maven directo
mvn spotless:apply
```

### Formatear + Build + Tests
```bash
./scripts/format-and-build.sh
```

---

## 🔨 Herramientas de Formateo

### Spotless (Maven Plugin)
- **Formatea:** Google Java Format AOSP style
- **Organiza imports:** Automáticamente
- **Ejecuta:** En build (verificación) y on-demand

### EditorConfig
- **Archivo:** `.editorconfig` (raíz del proyecto)
- **Cubre:** Indentación, line endings, charset
- **Soportado por:** IntelliJ, VS Code, Eclipse, etc.

### IDE Settings
- **IntelliJ:** `.idea/codeStyles/Project.xml` (Google Style)
- **VS Code:** `.vscode/settings.json` (GoogleJavaFormat)

---

## 📐 Estándares

### Java

#### Indentación
```java
// ✅ Correcto - 4 espacios
public class Example {
    private String value;
    
    public void method() {
        if (condition) {
            doSomething();
        }
    }
}

// ❌ Incorrecto - tabs o 2 espacios
public class Example {
	private String value;  // Tab
	
  public void method() {   // 2 espacios
```

#### Longitud de Línea
- **Máximo:** 120 caracteres
- **Ideal:** < 100 caracteres
- **Rulers en editors:** Ya configurados

```java
// ✅ Bueno (< 100 chars)
public PaymentLinkResponse create(@Valid @RequestBody PaymentLinkRequest req) {
    return service.create(req);
}

// ⚠️ Aceptable (100-120 chars)
public PaymentLinkResponse createPaymentLink(
    @Valid @RequestBody PaymentLinkRequest paymentLinkRequest) {
    return service.create(paymentLinkRequest);
}

// ❌ Malo (> 120 chars)
public PaymentLinkResponse createPaymentLinkWithValidationAndPersistenceAndReturnTheResponseWithCompleteData(...)
```

#### Imports
```java
// Orden automática: java, javax, org, com, others
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkInPort;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;
import lombok.extern.slf4j.Slf4j;
```

#### Espacios en Blanco
```java
// ✅ Correcto
public void method() {
    String value = calculateValue();
    
    if (value != null) {
        processValue(value);
    }
    
    return true;
}

// ❌ Incorrecto
public void method(){
    String value=calculateValue();
    if(value!=null){
        processValue(value);}
    return true;}
```

#### Comentarios
```java
// ✅ Comentarios útiles
public void processPayment(UUID paymentId) {
    // Load the payment with all related transactions
    Payment payment = repository.findWithTransactions(paymentId);
    
    // Validate amount matches invoice total
    if (!isAmountValid(payment)) {
        throw new PaymentValidationException("Amount mismatch");
    }
}

// ❌ Comentarios obvios
public void processPayment(UUID paymentId) {
    // Get the payment
    Payment payment = repository.findWithTransactions(paymentId);
    
    // Check if valid
    if (!isAmountValid(payment)) {
        // Throw exception
        throw new PaymentValidationException("Amount mismatch");
    }
}
```

#### Nombres (Convenciones)
```java
// ✅ Correcto
public class PaymentLinkRepository { }
public interface PaymentLinkPort { }
public enum PaymentStatus { ACTIVE, EXPIRED, DISABLED }
private final String description;
public static final int DEFAULT_TIMEOUT = 5000;
private boolean isActive;  // Boolean prefijo "is"

// ❌ Incorrecto
public class paymentLinkRepository { }
public interface IPaidLink { }  // Interface prefix (Java no uses 'I')
public enum payment_status { }  // SCREAMING_SNAKE_CASE for non-constants
private final String desc;  // Too short
private final Boolean active;  // Use primitive boolean
```

### XML / YAML

#### Indentación
```xml
<!-- ✅ Correcto - 2 espacios -->
<project>
  <groupId>br.com.roberto</groupId>
  <properties>
    <java.version>21</java.version>
  </properties>
</project>

<!-- ❌ Incorrecto - 4 espacios -->
<project>
    <groupId>br.com.roberto</groupId>
    <properties>
        <java.version>21</java.version>
    </properties>
</project>
```

### Markdown

#### Encabezados
```markdown
# H1 - Título principal
## H2 - Secciones principales
### H3 - Subsecciones
#### H4 - Detalles menores

# ❌ Evitar H5+
```

---

## 🔄 Workflow Típico

### 1. Desarrollo
```bash
# Hacer cambios
# ... editar archivos ...

# El IDE puede formatear automáticamente (habilitado en settings)
```

### 2. Formatear antes de Commit
```bash
./scripts/format-code.sh
# O: mvn spotless:apply
```

### 3. Verificar Cambios
```bash
git diff  # Revisar qué cambió
git status  # Ver qué será committed
```

### 4. Commit
```bash
git add .
git commit -m "feat: descripción clara del cambio"
```

### 5. CI/CD (Automático)
```
Pipeline ejecuta:
✅ mvn spotless:check  (verifica formateo)
✅ mvn clean test      (ejecuta tests)
✅ mvn clean package   (build)
```

---

## ⚙️ Configuración por IDE

### IntelliJ IDEA

**Usar esta configuración:**
- Settings > Editor > Code Style > Scheme: **GoogleStyle**
- Settings > Editor > Code Style > Enable EditorConfig: **✅**
- Settings > Editor > General > Auto Import: **✅ On the fly**

**Atajos útiles:**
- `⌘L` (Mac) / `Ctrl+Alt+L` (Windows) - Formatear archivo
- `⌘O` (Mac) / `Ctrl+Alt+O` (Windows) - Organizar imports

### VS Code

**Configuración automática en `.vscode/settings.json`:**
- Format on Save: ✅
- Default Formatter: redhat.java
- Rulers: 80, 120

**Extensiones recomendadas:**
```bash
code --install-extension redhat.java
code --install-extension vscjava.vscode-maven
code --install-extension pivotal.vscode-spring-boot
```

### Eclipse

**Usar EditorConfig:**
1. Install plugin: Help > Eclipse Marketplace > "EditorConfig"
2. Window > Preferences > EditorConfig > habilitado
3. El archivo `.editorconfig` será aplicado automáticamente

---

## 🚫 Qué NO Hacer

```java
// ❌ No usar System.out.println()
System.out.println("Debug info");  // NUNCA

// ✅ Usar SLF4J (ya configurado con Lombok @Slf4j)
log.debug("Debug info");
log.info("Important operation");

// ❌ No dejar código comentado
// public void oldMethod() {
//     doSomething();
// }

// ✅ Eliminar o crear issue si es importante
// TODO: Refactor when project matures
// Issue: #123 - Improve performance

// ❌ No ignorar formatting
public void method(){ String x=5; }  // Mal formateado

// ✅ Aplicar formatting
public void method() {
    String x = 5;
}

// ❌ No usar tipos raw
List list = new ArrayList();
Map map = new HashMap();

// ✅ Usar tipos genéricos
List<String> list = new ArrayList<>();
Map<String, Integer> map = new HashMap<>();
```

---

## 📊 Checking Compliance

### Antes de Commit
```bash
# Verificar sin cambiar
mvn spotless:check

# Aplicar automáticamente
mvn spotless:apply
```

### En Pre-Commit Hook (Opcional)
Puedes agregar a `.git/hooks/pre-commit`:
```bash
#!/bin/bash
mvn spotless:check || {
    echo "❌ Code formatting issues found"
    echo "Run: mvn spotless:apply"
    exit 1
}
```

---

## 📚 Referencias

- **Google Java Style Guide:** https://google.github.io/styleguide/javaguide.html
- **Spotless GitHub:** https://github.com/diffplug/spotless
- **EditorConfig:** https://editorconfig.org/
- **Effective Java (Joshua Bloch)** - Libro recomendado

---

## ❓ FAQ

**P: ¿Por qué Google Java Format?**
A: Es el estándar de Google, usado en Android, y muy consistente.

**P: ¿Mi IDE puede formatear automáticamente?**
A: Sí, IntelliJ y VS Code están configurados para hacerlo al guardar.

**P: ¿Qué pasa si no formaateo antes de commit?**
A: El CI/CD fallará en `mvn spotless:check` y tu PR será rechazado.

**P: ¿Cómo hago rollback a código mal formateado?**
A: Usa `git checkout -- archivo.java` y luego `mvn spotless:apply`.

---

**¡Mantengamos el código limpio y consistente! 🎯**
