import React from "react";
import CreateSpecForm from "../../../components/CreateSpecForm";
import { Card, message } from 'antd';
import { createNewSpec } from "../../../services/spec.service"
import { AuthContext } from "../../../../App";
import { useHistory } from "react-router-dom";

function AddSpec(props) {
    const { state } = React.useContext(AuthContext);
    const history = useHistory();

    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", height: "100%" }}>
            <Card title="Login" bordered={false} style={{ width: 400, marginTop: "30px" }}>
                <CreateSpecForm
                    onFinish={(v) => {
                        createNewSpec(v, state.token)
                            .then(res => {
                                console.log(res)
                                message.success('New data sheet created');
                                history.push("/admin/speclist")

                            })
                            .catch(err => {
                                console.log(err)
                                message.error(err.response.data.message)
                            })
                    }}
                />
            </Card>
        </div>
    )
}

export default AddSpec