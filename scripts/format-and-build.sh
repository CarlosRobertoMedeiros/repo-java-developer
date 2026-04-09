#!/bin/bash
# Script para formatear código y compilar
# Uso: ./scripts/format-and-build.sh

set -e

echo "🔍 Hub Manager - Format & Build"
echo "================================"
echo ""

# Colors
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${BLUE}1️⃣  Applying code formatting...${NC}"
mvn spotless:apply -q
echo -e "${GREEN}✅ Formatting applied${NC}"
echo ""

echo -e "${BLUE}2️⃣  Verifying code style...${NC}"
mvn spotless:check -q
echo -e "${GREEN}✅ Code style verified${NC}"
echo ""

echo -e "${BLUE}3️⃣  Running tests...${NC}"
mvn clean test -q
echo -e "${GREEN}✅ Tests passed${NC}"
echo ""

echo -e "${BLUE}4️⃣  Building project...${NC}"
mvn clean package -DskipTests -q
echo -e "${GREEN}✅ Build successful${NC}"
echo ""

echo -e "${GREEN}🎉 All checks passed! Ready to commit.${NC}"
echo ""
echo "Next steps:"
echo "  1. Review your changes: git status"
echo "  2. Stage changes: git add ."
echo "  3. Commit: git commit -m 'feat: description'"
echo "  4. Push: git push origin feature-branch"
