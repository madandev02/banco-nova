import PageHeader from "../../components/PageHeader";
import { Card, List, Button } from "antd";

const docs=[
  {id:1, titulo:"Cartola Cuenta Corriente - Noviembre 2025", tipo:"PDF"},
  {id:2, titulo:"Comprobante Transferencia #55231", tipo:"PDF"},
  {id:3, titulo:"Resumen Tarjeta Crédito - Octubre 2025", tipo:"PDF"},
];

export default function Documentos(){
  return (
    <div className="space-y-4">
      <PageHeader title="Documentos" subtitle="Cartolas y comprobantes" />
      <Card className="nova-card">
        <List
          dataSource={docs}
          renderItem={(d)=>(
            <List.Item actions={[<Button size="small" key="k">Descargar</Button>]}>
              <List.Item.Meta title={d.titulo} description={d.tipo}/>
            </List.Item>
          )}
        />
      </Card>
    </div>
  );
}
