import { Layout } from "antd";
import { Link } from "react-router-dom";
const {Footer}=Layout;
export default function FooterPublic(){
  return (
    <Footer className="bg-nova-ink text-white mt-10">
      <div className="nova-section grid grid-cols-1 md:grid-cols-4 gap-6 py-6">
        <div className="md:col-span-2">
          <div className="font-extrabold text-lg">Banco Nova</div>
          <p className="text-sm text-white/80 mt-2">Proyecto ficticio para portafolio. No ofrece servicios bancarios reales.</p>
        </div>
        <div>
          <div className="font-bold mb-2">Información</div>
          <ul className="text-sm space-y-1 text-white/80">
            <li><Link to="/corporativo" className="hover:underline">Corporativo</Link></li>
            <li><Link to="/productos" className="hover:underline">Productos y servicios</Link></li>
            <li><Link to="/legal" className="hover:underline">Responsabilidades</Link></li>
          </ul>
        </div>
        <div>
          <div className="font-bold mb-2">Soporte</div>
          <ul className="text-sm space-y-1 text-white/80">
            <li><Link to="/ayuda" className="hover:underline">Centro de ayuda</Link></li>
            <li><Link to="/faq" className="hover:underline">FAQ</Link></li>
            <li><Link to="/contacto" className="hover:underline">Contacto</Link></li>
          </ul>
        </div>
      </div>
      <div className="border-t border-white/10 pt-4 text-center text-xs text-white/60">© 2025 Banco Nova · Frontend React + Ant Design</div>
    </Footer>
  );
}
