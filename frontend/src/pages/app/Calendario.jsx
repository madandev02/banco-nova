import PageHeader from "../../components/PageHeader";
import { Card, Calendar } from "antd";
export default function Calendario(){
  return (
    <div className="space-y-4">
      <PageHeader title="Calendario" subtitle="Organiza tus pagos y vencimientos" />
      <Card className="nova-card"><Calendar/></Card>
    </div>
  );
}
