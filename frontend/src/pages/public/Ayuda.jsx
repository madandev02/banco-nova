import { Card } from "antd";

export default function Ayuda() {
  return (
    <main className="nova-section my-8 min-h-[65vh]">
      <Card className="nova-card" bodyStyle={{ padding: 24 }}>
        <h1 className="text-3xl font-extrabold mb-4">Centro de Ayuda</h1>

        <p className="text-slate-600 mb-6">
          Aquí encontrarás respuestas rápidas, soporte ficticio y guías útiles para resolver dudas relacionadas con tu experiencia en Banco Nova.
        </p>

        <h2 className="text-xl font-bold mt-4">Preguntas frecuentes</h2>
        <ul className="list-disc list-inside text-slate-600 mt-2 space-y-1">
          <li>¿Cómo recuperar mi contraseña?</li>
          <li>¿Cómo bloquear mi tarjeta?</li>
          <li>¿Cómo realizar una transferencia?</li>
          <li>¿Qué hago si detecto un movimiento sospechoso?</li>
        </ul>

        <h2 className="text-xl font-bold mt-6">Soporte digital</h2>
        <p className="text-slate-600 mt-1">
          Nuestro equipo ficticio está disponible para ayudarte a resolver problemas técnicos, acceso a banca en línea
          y orientación general sobre el uso de la plataforma.
        </p>

        <h2 className="text-xl font-bold mt-6">Seguridad y emergencias</h2>
        <p className="text-slate-600 mt-1">
          Si crees que tu cuenta ha sido comprometida, recomendamos bloquear tus tarjetas inmediatamente desde la sección de Seguridad
          o contactar al soporte ficticio del banco.
        </p>

        <h2 className="text-xl font-bold mt-6">Reclamos y sugerencias</h2>
        <p className="text-slate-600 mt-1">
          Banco Nova ofrece un canal ficticio para reclamos, consultas y sugerencias orientadas a mejorar la experiencia de los usuarios.
        </p>
      </Card>
    </main>
  );
}
