import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import SpecCard from "../../components/SpecCard";
import { Button } from 'antd';
import { DownloadOutlined } from '@ant-design/icons';
import { Link } from "react-router-dom";
import { Typography } from 'antd';
import { search } from "../../services/spec.service"
import { Pagination, message } from 'antd';
import { useHistory } from "react-router-dom";
import { Empty } from 'antd';

const { Title } = Typography;
function useQuery() {
    return new URLSearchParams(useLocation().search);
}


const formatSpecDescription = item => {
    let string = ""
    if (item.so) string += "OS: " + item.so
    if (item.memory) string += "  Memory: " + item.memory
    if (item.ram) string += "  RAM: " + item.ram
    return string
}
function SearchResults(props) {
    let query = useQuery();
    const searchQuery = query.get("s")

    const [data, setData] = useState([])
    const pageSize = 50
    const [currentPage, setCurrentPage] = useState(1)
    const history = useHistory();

    useEffect(() => {
        search(searchQuery)
            .then(res => {
                console.log(res)
                setData(res)
            })
            .catch(err => {
                history.push("/404")
                console.log(err)
                message.error('Unable to complete the search');
            })
    }, [])



    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
            <Title>Search Results</Title>
            <div style={{ display: "flex", flexDirection: "row", alignItems: "center", flexWrap: "wrap" }}>
                {
                    data && data.length > 0 ? data.slice(pageSize * (currentPage - 1), pageSize * (currentPage)).map((item, index) =>
                        <SpecCard
                            key={index}
                            imageUrl={item.image}
                            title={item.name + " - " + item.price}
                            description={formatSpecDescription(item)}
                            id={item.id}
                        />
                    )
                        :
                        <Empty />
                }

            </div>
            {
                data && data.length !== 0 && <Pagination size="small"
                    total={pageSize}
                    onChange={setCurrentPage}
                />
            }



            <Title level={2} style={{ margin: "0x", marginTop: "20px" }}>Are you looking for a smartphone?</Title>
            <Link to="/dora" >
                <Button type="primary" shape="round" icon={<DownloadOutlined />} >Discover DoraIA</Button>
            </Link>


        </div>
    )
}
export default SearchResults