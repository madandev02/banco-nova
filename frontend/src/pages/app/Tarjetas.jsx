import PageHeader from "../../components/PageHeader";
import { Card, Row, Col, Button, Tag } from "antd";

const cards=[
  {id:1, tipo:"Débito Nova", numero:"**** 4455", cupo:0, estado:"Activa"},
  {id:2, tipo:"Crédito Nova Gold", numero:"**** 9988", cupo:1200000, utilizado:420000, estado:"Activa"},
];

export default function Tarjetas(){
  return (
    <div className="space-y-4">
      <PageHeader title="Tarjetas" subtitle="Débito y crédito" />
      <Row gutter={[12,12]}>
        {cards.map(c=>(
          <Col xs={24} md={12} lg={8} key={c.id}>
            <Card className="nova-card">
              <div className="font-bold text-lg">{c.tipo}</div>
              <div className="nova-muted text-sm">{c.numero}</div>
              <div className="mt-3">
                {c.cupo>0 && <div className="text-sm">Cupo: <b>{c.cupo.toLocaleString("es-CL")}</b></div>}
                {c.utilizado!=null && <div className="text-xs nova-muted">Utilizado: {c.utilizado.toLocaleString("es-CL")}</div>}
              </div>
              <div className="mt-3 flex items-center justify-between">
                <Tag color="green">{c.estado}</Tag>
                <Button size="small">Bloquear</Button>
              </div>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
}
