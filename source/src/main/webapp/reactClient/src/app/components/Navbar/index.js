import React, { useState, useEffect } from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import { Layout, } from 'antd';
import SearchBarForm from "../SearchBarForm";
import Row from "../Row";
import { Button } from 'antd';
import { Avatar } from 'antd';
import { UserOutlined, HomeOutlined } from '@ant-design/icons';
import { useHistory } from "react-router-dom";
import AdminNavbar from "./admin";


const { Header } = Layout;
function Navbar(props) {
    const history = useHistory();

    //TODO: gestisci autenticazione correttamente => Si sblocca il task solo dopo aver gestito correttamente JWT
    const [isAdmin, setIsAdmin] = useState(true)
    const [isInAdmin, setIsInAdmin] = useState(false)

    if (isAdmin && isInAdmin) return <AdminNavbar />

    return (
        <Header style={{ backgroundColor: "#ccd3de" }}>
            <Row style={{ height: "100%", justifyContent: "space-between" }}>
                <Link to="/" >
                    <h1>SmartBlog</h1>
                </Link>

                <Row style={{ maxWidth: "400px" }}>
                    <Link to="/login">
                        <Button type="primary" style={{ marginRight: "20px" }}>Login</Button>
                    </Link>

                    <SearchBarForm
                        onSearch={data => {
                            window.location.href = "/search?s=" + data
                        }}
                    />
                    <div style={{ marginLeft: "20px" }}>
                        <Link to="/profile" >
                            <Avatar style={{ backgroundColor: "#f56a00", verticalAlign: 'middle' }} size="large" icon={<UserOutlined />} />
                        </Link>
                    </div>

                    <div style={{ marginLeft: "20px" }}>
                        <Link to="/admin/home" >
                            <Avatar style={{ backgroundColor: "#f56a00", verticalAlign: 'middle' }} size="large" icon={<HomeOutlined />} />
                        </Link>
                    </div>

                </Row>

            </Row>
        </Header>
    )
}

export default Navbar