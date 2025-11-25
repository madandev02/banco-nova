import { Layout, Menu, Avatar, Dropdown, Badge } from "antd";
import {
  DashboardOutlined, CreditCardOutlined, HistoryOutlined, SendOutlined, BarChartOutlined, CalendarOutlined,
  UserOutlined, SettingOutlined, LogoutOutlined, TeamOutlined, WalletOutlined, BankOutlined,
  DollarOutlined, FileTextOutlined, SafetyCertificateOutlined
} from "@ant-design/icons";
import { Outlet, useLocation, useNavigate } from "react-router-dom";
import useAuthStore from "../store/useAuthStore";
import Logo from "../components/Logo.jsx";
const {Header,Sider,Content}=Layout;

const items=[
  { key:"/app/dashboard", icon:<DashboardOutlined/>, label:"Resumen" },
  { key:"/app/cuentas", icon:<CreditCardOutlined/>, label:"Cuentas" },
  { key:"/app/movimientos", icon:<HistoryOutlined/>, label:"Movimientos" },
  { key:"/app/transferencias", icon:<SendOutlined/>, label:"Transferencias" },
  { key:"/app/beneficiarios", icon:<TeamOutlined/>, label:"Destinatarios" },
  { key:"/app/tarjetas", icon:<WalletOutlined/>, label:"Tarjetas" },
  { key:"/app/pagos", icon:<DollarOutlined/>, label:"Pagos y servicios" },
  { key:"/app/creditos", icon:<BankOutlined/>, label:"Créditos" },
  { key:"/app/inversiones", icon:<BarChartOutlined/>, label:"Inversiones" },
  { key:"/app/documentos", icon:<FileTextOutlined/>, label:"Documentos" },
  { key:"/app/seguridad", icon:<SafetyCertificateOutlined/>, label:"Seguridad" },
  { key:"/app/calendario", icon:<CalendarOutlined/>, label:"Calendario" },
  { key:"/app/perfil", icon:<UserOutlined/>, label:"Perfil" },
  { key:"/app/configuracion", icon:<SettingOutlined/>, label:"Configuración" },
];

export default function DashboardLayout(){
  const nav=useNavigate();
  const loc=useLocation();
  const {user,clearSession}=useAuthStore();

  const dropdownItems=[
    {key:"perfil", label:"Mi perfil", onClick:()=>nav("/app/perfil")},
    {type:"divider"},
    {key:"logout", label:"Cerrar sesión", icon:<LogoutOutlined/>, danger:true, onClick:()=>{clearSession();nav("/login");}}
  ];

  return (
    <Layout style={{minHeight:"100vh"}}>
      <Sider theme="light" width={260} breakpoint="lg" collapsedWidth="0">
        <div className="h-16 flex items-center justify-center px-3 border-b"><Logo size="h-7"/></div>
        <Menu mode="inline" selectedKeys={[loc.pathname]} onClick={({key})=>nav(key)} items={items} className="border-r-0"/>
      </Sider>

      <Layout>
        <Header className="bg-white flex items-center justify-between px-4 md:px-6 shadow-sm">
          <div className="font-bold text-lg text-nova-primary">Portal Cliente</div>
          <div className="flex items-center gap-3">
            <Badge dot><SafetyCertificateOutlined className="text-slate-500"/></Badge>
            <Dropdown menu={{items:dropdownItems}} placement="bottomRight">
              <div className="flex items-center gap-2 cursor-pointer">
                <Avatar style={{backgroundColor:"#1AAFD0"}}>{(user?.nombre||"U")[0]}</Avatar>
                <div className="hidden md:block">
                  <div className="text-sm font-semibold leading-tight">{user?.nombre||"Cliente"}</div>
                  <div className="text-xs text-slate-500">{user?.rut||""}</div>
                </div>
              </div>
            </Dropdown>
          </div>
        </Header>
        <Content className="p-4 md:p-6 bg-[#F4F7FB]"><Outlet/></Content>
      </Layout>
    </Layout>
  );
}
