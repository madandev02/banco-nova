import { useEffect, useState } from "react";
import { Card, Select } from "antd";
import PageHeader from "../../components/PageHeader";
import TransactionTable from "../../components/TransactionTable";
import { getCuentas } from "../../services/cuentasService";
import { getMovimientos } from "../../services/movimientosService";
import { useSearchParams } from "react-router-dom";

export default function Movimientos(){
  const [sp]=useSearchParams();
  const startId=sp.get("cuentaId");
  const [cuentas,setCuentas]=useState([]);
  const [cuentaId,setCuentaId]=useState(startId?Number(startId):null);
  const [movs,setMovs]=useState([]);
  const [loading,setLoading]=useState(true);

  useEffect(()=>{(async()=>{const cs=await getCuentas(); setCuentas(cs); setCuentaId(prev=>prev||cs?.[0]?.id);})();},[]);
  useEffect(()=>{(async()=>{ if(!cuentaId) return; setLoading(true); setMovs(await getMovimientos(cuentaId)); setLoading(false);})();},[cuentaId]);

  return (
    <div className="space-y-4">
      <PageHeader title="Movimientos" subtitle="Historial y estado de tus operaciones" />
      <Card className="nova-card">
        <div className="flex flex-wrap gap-2 items-center mb-3">
          <div className="text-sm font-semibold">Cuenta:</div>
          <Select value={cuentaId} onChange={setCuentaId}
            options={cuentas.map(c=>({value:c.id,label:`${c.tipo} · ${c.numero}`}))} style={{minWidth:240}} />
        </div>
        <TransactionTable data={movs} loading={loading}/>
      </Card>
    </div>
  );
}
