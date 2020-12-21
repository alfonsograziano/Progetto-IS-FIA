import React from "react";
import { Avatar } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import { Typography } from 'antd';
import { Button } from 'antd';
import ReviewCard from "../../components/ReviewCard";

const { Title, Paragraph } = Typography;
function Profile(props) {

    const email = "test@test.com"
    const username = "pippo CATTANEO"

    return (
        <div style={{ marginTop: "20px", display: "flex", flexDirection: "column", alignItems: "center" }}>
            <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                <Avatar
                    style={{ backgroundColor: "#f56a00", verticalAlign: 'middle' }}
                    size={{ xs: 24, sm: 32, md: 40, lg: 64, xl: 80, xxl: 100 }}
                    icon={<UserOutlined />}
                />
                <Title level={3} style={{ marginBottom: "0px" }}>{email}</Title>
                <Paragraph><b>{username}</b></Paragraph>
            </div>

            <Title level={2}>Le mie recensioni</Title>

            <div style={{ display: "flex", flexDirection: "row", alignItems: "center", flexWrap: "wrap" }}>
                <ReviewCard
                    title="pippo CATTANEO"
                    subtitle="12/02/2020"
                    camera={5}
                    display={3}
                    performance={4}
                    battery={5}
                    description="Descrizione della recensione, tutto molto bello"
                />
                <ReviewCard
                    title="pippo CATTANEO"
                    subtitle="12/02/2020"
                    camera={5}
                    display={3}
                    performance={4}
                    battery={5}
                    description="Descrizione della recensione, tutto molto bello"
                />
                <ReviewCard
                    title="pippo CATTANEO"
                    subtitle="12/02/2020"
                    camera={5}
                    display={3}
                    performance={4}
                    battery={5}
                    description="Descrizione della recensione, tutto molto bello"
                />
            </div>
         

            <Button type="primary" style={{marginTop:"30px"}}>Logout</Button>


        </div>
    )
}

export default Profile