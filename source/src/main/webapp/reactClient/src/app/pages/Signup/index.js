import React from "react";
import SignUpForm from "../../components/SignUpForm";
import { Card } from 'antd';
import { Link } from "react-router-dom";

function Signup(props) {
    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", height: "100%" }}>
            <Card title="SignUp" bordered={false} style={{ width: 300 }}>
                <SignUpForm
                    onFinish={(data) => {
                        console.log(data)
                    }}
                    onFinishFailed={() => { }}
                />
            </Card>
            <p>Hai un account? <Link to="/login">Accedi</Link></p>


        </div>
    )
}

export default Signup