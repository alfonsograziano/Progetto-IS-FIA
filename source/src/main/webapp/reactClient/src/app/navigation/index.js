import React, { useEffect, useState } from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
} from "react-router-dom";
import Login from "../pages/Login"
import Signup from "../pages/Signup"
import Home from "../pages/Home"
import Spec from "../pages/Spec";
import { Layout } from 'antd';
import Profile from "../pages/Profile";
import DoraIA from "../pages/DoraIA";
import SearchResults from "../pages/SearchResults";
import Navbar from "../components/Navbar";
import AdminHome from "../pages/admin/AdminHome";
import SpecList from "../pages/admin/SpecList";
import Reviews from "../pages/admin/Reviews";
import AddSpec from "../pages/admin/AddSpec";
import { AuthContext } from "../../App";

const { Content } = Layout;

function MainNavigation(props) {

    const [isAdmin, setIsAdmin] = useState(false)

    const { state } = React.useContext(AuthContext);

    useEffect(() => {
        if (state && state.user) {
            if (state.user.phoneNumber) {
                setIsAdmin(true)
            }
        }
    }, [state])

    return (
        <Router>

            <Layout style={{ height: "100%" }}>
                <Navbar />
                <Content style={{ padding: '0 50px', height: "100%", marginTop: "20px", backgroundColor: "transparent" }}>

                    <Switch>


                        <Route path="/login" component={Login} />
                        <Route path="/signup" component={Signup} />
                        <Route path="/details" component={Spec} />
                        <Route path="/profile" component={Profile} />
                        <Route path="/dora" component={DoraIA} />
                        <Route path="/search" component={SearchResults} />
                        <Route path="/home" component={Home} />
                        {
                            isAdmin &&
                            <React.Fragment>
                                <Route path="/admin/home" component={AdminHome} />
                                <Route path="/admin/speclist" component={SpecList} />
                                <Route path="/admin/createspec" component={AddSpec} />
                                <Route path="/admin/reviews" component={Reviews} />

                            </React.Fragment>
                        }

                    </Switch>



                </Content>
            </Layout >
        </Router >

    )
}

export default MainNavigation