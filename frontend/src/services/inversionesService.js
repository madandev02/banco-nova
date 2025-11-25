import api from "./api";
export async function getInversiones(){
  try{
    const {data}=await api.get("/inversiones");
    return data.data||[];
  }catch{
    return [
      {id:1, tipo:"Depósito a Plazo", monto:500000, rentabilidadAnual:6.2, vencimiento:"2026-02-01"},
      {id:2, tipo:"Fondo Conservador", monto:300000, rentabilidadAnual:4.1, vencimiento:"Sin vencimiento"}
    ];
  }
}
