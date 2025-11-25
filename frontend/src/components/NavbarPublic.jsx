import { Layout, Button } from "antd";
import { Link, NavLink } from "react-router-dom";
import Logo from "./Logo.jsx";
const {Header}=Layout;

export default function NavbarPublic(){
  const cls=({isActive})=>"text-sm font-semibold "+(isActive?"text-nova-secondary":"text-nova-ink hover:text-nova-secondary");
  return (
    <Header className="bg-white shadow-sm">
      <div className="nova-section h-full flex items-center justify-between">
        <Link to="/"><Logo/></Link>
        <div className="hidden md:flex items-center gap-5">
          <NavLink to="/corporativo" className={cls}>Sobre Banco Nova</NavLink>
          <NavLink to="/productos" className={cls}>Productos</NavLink>
          <NavLink to="/ayuda" className={cls}>Ayuda</NavLink>
          <NavLink to="/contacto" className={cls}>Contacto</NavLink>
        </div>
        <div className="flex gap-2">
          <Link to="/register"><Button>Hazte cliente</Button></Link>
          <Link to="/login"><Button type="primary" className="bg-nova-primary">Banca en Línea</Button></Link>
        </div>
      </div>
    </Header>
  );
}
