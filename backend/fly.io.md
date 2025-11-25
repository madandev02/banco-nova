
# Deploy en Fly.io - Banco Nova Backend

1. Instala Fly:
   - `curl -L https://fly.io/install.sh | sh`
   - `fly auth signup` / `fly auth login`

2. En el root del proyecto (`banco-nova-backend`), ejecuta:
   - `fly launch`
   - Responde:
     - App name: `banco-nova-backend`
     - Builder: `Dockerfile`
     - PostgreSQL: puedes crear una base gestionada por Fly si quieres, o usar Railway/otro.

3. Configura variables de entorno en Fly:
   - `fly secrets set DB_URL=...`
   - `fly secrets set DB_USER=...`
   - `fly secrets set DB_PASS=...`
   - `fly secrets set JWT_SECRET=tu-secreto`
   - `fly secrets set SERVER_PORT=8080`

4. Deploy:
   - `fly deploy`

5. Usa la URL de Fly en tu frontend:
   - Ejemplo: `https://banco-nova-backend.fly.dev`
