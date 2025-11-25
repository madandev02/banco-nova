
# Deploy en Railway - Banco Nova Backend

1. Sube tu proyecto completo (Parte 1 + Parte 2 + Parte 3 integradas) a GitHub.
2. En Railway:
   - Crea un nuevo proyecto → "Deploy from GitHub Repo".
   - Selecciona tu repo `banco-nova-backend`.
   - Railway detecta el `Dockerfile` automáticamente.

3. Variables de entorno en Railway:
   - `DB_URL` → URL de la base de datos PostgreSQL de Railway (otro servicio dentro del mismo proyecto).
   - `DB_USER`
   - `DB_PASS`
   - `JWT_SECRET`
   - `SERVER_PORT` = `8080`

4. Frontend (Netlify) debe apuntar a la URL de Railway, por ejemplo:

```ts
export const API_URL = "https://banco-nova-backend-production.up.railway.app";
```

5. CORS: ya está configurado para permitir `https://banco-nova.netlify.app`.
