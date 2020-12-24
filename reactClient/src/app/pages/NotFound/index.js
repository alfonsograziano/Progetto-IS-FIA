import React from "react";
import { Result, Button } from 'antd';
import { useHistory } from "react-router-dom";

function NotFound(props) {
    const history = useHistory();

    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", height: "100%" }}>
            <Result
                status="404"
                title="404"
                subTitle="La pagina che hai cercato non esiste"
                extra={<Button type="primary" onClick={() => {
                    history.push("/home")
                }}>Torna alla homepage</Button>}
            />,
        </div>
    )
}

export default NotFound