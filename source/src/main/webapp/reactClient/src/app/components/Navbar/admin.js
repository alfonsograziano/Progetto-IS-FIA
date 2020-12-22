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

    const [isAdmin, setIsAdmin] = useState(false)
    const [isReviewer, setIsReviewr] = useState(false)

    const { state } = React.useContext(AuthContext);

    useEffect(() => {
        if (state && state.user) {
            if (state.user.phoneNumber) {
                if (state.user.rank) {
                    setIsReviewr(true)
                    setIsAdmin(false)
                } else {
                    setIsReviewr(true)
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

                {
                    isAdmin &&
                    <Link to="/admin/speclist">
                        <Button type="primary" style={{ marginRight: "20px" }}>Schede tecniche</Button>
                    </Link>
                }

                <Link to="/admin/reviews">
                    <Button type="primary" style={{ marginRight: "20px" }}>Recensioni</Button>
                </Link>

                <div style={{ marginLeft: "20px" }}>
                    <Link to="/home" >
                        <Avatar style={{ backgroundColor: "#f56a00", verticalAlign: 'middle' }} size="large" icon={<HomeOutlined />} />
                    </Link>
                </div>
            </Row>
        </Row>
    )
}

export default AdminNavbar