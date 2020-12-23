import React from "react";
import Row from "../Row";
import TitledAvatar from "../TitledAvatar";


function SpecTable({ data }) {

    return (
        <Row style={{ flexWrap: "wrap" }}>
            {
                data.map((item, index) =>
                    <TitledAvatar
                        key={index}
                        title={item.key}
                        value={item.value}
                        icon={item.icon}
                    />)
            }

        </Row>
    )
}

export default SpecTable