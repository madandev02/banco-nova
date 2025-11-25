import api from "./api";
export async function getMovimientos(cuentaId){
  try{
    const {data}=await api.get("/movimientos",{params:{cuentaId}});
    return data.data||[];
  }catch{
    return [
      { id:1, fecha:"2025-11-22", descripcion:"Transferencia recibida - Juan Pérez", monto:50000, tipo:"ABONO", estado:"OK" },
      { id:2, fecha:"2025-11-21", descripcion:"Pago tarjeta crédito", monto:30000, tipo:"CARGO", estado:"OK" },
      { id:3, fecha:"2025-11-20", descripcion:"Compra Amazon", monto:14990, tipo:"CARGO", estado:"PENDIENTE" }
    ];
  }
}
export async function getUltimosMovimientos(cuentaId){
  const all=await getMovimientos(cuentaId);
  return all.slice(0,6);
}
