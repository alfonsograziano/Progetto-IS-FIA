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
import { faCamera } from '@fortawesome/free-solid-svg-icons'

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

const formatSpecDescription = item => {
    let string = ""
    if (item.so) string += "OS: " + item.so
    if (item.memory) string += "  Memory: " + item.memory
    if (item.ram) string += "  RAM: " + item.ram
    return string
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
    const [points, setPoints] = useState([])

    const [isModalVisible, setIsModalVisible] = useState(false);

    const showModal = () => setIsModalVisible(true);

    const saveReview = data => {
        if (!state.token) {
            message.error('You need to log in to add a review');
            history.push("/login")
        } else {
            addReview({ ...data, specId }, state.token)
                .then(res => {
                    message.success('Review added ... Will be checked shortly');
                    console.log(res)
                    setIsModalVisible(false);
                })
                .catch(err => {
                    message.error(err.response.data.message)
                    console.log(err)
                })
        }
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
                history.push("/404")
                console.log(err)
            })
    }, [])

    useEffect(() => {
        if (spec) {
            console.log(JSON.parse(JSON.stringify(spec)))
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
            const tempPoints = [
                {
                    key: "Display",
                    value: String(spec.display),
                    icon: faMobileAlt
                },
                {
                    key: "Camera",
                    value: String(spec.camera),
                    icon: faCamera
                },
                {
                    key: "Performance",
                    value: String(spec.performance),
                    icon: faMicrochip
                },

            ]
            console.log(data)
            setSpecTable(data)
            setPoints(tempPoints)
        }

    }, [spec])


    return (
        <div style={{ marginTop: "20px" }}>
            <Breadcrumb>
                <Breadcrumb.Item href="/home">
                    <HomeOutlined />
                </Breadcrumb.Item>
                <Breadcrumb.Item>{spec.name}</Breadcrumb.Item>
            </Breadcrumb>
            <Row>
                <Col xs={{ span: 5, offset: 1 }} lg={{ span: 6, offset: 2 }}>

                    <div style={{ alignItems: "center", width: "100%", display: "flex", flexDirection: "column" }}>
                        <SpecCard
                            imageUrl={spec.image}
                            title={spec.name + " - " + spec.price + "€"}
                            description={formatSpecDescription(spec)}
                        />

                        <Button shape="round" style={{ backgroundColor: "#ff9900" }} icon={<AmazonOutlined />}
                            onClick={() => {
                                const amzUrl = "https://www.amazon.it/s?k="
                                window.location.href = amzUrl + spec.name
                            }}
                        >
                            Buy on Amazon
                        </Button>
                    </div>

                </Col>
                <Col xs={{ span: 11, offset: 1 }} lg={{ span: 6, offset: 2 }}>
                    <div style={{ alignItems: "center", width: "100%", display: "flex", flexDirection: "column" }}>
                        <Title>{spec.name}</Title>
                        {
                            spec && spec.reviewer &&
                            <Card title={"Scores offered by " + spec.reviewer.username}>
                                <SpecTable
                                    data={points}
                                />
                            </Card>
                        }

                        <Card title="Data sheet" style={{ marginTop: "20px" }}>
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
                                    title={review.user.username}
                                    camera={review.camera}
                                    display={review.display}
                                    performance={review.performance}
                                    battery={review.battery}
                                    description={review.text}
                                />)
                                :
                                <Empty />
                        }
                        <Button type="primary" onClick={() => showModal()}>Add review</Button>
                    </div>
                </Col>
            </Row>

            <Modal title="Add new review" visible={isModalVisible} footer={[]} onCancel={handleCancel}>
                <ReviewForm onSave={saveReview} />
            </Modal>

        </div>
    )
}

export default Spec