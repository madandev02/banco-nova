# 🏦 Banco Nova -- Plataforma Bancaria Fullstack

Banco Nova es una plataforma bancaria fullstack profesional inspirada en
la banca chilena. Incluye frontend moderno, backend empresarial con Java
y Spring Boot, JWT, PostgreSQL, migraciones, dockerización y más.

## 📌 Tabla de Contenidos

-   Descripción
-   Características
-   Tecnologías
-   Arquitectura
-   Estructura del Proyecto
-   Instalación Local
-   Variables de Entorno
-   Base de Datos
-   Usuario Demo
-   API Docs (Swagger)
-   Screenshots
-   Autor

# 📝 Descripción

Banco Nova replica funcionalidades de un sistema bancario real: -
Autenticación segura - Gestión de cuentas - Movimientos reales -
Transferencias - Beneficiarios - Tarjetas - Inversiones - Dashboard
moderno

Frontend en React + Tailwind + Ant Design. Backend Java + Spring Boot
3 + PostgreSQL + JPA + Flyway + JWT.

# ✨ Características Principales

### 🔐 Seguridad

-   JWT\
-   BCrypt\
-   Validación\
-   CORS\
-   Variables de entorno

### 💳 Funcionalidades Bancarias

-   Cuentas bancarias\
-   Movimientos\
-   Transferencias\
-   Beneficiarios\
-   Tarjetas\
-   Inversiones\
-   Dashboard dinámico

### 🎨 Frontend

React + Vite + TailwindCSS + Ant Design

### 🧱 Backend

Java 17 + Spring Boot 3 + PostgreSQL + Flyway + JPA + JWT

# 🏗 Arquitectura General

Frontend en React.\
Backend en Spring Boot.\
API REST.\
Base de datos PostgreSQL.

# 📁 Estructura del Proyecto

    frontend/
    backend/

# 💻 Instalación Local

Requisitos: - Node.js 18+ - Java 17+ - Maven - PostgreSQL

## Backend

Crear base:

    CREATE DATABASE banconova;

Variables:

    DB_URL
    DB_USER
    DB_PASS
    JWT_SECRET

Run:

    mvn spring-boot:run

## Frontend

    npm install
    VITE_API_URL=http://localhost:8080/api
    npm run dev

# 🔧 Variables de Entorno

Backend:

    DB_URL
    DB_USER
    DB_PASS
    JWT_SECRET

Frontend:

    VITE_API_URL

# 🗄 Base de Datos

Flyway crea todas las tablas automáticamente.

# 👤 Usuario Demo

    Usuario: 12.345.678-9
    Email: mauricio@email.com
    Clave: 1234

# 📚 Swagger

    http://localhost:8080/swagger-ui/index.html

# 🖼 Screenshots

(Agregar aquí)

# 👨‍💻 Autor

Mauricio Narváez -- Fullstack Developer\
Portfolio: https://madandev-portfolio.vercel.app\
GitHub: https://github.com/madandev02
