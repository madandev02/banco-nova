import { Card } from "antd";
export default function Faq() {
  return (
    <main className="nova-section my-8 min-h-[65vh]">
      <Card className="nova-card" bodyStyle={{ padding: 20 }}>
        <h1 className="text-3xl font-extrabold mb-3">Preguntas frecuentes</h1>
        <div className="space-y-3 text-sm text-slate-600">
          <details className="border rounded-xl p-3 bg-white" open>
            <summary className="font-semibold cursor-pointer">
              ¿Cómo recupero mi clave?
            </summary>
            <p className="mt-2">En producción sería con correo/SMS.</p>
          </details>
          <details className="border rounded-xl p-3 bg-white">
            <summary className="font-semibold cursor-pointer">
              ¿Cuánto demoran las transferencias?
            </summary>
            <p className="mt-2">Depende del banco destino (demo).</p>
          </details>
        </div>
      </Card>
    </main>
  );
}
