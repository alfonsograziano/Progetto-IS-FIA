import Axios from "axios"
import { GET_SPEC_BY_ID, ALL_SPECS, SEARCH_SPECS, DELETE_SPEC, ADD_SPEC } from "./api"
const qs = require('querystring')

const config = (token = "") =>
({
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Bearer ' + token
    }
})

const createNewSpec = (data, token) => {
    console.log(ADD_SPEC)
    return Axios.post(ADD_SPEC, qs.stringify(data), config(token))
        .then(res => res.data)
}

const getSpecById = id => {
    return Axios.get(GET_SPEC_BY_ID + "?id=" + id).then(res => res.data)
}

const getAll = () => {
    return Axios.get(ALL_SPECS).then(res => res.data)
}

const search = query => {
    return Axios.get(SEARCH_SPECS + "?s=" + query).then(res => res.data)
}


const deleteSpec = (specId, token) => {
    return Axios.post(DELETE_SPEC, qs.stringify({ specId }), config(token))
        .then(res => res.data)
}

export { getSpecById, getAll, search, deleteSpec, createNewSpec }