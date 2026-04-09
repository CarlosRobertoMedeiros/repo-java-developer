# 🚀 Pull Request Guide

Instrucciones para hacer el Pull Request a tu fork y luego al repo upstream.

---

## 📋 Pre-requisitos

- ✅ Todos los tests pasan (`mvn clean test`)
- ✅ Formateo correcto (`mvn spotless:check`)
- ✅ Build exitoso (`mvn clean package`)
- ✅ Cambios testeados localmente

Ver `TESTING_INSTRUCTIONS.md` para detalles.

---

## 🔄 Flujo de PR (Fork + Upstream)

### Escenario: Tu fork → Tu rama → Upstream repo

```
Tu Fork en GitHub
    ↓
Tu rama: feature/improve-architecture-and-setup
    ↓
PR a main (en tu fork)
    ↓
PR a upstream (repo original)
    ↓
Merge en upstream
```

---

## ✅ Paso 1: Verificar Status Local

```bash
cd /Users/fabianskier/work/repo-java-developer/improve-dev-setup

# Ver status
git status
# Debería estar limpio

# Ver commits
git log --oneline main..
# Debería mostrar 2 commits

# Ver diferencias
git diff main
# Ver todos los cambios
```

---

## ✅ Paso 2: Push a Tu Fork

```bash
# Agregar remote de tu fork (si no está)
git remote -v
# Debería mostrar origin -> tu fork

# Si no:
git remote add origin https://github.com/TU_USUARIO/repo-java-developer.git

# Push la rama
git push -u origin feature/improve-architecture-and-setup

# Verificar en GitHub
# https://github.com/TU_USUARIO/repo-java-developer/tree/feature/improve-architecture-and-setup
```

---

## ✅ Paso 3: Crear PR en GitHub

### Opción A: Desde GitHub UI

1. **Ir a tu fork:** https://github.com/TU_USUARIO/repo-java-developer
2. **Click en "Pull requests"**
3. **Click en "New pull request"**
4. **Seleccionar:**
   - Base: `main` (tu fork)
   - Compare: `feature/improve-architecture-and-setup`
5. **Click "Create pull request"**

### Opción B: Desde CLI (gh)

```bash
# Si tienes gh instalado
gh pr create \
  --title "feat: Implement hexagonal architecture improvements and dev setup" \
  --body "$(cat <<'EOF'
## Summary
Comprehensive improvements to hub-manager project implementing production-ready enhancements and development infrastructure:

### TIER 1: Critical for Production
- Global exception handler with standardized JSON error responses
- Input validation using JSR-303 annotations
- Comprehensive logging with SLF4J and request tracing

### TIER 2: Architectural Improvements
- Domain-Driven Design with value objects
- Entity audit fields and optimistic locking
- Repository pattern improvements with payment URL generation

### TIER 3.1: API Documentation
- OpenAPI/Swagger configuration with full endpoint documentation

### Development Infrastructure
- Spotless Maven plugin for automatic code formatting
- EditorConfig for cross-IDE consistency
- IDE configuration for IntelliJ and VS Code
- Convenience scripts for developers

### Testing
- Comprehensive smoke tests covering CRUD operations
- Health check and metrics verification
- API documentation accessibility

## Test plan
- [x] Compile: `mvn clean package`
- [x] Run tests: `mvn clean test`
- [x] Smoke tests: `mvn clean test -pl hub-manager-smoke-tests`
- [x] Verify formatting: `mvn spotless:check`
- [x] Access Swagger UI
- [x] Check health endpoint
EOF
)" \
  --base main
```

---

## 📝 Descripción del PR (Template)

Copiar y ajustar:

```markdown
## Summary

Comprehensive improvements to hub-manager project implementing production-ready enhancements and development infrastructure across 3 implementation tiers.

## Changes

### TIER 1: Critical for Production
- **Global Exception Handler**: Centralized error handling with standardized JSON responses
- **Input Validation**: JSR-303 annotations for request validation
- **Comprehensive Logging**: SLF4J integration across all layers with request tracing

### TIER 2: Architectural Improvements
- **Domain-Driven Design**: Value objects (Amount, Description, PaymentUrl) and builder pattern
- **Entity Audit & Locking**: Version field for optimistic locking, audit fields for tracking changes
- **Repository Pattern**: PaymentUrlGenerator interface with validation strategies

### TIER 3.1: API Documentation
- **OpenAPI/Swagger**: Full API documentation with interactive UI
- **Annotations**: @Operation, @ApiResponse on all endpoints

### Development Infrastructure
- **Spotless Maven Plugin**: Automatic code formatting with Google Java Format
- **EditorConfig**: Cross-IDE configuration file
- **IDE Setup**: IntelliJ and VS Code configurations
- **Developer Scripts**: Convenience scripts for formatting and building
- **Documentation**: Comprehensive guides for developers

## Files Changed
- 31 files changed
- 2298 insertions (+)
- 46 deletions (-)

## Test Results
✅ All tests passing
✅ Build successful
✅ Smoke tests passing
✅ Code formatting verified

## Related Issues
Closes #N/A (si hay issue)

## Checklist
- [x] Tests pass locally
- [x] Code is formatted
- [x] Documentation updated
- [x] No breaking changes
- [x] API documentation complete
```

