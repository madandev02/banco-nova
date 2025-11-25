import { Table, Tag } from "antd";
import { formatDate, money } from "../lib/helpers";

export default function TransactionTable({data,loading}){
  const cols=[
    {title:"Fecha",dataIndex:"fecha",render:v=>formatDate(v)},
    {title:"Descripción",dataIndex:"descripcion"},
    {title:"Monto",dataIndex:"monto",align:"right",
      render:(v,r)=><span className={r.tipo==="ABONO"?"text-emerald-600 font-semibold":"text-rose-600 font-semibold"}>
        {r.tipo==="ABONO"?"+":"-"} {money(v)}
      </span>},
    {title:"Estado",dataIndex:"estado",align:"right",
      render:v=><Tag color={v==="OK"?"green":v==="PENDIENTE"?"gold":"red"}>{v}</Tag>}
  ];
  return <Table columns={cols} rowKey="id" dataSource={data} loading={loading} pagination={{pageSize:8}} />;
}
