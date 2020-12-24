import { useState, useEffect } from "react"
import { Card } from "antd";
import React from "react";
import DoraForm from "../../components/DoraForm";
import { Breadcrumb } from 'antd';
import { HomeOutlined } from '@ant-design/icons';
import { searchWithDoraIa } from "../../services/dora.service"
import SpecCard from "../../components/SpecCard";
import Row from "../../components/Row";
import { AuthContext } from "../../../App";
import { message } from 'antd';
import { Spin } from 'antd';

function DoraIA(props) {

    const [results, setResults] = useState([])
    const [loading, setLoading] = useState(false)
    const { state } = React.useContext(AuthContext);

    //TODO: Dora non filtra correttamente in base al prezzo, fixa questa cosa

    return (
        <div>
            <Breadcrumb>
                <Breadcrumb.Item href="/home">
                    <HomeOutlined />
                </Breadcrumb.Item>
                <Breadcrumb.Item href="/dora">
                    <span>DoraIA</span>
                </Breadcrumb.Item>
            </Breadcrumb>
            <Row style={{ flexWrap: "wrap-reverse" }}>
                <Spin spinning={loading} tip="Dora l'esploratrice sta cercando..."
                    size="large">
                    <Card title="DoraIA" style={{ margin: "20px", width: "300px" }} >
                        <DoraForm
                            onSearch={(values) => {
                                if (state && state.token) {
                                    console.log(values)
                                    setLoading(true)
                                    message.warning('Aspetta qualche secondo');
                                    searchWithDoraIa(values, state.token)
                                        .then(res => {
                                            setLoading(false)
                                            console.log(res)
                                            setResults(res)
                                        })
                                        .catch(err => {
                                            console.log(err)
                                            setLoading(false)
                                            message.error('Impossibile caricare i dati da DoraIA');
                                        })
                                } else {
                                    message.error('Devi registrarti per poter usare DoraIA');
                                }

                            }} />
                    </Card>
                </Spin>

                {
                    results.length > 0 &&
                    <div>
                        <h3>Risultati di ricerca...</h3>
                        <Row style={{ flexWrap: "wrap" }}>
                            {
                                results.map((item, index) =>
                                    <SpecCard
                                        key={index}
                                        imageUrl={item.image}
                                        title={item.name + " - " + item.price}
                                        description={"SO: " + item.so + "  Memoria: " + item.memory + "  RAM: " + item.ram}
                                        id={item.id}
                                    />
                                )
                            }</Row>
                    </div>
                }

            </Row>
        </div>
    )
}

export default DoraIA