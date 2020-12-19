import logo from './logo.svg';
import './App.css';
import axios from "axios"
import {useState, useEffect} from "react"

function App() {

  const [users, setUsers] = useState([])

  useEffect(()=>{
    axios.get("/hello")
    .then(res => {
      console.log(res.data)
      setUsers(res.data)
    })
  },[])


  return (
    <div className="App">
      {users.map(user => <p>User: {user.email} | pass: {user.password}</p>)}
    </div>
  );
}

export default App;
