import { useEffect, useState } from "react";
import { Card, Button, Table, Modal, Form, Input, Select, Switch, message } from "antd";
import PageHeader from "../../components/PageHeader";
import { getBeneficiarios, addBeneficiario, toggleFavorito } from "../../services/beneficiariosService";

export default function Beneficiarios(){
  const [data,setData]=useState([]);
  const [open,setOpen]=useState(false);
  const [loading,setLoading]=useState(true);
  const [form]=Form.useForm();

  const load=async()=>{
    setLoading(true);
    setData(await getBeneficiarios());
    setLoading(false);
  };

  useEffect(()=>{load();},[]);

  const onCreate=async(v)=>{
    const res=await addBeneficiario(v);
    if(res.ok){ message.success("Destinatario agregado ✅"); setOpen(false); form.resetFields(); load(); }
    else message.error(res.message||"Error");
  };

  const cols=[
    {title:"Alias", dataIndex:"alias"},
    {title:"Nombre", dataIndex:"nombre"},
    {title:"RUT", dataIndex:"rut"},
    {title:"Banco", dataIndex:"banco"},
    {title:"Tipo", dataIndex:"tipoCuenta"},
    {title:"Nº cuenta", dataIndex:"numeroCuenta"},
    {title:"Favorito", dataIndex:"favorito", render:(v,r)=><Switch checked={v} onChange={async()=>{await toggleFavorito(r.id); load();}}/>}
  ];

  return (
    <div className="space-y-4">
      <PageHeader title="Destinatarios" subtitle="Usuarios registrados para transferencias"
        right={<Button type="primary" className="bg-nova-primary" onClick={()=>setOpen(true)}>Nuevo destinatario</Button>}
      />
      <Card className="nova-card">
        <Table rowKey="id" columns={cols} dataSource={data} loading={loading}/>
      </Card>

      <Modal title="Nuevo destinatario" open={open} onCancel={()=>setOpen(false)} onOk={()=>form.submit()} okText="Guardar">
        <Form form={form} layout="vertical" onFinish={onCreate}>
          <Form.Item label="Alias" name="alias" rules={[{required:true}]}><Input placeholder="Ej: Mamá"/></Form.Item>
          <Form.Item label="Nombre" name="nombre" rules={[{required:true}]}><Input/></Form.Item>
          <Form.Item label="RUT" name="rut" rules={[{required:true}]}><Input/></Form.Item>
          <Form.Item label="Banco" name="banco" rules={[{required:true}]}>
            <Select options={["Banco Nova","Banco Estado","BCI","Santander","Scotiabank","Falabella","Itaú"].map(b=>({value:b,label:b}))}/>
          </Form.Item>
          <Form.Item label="Tipo de cuenta" name="tipoCuenta" rules={[{required:true}]}>
            <Select options={["Cuenta Corriente","Cuenta Vista","Cuenta de Ahorro","RUT"].map(t=>({value:t,label:t}))}/>
          </Form.Item>
          <Form.Item label="Número de cuenta" name="numeroCuenta" rules={[{required:true}]}><Input/></Form.Item>
        </Form>
      </Modal>
    </div>
  );
}
