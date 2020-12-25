import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { AuthContext } from "../../../../App";
import { getSpecById, setScores } from "../../../services/spec.service"
import { useHistory } from "react-router-dom";
import SetScoresForm from "../../../components/SetScoresForm";
import { message } from 'antd';
import SpecList from "../SpecList";


function useQuery() {
    return new URLSearchParams(useLocation().search);
}

function SetScores(props) {
    let query = useQuery();
    const specId = query.get("id")
    const { state } = React.useContext(AuthContext);
    const [spec, setSpec] = useState({
        name: "",
    })
    const history = useHistory();

    useEffect(() => {
        getSpecById(specId)
            .then(res => {
                console.log(res)
                setSpec(res)
            })
            .catch(err => {
                history.push("/404")
                console.log(err)
            })
    }, [])

    return (
        <div style={{ marginTop: "20px", display: "flex", flexDirection: "column", alignItems: "center" }}>
            <h2>{spec.name}</h2>
            <div>
                {
                    spec && spec.id &&
                    <SetScoresForm
                        initialValues={spec}
                        onFinish={data => {
                            console.log(data)
                            setScores({ ...data, specId }, state.token)
                                .then(res => {
                                    message.success("Punteggi aggiornati correttamente")
                                    history.push("/admin/speclist")
                                })
                                .catch(err => {
                                    message.error("Error..")
                                })
                        }}
                    />
                }

            </div>
        </div>
    )
}

export default SetScores