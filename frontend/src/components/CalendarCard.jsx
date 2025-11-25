import { Card, Calendar, Badge } from "antd";
import dayjs from "dayjs";
const events={
  [dayjs().date(5).format("YYYY-MM-DD")]:[{type:"warning",content:"Vencimiento tarjeta"}],
  [dayjs().date(12).format("YYYY-MM-DD")]:[{type:"success",content:"Depósito nómina"}],
  [dayjs().date(22).format("YYYY-MM-DD")]:[{type:"processing",content:"Pago servicios"}]
};
function cellRender(value){
  const key=value.format("YYYY-MM-DD");
  const list=events[key]||[];
  return <ul className="list-none p-0 m-0">{list.map((it,i)=>(
    <li key={i}><Badge status={it.type} text={<span className="text-xs">{it.content}</span>} /></li>
  ))}</ul>;
}
export default function CalendarCard(){
  return (
    <Card className="nova-card" bodyStyle={{padding:12}} title="Calendario">
      <Calendar fullscreen={false} cellRender={cellRender}/>
    </Card>
  );
}
