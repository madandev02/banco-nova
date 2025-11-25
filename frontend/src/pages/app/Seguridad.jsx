import PageHeader from "../../components/PageHeader";
import { Card, Form, Input, Button, message, Alert } from "antd";

export default function Seguridad(){
  const onFinish=()=>message.success("Clave actualizada (demo)");
  return (
    <div className="space-y-4">
      <PageHeader title="Seguridad" subtitle="Protege tu cuenta" />
      <Alert type="warning" showIcon message="Recomendación" description="Activa doble factor en backend (cuando lo integremos)."/>
      <Card className="nova-card max-w-xl">
        <Form layout="vertical" onFinish={onFinish}>
          <Form.Item label="Clave actual" name="old" rules={[{required:true}]}><Input.Password/></Form.Item>
          <Form.Item label="Nueva clave" name="new" rules={[{required:true},{min:6}]}><Input.Password/></Form.Item>
          <Form.Item label="Confirmar clave" name="confirm" dependencies={["new"]}
            rules={[{required:true},{validator:(_,v,cb)=>cb.getFieldValue("new")===v?Promise.resolve():Promise.reject("No coincide")}]}><Input.Password/></Form.Item>
          <Button type="primary" htmlType="submit" className="bg-nova-primary">Actualizar clave</Button>
        </Form>
      </Card>
    </div>
  );
}
