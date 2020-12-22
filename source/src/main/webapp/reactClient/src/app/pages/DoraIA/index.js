import { useState, useEffect } from "react"
import { Card } from "antd";
import React from "react";
import DoraForm from "../../components/DoraForm";
import { Breadcrumb } from 'antd';
import { HomeOutlined } from '@ant-design/icons';
import { searchWithDoraIa } from "../../services/dora.service"
import SpecCard from "../../components/SpecCard";
import Row from "../../components/Row";

function DoraIA(props) {

    const [results, setResults] = useState([])

    return (
        <div>
            <Breadcrumb>
                <Breadcrumb.Item href="/">
                    <HomeOutlined />
                </Breadcrumb.Item>
                <Breadcrumb.Item href="/dora">
                    <span>DoraIA</span>
                </Breadcrumb.Item>
            </Breadcrumb>
            <Row style={{ flexWrap: "wrap-reverse" }}>
                <Card title="DoraIA" style={{ margin: "20px", width: "300px" }}>
                    <DoraForm
                        onSearch={(values) => {
                            searchWithDoraIa(values)
                                .then(res => {
                                    console.log(res)
                                    setResults(res)
                                })
                        }} />
                </Card>
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