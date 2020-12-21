import React, { useState, useEffect } from "react";
import { Table, Button, Space, Modal } from 'antd';
import { getPendingReviews } from "../../../services/review.service"
import ReviewCard from "../../../components/ReviewCard";
import { changeReviewStatus } from "../../../services/review.service"

function Reviews(props) {

    const [data, setData] = useState([])
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [reviewDetail, setReviewDetail] = useState()


    useEffect(() => {
        loadPendingReviews()
    }, [])


    const loadPendingReviews = () => {
        setReviewDetail(undefined)
        getPendingReviews()
            .then(res => {
                console.log(res)
                const tempData = res.map(item => ({ ...item, title: item.spec.name + " | " + item.user.username }))
                setData(tempData)
            })
    }


    const renderReviewDetails = review => {
        setReviewDetail(review)
    }
    const handleCancel = () => {
        setReviewDetail(undefined)
    };

    useEffect(() => {
        setIsModalVisible(!!reviewDetail);
    }, [reviewDetail])

    const approveReview = review => {
        changeReviewStatus({ id: review.id, approved: true })
            .then(res => {
                console.log(res)
                loadPendingReviews()
            })
            .catch(err => {
                console.log(err)
            })
    }
    const rejectReview = review => {
        changeReviewStatus({ id: review.id, approved: false })
            .then(res => {
                console.log(res)
                loadPendingReviews()

            })
            .catch(err => {
                console.log(err)
            })
    }

    const columns = [
        {
            title: 'Recensione',
            dataIndex: 'title',
            key: 'title',
        }
    ];


    return (
        <div>
            <Table
                dataSource={data}
                columns={columns}
                onRow={(record, rowIndex) => {
                    return {
                        onClick: event => { renderReviewDetails(record) },

                    };
                }}
            />
            <Modal title="Dettagli recensione" visible={isModalVisible} onCancel={handleCancel}
                footer={[
                    <Button onClick={() => { rejectReview(reviewDetail) }}>
                        Rifiuta
                    </Button>,
                    <Button type="primary" onClick={() => { approveReview(reviewDetail) }}>
                        Approva
                    </Button>,
                ]}>
                {
                    reviewDetail && <ReviewCard
                        title={reviewDetail.user.email}
                        camera={reviewDetail.camera}
                        display={reviewDetail.display}
                        performance={reviewDetail.performance}
                        battery={reviewDetail.battery}
                        description={reviewDetail.text}
                    />
                }

            </Modal>

        </div>
    )
}

export default Reviews