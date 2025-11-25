import { useEffect, useState } from "react";
import { Row, Col, Skeleton } from "antd";
import PageHeader from "../../components/PageHeader";
import AccountCard from "../../components/AccountCard";
import { getCuentas } from "../../services/cuentasService";
import { useNavigate } from "react-router-dom";

export default function Cuentas(){
  const nav=useNavigate();
  const [cuentas,setCuentas]=useState([]);
  const [loading,setLoading]=useState(true);
  useEffect(()=>{(async()=>{setLoading(true);setCuentas(await getCuentas());setLoading(false);})();},[]);
  return (
    <div className="space-y-4">
      <PageHeader title="Mis cuentas" subtitle="Productos disponibles"
        right={<button onClick={()=>nav("/app/transferencias")} className="nova-btn bg-nova-primary text-white">Nueva transferencia</button>}
      />
      <Row gutter={[12,12]}>
        {loading ? [1,2,3].map(i=>(<Col xs={24} md={12} lg={8} key={i}><Skeleton active/></Col>))
          : cuentas.map(c=>(
            <Col xs={24} md={12} lg={8} key={c.id}>
              <AccountCard cuenta={c} onMovimientos={()=>nav(`/app/movimientos?cuentaId=${c.id}`)} onTransferir={()=>nav("/app/transferencias")} />
            </Col>
          ))}
      </Row>
    </div>
  );
}
