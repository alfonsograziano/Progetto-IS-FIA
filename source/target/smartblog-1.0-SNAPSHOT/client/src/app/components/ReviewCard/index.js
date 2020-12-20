import React from "react";
import { StarFilled } from "@ant-design/icons"
import Row from "../Row"


function range(i) { return i ? range(i - 1).concat(i) : [] }

function ReviewCard({
    title,
    subtitle,
    camera,
    display,
    performance,
    battery,
    description
}) {
    return (
        <div style={{
            margin: "10px",
            padding: "10px",
            backgroundColor: "#fff",
            maxWidth: "250px",
            borderRadius:"5px",
            boxShadow:"0px 0px 5px rgba(0,0,0,0.1)"
        }}>
            <div style={{ display: "flex", flexDirection: "row", alignItems: "center", justifyContent: "space-between", width: "100%" }}>
                <h2 style={{ margin: "0px" }}>{title}</h2>
                <p style={{ margin: "0px" }}>{subtitle}</p>
            </div>
            <Row style={{ justifyContent: "space-between", width: "100%" }}>
                <p style={{ margin: "0px" }}>Camera</p>
                <Row>
                    {range(camera).map(item => <StarFilled />)}
                </Row>
            </Row>
            <Row style={{ justifyContent: "space-between", width: "100%" }}>
                <p style={{ margin: "0px" }}>Display</p>
                <Row>
                    {range(display).map(item => <StarFilled />)}
                </Row>
            </Row>
            <Row style={{ justifyContent: "space-between", width: "100%" }}>
                <p style={{ margin: "0px" }}>Performance</p>
                <Row>
                    {range(performance).map(item => <StarFilled />)}
                </Row>
            </Row>
            <Row style={{ justifyContent: "space-between", width: "100%" }}>
                <p style={{ margin: "0px" }}>Battery</p>
                <Row>
                    {range(battery).map(item => <StarFilled />)}
                </Row>
            </Row>

            <p style={{ marginTop: "20px" }}>{description}</p>
        </div>
    )
}

export default ReviewCard