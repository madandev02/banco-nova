import axios from "axios";
const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080/api",
  timeout: 10000
});
api.interceptors.request.use((config)=>{
  const t=localStorage.getItem("token");
  if(t) config.headers.Authorization=`Bearer ${t}`;
  return config;
});
api.interceptors.response.use(r=>r, err=>{
  const msg=err?.response?.data?.message || err.message || "Error de red";
  return Promise.reject(new Error(msg));
});
export default api;
