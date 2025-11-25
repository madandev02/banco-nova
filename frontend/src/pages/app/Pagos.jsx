import PageHeader from "../../components/PageHeader";
import { Card, Form, Input, Select, Button, InputNumber, message, Row, Col, Tag } from "antd";
import { useState } from "react";

const proveedores = [
  {
    categoria: "Electricidad",
    items: [
      { value: "Enel", label: "Enel Distribución", logo: "EN" },
      { value: "Chilquinta", label: "Chilquinta Energía", logo: "CH" },
      { value: "CGE", label: "CGE", logo: "CG" }
    ]
  },
  {
    categoria: "Agua",
    items: [
      { value: "Aguas Andinas", label: "Aguas Andinas", logo: "AA" },
      { value: "Essbio", label: "Essbio", logo: "ES" },
      { value: "Esval", label: "Esval", logo: "EV" }
    ]
  },
  {
    categoria: "Internet / TV",
    items: [
      { value: "Movistar Hogar", label: "Movistar Hogar", logo: "MV" },
      { value: "VTR", label: "VTR", logo: "VT" },
      { value: "Claro Hogar", label: "Claro Hogar", logo: "CL" },
      { value: "DIRECTV", label: "DIRECTV", logo: "DT" }
    ]
  },
  {
    categoria: "Telefonía móvil",
    items: [
      { value: "Entel", label: "Entel", logo: "ET" },
      { value: "WOM", label: "WOM", logo: "WM" },
      { value: "Claro", label: "Claro", logo: "CR" },
      { value: "Movistar", label: "Movistar", logo: "MS" }
    ]
  },
  {
    categoria: "Retail / Tarjetas",
    items: [
      { value: "CMR Falabella", label: "CMR Falabella", logo: "CMR" },
      { value: "Ripley", label: "Ripley", logo: "RP" },
      { value: "Cencosud Scotiabank", label: "Cencosud Scotiabank", logo: "CS" }
    ]
  },
  {
    categoria: "Servicios públicos",
    items: [
      { value: "Registro Civil", label: "Registro Civil", logo: "RC" },
      { value: "TAG Autopistas", label: "TAG / Autopistas Urbanas", logo: "TG" },
      { value: "Contribuciones", label: "Contribuciones SII/TGR", logo: "SI" }
    ]
  }
];

const topPills = proveedores.flatMap(c => c.items).slice(0, 8);

function LogoPill({p, onClick}){
  return (
    <button onClick={()=>onClick(p)} className="w-full nova-card p-3 flex items-center gap-3 hover:shadow-md transition text-left">
      <div className="h-10 w-10 rounded-full bg-nova-primary text-white grid place-items-center text-xs font-extrabold">
        {p.logo}
      </div>
      <div className="flex-1">
        <div className="text-sm font-semibold">{p.label}</div>
        <div className="text-xs text-slate-500">Pago rápido Banco Nova</div>
      </div>
      <Tag color="blue">Chile</Tag>
    </button>
  );
}

export default function Pagos(){
  const [form] = Form.useForm();
  const [seleccion, setSeleccion] = useState(null);

  const onFinish = () => {
    message.success("Pago registrado (demo) ✅");
    form.resetFields();
    setSeleccion(null);
  };

  const setProveedor = (p) => {
    setSeleccion(p);
    form.setFieldsValue({ proveedor: p.value });
  };

  return (
    <div className="space-y-4">
      <PageHeader
        title="Pagos y servicios"
        subtitle="Paga tus cuentas y servicios chilenos en línea"
      />

      {/* Accesos rápidos */}
      <Card className="nova-card" title="Pagos rápidos">
        <Row gutter={[12,12]}>
          {topPills.map((p)=>(
            <Col xs={24} sm={12} md={8} lg={6} key={p.value}>
              <LogoPill p={p} onClick={setProveedor}/>
            </Col>
          ))}
        </Row>
      </Card>

      {/* Formulario */}
      <Card className="nova-card max-w-3xl">
        <Form
          form={form}
          layout="vertical"
          onFinish={onFinish}
          initialValues={{ moneda:"CLP" }}
        >
          <Form.Item label="Proveedor" name="proveedor" rules={[{required:true,message:"Selecciona un proveedor"}]}>
            <Select
              placeholder="Selecciona servicio"
              onChange={(v)=>{
                const p=proveedores.flatMap(x=>x.items).find(i=>i.value===v);
                setSeleccion(p||null);
              }}
              options={proveedores.map((cat)=>({
                label: cat.categoria,
                options: cat.items
              }))}
            />
          </Form.Item>

          {seleccion && (
            <div className="mb-3 text-sm">
              <Tag color="green">Seleccionado</Tag> {seleccion.label}
            </div>
          )}

          <div className="grid md:grid-cols-2 gap-3">
            <Form.Item label="Nº Cliente / Identificador" name="identificador" rules={[{required:true,message:"Campo requerido"}]}>
              <Input placeholder="Ej: N° cliente, RUT, ID factura"/>
            </Form.Item>
            <Form.Item label="Correo para comprobante" name="correo" rules={[{required:true,type:"email"}]}>
              <Input placeholder="comprobante@correo.cl"/>
            </Form.Item>
          </div>

          <div className="grid md:grid-cols-2 gap-3">
            <Form.Item label="Monto a pagar" name="monto" rules={[{required:true}]}>
              <InputNumber className="w-full" min={1000} step={500} addonAfter="CLP"/>
            </Form.Item>
            <Form.Item label="Descripción (opcional)" name="descripcion">
              <Input placeholder="Ej: boleta Noviembre"/>
            </Form.Item>
          </div>

          <Button type="primary" htmlType="submit" className="bg-nova-primary" block>
            Confirmar pago
          </Button>
        </Form>
      </Card>
    </div>
  );
}
