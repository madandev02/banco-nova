import { Routes, Route } from "react-router-dom";
import PublicLayout from "../layouts/PublicLayout.jsx";
import DashboardLayout from "../layouts/DashboardLayout.jsx";
import ProtectedRoute from "./ProtectedRoute.jsx";

import Home from "../pages/public/Home.jsx";
import Productos from "../pages/public/Productos.jsx";
import Ayuda from "../pages/public/Ayuda.jsx";
import Faq from "../pages/public/Faq.jsx";
import Corporativo from "../pages/public/Corporativo.jsx";
import Sucursales from "../pages/public/Sucursales.jsx";
import Contacto from "../pages/public/Contacto.jsx";
import Legal from "../pages/public/Legal.jsx";
import NotFound from "../pages/public/NotFound.jsx";

import Login from "../pages/auth/Login.jsx";
import Register from "../pages/auth/Register.jsx";

import Dashboard from "../pages/app/Dashboard.jsx";
import Cuentas from "../pages/app/Cuentas.jsx";
import Movimientos from "../pages/app/Movimientos.jsx";
import Transferencias from "../pages/app/Transferencias.jsx";
import Beneficiarios from "../pages/app/Beneficiarios.jsx";
import Tarjetas from "../pages/app/Tarjetas.jsx";
import Pagos from "../pages/app/Pagos.jsx";
import Creditos from "../pages/app/Creditos.jsx";
import Inversiones from "../pages/app/Inversiones.jsx";
import Documentos from "../pages/app/Documentos.jsx";
import Seguridad from "../pages/app/Seguridad.jsx";
import Calendario from "../pages/app/Calendario.jsx";
import Perfil from "../pages/app/Perfil.jsx";
import Configuracion from "../pages/app/Configuracion.jsx";

export default function AppRouter(){
  return (
    <Routes>
      <Route element={<PublicLayout/>}>
        <Route path="/" element={<Home/>}/>
        <Route path="/productos" element={<Productos/>}/>
        <Route path="/ayuda" element={<Ayuda/>}/>
        <Route path="/faq" element={<Faq/>}/>
        <Route path="/corporativo" element={<Corporativo/>}/>
        <Route path="/sucursales" element={<Sucursales/>}/>
        <Route path="/contacto" element={<Contacto/>}/>
        <Route path="/legal" element={<Legal/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/register" element={<Register/>}/>
      </Route>

      <Route path="/app" element={<ProtectedRoute><DashboardLayout/></ProtectedRoute>}>
        <Route index element={<Dashboard/>}/>
        <Route path="dashboard" element={<Dashboard/>}/>
        <Route path="cuentas" element={<Cuentas/>}/>
        <Route path="movimientos" element={<Movimientos/>}/>
        <Route path="transferencias" element={<Transferencias/>}/>
        <Route path="beneficiarios" element={<Beneficiarios/>}/>
        <Route path="tarjetas" element={<Tarjetas/>}/>
        <Route path="pagos" element={<Pagos/>}/>
        <Route path="creditos" element={<Creditos/>}/>
        <Route path="inversiones" element={<Inversiones/>}/>
        <Route path="documentos" element={<Documentos/>}/>
        <Route path="seguridad" element={<Seguridad/>}/>
        <Route path="calendario" element={<Calendario/>}/>
        <Route path="perfil" element={<Perfil/>}/>
        <Route path="configuracion" element={<Configuracion/>}/>
      </Route>

      <Route path="*" element={<NotFound/>}/>
    </Routes>
  );
}
