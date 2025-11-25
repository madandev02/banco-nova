export async function getReporte(){
  return {
    meses:["Jun","Jul","Ago","Sep","Oct","Nov"],
    saldo:[820000,860000,910000,940000,1010000,1250000],
    gastosCategorias:{ labels:["Hogar","Compras","Transporte","Comida","Otros"], data:[32,21,15,18,14] },
    ingresosVsEgresos:{ labels:["Jun","Jul","Ago","Sep","Oct","Nov"], ingresos:[420000,460000,500000,520000,600000,740000], egresos:[350000,390000,410000,430000,460000,520000] }
  };
}
