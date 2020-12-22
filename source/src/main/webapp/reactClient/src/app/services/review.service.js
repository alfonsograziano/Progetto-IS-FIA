import Axios from "axios"
import { ADD_REVIEW, GET_PENDING_REVIEWS, CHANGE_REVIEW_STATUS } from "./api"
const qs = require('querystring')

const config = (token = "") =>
({
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Bearer ' + token
    }
})
const addReview = (data, token) => {
    return Axios.post(ADD_REVIEW, qs.stringify(data), config(token))
        .then(res => res.data)
}

const changeReviewStatus = (data, token) => {
    return Axios.post(CHANGE_REVIEW_STATUS, qs.stringify(data), config(token))
        .then(res => res.data)
}

const getPendingReviews = (token) => {
    return Axios.get(GET_PENDING_REVIEWS, config(token)).then(res => res.data)
}


export { addReview, getPendingReviews, changeReviewStatus }