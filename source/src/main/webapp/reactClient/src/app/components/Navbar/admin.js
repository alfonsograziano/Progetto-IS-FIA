import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Layout, } from 'antd';
import Row from "../Row";
import { Button } from 'antd';
import { Avatar } from 'antd';
import { HomeOutlined } from '@ant-design/icons';
import { useHistory } from "react-router-dom";


const { Header } = Layout;
function AdminNavbar(props) {
    const history = useHistory();

    return (
        <Header style={{ backgroundColor: "#ccd3de" }}>
            <Row style={{ height: "100%", justifyContent: "space-between" }}>
                <Link to="/" >
                    <h1>SmartBlog</h1>
                </Link>

                <Row style={{ maxWidth: "400px" }}>
                    <Link to="/admin/speclist">
                        <Button type="primary" style={{ marginRight: "20px" }}>Schede tecniche</Button>
                    </Link>

                    <Link to="/admin/reviews">
                        <Button type="primary" style={{ marginRight: "20px" }}>Recensioni</Button>
                    </Link>

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

export default AdminNavbar