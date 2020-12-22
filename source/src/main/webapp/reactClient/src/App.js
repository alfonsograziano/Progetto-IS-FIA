import './App.css';
import React, { useEffect } from "react"
import MainNavigation from './app/navigation';
import 'antd/dist/antd.css';

export const AuthContext = React.createContext(); // added this
const initialState = {
  isAuthenticated: false,
  user: null,
  token: null,
};

const reducer = (state, action) => {
  switch (action.type) {
    case "LOGIN":
      localStorage.setItem("user", JSON.stringify(action.payload.user));
      localStorage.setItem("token", JSON.stringify(action.payload.token));
      return {
        ...state,
        isAuthenticated: true,
        user: action.payload.user,
        token: action.payload.token
      };
    case "LOGOUT":
      localStorage.clear();
      return {
        ...state,
        isAuthenticated: false,
        user: null,
        token: null
      };
    default:
      return state;
  }
};

function App() {
  const [state, dispatch] = React.useReducer(reducer, initialState);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"))
    const token = JSON.parse(localStorage.getItem("token"))
    console.log("Token => ",token)
    if (token && !token.includes(null)) {
      dispatch({
        type: "LOGIN",
        payload: { user, token }
      })
    }


  }, [])

  return (
    <div style={{ width: "100%", height: "100%" }}>
      <AuthContext.Provider value={{
        state,
        dispatch
      }}>

        <MainNavigation />

      </AuthContext.Provider>
    </div>

  );
}

export default App;
