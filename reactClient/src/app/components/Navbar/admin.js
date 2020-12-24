import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Layout, } from 'antd';
import Row from "../Row";
import { Button } from 'antd';
import { Avatar } from 'antd';
import { HomeOutlined } from '@ant-design/icons';
import { AuthContext } from "../../../App";

const { Header } = Layout;
function AdminNavbar(props) {
    const { state } = React.useContext(AuthContext);

    const [isAdmin, setIsAdmin] = useState(false)
    const [isReviewer, setIsReviewer] = useState(false)


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

    return (
        <Row style={{ height: "100%", justifyContent: "space-between" }}>
            <Link to="/home" >
                <h1>SmartBlog</h1>
            </Link>

            <Row style={{ maxWidth: "400px" }}>

                <div style={{ marginLeft: "20px" }}>
                    <Link to="/admin/speclist" style={{ marginRight: "20px" }}>
                        Lista schede tecniche
                    </Link>

                    {isReviewer &&
                        <Link to="/admin/reviews" style={{ marginRight: "20px" }}>
                            Recensioni
                        </Link>
                    }
                    <Link to="/home" >
                        <Avatar style={{ backgroundColor: "#f56a00", verticalAlign: 'middle' }} size="large" icon={<HomeOutlined />} />
                    </Link>
                </div>
            </Row>
        </Row>
    )
}

export default AdminNavbar