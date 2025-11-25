export const money=(n)=> new Intl.NumberFormat("es-CL",{style:"currency",currency:"CLP",maximumFractionDigits:0}).format(n??0);
export const formatDate=(iso)=>{ try{ return new Date(iso).toLocaleDateString("es-CL",{day:"2-digit",month:"short",year:"numeric"});}catch{return iso;} };
