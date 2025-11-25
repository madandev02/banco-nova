import api from "./api";
export async function getCuentas(){
  try{
    const {data}=await api.get("/cuentas");
    return data.data||[];
  }catch{
    return [
      { id:1, tipo:"CORRIENTE", numero:"12345678", moneda:"CLP", saldo:980000 },
      { id:2, tipo:"VISTA", numero:"99887766", moneda:"CLP", saldo:270000 },
      { id:3, tipo:"AHORRO", numero:"44556677", moneda:"CLP", saldo:1250000 }
    ];
  }
}
