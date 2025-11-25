import { create } from "zustand";

const useAuthStore = create((set)=>({
  token: localStorage.getItem("token") || null,
  user: (()=>{ const r=localStorage.getItem("user"); return r?JSON.parse(r):null; })(),
  setSession: ({token,user})=>{
    localStorage.setItem("token", token);
    localStorage.setItem("user", JSON.stringify(user));
    set({token,user});
  },
  clearSession: ()=>{
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    set({token:null,user:null});
  }
}));
export default useAuthStore;
