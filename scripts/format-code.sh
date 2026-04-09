#!/bin/bash
# Quick script to format code
# Uso: ./scripts/format-code.sh

echo "🎨 Formatting code..."
mvn spotless:apply -q
echo "✅ Code formatted successfully!"
