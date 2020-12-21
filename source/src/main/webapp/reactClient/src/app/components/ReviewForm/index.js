import React, { useState } from 'react';
import { Button } from 'antd';
import Row from '../Row';
import { Typography } from 'antd';
import { Rate } from 'antd';
import { Input } from 'antd';

const { TextArea } = Input;
const { Title } = Typography;
function ReviewForm({
    onSave = () => { }
}) {

    const [review, setReview] = useState({
        totalScore: 5,
        performance: 5,
        display: 5,
        camera: 5,
        battery: 5,
        text: "",
    })


    return (
        <div>
            <Row style={{ justifyContent: "space-between" }}>
                <Title level={5} style={{ margin: "0px" }}>Valutazione complessiva: </Title>
                <Rate onChange={(v) => setReview({ ...review, totalScore: v })} value={review.totalScore} />
            </Row>

            <Row style={{ justifyContent: "space-between" }}>
                <Title level={5} style={{ margin: "0px" }}>Fotocamera</Title>
                <Rate onChange={(v) => setReview({ ...review, camera: v })} value={review.camera} />
            </Row>

            <Row style={{ justifyContent: "space-between" }}>
                <Title level={5} style={{ margin: "0px" }}>Display </Title>
                <Rate onChange={(v) => setReview({ ...review, display: v })} value={review.display} />
            </Row>

            <Row style={{ justifyContent: "space-between" }}>
                <Title level={5} style={{ margin: "0px" }}>Prestazioni</Title>
                <Rate onChange={(v) => setReview({ ...review, performance: v })} value={review.performance} />
            </Row>

            <Row style={{ justifyContent: "space-between" }}>
                <Title level={5} style={{ margin: "0px" }}>Batteria</Title>
                <Rate onChange={(v) => setReview({ ...review, battery: v })} value={review.battery} />
            </Row>

            <TextArea
                rows={4}
                placeholder="Descrizione"
                value={review.text}
                onChange={e => setReview({ ...review, text: e.target.value })}
            />

            <div style={{ width: "100%", justifyContent: "flex-end", marginTop: "20px", display: "flex" }}>
                <Button type="primary"
                    onClick={() => {
                        onSave(review)
                    }}>
                    Aggiungi
            </Button>
            </div>


        </div>
    )
}

export default ReviewForm