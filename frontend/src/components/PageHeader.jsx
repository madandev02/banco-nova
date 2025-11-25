export default function PageHeader({title,subtitle,right}){
  return (
    <div className="flex flex-wrap items-center justify-between gap-2">
      <div>
        <h1 className="text-2xl md:text-3xl font-extrabold">{title}</h1>
        {subtitle && <p className="nova-muted text-sm mt-1">{subtitle}</p>}
      </div>
      {right}
    </div>
  );
}
