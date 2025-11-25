import PageHeader from "../../components/PageHeader";
import { Card, Switch, Form } from "antd";
export default function Configuracion(){
  return (
    <div className="space-y-4">
      <PageHeader title="Configuración" subtitle="Preferencias de la banca en línea" />
      <Card className="nova-card max-w-xl">
        <Form layout="vertical">
          <Form.Item label="Notificaciones por correo"><Switch defaultChecked/></Form.Item>
          <Form.Item label="Alertas de movimientos"><Switch defaultChecked/></Form.Item>
          <Form.Item label="Newsletter Banco Nova"><Switch/></Form.Item>
        </Form>
      </Card>
    </div>
  );
}
