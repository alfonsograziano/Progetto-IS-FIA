import { Card } from "antd";
import React from "react";
import { useHistory } from "react-router-dom";

const { Meta } = Card;

function SpecCard({
    title,
    imageUrl,
    description,
    id,
    onClick
}) {

    const history = useHistory();

    return (
        <div
            style={{ padding: "10px" }}
            onClick={() => {
                if (onClick) {
                    onClick()
                } else if (id) {
                    history.push("/details?id=" + id);
                }
            }}
        >
            <Card
                hoverable
                style={{ width: 240 }}
                cover={<img alt="example" src={imageUrl} />}
            >
                <Meta title={title} description={description} />
            </Card>
        </div>

    )
}

export default SpecCard