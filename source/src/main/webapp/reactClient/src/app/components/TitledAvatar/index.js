import React from "react";
import { Avatar } from 'antd';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
function TitledAvatar({ icon="", title="", value="" }) {
    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center",margin:"20px" }}>
            <Avatar style={{ backgroundColor: "#919191" }} size="large" icon={<FontAwesomeIcon icon={icon} />} />
            <p style={{ margin: "0px" }}><b>{title}</b></p>
            <p style={{ margin: "0px" }}>{value.substring(0,10)}</p>
        </div>
    )
}

export default TitledAvatar