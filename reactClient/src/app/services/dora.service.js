import Axios from "axios"
import { DORAIA } from "./api"
const qs = require('querystring')

const config = (token = "") =>
({
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Bearer ' + token
    }
})
const searchWithDoraIa = (data, token) => {
    return Axios.post(DORAIA, qs.stringify(data), config(token))
        .then(res => res.data)
}

export { searchWithDoraIa }