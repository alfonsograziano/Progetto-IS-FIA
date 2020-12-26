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
                subTitle="The page you searched for does not exist"
                extra={<Button type="primary" onClick={() => {
                    history.push("/home")
                }}>Back to home Page</Button>}
            />,
        </div>
    )
}

export default NotFound