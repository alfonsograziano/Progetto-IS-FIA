import { Card } from "antd";
import Title from "antd/lib/typography/Title";
import React from "react";
import DoraForm from "../../components/DoraForm";
import SpecCard from "../../components/SpecCard";

import { Breadcrumb } from 'antd';
import { HomeOutlined, UserOutlined } from '@ant-design/icons';


function DoraResults(props) {
    return (
        <div>
            <Breadcrumb>
                <Breadcrumb.Item href="/">
                    <HomeOutlined />
                </Breadcrumb.Item>
                <Breadcrumb.Item href="/dora">
                    <span>DoraIA</span>
                </Breadcrumb.Item>
                <Breadcrumb.Item>Risultati di ricerca</Breadcrumb.Item>
            </Breadcrumb>

            <div style={{ display: "flex", flexDirection: "row", flexWrap: "wrap" }}>
                <Card title="DoraIA" style={{ margin: "20px", width: "300px" }}>
                    <DoraForm
                        onSearch={(values) => {
                            console.log(values)
                        }} />
                </Card>

                <div>
                    <Title level={2}>Risultati della ricerca...</Title>
                    <div style={{ display: "flex", flexDirection: "row", alignItems: "center", flexWrap: "wrap" }}>
                        <SpecCard
                            imageUrl="https://images-na.ssl-images-amazon.com/images/I/71YeIBbC%2BFL._AC_SL1500_.jpg"
                            title="Oneplus6"
                            description="Descrizione del mio telefono preferito <3"
                            id={123}
                        />
                        <SpecCard
                            imageUrl="https://images-na.ssl-images-amazon.com/images/I/71YeIBbC%2BFL._AC_SL1500_.jpg"
                            title="Oneplus6"
                            description="Descrizione del mio telefono preferito <3"
                        />
                        <SpecCard
                            imageUrl="https://images-na.ssl-images-amazon.com/images/I/71YeIBbC%2BFL._AC_SL1500_.jpg"
                            title="Oneplus6"
                            description="Descrizione del mio telefono preferito <3"
                        />
                    </div>
                </div>

            </div>
        </div>

    )
}

export default DoraResults