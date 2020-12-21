import logo from './logo.svg';
import './App.css';
import axios from "axios"
import { useState, useEffect } from "react"
import MainNavigation from './app/navigation';
import 'antd/dist/antd.css';


function App() {

  return (
    <div style={{ width: "100%", height: "100%" }}>
      <MainNavigation />
      <div>Lol</div>
    </div>

  );
}

export default App;
