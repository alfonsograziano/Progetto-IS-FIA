import React from "react";
import SignInForm from "../../components/SignInForm";
import { Card } from 'antd';
import { Link } from "react-router-dom";

function Login(props) {
    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", height: "100%" }}>
            <Card title="Login" bordered={false} style={{ width: 300 }}>
                <SignInForm
                    onFinish={(data) => {
                        console.log(data)
                    }}
                    onFinishFailed={() => { }}
                />
            </Card>
            <p>Non hai un account? <Link to="/signup">Registrati</Link></p>

        </div>
    )
}

export default Login