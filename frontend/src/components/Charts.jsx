import { Card } from "antd";
import { ResponsiveContainer, LineChart, Line, XAxis, YAxis, Tooltip, CartesianGrid, PieChart, Pie, Cell, BarChart, Bar, Legend } from "recharts";
const COLORS=["#004A77","#1AAFD0","#FFC24B","#94a3b8","#0ea5e9"];

export function ChartBalance({meses,saldo}){
  const data=meses.map((m,i)=>({mes:m,saldo:saldo[i]}));
  return (
    <Card className="nova-card" title="Evolución del saldo" bodyStyle={{height:280}}>
      <ResponsiveContainer width="100%" height="100%">
        <LineChart data={data}>
          <CartesianGrid strokeDasharray="3 3"/><XAxis dataKey="mes"/><YAxis/><Tooltip/>
          <Line type="monotone" dataKey="saldo" stroke={COLORS[0]} strokeWidth={3} dot={false}/>
        </LineChart>
      </ResponsiveContainer>
    </Card>
  );
}

export function ChartGastos({labels,data}){
  const d=labels.map((l,i)=>({name:l,value:data[i]}));
  return (
    <Card className="nova-card" title="Distribución de gastos" bodyStyle={{height:280}}>
      <ResponsiveContainer width="100%" height="100%">
        <PieChart>
          <Pie data={d} dataKey="value" nameKey="name" innerRadius={60} outerRadius={90}>
            {d.map((_,i)=><Cell key={i} fill={COLORS[i%COLORS.length]}/>)}
          </Pie>
          <Tooltip/><Legend verticalAlign="bottom" height={36}/>
        </PieChart>
      </ResponsiveContainer>
    </Card>
  );
}

export function ChartIngresosEgresos({labels,ingresos,egresos}){
  const d=labels.map((l,i)=>({mes:l,ingresos:ingresos[i],egresos:egresos[i]}));
  return (
    <Card className="nova-card" title="Ingresos vs Egresos" bodyStyle={{height:280}}>
      <ResponsiveContainer width="100%" height="100%">
        <BarChart data={d}>
          <CartesianGrid strokeDasharray="3 3"/><XAxis dataKey="mes"/><YAxis/><Tooltip/><Legend/>
          <Bar dataKey="ingresos" fill={COLORS[1]} radius={[6,6,0,0]}/>
          <Bar dataKey="egresos" fill={COLORS[2]} radius={[6,6,0,0]}/>
        </BarChart>
      </ResponsiveContainer>
    </Card>
  );
}
