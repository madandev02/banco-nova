import api from "./api";
const KEY="nova_beneficiarios_mock";
const load=()=>JSON.parse(localStorage.getItem(KEY)||"[]");
const save=(v)=>localStorage.setItem(KEY, JSON.stringify(v));

export async function getBeneficiarios(){
  try{
    const {data}=await api.get("/beneficiarios");
    return data.data||[];
  }catch{
    const v=load();
    if(v.length) return v;
    const seed=[
      {id:1, alias:"Mamá", nombre:"Ana Narváez", rut:"11.111.111-1", banco:"Banco Nova", tipoCuenta:"Cuenta Vista", numeroCuenta:"77778888", favorito:true},
      {id:2, alias:"Amigo Juan", nombre:"Juan Pérez", rut:"22.222.222-2", banco:"Santander", tipoCuenta:"Cuenta Corriente", numeroCuenta:"12312312", favorito:false},
    ];
    save(seed); return seed;
  }
}

export async function addBeneficiario(b){
  try{
    const {data}=await api.post("/beneficiarios",b);
    return data;
  }catch{
    const v=load();
    const nb={...b, id:Date.now(), favorito:false};
    v.push(nb); save(v);
    return {ok:true, data:nb};
  }
}

export async function toggleFavorito(id){
  const v=load();
  const i=v.findIndex(x=>x.id===id);
  if(i>=0){ v[i].favorito=!v[i].favorito; save(v); }
  return v;
}
