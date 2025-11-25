import { Card, Row, Col, Button } from "antd";
import { Link } from "react-router-dom";

export default function Productos(){
  const items=[
    {id:"cuentas", title:"Cuentas", desc:"Corriente, Vista y Ahorro", to:"/register"},
    {id:"tarjetas", title:"Tarjetas", desc:"Débito y Crédito", to:"/register"},
    {id:"creditos", title:"Créditos", desc:"Consumo e Hipotecario", to:"/register"},
    {id:"inversiones", title:"Inversiones", desc:"DAP y Fondos", to:"/register"},
    {id:"simuladores", title:"Simuladores", desc:"Crédito y Ahorro", to:"/app/creditos"},
  ];
  return (
    <main className="nova-section my-8 min-h-[65vh] space-y-4">
      <h1 className="text-3xl font-extrabold">Productos y servicios</h1>
      <Row gutter={[16,16]}>
        {items.map(p=>(
          <Col xs={24} md={12} lg={8} key={p.id}>
            <Card id={p.id} className="nova-card h-full" title={p.title}>
              <p className="text-sm text-slate-600">{p.desc}</p>
              <div className="mt-4">
                <Link to={p.to}><Button type="primary" className="bg-nova-primary">Conocer más</Button></Link>
              </div>
            </Card>
          </Col>
        ))}
      </Row>
    </main>
  );
}
