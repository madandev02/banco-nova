import PageHeader from "../../components/PageHeader";
import { Card, Form, Input, Button, message } from "antd";
import useAuthStore from "../../store/useAuthStore";
export default function Perfil(){
  const {user}=useAuthStore();
  const onFinish=()=>message.success("Datos guardados (demo)");
  return (
    <div className="space-y-4">
      <PageHeader title="Mi perfil" subtitle="Datos personales del cliente" />
      <Card className="nova-card max-w-2xl">
        <Form layout="vertical" onFinish={onFinish} initialValues={{
          nombre:user?.nombre||"Cliente Demo",
          rut:user?.rut||"12.345.678-9",
          email:user?.email||"demo@banconova.cl",
          telefono:"+56 9 1234 5678"
        }}>
          <Form.Item label="Nombre" name="nombre"><Input/></Form.Item>
          <Form.Item label="RUT" name="rut"><Input disabled/></Form.Item>
          <Form.Item label="Correo" name="email" rules={[{type:"email"}]}><Input/></Form.Item>
          <Form.Item label="Teléfono" name="telefono"><Input/></Form.Item>
          <Button type="primary" htmlType="submit" className="bg-nova-primary">Guardar</Button>
        </Form>
      </Card>
    </div>
  );
}
