import { Card, Button, Tag } from "antd";
import { CreditCardOutlined, SwapOutlined } from "@ant-design/icons";
import { money } from "../lib/helpers";
const tipoLabel=(t)=> t==="CORRIENTE"?"Cuenta Corriente":t==="VISTA"?"Cuenta Vista":"Cuenta Ahorro";

export default function AccountCard({cuenta,onMovimientos,onTransferir}){
  return (
    <Card className="nova-card" hoverable bodyStyle={{padding:16}}
      title={<div className="flex items-center gap-2"><CreditCardOutlined/><span className="font-semibold">{tipoLabel(cuenta.tipo)}</span></div>}
      extra={<Tag color="blue">{cuenta.moneda||"CLP"}</Tag>}
    >
      <div className="text-xs nova-muted">Nº {cuenta.numero}</div>
      <div className="mt-3">
        <div className="text-xs nova-muted">Saldo disponible</div>
        <div className="text-xl font-extrabold">{money(cuenta.saldo)}</div>
      </div>
      <div className="mt-4 grid grid-cols-2 gap-2">
        <Button onClick={onMovimientos} icon={<SwapOutlined/>}>Movimientos</Button>
        <Button type="primary" onClick={onTransferir}>Transferir</Button>
      </div>
    </Card>
  );
}
