import { useEffect, useMemo, useState } from "react";
import PageHeader from "../../components/PageHeader";
import { getCuentas } from "../../services/cuentasService";
import { getUltimosMovimientos } from "../../services/movimientosService";
import { getBeneficiarios } from "../../services/beneficiariosService";
import { money } from "../../lib/helpers";
import { Row, Col, Card, Skeleton, Button, Avatar, Tag } from "antd";
import AccountCard from "../../components/AccountCard";
import TransactionTable from "../../components/TransactionTable";
import CalendarCard from "../../components/CalendarCard";
import { ChartBalance, ChartGastos, ChartIngresosEgresos } from "../../components/Charts";
import { getReporte } from "../../services/reportesService";
import { useNavigate } from "react-router-dom";

export default function Dashboard(){
  const nav=useNavigate();
  const [cuentas,setCuentas]=useState([]);
  const [movs,setMovs]=useState([]);
  const [benef,setBenef]=useState([]);
  const [rep,setRep]=useState(null);
  const [loading,setLoading]=useState(true);

  useEffect(()=>{
    (async()=>{
      setLoading(true);
      const cs=await getCuentas(); setCuentas(cs);
      if(cs[0]) setMovs(await getUltimosMovimientos(cs[0].id));
      setBenef((await getBeneficiarios()).filter(b=>b.favorito).slice(0,5));
      setRep(await getReporte());
      setLoading(false);
    })();
  },[]);

  const saldoTotal=useMemo(()=>cuentas.reduce((a,c)=>a+Number(c.saldo||0),0),[cuentas]);

  return (
    <div className="space-y-4">
      <PageHeader title="Resumen general" subtitle="Tu panorama financiero al día"
        right={<Button type="primary" className="bg-nova-primary" onClick={()=>nav("/app/transferencias")}>Nueva transferencia</Button>}
      />

      <Row gutter={[12,12]}>
        {[["Saldo total", money(saldoTotal)], ["Cuentas activas", cuentas.length], ["Movimientos recientes", movs.length]].map(([t,v])=>(
          <Col xs={24} md={8} key={t}>
            <Card className="nova-card">{loading?<Skeleton active paragraph={{rows:1}}/>:(<>
              <div className="text-xs nova-muted">{t}</div><div className="nova-kpi mt-1">{v}</div></>)}
            </Card>
          </Col>
        ))}
      </Row>


      <Row gutter={[12,12]}>
        <Col xs={24} md={12}>
          <Card className="nova-card bg-white">
            <div className="flex items-center justify-between gap-3">
              <div>
                <div className="font-extrabold text-lg">Simula tu crédito</div>
                <div className="text-sm text-slate-600">Conoce tu cuota en segundos.</div>
              </div>
              <Button type="primary" className="bg-nova-primary" onClick={()=>nav("/app/creditos")}>Simula aquí</Button>
            </div>
          </Card>
        </Col>
        <Col xs={24} md={12}>
          <Card className="nova-card bg-white">
            <div className="flex items-center justify-between gap-3">
              <div>
                <div className="font-extrabold text-lg">Beneficios para ti</div>
                <div className="text-sm text-slate-600">Promos y descuentos Banco Nova.</div>
              </div>
              <Button onClick={()=>nav("/productos")}>Conoce más</Button>
            </div>
          </Card>
        </Col>
      </Row>

      <Row gutter={[12,12]}>
        <Col xs={24} lg={16}>{rep?<ChartBalance meses={rep.meses} saldo={rep.saldo}/>:<Card className="nova-card"><Skeleton active/></Card>}</Col>
        <Col xs={24} lg={8}><CalendarCard/></Col>
      </Row>

      <Row gutter={[12,12]}>
        <Col xs={24} lg={12}>{rep?<ChartGastos labels={rep.gastosCategorias.labels} data={rep.gastosCategorias.data}/>:<Card className="nova-card"><Skeleton active/></Card>}</Col>
        <Col xs={24} lg={12}>{rep?<ChartIngresosEgresos {...rep.ingresosVsEgresos}/>:<Card className="nova-card"><Skeleton active/></Card>}</Col>
      </Row>

      <Row gutter={[12,12]}>
        <Col xs={24} lg={16}>
          <Card className="nova-card" title="Tus productos">
            <Row gutter={[12,12]}>
              {loading && !cuentas.length ? [1,2,3].map(i=>(
                <Col xs={24} md={12} lg={8} key={i}><Skeleton active/></Col>
              )) : cuentas.map(c=>(
                <Col xs={24} md={12} lg={8} key={c.id}>
                  <AccountCard cuenta={c} onMovimientos={()=>nav(`/app/movimientos?cuentaId=${c.id}`)} onTransferir={()=>nav("/app/transferencias")} />
                </Col>
              ))}
            </Row>
          </Card>
        </Col>
        <Col xs={24} lg={8}>
          <Card className="nova-card" title="Contactos favoritos">
            {!benef.length && <div className="text-sm text-slate-500">Aún no tienes favoritos.</div>}
            <div className="space-y-3">
              {benef.map(b=>(
                <div key={b.id} className="flex items-center justify-between">
                  <div className="flex items-center gap-2">
                    <Avatar style={{background:"#1AAFD0"}}>{b.alias?.[0]||b.nombre?.[0]}</Avatar>
                    <div>
                      <div className="text-sm font-semibold">{b.alias || b.nombre}</div>
                      <div className="text-xs text-slate-500">{b.banco} · {b.numeroCuenta}</div>
                    </div>
                  </div>
                  <Button size="small" onClick={()=>nav("/app/transferencias")}>Transferir</Button>
                </div>
              ))}
            </div>
          </Card>
        </Col>
      </Row>

      <Card className="nova-card" title="Últimos movimientos">
        <TransactionTable data={movs} loading={loading}/>
      </Card>
    </div>
  );
}
