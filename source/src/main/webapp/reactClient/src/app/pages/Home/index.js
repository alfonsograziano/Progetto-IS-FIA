import React, { useState, useEffect } from "react";
import SpecCard from "../../components/SpecCard";
import { Button } from 'antd';
import { DownloadOutlined } from '@ant-design/icons';
import { Link } from "react-router-dom";
import { Typography } from 'antd';
import { getAll } from "../../services/spec.service"
import { Pagination } from 'antd';

const { Title } = Typography;

function Home(props) {


    const [data, setData] = useState([])
    const pageSize = 10
    const [currentPage, setCurrentPage] = useState(1)

    useEffect(() => {
        getAll()
            .then(res => {
                console.log(res)
                setData(res)
            })
    }, [])

    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
            <Title level={2} style={{ margin: "0x", marginTop: "20px" }}>Cerchi uno smartphone?</Title>
            <Link to="/dora" >
                <Button type="primary" shape="round" icon={<DownloadOutlined />} >Scopri DoraIA</Button>
            </Link>

            <Title style={{ marginTop: "20px" }}>Nuovi arrivi</Title>
            <div style={{ display: "flex", flexDirection: "row", alignItems: "center", flexWrap: "wrap" }}>
                {
                    data.slice(pageSize * (currentPage - 1), pageSize * (currentPage)).map((item, index) =>
                        <SpecCard
                            key={index}
                            imageUrl={item.image}
                            title={item.name + " - " + item.price}
                            description={"SO: " + item.so + "  Memoria: " + item.memory + "  RAM: " + item.ram}
                            id={item.id}
                        />
                    )
                }
            </div>
            <Pagination size="small"
                total={pageSize}
                onChange={setCurrentPage}
                total={data && data.length}
                showSizeChanger={false}
            />





        </div>
    )
}

export default Home