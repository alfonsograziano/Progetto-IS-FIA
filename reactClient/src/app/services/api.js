const ROOT_URL = "/api"

export const DORAIA = `${ROOT_URL}/dora`;

export const ADD_SPEC = `${ROOT_URL}/spec/add`;
export const DELETE_SPEC = `${ROOT_URL}/spec/delete`;
export const ALL_SPECS = `${ROOT_URL}/spec/all`;
export const GET_SPEC_BY_ID = `${ROOT_URL}/spec`;
export const SEARCH_SPECS = `${ROOT_URL}/search`;
export const SET_SCORES = `${ROOT_URL}/spec/setscores`;

export const ADD_REVIEW = `${ROOT_URL}/review/add`;
export const CHANGE_REVIEW_STATUS = `${ROOT_URL}/changeReviewStatus`;
export const GET_PENDING_REVIEWS = `${ROOT_URL}/review/pending`;

export const LOG_IN = `${ROOT_URL}/login`;
export const SIGN_UP = `${ROOT_URL}/signup`;
export const PROFILE = `${ROOT_URL}/profile`;


export const authHeader = token => ({ Authorization: 'Bearer ' + token })
