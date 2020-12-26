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
                <Title level={5} style={{ margin: "0px" }}>Overall rating</Title>
                <Rate onChange={(v) => setReview({ ...review, totalScore: v })} value={review.totalScore} allowClear={false} />
            </Row>

            <Row style={{ justifyContent: "space-between" }}>
                <Title level={5} style={{ margin: "0px" }}>Camera</Title>
                <Rate onChange={(v) => setReview({ ...review, camera: v })} value={review.camera} allowClear={false} />
            </Row>

            <Row style={{ justifyContent: "space-between" }}>
                <Title level={5} style={{ margin: "0px" }}>Display </Title>
                <Rate onChange={(v) => setReview({ ...review, display: v })} value={review.display} allowClear={false} />
            </Row>

            <Row style={{ justifyContent: "space-between" }}>
                <Title level={5} style={{ margin: "0px" }}>Performance</Title>
                <Rate onChange={(v) => setReview({ ...review, performance: v })} value={review.performance} allowClear={false} />
            </Row>

            <Row style={{ justifyContent: "space-between" }}>
                <Title level={5} style={{ margin: "0px" }}>Battery</Title>
                <Rate onChange={(v) => setReview({ ...review, battery: v })} value={review.battery} allowClear={false} />
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
                    Add
            </Button>
            </div>


        </div>
    )
}

export default ReviewForm