---

## ✅ Paso 4: PR al Upstream (Repo Original)

Una vez que tu PR esté en tu fork:

### Opción A: Desde GitHub UI

1. **Ir al repo original:** https://github.com/UPSTREAM_OWNER/repo-java-developer
2. **Click "Pull requests"**
3. **Click "New pull request"**
4. **Click "compare across forks"**
5. **Seleccionar:**
   - Base repo: `UPSTREAM_OWNER/repo-java-developer`
   - Base: `main`
   - Head repo: `TU_USUARIO/repo-java-developer`
   - Compare: `feature/improve-architecture-and-setup`
6. **Click "Create pull request"**
7. **Copiar la descripción desde tu PR anterior**

### Opción B: Desde CLI

```bash
# Agregar remote del upstream
git remote add upstream https://github.com/UPSTREAM_OWNER/repo-java-developer.git

# Fetch del upstream
git fetch upstream

# Crear PR (si tienes gh configurado para el upstream)
gh pr create \
  --repo UPSTREAM_OWNER/repo-java-developer \
  --title "feat: Implement hexagonal architecture improvements and dev setup" \
  --body "$(cat PULL_REQUEST_DESCRIPTION.md)" \
  --base main
```

---

## 🔍 Verificación Pre-PR

Antes de hacer click en "Create pull request":

```
Checklist final:
☐ Todos los tests pasan
☐ Build es exitoso
☐ Código está formateado (spotless:check)
☐ Cambios están en la rama correcta
☐ Descripción es clara y detallada
☐ No hay conflictos con main
☐ Documentación está actualizada
☐ Commits tienen mensajes claros
```

---

## 📊 Información de PR

**Branch source:** `feature/improve-architecture-and-setup`
**Branch target:** `main`
**Commits:** 2
**Total changes:** 31 files, 2298 insertions

### Commits incluidos:

```
1b76bae - config: Add code formatting and development setup infrastructure
8fd1fc3 - feat: Implement hexagonal architecture best practices and production-ready improvements
```

---

## 💬 Responding to Review

Si los reviewers piden cambios:

```bash
# Hacer cambios
# ... editar archivos ...

# Formatear
mvn spotless:apply

# Commit
git add .
git commit -m "fix: Address review comments"

# Push (no force push en PR)
git push origin feature/improve-architecture-and-setup

# El PR se actualiza automáticamente
```

---

## ✨ Después del Merge

Una vez que el upstream mergeea tu PR:

```bash
# Actualizar tu main local
git checkout main
git pull upstream main

# Eliminar worktree
cd /Users/fabianskier/work/repo-java-developer
git worktree remove improve-dev-setup

# Verificar
git worktree list
# Solo debería mostrar: main
```

---

## 🎉 ¡Listo!

Una vez mergeado:

1. Todos los cambios están en el repo original
2. Otros developers pueden clonar/pull
3. Empezar a usar las nuevas herramientas de desarrollo

```bash
# Otros developers pueden:
./scripts/format-code.sh
./scripts/format-and-build.sh
```

---

## ❓ Troubleshooting

### "PR shows too many changes"

Probablemente hay conflictos con main. Resuelve:

```bash
# En tu rama
git fetch upstream
git rebase upstream/main

# Si hay conflictos, resolver manualmente
# Luego:
git add .
git rebase --continue

# Push con force (es OK en rama personal)
git push --force-with-lease origin feature/improve-architecture-and-setup
```

### "PR no aparece en upstream"

Verifica que:
1. Estés haciendo PR a la rama correcta (main)
2. Tu fork tenga la rama actualizada
3. GitHub permita PRs de forks

---

## 📚 Referencias

- GitHub PR Docs: https://docs.github.com/en/pull-requests
- Contributing Guides: https://opensource.guide/how-to-contribute/
- Git Workflow: https://git-scm.com/book/en/v2

---

**¡Tu PR está lista! 🚀**
