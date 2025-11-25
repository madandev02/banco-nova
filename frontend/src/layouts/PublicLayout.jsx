import { Layout } from "antd";
import NavbarPublic from "../components/NavbarPublic.jsx";
import FooterPublic from "../components/FooterPublic.jsx";
import { Outlet } from "react-router-dom";
const {Content}=Layout;
export default function PublicLayout(){
  return (
    <Layout style={{minHeight:"100vh",background:"var(--nova-bg)"}}>
      <NavbarPublic/>
      <Content style={{background:"var(--nova-bg)"}}><Outlet/></Content>
      <FooterPublic/>
    </Layout>
  );
}
