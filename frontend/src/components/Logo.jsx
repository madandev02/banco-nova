import icon from "../assets/logo-icon.svg";
export default function Logo({size="h-8"}){
  return (
    <div className="flex items-center gap-2">
      <img src={icon} className={size} alt="Banco Nova"/>
      <span className="font-extrabold text-lg tracking-tight text-nova-ink">Banco Nova</span>
    </div>
  );
}
