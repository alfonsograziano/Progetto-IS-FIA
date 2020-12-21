import React, { useState, useEffect } from "react";
import { getAll } from "../../../services/spec.service"
import { Table, Button, Space } from 'antd';
import SearchBarForm from "../../../components/SearchBarForm";
import { deleteSpec } from "../../../services/spec.service"
function SpecList(props) {

    const [data, setData] = useState([])
    const [filter, setFilter] = useState("")
    const [filteredData, setFilteredData] = useState([])


    useEffect(() => {
        getAll()
            .then(res => {
                console.log(res)
                setData(res)
            })
    }, [])


    useEffect(() => {
        if (filter.trim("") !== "") {
            console.log("i have to filter...")
            setFilteredData(data.filter(item => item.name.toLowerCase().includes(filter.toLowerCase())))
        } else {
            setFilteredData(data)
        }
    }, [filter])

    const deleteSelectedSpec = spec => {
        console.log(spec.id)
         deleteSpec(spec.id)
             .then(res => {
                 console.log(res)
             })
             .catch(err => {
                 console.log(err)
             })
 
    }


    const columns = [
        {
            title: 'Nome dispositivo',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: 'Data di uscita',
            dataIndex: 'date',
            key: 'date',
        },
        {
            title: 'Azioni',
            key: 'action',
            render: (text, record) => (
                <Space size="middle">
                    <Button type="link" onClick={() => { deleteSelectedSpec(record) }}>Cancella</Button>
                </Space>
            ),
        },
    ];


    return (
        <div>
            <div style={{ margin: "20px" }}>
                <SearchBarForm
                    onSearch={data => {
                        setFilter(data)
                    }} />
            </div>

            <Table
                dataSource={filteredData}
                columns={columns}
            />
        </div>
    )
}

export default SpecList