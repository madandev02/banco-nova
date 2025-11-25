import { useEffect, useState } from "react";
import PageHeader from "../../components/PageHeader";
import { Card, Table, Tag } from "antd";
import { getInversiones } from "../../services/inversionesService";
import { money, formatDate } from "../../lib/helpers";

export default function Inversiones(){
  const [data,setData]=useState([]);
  const [loading,setLoading]=useState(true);
  useEffect(()=>{(async()=>{setLoading(true);setData(await getInversiones());setLoading(false);})();},[]);
  const cols=[
    {title:"Producto", dataIndex:"tipo"},
    {title:"Monto", dataIndex:"monto", render:v=>money(v)},
    {title:"Rentab. anual", dataIndex:"rentabilidadAnual", render:v=><Tag color="blue">{v}%</Tag>},
    {title:"Vencimiento", dataIndex:"vencimiento", render:v=> v.includes("-")?formatDate(v):v},
  ];
  return (
    <div className="space-y-4">
      <PageHeader title="Inversiones" subtitle="Fondos y depósitos a plazo" />
      <Card className="nova-card">
        <Table rowKey="id" columns={cols} dataSource={data} loading={loading}/>
      </Card>
    </div>
  );
}
