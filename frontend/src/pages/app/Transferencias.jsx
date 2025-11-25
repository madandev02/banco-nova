import { useEffect, useMemo, useState } from "react";
import { Card, Form, Input, Select, Button, InputNumber, message, Divider, Tag } from "antd";
import PageHeader from "../../components/PageHeader";
import { getCuentas } from "../../services/cuentasService";
import { postTransferencia } from "../../services/transferenciasService";
import { getBeneficiarios, addBeneficiario } from "../../services/beneficiariosService";
import LoadingOverlay from "../../components/LoadingOverlay";

export default function Transferencias(){
  const [cuentas,setCuentas]=useState([]);
  const [benef,setBenef]=useState([]);
  const [loading,setLoading]=useState(false);
  const [overlay,setOverlay]=useState(false);
  const [form]=Form.useForm();

  useEffect(()=>{(async()=>{
    setLoading(true);
    setCuentas(await getCuentas());
    setBenef(await getBeneficiarios());
    setLoading(false);
  })();},[]);

  const favoritos = useMemo(()=>benef.filter(b=>b.favorito),[benef]);

  const onPickBenef=(id)=>{
    const b=benef.find(x=>x.id===id);
    if(!b) return;
    form.setFieldsValue({
      rut:b.rut, nombre:b.nombre, bancoDestino:b.banco, tipoCuenta:b.tipoCuenta, numeroCuenta:b.numeroCuenta
    });
  };

  const onFinish=async(v)=>{
    setOverlay(true);
    const res=await postTransferencia(v);
    setOverlay(false);
    if(res.ok) message.success("Transferencia realizada ✅");
    else message.error(res.message||"No se pudo transferir");
  };

  return (
    <div className="space-y-4">
      <LoadingOverlay show={overlay} text="Procesando transferencia..."/>
      <PageHeader title="Nueva transferencia" subtitle="Envía dinero a terceros de forma segura"/>

      <Card className="nova-card max-w-3xl">
        {favoritos.length>0 && (
          <>
            <div className="text-sm font-semibold mb-2">Favoritos</div>
            <div className="flex flex-wrap gap-2">
              {favoritos.map(f=>(
                <Button key={f.id} size="small" onClick={()=>onPickBenef(f.id)}>
                  {f.alias} <Tag className="ml-1">{f.banco}</Tag>
                </Button>
              ))}
            </div>
            <Divider/>
          </>
        )}

        <Form form={form} layout="vertical" onFinish={onFinish} initialValues={{cuentaOrigenId:cuentas?.[0]?.id}}>
          <Form.Item name="cuentaOrigenId" label="Cuenta origen" rules={[{required:true}]}>
            <Select loading={loading} options={cuentas.map(c=>({value:c.id,label:`${c.tipo} · ${c.numero}`}))}/>
          </Form.Item>

          <Form.Item label="Destinatario registrado" name="beneficiarioId">
            <Select
              placeholder="Selecciona un destinatario (opcional)"
              options={benef.map(b=>({value:b.id,label:`${b.alias} · ${b.rut}`}))}
              onChange={onPickBenef}
              allowClear
            />
          </Form.Item>

          <Form.Item name="bancoDestino" label="Banco destino" rules={[{required:true}]}>
            <Select options={["Banco Nova","Banco Estado","BCI","Santander","Scotiabank","Falabella","Itaú"].map(b=>({value:b,label:b}))}/>
          </Form.Item>

          <div className="grid md:grid-cols-2 gap-3">
            <Form.Item name="rut" label="RUT destinatario" rules={[{required:true}]}><Input/></Form.Item>
            <Form.Item name="nombre" label="Nombre destinatario" rules={[{required:true}]}><Input/></Form.Item>
          </div>

          <div className="grid md:grid-cols-2 gap-3">
            <Form.Item name="tipoCuenta" label="Tipo de cuenta" rules={[{required:true}]}>
              <Select options={["Cuenta Corriente","Cuenta Vista","Cuenta de Ahorro","RUT"].map(t=>({value:t,label:t}))}/>
            </Form.Item>
            <Form.Item name="numeroCuenta" label="Nº cuenta destino" rules={[{required:true}]}><Input/></Form.Item>
          </div>

          <Form.Item name="monto" label="Monto (CLP)" rules={[{required:true}]}>
            <InputNumber className="w-full" min={1000} max={5000000} step={1000}/>
          </Form.Item>

          <Form.Item name="mensaje" label="Mensaje (opcional)"><Input/></Form.Item>

          <Button type="primary" htmlType="submit" className="bg-nova-primary" block>Confirmar transferencia</Button>
        </Form>
      </Card>
    </div>
  );
}
