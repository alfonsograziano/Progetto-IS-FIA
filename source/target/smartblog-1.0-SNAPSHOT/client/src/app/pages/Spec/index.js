import React from "react";
import {
    useLocation
} from "react-router-dom";
import { Button, Card } from 'antd';
import SpecTable from "../../components/SpecTable";
import ReviewCard from "../../components/ReviewCard";

import SpecCard from "../../components/SpecCard";
import { Row, Col } from 'antd';

import { Typography } from 'antd';

import { AmazonOutlined } from '@ant-design/icons';


import { faMemory } from '@fortawesome/free-solid-svg-icons'
import { faSdCard } from '@fortawesome/free-solid-svg-icons'
import { faMobileAlt } from '@fortawesome/free-solid-svg-icons'
import { faGamepad } from '@fortawesome/free-solid-svg-icons'
import { faMicrochip } from '@fortawesome/free-solid-svg-icons'
import { faAndroid } from '@fortawesome/free-brands-svg-icons'

import { Breadcrumb } from 'antd';
import { HomeOutlined, UserOutlined } from '@ant-design/icons';


const { Title } = Typography;
function useQuery() {
    return new URLSearchParams(useLocation().search);
}

function Spec(props) {

    let query = useQuery();
    const specId = query.get("id")

    const data = [
        {
            key: "SO",
            value: "Android 10 ROG Gaming UI",
            icon: faAndroid
        },
        {
            key: "cpu",
            value: "1x 3.1 GHz Kryo 585 Prime + 3x 2.42 GHz Kryo 585 + 4x 1.8 GHz Kryo 585",
            icon: faMicrochip
        },
        {
            key: "chipset",
            value: "Snapdragon 865 Plus Qualcomm SM8250",
            icon: faMemory
        },
        {
            key: "gpu",
            value: "Adreno 650",
            icon: faGamepad
        },
        {
            key: "ram",
            value: "16 GB",
            icon: faMemory
        },
        {
            key: "storage",
            value: "512 GB",
            icon: faSdCard
        },
        {
            key: "screenSize",
            value: "6.59",
            icon: faMobileAlt
        }
    ]



    return (
        <div style={{ marginTop: "20px" }}>
            <Breadcrumb>
                <Breadcrumb.Item href="/">
                    <HomeOutlined />
                </Breadcrumb.Item>
                <Breadcrumb.Item href="">
                    <span>Schede tecniche</span>
                </Breadcrumb.Item>
                <Breadcrumb.Item>Oneplus 6</Breadcrumb.Item>
            </Breadcrumb>
            <Row>
                <Col xs={{ span: 5, offset: 1 }} lg={{ span: 6, offset: 2 }}>

                    <div style={{ alignItems: "center", width: "100%", display: "flex", flexDirection: "column" }}>
                        <SpecCard
                            imageUrl="https://images-na.ssl-images-amazon.com/images/I/71YeIBbC%2BFL._AC_SL1500_.jpg"
                            title="Oneplus6"
                            description="Descrizione del mio telefono preferito <3"
                        />

                        <Button shape="round" style={{ backgroundColor: "#ff9900" }} icon={<AmazonOutlined />}  >
                            Acquista su amazon
                        </Button>
                    </div>

                </Col>
                <Col xs={{ span: 11, offset: 1 }} lg={{ span: 6, offset: 2 }}>
                    <div style={{ alignItems: "center", width: "100%", display: "flex", flexDirection: "column" }}>
                        <Title>Oneplus 6</Title>
                        <Card title="Scheda tecnica">
                            <SpecTable
                                data={data}
                            />
                        </Card>

                    </div>
                </Col>
                <Col xs={{ span: 5, offset: 1 }} lg={{ span: 6, offset: 2 }}>

                    <div style={{ alignItems: "center", width: "100%", display: "flex", flexDirection: "column" }}>
                        <ReviewCard
                            title="Username"
                            subtitle="12/02/2020"
                            camera={5}
                            display={3}
                            performance={4}
                            battery={5}
                            description="Descrizione della recensione, tutto molto bello"
                        />


                        <ReviewCard
                            title="Username"
                            subtitle="12/02/2020"
                            camera={5}
                            display={3}
                            performance={4}
                            battery={5}
                            description="Descrizione della recensione, tutto molto bello"
                        />

                        <Button type="primary" style={{ marginBottom: "20px" }}>Visualizza tutte le recensioni</Button>
                        <Button type="primary">Aggiungi recensione</Button>
                    </div>


                </Col>
            </Row>

        </div>
    )
}

export default Spec