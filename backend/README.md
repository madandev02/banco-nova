# Banco Nova Backend — Pro (Spring Boot + PostgreSQL + JWT + Flyway)

Backend completo y profesional para Banco Nova. Listo para desarrollo local, GitHub y despliegue gratuito.

## Stack
- Spring Boot 3 / Java 17
- PostgreSQL
- Spring Security + JWT (BCrypt)
- JPA/Hibernate
- Flyway migrations
- Swagger/OpenAPI

## 1. Levantar la base de datos (Docker)
Requisito: tener Docker instalado.

```bash
docker compose up -d
```

Esto crea automáticamente la DB `banconova` en `localhost:5432`.

## 2. Configurar variables (opcional)
Copia `.env.example` a `.env` o exporta variables en tu sistema.
Por defecto funciona con los valores locales.

## 3. Ejecutar el backend
```bash
mvn spring-boot:run
```

Flyway crea las tablas con la migración `V1__init.sql`.

## Usuario demo
El seeder crea un usuario demo si la DB está vacía:
- usuario: `12.345.678-9`
- clave: `1234`

## Swagger
http://localhost:8080/swagger-ui/index.html

## Endpoints
Auth
- POST `/api/auth/register`
- POST `/api/auth/login`
- GET  `/api/auth/me`

Core
- GET `/api/cuentas`
- GET `/api/movimientos?cuentaId=1`
- GET `/api/movimientos/ultimos?cuentaId=1`
- POST `/api/transferencias`
- GET  `/api/transferencias/ultimas`

Extras
- GET `/api/beneficiarios`
- POST `/api/beneficiarios`
- PATCH `/api/beneficiarios/{id}/favorito`
- GET `/api/tarjetas`
- GET `/api/inversiones`

## Seguridad / buenas prácticas para GitHub
- No subas `.env` ni `application-local.yml` (ya están ignorados).
- Cambia `JWT_SECRET` en producción.
- Usa un usuario de BD distinto a `postgres` si despliegas real.

## Despliegue gratis recomendado (Render)
**Render.com** permite backend Java gratis (con “sleep”) y PostgreSQL gratis.

Pasos:
1. Sube este repo a GitHub.
2. En Render crea:
   - **PostgreSQL** (Free)
   - **Web Service** (Free) desde GitHub
3. Variables de entorno en Render:
   - `DB_URL` (Render te la da)
   - `DB_USER`
   - `DB_PASS`
   - `JWT_SECRET`
   - `CORS_ORIGINS` = URL de tu frontend Vercel/Netlify

Render detecta Maven y ejecuta:
`mvn clean package` y `java -jar target/*.jar`

Alternativas gratis:
- Railway.app (tiene free trial)
- Fly.io (free tier limitado)
- Koyeb.com (free tier)
