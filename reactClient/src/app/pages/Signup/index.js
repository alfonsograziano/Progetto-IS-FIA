import React from "react";
import SignUpForm from "../../components/SignUpForm";
import { Card } from 'antd';
import { Link } from "react-router-dom";
import { signUp } from "../../services/user.service"
import { message } from 'antd';

function Signup(props) {
    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", height: "100%" }}>
            <Card title="SignUp" bordered={false} style={{ width: 300 }}>
                <SignUpForm
                    onFinish={(data) => {
                        console.log(data)
                        signUp(data)
                            .then(res => {
                                message.success('Registration completed successfully');
                                console.log(res)
                            })
                            .catch(err => {
                                console.log(err)
                                message.error(err.response.data.message);
                            })
                    }}
                    onFinishFailed={() => { }}
                />
            </Card>
            <p>Do you have an account? <Link to="/login">Log in</Link></p>


        </div>
    )
}

export default Signup