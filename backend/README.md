
# Banco Nova Backend - Part 1 (Auth + Security + JWT)

Este proyecto es la **Parte 1** del backend de Banco Nova:

- Java 17
- Spring Boot 3.3
- Spring Web
- Spring Security 6 + JWT
- Spring Data JPA
- PostgreSQL + Flyway
- Swagger / OpenAPI 3
- Dockerfile multi-stage
- CORS para `https://banco-nova.netlify.app`

## Endpoints incluidos

- `GET /` — Health check
- `POST /api/auth/register` — Registro
- `POST /api/auth/login` — Login

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Variables de entorno

- `DB_URL` (ej: `jdbc:postgresql://localhost:5432/banco_nova`)
- `DB_USER`
- `DB_PASS`
- `JWT_SECRET`
- `SERVER_PORT` (opcional, por defecto 8080)

## Ejecutar localmente

```bash
mvn spring-boot:run
```

O con Docker:

```bash
docker build -t banco-nova-backend-part1 .
docker run -p 8080:8080   -e DB_URL=jdbc:postgresql://host.docker.internal:5432/banco_nova   -e DB_USER=postgres   -e DB_PASS=postgres   -e JWT_SECRET=super-secret   banco-nova-backend-part1
```
