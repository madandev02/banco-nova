import { Card, Form, Input, Button, Alert, message, Checkbox } from "antd";
import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { register } from "../../services/authService";

export default function Register(){
  const nav=useNavigate();
  const [err,setErr]=useState(null);
  const [loading,setLoading]=useState(false);

  const onFinish=async(values)=>{
    setErr(null); setLoading(true);
    const res=await register(values);
    setLoading(false);
    if(res.ok){ message.success("Registro exitoso ✅"); nav("/login"); }
    else setErr(res.message||"No se pudo registrar");
  };

  return (
    <main className="min-h-[75vh] grid place-items-center nova-section py-10">
      <Card className="nova-card w-full max-w-xl" bodyStyle={{padding:22}}>
        <h1 className="text-2xl font-extrabold text-center">Hazte cliente</h1>
        <p className="text-center text-sm nova-muted mt-1">Crea tu cuenta Banco Nova</p>
        {err && <Alert type="error" showIcon className="mt-4" message={err}/>}
        <Form layout="vertical" className="mt-5" onFinish={onFinish}>
          <div className="grid md:grid-cols-2 gap-3">
            <Form.Item label="Nombre completo" name="nombre" rules={[{required:true}]}><Input/></Form.Item>
            <Form.Item label="RUT" name="usuario" rules={[{required:true,message:"RUT requerido"}]}><Input placeholder="12.345.678-9"/></Form.Item>
          </div>
          <div className="grid md:grid-cols-2 gap-3">
            <Form.Item label="Correo" name="email" rules={[{required:true,type:"email"}]}><Input/></Form.Item>
            <Form.Item label="Teléfono" name="telefono" rules={[{required:true}]}><Input/></Form.Item>
          </div>
          <Form.Item label="Contraseña" name="password" rules={[{required:true},{min:6,message:"Mínimo 6 caracteres"}]}><Input.Password/></Form.Item>

          <Form.Item name="terms" valuePropName="checked" rules={[{validator:(_,v)=>v?Promise.resolve():Promise.reject(new Error("Debes aceptar los términos"))}]}>
            <Checkbox>Acepto términos y responsabilidades ficticias del banco</Checkbox>
          </Form.Item>

          <Button type="primary" htmlType="submit" block loading={loading} className="bg-nova-primary">Crear cuenta</Button>
        </Form>
        <div className="text-center text-sm mt-4">
          ¿Ya tienes cuenta? <Link to="/login" className="font-semibold text-nova-primary">Inicia sesión</Link>
        </div>
      </Card>
    </main>
  );
}
