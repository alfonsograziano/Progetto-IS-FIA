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

const formatSpecDescription = item => {
    let string = ""
    if (item.so) string += "OS: " + item.so
    if (item.memory) string += "  Memory: " + item.memory
    if (item.ram) string += "  RAM: " + item.ram
    return string
}


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
            <div style={{ marginTop: "20px", display: "flex", flexDirection: "column", alignItems: "center" }}>
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
                                            message.error('Unable to load data from DoraIA');
                                        })
                                } else {
                                    message.error("You must register in order to use DoraIA");
                                }

                            }} />
                    </Card>
                </Spin>

                {
                    results.length > 0 &&
                    <div>
                        <h3>Search Results</h3>
                        <Row style={{ flexWrap: "wrap" }}>
                            {
                                results.map((item, index) =>
                                    <SpecCard
                                        key={index}
                                        imageUrl={item.image}
                                        title={item.name + " - " + item.price+"€"}
                                        description={formatSpecDescription(item)}
                                        id={item.id}
                                        openInNewTab={true}
                                    />
                                )
                            }</Row>
                    </div>
                }

            </div>

        </div>
    )
}

export default DoraIA