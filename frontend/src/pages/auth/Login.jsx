import { Card, Form, Input, Button, Alert } from "antd";
import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import { login } from "../../services/authService";
import useAuthStore from "../../store/useAuthStore";

export default function Login(){
  const nav=useNavigate();
  const {setSession}=useAuthStore();
  const [err,setErr]=useState(null);
  const [loading,setLoading]=useState(false);

  const onFinish=async(values)=>{
    setErr(null); setLoading(true);
    const res=await login(values.usuario, values.password);
    setLoading(false);
    if(res.ok){ setSession({token:res.token, user:res.user}); nav("/app/dashboard"); }
    else setErr(res.message||"No se pudo iniciar sesión");
  };

  return (
    <main className="min-h-[75vh] grid place-items-center nova-section py-10">
      <Card className="nova-card w-full max-w-md" bodyStyle={{padding:22}}>
        <h1 className="text-2xl font-extrabold text-center">Banca en Línea</h1>
        <p className="text-center text-sm nova-muted mt-1">Ingresa con tu RUT o correo</p>
        {err && <Alert type="error" showIcon className="mt-4" message={err}/>}
        <Form layout="vertical" className="mt-5" onFinish={onFinish}>
          <Form.Item label="RUT / Usuario" name="usuario" rules={[{required:true,message:"Ingresa tu RUT o correo"}]}><Input/></Form.Item>
          <Form.Item label="Contraseña" name="password" rules={[{required:true},{min:4,message:"Mínimo 4 caracteres"}]}><Input.Password/></Form.Item>
          <Button type="primary" htmlType="submit" block loading={loading} className="bg-nova-primary">Entrar</Button>
        </Form>
        <div className="text-center text-sm mt-4">
          ¿No tienes cuenta? <Link to="/register" className="font-semibold text-nova-primary">Regístrate</Link>
        </div>
        <p className="text-xs nova-muted text-center mt-3">Proyecto ficticio de portafolio.</p>
      </Card>
    </main>
  );
}
