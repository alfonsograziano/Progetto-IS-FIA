import React from "react";
import SignInForm from "../../components/SignInForm";
import { Card } from 'antd';
import { Link } from "react-router-dom";
import { AuthContext } from "../../../App";
import { login, getProfileInfo } from "../../services/user.service"
import { useHistory } from "react-router-dom";
import { message } from 'antd';

function Login(props) {
    const { dispatch } = React.useContext(AuthContext);
    const history = useHistory();

    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", height: "100%" }}>
            <Card title="Login" bordered={false} style={{ width: 300 }}>
                <SignInForm
                    onFinish={(data) => {
                        console.log(data)
                        login(data.email, data.password)
                            .then(res => {
                                message.success("Login fulfilled")
                                const token = res.message
                                getProfileInfo(token)
                                    .then(res => {
                                        dispatch({
                                            type: "LOGIN",
                                            payload: {
                                                user: res,
                                                token
                                            }
                                        })
                                        if(res.phoneNumber){
                                            if(res.rank){
                                                history.push("/admin/reviews") 
                                            }else{
                                                history.push("/admin/speclist")
                                            }
                                        }else{
                                            history.push("/home")
                                        }
                                    })
                            })
                            .catch(err => {
                               message.error(err.response.data.message)
                            })
                    }}
                    onFinishFailed={() => { }}
                />
            </Card>
            <p>Do not have an account?<Link to="/signup">Sign in</Link></p>

        </div>
    )
}

export default Login