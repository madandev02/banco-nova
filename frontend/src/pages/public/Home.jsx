import { Button, Card, Row, Col, Carousel, Tag } from "antd";
import { Link } from "react-router-dom";

const slides = [
  {
    title: "Banco Nova, tu banca digital",
    text: "Gestiona tus cuentas, tarjetas y transferencias con seguridad y rapidez.",
    cta: "Banca en Línea",
    to: "/login",
    pills: ["Transferencias 24/7", "Alertas inteligentes", "Tu saldo al instante"],
    gradient: "from-[#004A77] via-[#0A5F90] to-[#1AAFD0]"
  },
  {
    title: "Créditos simples y transparentes",
    text: "Simula tu crédito de consumo y conoce tu cuota estimada en segundos.",
    cta: "Simular crédito",
    to: "/app/creditos",
    pills: ["Hasta 60 cuotas", "Contratación online", "Tasa preferente"],
    gradient: "from-[#0A2342] via-[#004A77] to-[#0EA5E9]"
  },
  {
    title: "Invierte con confianza",
    text: "Fondos y depósitos a plazo para que alcances tus metas financieras.",
    cta: "Ver inversiones",
    to: "/app/inversiones",
    pills: ["Fondos conservadores", "DAP flexible", "Seguimiento mensual"],
    gradient: "from-[#004A77] via-[#0B3E66] to-[#FFC24B]"
  }
];

const highlights = [
  { title: "Cuenta Corriente Nova", desc: "Tu cuenta principal con transferencias gratis.", icon: "💳", to: "/productos#cuentas" },
  { title: "Tarjeta Nova Gold", desc: "Seguridad y beneficios exclusivos.", icon: "✨", to: "/productos#tarjetas" },
  { title: "Crédito de Consumo", desc: "Financia tus proyectos con cuotas claras.", icon: "🏡", to: "/productos#creditos" },
  { title: "Inversiones Nova", desc: "Haz crecer tu ahorro con respaldo.", icon: "📈", to: "/productos#inversiones" },
];

