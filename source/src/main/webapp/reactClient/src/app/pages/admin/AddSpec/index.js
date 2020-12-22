import React  from "react";
import CreateSpecForm from "../../../components/CreateSpecForm";
import { Card } from 'antd';
import { createNewSpec } from "../../../services/spec.service"

function index(props) {
    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", height: "100%" }}>
            <Card title="Login" bordered={false} style={{ width: 400, marginTop: "30px" }}>
                <CreateSpecForm
                    onFinish={(v) => {
                        createNewSpec(v)
                            .then(res => {
                                console.log(res)
                            })
                            .catch(err => {
                                console.log(err)
                            })
                    }}
                />
            </Card>
        </div>
    )
}

export default index