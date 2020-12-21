import { Card } from "antd";
import React from "react";
import DoraForm from "../../components/DoraForm";
import { Breadcrumb } from 'antd';
import { HomeOutlined, UserOutlined } from '@ant-design/icons';
import { searchWithDoraIa } from "../../services/dora.service"

function DoraIA(props) {


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
            <div style={{ marginTop: "20px", display: "flex", flexDirection: "column", alignItems: "center" }}>
                <Card title="DoraIA" style={{ margin: "20px", width: "300px" }}>
                    <DoraForm
                        onSearch={(values) => {
                            searchWithDoraIa(values)
                                .then(res => {
                                    console.log(res)
                                })
                        }} />
                </Card>
            </div>
        </div>
    )
}

export default DoraIA