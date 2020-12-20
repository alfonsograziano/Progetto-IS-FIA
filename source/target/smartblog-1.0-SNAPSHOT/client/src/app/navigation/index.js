import React from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

import Login from "../pages/Login"
import Signup from "../pages/Signup"
import Home from "../pages/Home"
import Spec from "../pages/Spec";

import { Layout, Menu, Breadcrumb } from 'antd';
import SearchBarForm from "../components/SearchBarForm";
import Row from "../components/Row";
import { Button } from 'antd';
import { Avatar } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import Profile from "../pages/Profile";
import DoraIA from "../pages/DoraIA";
import DoraResults from "../pages/DoraResults";


const { Header, Content, Footer } = Layout;



function MainNavigation(props) {
    return (
        <Router>

            <Layout style={{ height: "100%" }}>
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
                                onSearch={
                                    (data) => {
                                        console.log("Searched ", data)
                                    }
                                }
                            />
                            <div style={{ marginLeft: "20px" }}>
                                <Link to="/profile" >
                                    <Avatar style={{ backgroundColor: "#f56a00", verticalAlign: 'middle' }} size="large" icon={<UserOutlined />} />
                                </Link>
                            </div>

                        </Row>

                    </Row>
                </Header>
                <Content style={{ padding: '0 50px', height: "100%", marginTop:"20px" }}>

                    <Switch>
                        <Route path="/login" component={Login} />
                        <Route path="/signup" component={Signup} />
                        <Route path="/details" component={Spec} />
                        <Route path="/profile" component={Profile} />
                        <Route path="/results" component={DoraResults} />
                        <Route path="/dora" component={DoraIA} />

                        <Route path="/" component={Home} />

                    </Switch>
                </Content>
            </Layout >
        </Router >

    )
}

export default MainNavigation