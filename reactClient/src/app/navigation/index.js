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
import SpecList from "../pages/admin/SpecList";
import Reviews from "../pages/admin/Reviews";
import AddSpec from "../pages/admin/AddSpec";
import SetScores from "../pages/admin/SetScores";
import NotFound from "../pages/NotFound";

import { AuthContext } from "../../App";

const { Content } = Layout;

function MainNavigation(props) {

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
        console.log("Admin: ", isAdmin, " | Reviewer: ", isReviewer)
    }, [isReviewer, isAdmin])

    return (
        <Router>

            <Layout style={{ height: "100%", backgroundColor: "transparent" }}>
                <Navbar />
                <Content style={{ padding: '0 50px', height: "100%", marginTop: "20px", backgroundColor: "transparent" }}>

                    <Switch>


                        <Route exact path="/login" component={Login} />
                        <Route exact path="/signup" component={Signup} />
                        <Route exact path="/details" component={Spec} />
                        <Route exact path="/profile" component={Profile} />
                        <Route exact path="/dora" component={DoraIA} />
                        <Route exact path="/search" component={SearchResults} />
                        <Route exact path="/home" component={Home} />

                        <Route exact path="/admin/speclist" component={() => (isAdmin || isReviewer) ? <SpecList /> : <Login />} />

                        <Route exact path="/admin/createspec" component={() => isAdmin ? <AddSpec /> : <Login />} />

                        <Route exact path="/admin/reviews" component={() => isReviewer ? <Reviews /> : <Login />} />
                        <Route exact path="/admin/setscores" component={() => isReviewer ? <SetScores /> : <Login />} />

                        <Route component={() => <NotFound />} />

                    </Switch>



                </Content>
            </Layout >
        </Router >

    )
}

export default MainNavigation