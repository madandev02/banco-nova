import api from "./api";
export async function postTransferencia(payload){
  try{
    const {data}=await api.post("/transferencias",payload);
    return data;
  }catch{
    return {ok:true, message:"Transferencia demo realizada", data:payload};
  }
}