export default function Home(){
  return (
    <main>
      {/* HERO SLIDER */}
      <section className="bg-white">
        <div className="nova-section py-8">
          <Carousel autoplay dots className="rounded-xl2 overflow-hidden">
            {slides.map((s,i)=>(
              <div key={i}>
                <div className={`min-h-[320px] md:min-h-[380px] bg-gradient-to-r ${s.gradient} text-white p-7 md:p-12 flex items-center`}>
                  <Row className="w-full" gutter={[24,24]} align="middle">
                    <Col xs={24} md={14}>
                      <div className="max-w-2xl">
                        <h1 className="text-3xl md:text-5xl font-extrabold leading-tight">{s.title}</h1>
                        <p className="mt-3 text-base md:text-lg text-white/90">{s.text}</p>

                        <div className="mt-4 flex flex-wrap gap-2">
                          {s.pills.map(p => (
                            <Tag key={p} color="blue" className="px-3 py-1 text-xs md:text-sm bg-white/10 border-white/20 text-white">
                              {p}
                            </Tag>
                          ))}
                        </div>

                        <div className="mt-6 flex gap-2">
                          <Link to={s.to}>
                            <Button size="large" type="primary" className="bg-nova-accent text-nova-ink border-0">
                              {s.cta}
                            </Button>
                          </Link>
                          <Link to="/register">
                            <Button size="large" className="bg-white/10 text-white border-white/30 hover:!text-white">
                              Hazte cliente
                            </Button>
                          </Link>
                        </div>
                      </div>
                    </Col>

                    {/* "Ilustración" con cards */}<Col xs={24} md={10}>
    {/* Ilustración abstracta del producto (NO datos del cliente) */}
    <div className="relative">
      <div className="nova-card bg-white text-gray-800 p-4">
        <div className="flex items-center justify-between">
          <div>
            <div className="text-xs nova-muted">Tarjeta Nova Gold</div>
            <div className="text-lg font-extrabold mt-1">Beneficios exclusivos</div>
            <div className="text-xs nova-muted mt-1">Acumula puntos y controla tus gastos</div>
          </div>
          <div className="h-12 w-20 rounded-xl2 bg-gradient-to-r from-nova-primary to-nova-secondary opacity-90" />
        </div>
        <div className="mt-3 grid grid-cols-3 gap-2">
          <div className="h-10 rounded-lg bg-slate-100" />
          <div className="h-10 rounded-lg bg-slate-100" />
          <div className="h-10 rounded-lg bg-slate-100" />
        </div>
      </div>

      <div className="mt-3 grid grid-cols-2 gap-3">
        <div className="nova-card bg-white text-gray-800 p-4">
          <div className="text-xs nova-muted">Transferencias</div>
          <div className="text-xl font-extrabold mt-1">24/7</div>
          <div className="text-xs nova-muted mt-1">A otros bancos y Nova</div>
        </div>
        <div className="nova-card bg-white text-gray-800 p-4">
          <div className="text-xs nova-muted">Seguridad</div>
          <div className="text-xl font-extrabold mt-1">Protección</div>
          <div className="text-xs nova-muted mt-1">Alertas y bloqueo instantáneo</div>
        </div>
      </div>

      {/* Brillos decorativos */}
      <div className="absolute -top-6 -right-6 h-24 w-24 rounded-full bg-white/10 blur-2xl" />
      <div className="absolute -bottom-8 -left-8 h-28 w-28 rounded-full bg-white/10 blur-2xl" />
    </div>
  </Col>
</Row>
                </div>
              </div>
            ))}
          </Carousel>
        </div>
      </section>

      {/* HIGHLIGHTS */}
      <section className="nova-section py-4">
        <Row gutter={[16,16]}>
          {highlights.map((h)=>(
            <Col xs={24} md={12} lg={6} key={h.title}>
              <Link to={h.to}>
                <Card className="nova-card h-full hover:shadow-md transition">
                  <div className="text-3xl">{h.icon}</div>
                  <div className="font-extrabold mt-2">{h.title}</div>
                  <div className="text-sm text-slate-600 mt-1">{h.desc}</div>
                  <div className="text-sm font-semibold text-nova-primary mt-3">Conocer más →</div>
                </Card>
              </Link>
            </Col>
          ))}
        </Row>
      </section>

      {/* PROMO STRIP */}
      <section className="nova-section pb-8">
        <Card className="nova-card">
          <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-3">
            <div>
              <div className="font-extrabold text-xl">Simula tu Crédito de Consumo</div>
              <div className="text-sm text-slate-600">Hasta 60 cuotas. 100% online.</div>
            </div>
            <div className="flex gap-2">
              <Link to="/app/creditos"><Button type="primary" className="bg-nova-primary">Simula aquí</Button></Link>
              <Link to="/productos#creditos"><Button>Ver requisitos</Button></Link>
            </div>
          </div>
        </Card>
      </section>

      {/* TRUST + BENEFITS */}
      <section className="nova-section pb-10">
        <Row gutter={[16,16]}>
          <Col xs={24} lg={12}>
            <Card className="nova-card h-full">
              <div className="font-extrabold text-xl">Seguridad Banco Nova</div>
              <ul className="mt-3 text-sm text-slate-600 space-y-2 list-disc pl-5">
                <li>Autenticación reforzada y cifrado end‑to‑end.</li>
                <li>Notificaciones en tiempo real ante movimientos.</li>
                <li>Bloqueo inmediato de tarjetas desde tu portal.</li>
              </ul>
              <Link to="/legal"><Button className="mt-4">Conoce nuestras políticas</Button></Link>
            </Card>
          </Col>
          <Col xs={24} lg={12}>
            <Card className="nova-card h-full">
              <div className="font-extrabold text-xl">Beneficios y educación financiera</div>
              <p className="mt-2 text-sm text-slate-600">
                Accede a herramientas de ahorro, simuladores y productos pensados para tu día a día.
              </p>
              <Row gutter={[12,12]} className="mt-4">
                {["Ahorro programado","Cuotas claras","Alertas de gasto","Gestión de metas"].map(b=>(
                  <Col xs={12} key={b}>
                    <div className="bg-nova-bg rounded-xl2 p-3 text-sm font-semibold">{b}</div>
                  </Col>
                ))}
              </Row>
            </Card>
          </Col>
        </Row>
      </section>
    </main>
  );
}
