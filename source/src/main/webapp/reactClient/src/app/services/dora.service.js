import Axios from "axios"
import { DORAIA } from "./api"
const qs = require('querystring')

const config = {
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
}
const searchWithDoraIa = data => {
    return Axios.post(DORAIA, qs.stringify(data), config)
        .then(res => res.data)
}

export { searchWithDoraIa }