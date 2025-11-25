import { Spin } from "antd";
export default function LoadingOverlay({show,text="Cargando..."}){
  if(!show) return null;
  return (
    <div className="fixed inset-0 bg-black/10 backdrop-blur-[1px] z-[9999] flex items-center justify-center">
      <div className="nova-card px-6 py-4 flex items-center gap-3"><Spin/><span className="font-semibold">{text}</span></div>
    </div>
  );
}
