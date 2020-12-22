import Axios from "axios"
import { LOG_IN, SIGN_UP, PROFILE } from "./api"
const qs = require('querystring')

const config = (token = "") =>
({
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Bearer ' + token
    }
})


const login = (email, password) => {
    return Axios.post(LOG_IN, qs.stringify({ email, password }), config())
        .then(res => res.data)
}

const signUp = (username, email, password, rePassword) => {
    return Axios.post(SIGN_UP, qs.stringify({ username, email, password, rePassword }), config())
        .then(res => res.data)
}

//TODO:Aggiungi il token appena gira...
const getProfileInfo = token => {
    console.log(config(token))
    return Axios.get(PROFILE,  config(token))
        .then(res => res.data)
}


export { getProfileInfo, login, signUp }