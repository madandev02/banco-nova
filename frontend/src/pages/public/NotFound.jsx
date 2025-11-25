import { Result, Button } from "antd";
import { Link } from "react-router-dom";
export default function NotFound() {
  return (
    <main className="nova-section my-10">
      <Result
        status="404"
        title="404"
        subTitle="Página no encontrada"
        extra={
          <Link to="/">
            <Button type="primary" className="bg-nova-primary">
              Volver al inicio
            </Button>
          </Link>
        }
      />
    </main>
  );
}
