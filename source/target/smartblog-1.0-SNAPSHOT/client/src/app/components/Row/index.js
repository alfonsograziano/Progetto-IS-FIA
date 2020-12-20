import React from "react";

function Row(props) {
    const { style } = props
    const defaultStyle = { display: "flex", flexDirection: "row", alignItems: "center"}

    return (
        <div style={{ ...defaultStyle, ...style }}>
            {props.children}
        </div>
    )
}

export default Row