import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import SpecCard from "../../components/SpecCard";
import { Button } from 'antd';
import { DownloadOutlined } from '@ant-design/icons';
import { Link } from "react-router-dom";
import { Typography } from 'antd';
import { search } from "../../services/spec.service"
import { Pagination, message } from 'antd';

const { Title } = Typography;
function useQuery() {
    return new URLSearchParams(useLocation().search);
}

function SearchResults(props) {
    let query = useQuery();
    const searchQuery = query.get("s")

    const [data, setData] = useState([])
    const pageSize = 50
    const [currentPage, setCurrentPage] = useState(1)

    useEffect(() => {
        search(searchQuery)
            .then(res => {
                console.log(res)
                setData(res)
            })
            .catch(err => {
                console.log(err)
                message.error('Impossibile completare la ricerca');
            })
    }, [])



    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
            <Title>Risultati di ricerca</Title>
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
            />


            <Title level={2} style={{ margin: "0x", marginTop: "20px" }}>Cerchi uno smartphone?</Title>
            <Link to="/dora" >
                <Button type="primary" shape="round" icon={<DownloadOutlined />} >Scopri DoraIA</Button>
            </Link>


        </div>
    )
}
export default SearchResults