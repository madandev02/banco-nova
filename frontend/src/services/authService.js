import api from "./api";

// mock users
const KEY="nova_users_mock";
const loadUsers=()=> JSON.parse(localStorage.getItem(KEY)||"[]");
const saveUsers=(u)=> localStorage.setItem(KEY, JSON.stringify(u));

export async function login(usuario, password){
  try{
    const {data}=await api.post("/auth/login",{usuario,password});
    if(data?.ok) return {ok:true, token:data.data.token, user:data.data.user};
    return {ok:false, message:data?.message||"Credenciales inválidas"};
  }catch(e){
    const users=loadUsers();
    const found=users.find(u=>u.usuario===usuario && u.password===password);
    if(found){
      return {ok:true, token:"mock-token", user:{ nombre:found.nombre, rut:found.usuario, email:found.email }};
    }
    // allow demo
    if(usuario && password){
      return {ok:true, token:"mock-token", user:{ nombre:"Cliente Demo", rut:usuario, email:"demo@banconova.cl" }};
    }
    return {ok:false, message:e.message};
  }
}

export async function register(payload){
  try{
    const {data}=await api.post("/auth/register",payload);
    return data;
  }catch{
    const users=loadUsers();
    if(users.some(u=>u.usuario===payload.usuario)) return {ok:false, message:"Usuario ya existe"};
    users.push({...payload, id:Date.now()});
    saveUsers(users);
    return {ok:true, message:"Registro demo exitoso"};
  }
}
