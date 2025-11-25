-- Banco Nova schema inicial (Flyway)
CREATE TABLE IF NOT EXISTS users (
  id BIGSERIAL PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  usuario VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL,
  telefono VARCHAR(255),
  password_hash VARCHAR(255) NOT NULL,
  role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
  id BIGSERIAL PRIMARY KEY,
  tipo VARCHAR(30),
  numero VARCHAR(50) NOT NULL UNIQUE,
  moneda VARCHAR(10),
  saldo NUMERIC(18,2) NOT NULL DEFAULT 0,
  user_id BIGINT NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS movements (
  id BIGSERIAL PRIMARY KEY,
  account_id BIGINT NOT NULL REFERENCES accounts(id),
  fecha TIMESTAMP,
  descripcion VARCHAR(500) NOT NULL,
  monto NUMERIC(18,2) NOT NULL,
  tipo VARCHAR(20),
  estado VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS beneficiaries (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL REFERENCES users(id),
  alias VARCHAR(255) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  rut VARCHAR(50) NOT NULL,
  banco VARCHAR(255) NOT NULL,
  tipo_cuenta VARCHAR(255) NOT NULL,
  numero_cuenta VARCHAR(255) NOT NULL,
  favorito BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS transfers (
  id BIGSERIAL PRIMARY KEY,
  cuenta_origen_id BIGINT NOT NULL REFERENCES accounts(id),
  user_id BIGINT NOT NULL REFERENCES users(id),
  banco_destino VARCHAR(255),
  rut_destino VARCHAR(50),
  nombre_destino VARCHAR(255),
  tipo_cuenta_destino VARCHAR(255),
  numero_cuenta_destino VARCHAR(255),
  monto NUMERIC(18,2) NOT NULL,
  mensaje VARCHAR(500),
  fecha TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cards (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL REFERENCES users(id),
  tipo VARCHAR(20),
  numero_masked VARCHAR(50) NOT NULL UNIQUE,
  cupo NUMERIC(18,2) DEFAULT 0,
  utilizado NUMERIC(18,2) DEFAULT 0,
  activa BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS investments (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL REFERENCES users(id),
  tipo VARCHAR(255),
  monto NUMERIC(18,2) NOT NULL,
  rentabilidad_anual DOUBLE PRECISION,
  vencimiento VARCHAR(50)
);
