import Axios from "axios"
import { GET_SPEC_BY_ID, ALL_SPECS, SEARCH_SPECS, DELETE_SPEC } from "./api"
const qs = require('querystring')

const config = {
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
}
const createNewSpec = () => {
    return new Promise((resolve, reject) => resolve("Done"))
}

const getSpecById = id => {
    return Axios.get(GET_SPEC_BY_ID + "/?id=" + id).then(res => res.data)
}

const getAll = () => {
    return Axios.get(ALL_SPECS).then(res => res.data)
}

const search = query => {
    return Axios.get(SEARCH_SPECS + "?s=" + query).then(res => res.data)
}


const deleteSpec = (specId) => {
    return Axios.post(DELETE_SPEC, qs.stringify({ specId: specId }), config)
        .then(res => res.data)
}

export { getSpecById, getAll, search, deleteSpec }