import React from "react";
import ReviewCard from "../../components/ReviewCard";
import SpecCard from "../../components/SpecCard";
import { Button, Radio } from 'antd';
import { DownloadOutlined } from '@ant-design/icons';
import { Link } from "react-router-dom";
import { Typography } from 'antd';

const { Title, Paragraph } = Typography;

function Home(props) {
    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
            <Title>Nuovi arrivi</Title>
            <div style={{ display: "flex", flexDirection: "row", alignItems: "center", flexWrap: "wrap" }}>
                <SpecCard
                    imageUrl="https://images-na.ssl-images-amazon.com/images/I/71YeIBbC%2BFL._AC_SL1500_.jpg"
                    title="Oneplus6"
                    description="Descrizione del mio telefono preferito <3"
                    id={123}
                />
                <SpecCard
                    imageUrl="https://images-na.ssl-images-amazon.com/images/I/71YeIBbC%2BFL._AC_SL1500_.jpg"
                    title="Oneplus6"
                    description="Descrizione del mio telefono preferito <3"
                />
                <SpecCard
                    imageUrl="https://images-na.ssl-images-amazon.com/images/I/71YeIBbC%2BFL._AC_SL1500_.jpg"
                    title="Oneplus6"
                    description="Descrizione del mio telefono preferito <3"
                />
            </div>

            <Title level={2} style={{ margin: "0x", marginTop: "20px" }}>Cerchi uno smartphone?</Title>
            <Link to="/dora" >
                <Button type="primary" shape="round" icon={<DownloadOutlined />} >Scopri DoraIA</Button>
            </Link>


        </div>
    )
}

export default Home