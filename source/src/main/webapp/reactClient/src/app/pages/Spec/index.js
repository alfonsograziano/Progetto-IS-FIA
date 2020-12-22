import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import SpecTable from "../../components/SpecTable";
import ReviewCard from "../../components/ReviewCard";
import SpecCard from "../../components/SpecCard";
import { Empty, message } from 'antd';

import { AmazonOutlined } from '@ant-design/icons';
import { Row, Col, Modal, Typography, Button, Card, Breadcrumb } from 'antd';
import { faMemory } from '@fortawesome/free-solid-svg-icons'
import { faSdCard } from '@fortawesome/free-solid-svg-icons'
import { faMobileAlt } from '@fortawesome/free-solid-svg-icons'
import { faGamepad } from '@fortawesome/free-solid-svg-icons'
import { faMicrochip } from '@fortawesome/free-solid-svg-icons'
import { faAndroid } from '@fortawesome/free-brands-svg-icons'

import { HomeOutlined } from '@ant-design/icons';
import { useHistory } from "react-router-dom";

import { getSpecById } from "../../services/spec.service"
import ReviewForm from "../../components/ReviewForm";
import { addReview } from "../../services/review.service"
import { AuthContext } from "../../../App";


const { Title } = Typography;
function useQuery() {
    return new URLSearchParams(useLocation().search);
}

function Spec(props) {

    let query = useQuery();
    const specId = query.get("id")
    const { state } = React.useContext(AuthContext);
    const history = useHistory();

    const [spec, setSpec] = useState({
        name: "",
        image: ""
    })
    const [specTable, setSpecTable] = useState([])

    const [isModalVisible, setIsModalVisible] = useState(false);

    const showModal = () => setIsModalVisible(true);

    const saveReview = data => {
        addReview({
            ...data,
            specId,
            userId: "1"
        }, state.token)
            .then(res => {
                message.success('Recensione aggiunta... Verrà controllata a breve');
                console.log(res)
            })
            .catch(err => {
                message.error('Impossibile aggiungere la recensione');
                console.log(err)
            })
        console.log(data)
        setIsModalVisible(false);
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };


    useEffect(() => {
        getSpecById(specId)
            .then(res => {
                console.log(res)
                setSpec(res)
            })
            .catch(err => {
                history.push("/home")
                console.log(err)
            })
    }, [])

    useEffect(() => {
        if (spec) {
            const data = [
                {
                    key: "SO",
                    value: spec.so,
                    icon: faAndroid
                },
                {
                    key: "cpu",
                    value: spec.cpu,
                    icon: faMicrochip
                },
                {
                    key: "chipset",
                    value: spec.chipset,
                    icon: faMemory
                },
                {
                    key: "gpu",
                    value: spec.gpu,
                    icon: faGamepad
                },
                {
                    key: "ram",
                    value: spec.ram,
                    icon: faMemory
                },
                {
                    key: "storage",
                    value: spec.memory,
                    icon: faSdCard
                },
                {
                    key: "screenSize",
                    value: spec.screenSize,
                    icon: faMobileAlt
                }
            ]
            console.log(data)
            setSpecTable(data)
        }

    }, [spec])


    return (
        <div style={{ marginTop: "20px" }}>
            <Breadcrumb>
                <Breadcrumb.Item href="/home">
                    <HomeOutlined />
                </Breadcrumb.Item>
                <Breadcrumb.Item href="">
                    <span>Schede tecniche</span>
                </Breadcrumb.Item>
                <Breadcrumb.Item>{spec.name}</Breadcrumb.Item>
            </Breadcrumb>
            <Row>
                <Col xs={{ span: 5, offset: 1 }} lg={{ span: 6, offset: 2 }}>

                    <div style={{ alignItems: "center", width: "100%", display: "flex", flexDirection: "column" }}>
                        <SpecCard
                            imageUrl={spec.image}
                            title={spec.name + " - " + spec.price}
                            description={"SO: " + spec.so + "  Memoria: " + spec.memory + "  RAM: " + spec.ram}
                        />

                        <Button shape="round" style={{ backgroundColor: "#ff9900" }} icon={<AmazonOutlined />}
                            onClick={() => {
                                const amzUrl = "https://www.amazon.it/s?k="
                                window.location.href = amzUrl + spec.name
                            }}
                        >
                            Acquista su amazon
                        </Button>
                    </div>

                </Col>
                <Col xs={{ span: 11, offset: 1 }} lg={{ span: 6, offset: 2 }}>
                    <div style={{ alignItems: "center", width: "100%", display: "flex", flexDirection: "column" }}>
                        <Title>{spec.name}</Title>
                        <Card title="Scheda tecnica">
                            <SpecTable
                                data={specTable}
                            />
                        </Card>

                    </div>
                </Col>
                <Col xs={{ span: 5, offset: 1 }} lg={{ span: 6, offset: 2 }}>

                    <div style={{ alignItems: "center", width: "100%", display: "flex", flexDirection: "column" }}>
                        {
                            spec && spec.reviews && spec.reviews.length > 0 ? spec.reviews.map(review =>
                                <ReviewCard
                                    title={review.user.email}
                                    camera={review.camera}
                                    display={review.display}
                                    performance={review.performance}
                                    battery={review.battery}
                                    description={review.text}
                                />)
                                :
                                <Empty />
                        }


                        {/* <Button type="primary" style={{ marginBottom: "20px" }}>Visualizza tutte le recensioni</Button> */}
                        <Button type="primary" onClick={() => showModal()}>Aggiungi recensione</Button>
                    </div>


                </Col>
            </Row>

            <Modal title="Aggiungi nuova recensione" visible={isModalVisible} footer={[]} onCancel={handleCancel}>
                <ReviewForm onSave={saveReview} />
            </Modal>

        </div>
    )
}

export default Spec