import React from "react";
import CreateSpecForm from "../../../components/CreateSpecForm";
import { Card, message } from 'antd';
import { createNewSpec } from "../../../services/spec.service"
import { AuthContext } from "../../../../App";

function AddSpec(props) {
    const { state } = React.useContext(AuthContext);

    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center", height: "100%" }}>
            <Card title="Login" bordered={false} style={{ width: 400, marginTop: "30px" }}>
                <CreateSpecForm
                    onFinish={(v) => {
                        createNewSpec(v, state.token)
                            .then(res => {
                                console.log(res)
                                message.success('Nuova scheda tecnica creata');
                            })
                            .catch(err => {
                                console.log(err)
                                message.error('Errore nella creazione della scheda tecnica, verifica tutti i parametri');
                            })
                    }}
                />
            </Card>
        </div>
    )
}

export default AddSpec