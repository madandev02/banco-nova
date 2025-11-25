import { useMemo, useState } from "react";
import PageHeader from "../../components/PageHeader";
import { Card, InputNumber, Slider, Select } from "antd";
import { money } from "../../lib/helpers";

export default function Creditos(){
  const [monto,setMonto]=useState(2000000);
  const [cuotas,setCuotas]=useState(24);
  const [tasa,setTasa]=useState(1.4); // mensual en %
  const cuotaMensual = useMemo(()=>{
    const r=tasa/100;
    return (monto*r)/(1-Math.pow(1+r,-cuotas));
  },[monto,cuotas,tasa]);

  return (
    <div className="space-y-4">
      <PageHeader title="Créditos" subtitle="Simula tu crédito de consumo" />
      <Card className="nova-card max-w-3xl">
        <div className="grid md:grid-cols-2 gap-6">
          <div>
            <div className="font-semibold">Monto</div>
            <InputNumber className="w-full mt-1" value={monto} onChange={v=>setMonto(v||0)} min={100000} step={100000}/>
            <Slider min={100000} max={10000000} step={100000} value={monto} onChange={setMonto}/>
          </div>
          <div>
            <div className="font-semibold">Cuotas</div>
            <InputNumber className="w-full mt-1" value={cuotas} onChange={v=>setCuotas(v||1)} min={6} max={60}/>
            <Slider min={6} max={60} value={cuotas} onChange={setCuotas}/>
          </div>
          <div>
            <div className="font-semibold">Tasa mensual</div>
            <Select className="w-full mt-1" value={tasa} onChange={setTasa}
              options={[
                {value:1.1,label:"1,1% (Excelente)"},
                {value:1.4,label:"1,4% (Estándar)"},
                {value:1.8,label:"1,8% (Flexible)"}
              ]}
            />
          </div>
          <div className="nova-card p-4">
            <div className="text-sm nova-muted">Resultado simulación</div>
            <div className="text-2xl font-extrabold mt-1">{money(cuotaMensual)}</div>
            <div className="text-xs nova-muted">Cuota mensual estimada</div>
          </div>
        </div>
      </Card>
    </div>
  );
}
