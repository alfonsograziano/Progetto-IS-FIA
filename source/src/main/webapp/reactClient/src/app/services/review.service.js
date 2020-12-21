import Axios from "axios"
import { ADD_REVIEW, GET_PENDING_REVIEWS, CHANGE_REVIEW_STATUS } from "./api"
const qs = require('querystring')

const config = {
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
}


const addReview = data => {
    return Axios.post(ADD_REVIEW, qs.stringify(data), config)
        .then(res => res.data)
}

const changeReviewStatus = data => {
    return Axios.post(CHANGE_REVIEW_STATUS, qs.stringify(data), config)
        .then(res => res.data)
}

const getPendingReviews = () => {
    return Axios.get(GET_PENDING_REVIEWS).then(res => res.data)
}


export { addReview, getPendingReviews, changeReviewStatus }