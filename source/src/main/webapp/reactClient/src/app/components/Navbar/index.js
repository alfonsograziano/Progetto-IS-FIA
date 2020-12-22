import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Layout, } from 'antd';
import SearchBarForm from "../SearchBarForm";
import Row from "../Row";
import { Button } from 'antd';
import { Avatar } from 'antd';
import { UserOutlined, HomeOutlined } from '@ant-design/icons';
import AdminNavbar from "./admin";
import { AuthContext } from "../../../App";
import { useLocation } from 'react-router-dom'

const { Header } = Layout;
function Navbar(props) {
    const location = useLocation();

    const [isInAdmin, setIsInAdmin] = useState(false)
    const [isAdmin, setIsAdmin] = useState(false)
    const [isReviewer, setIsReviewer] = useState(false)
    const { state } = React.useContext(AuthContext);

    useEffect(() => {
        if (state && state.user) {
            if (state.user.phoneNumber) {
                if (state.user.rank) {
                    setIsReviewer(true)
                } else {
                    setIsAdmin(true)
                }
            }
        }
    }, [state])

    useEffect(() => {
        setIsInAdmin(!!location.pathname.includes("admin"))
    }, [location])

    return (
        <Header style={{ backgroundColor: "#ccd3de" }}>
            {(isAdmin || isReviewer) && isInAdmin ?
                <AdminNavbar />
                :
                <Row style={{ height: "100%", justifyContent: "space-between" }}>
                    <Link to="/home" >
                        <h1>SmartBlog</h1>
                    </Link>

                    <Row style={{ maxWidth: "400px" }}>


                        <SearchBarForm
                            onSearch={data => {
                                window.location.href = "/search?s=" + data
                            }}
                        />

                        {
                            state && state.isAuthenticated ?
                                <div style={{ marginLeft: "20px", display: "flex", flexDirection: "column", alignItems: "center" }}>
                                    <Link to="/profile" >
                                        <Avatar style={{ backgroundColor: "#f56a00", verticalAlign: 'middle' }} size="large" icon={<UserOutlined />} />
                                    </Link>
                                    {/* <div>Ciao,{" "+state.user.username}</div> */}
                                </div>
                                :
                                <Link to="/login">
                                    <Button type="primary" style={{ marginRight: "20px" }}>Login</Button>
                                </Link>
                        }

                        {
                            isAdmin &&
                            <div style={{ marginLeft: "20px" }}>
                                <Link to="/admin/speclist" >
                                    <Avatar style={{ backgroundColor: "#f56a00", verticalAlign: 'middle' }} size="large" icon={<HomeOutlined />} />
                                </Link>
                            </div>
                        }
                        {
                            isReviewer &&
                            <div style={{ marginLeft: "20px" }}>
                                <Link to="/admin/reviews" >
                                    <Avatar style={{ backgroundColor: "#f56a00", verticalAlign: 'middle' }} size="large" icon={<HomeOutlined />} />
                                </Link>
                            </div>
                        }
                    </Row>
                </Row>
            }
        </Header>

    )
}

export default Navbar