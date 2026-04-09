# Quick Start - Hub Manager

## Setup (5 min)

```bash
# 1. PostgreSQL con Docker
docker run --name postgres-hub -e POSTGRES_USER=hub_manager_app \
  -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=db_hub_manager_app \
  -p 5432:5432 -d postgres:16

# 2. Navega al proyecto
cd general-spring/microservices/hub-manager
```

## Commands

```bash
# Build & compile
mvn clean package

# Test (unit tests solo)
mvn clean test

# Test con smoke tests (requiere app corriendo)
mvn test -DskipTests=false -pl hub-manager-smoke-tests

# Run
mvn spring-boot:run

# Health (puerto 8081)
curl http://localhost:8081/manage/health

# API (puerto 8041)
curl -X GET http://localhost:8041/hub-manager-app/api/payment-links

# Format code
mvn spotless:apply
```

## Archivos importantes

- **DEVELOPMENT.md** - Guía técnica completa
- **.editorconfig** - Configuración IDE automática
- **scripts/format-and-build.sh** - Build + format + test

## Swagger

```
http://localhost:8041/hub-manager-app/swagger-ui.html
```
