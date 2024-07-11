#!/bin/bash
# Backend build
cd backend/
./mvnw clean package
docker build -f src/main/docker/Dockerfile.jvm -t labseq-backend .

# Frontend build
cd ../
cd frontend/
docker build -t labseq-frontend .

# Run both apps
cd ../
docker-compose up
