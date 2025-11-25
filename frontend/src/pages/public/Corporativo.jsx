import { Card } from "antd";

export default function Corporativo() {
  return (
    <main className="nova-section my-8 min-h-[65vh]">
      <Card className="nova-card" bodyStyle={{ padding: 24 }}>
        <h1 className="text-3xl font-extrabold">Información Corporativa</h1>

        <p className="text-slate-600 mt-3">
          Banco Nova es una institución financiera ficticia creada para fines educativos y demostraciones profesionales.
          Su objetivo es representar el funcionamiento real de un banco moderno, seguro y centrado en la experiencia del usuario.
        </p>

        <h2 className="text-xl font-bold mt-8">Nuestra misión</h2>
        <p className="text-slate-600 mt-1">
          Entregar soluciones bancarias simples, rápidas y seguras, utilizando tecnología de vanguardia para mejorar la vida
          financiera de las personas y empresas.
        </p>

        <h2 className="text-xl font-bold mt-6">Nuestra visión</h2>
        <p className="text-slate-600 mt-1">
          Convertirnos en el banco digital líder en la región, destacando por innovación, transparencia y excelencia en el servicio.
        </p>

        <h2 className="text-xl font-bold mt-6">Gobierno corporativo</h2>
        <p className="text-slate-600 mt-1">
          Banco Nova se estructura mediante un directorio ficticio compuesto por expertos en tecnología, finanzas y seguridad.
          Su labor es asegurar el correcto funcionamiento, cumplimiento normativo y crecimiento sostenible del banco.
        </p>

        <h2 className="text-xl font-bold mt-6">Compromiso con la seguridad</h2>
        <p className="text-slate-600 mt-1">
          Aplicamos estándares modernos de ciberseguridad, incluyendo autenticación robusta, cifrado de datos,
          monitoreo 24/7 y protocolos antifraude diseñados para proteger a nuestros usuarios.
        </p>

        <h2 className="text-xl font-bold mt-6">Sostenibilidad</h2>
        <p className="text-slate-600 mt-1">
          Promovemos prácticas responsables, fomentando productos digitales que reduzcan el uso de papel
          y apoyando iniciativas comunitarias orientadas a la educación financiera.
        </p>
      </Card>
    </main>
  );
}
