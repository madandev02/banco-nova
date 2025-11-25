import { Card, Table } from "antd";
export default function Sucursales() {
  const data = [
    {
      key: 1,
      ciudad: "Santiago",
      direccion: "Av. Alameda 123",
      horario: "09:00 - 14:00",
    },
    {
      key: 2,
      ciudad: "Valparaíso",
      direccion: "Av. Brasil 456",
      horario: "09:00 - 14:00",
    },
  ];
  const cols = [
    { title: "Ciudad", dataIndex: "ciudad" },
    { title: "Dirección", dataIndex: "direccion" },
    { title: "Horario", dataIndex: "horario" },
  ];
  return (
    <main className="nova-section my-8 min-h-[65vh]">
      <Card className="nova-card" bodyStyle={{ padding: 20 }}>
        <h1 className="text-3xl font-extrabold mb-3">Sucursales</h1>
        <Table columns={cols} dataSource={data} pagination={false} />
      </Card>
    </main>
  );
}
