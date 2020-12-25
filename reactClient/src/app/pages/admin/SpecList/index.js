import React, { useState, useEffect } from "react";
import { getAll } from "../../../services/spec.service"
import { Table, Button, Space, message } from 'antd';
import SearchBarForm from "../../../components/SearchBarForm";
import { deleteSpec } from "../../../services/spec.service"
import { useHistory } from "react-router-dom";
import { AuthContext } from "../../../../App"

function SpecList(props) {
    const history = useHistory();

    const [data, setData] = useState([])
    const [filter, setFilter] = useState("")
    const [filteredData, setFilteredData] = useState([])
    const { state } = React.useContext(AuthContext);

    const [isAdmin, setIsAdmin] = useState(false)
    const [isReviewer, setIsReviewer] = useState(false)


    useEffect(() => {
        if (state && state.user) {
            if (state.user.phoneNumber) {
                if (state.user.rank) {
                    setIsReviewer(true)
                } else {
                    setIsAdmin(true)
                }
            }
        }
    }, [state])

    useEffect(() => {
        loadSpecs()
    }, [])

    const loadSpecs = () => {
        getAll()
            .then(res => {
                console.log(res)
                setData(res)
            })
    }

    useEffect(() => {
        if (filter.trim("") !== "") {
            console.log("i have to filter...")
            setFilteredData(data.filter(item => item.name.toLowerCase().includes(filter.toLowerCase())))
        } else {
            setFilteredData(data)
        }
    }, [data, filter])

    const deleteSelectedSpec = spec => {
        console.log(spec.id)
        deleteSpec(spec.id, state.token)
            .then(res => {
                message.success('Scheda tecnica calcellata');
                loadSpecs()
                console.log(res)
            })
            .catch(err => {
                message.error('Impossibile cancellare la scheda tecnica');
                console.log(err)
            })
    }

    useEffect(() => {
        console.log("Admin: ", isAdmin, " | Reviewer: ", isReviewer)
    }, [isReviewer, isAdmin])


    const [columns, setColumns] = useState([
        {
            title: 'Nome dispositivo',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: 'Data di uscita',
            dataIndex: 'date',
            key: 'date',
        }
    ]);

    useEffect(() => {
        if (isAdmin) {
            setColumns([...columns, {
                title: 'Azioni',
                key: 'action',
                render: (text, record) => (
                    <Space size="middle">
                        <Button type="link" onClick={() => {
                            var ok = prompt("Scrivi ok per confermare la cancellazione", "annulla");
                            if (ok === "ok")
                                deleteSelectedSpec(record)
                        }}>Cancella</Button>
                    </Space>
                ),
            },])
        }
    }, [isAdmin])

    const setScores = id => history.push("/admin/setscores?id=" + id)

    return (
        <div style={{ marginTop: "20px", display: "flex", flexDirection: "column", alignItems: "center" }}>
            <div style={{ margin: "20px" }}>
                <SearchBarForm
                    onSearch={data => {
                        setFilter(data)
                    }} />
            </div>

            <Table
                dataSource={filteredData}
                columns={columns}
                onRow={(record, rowIndex) => {
                    return {
                        onClick: event => {
                            if (isReviewer) {
                                setScores(record.id)
                            }
                        },

                    };
                }}
            />
            {
                isAdmin &&
                <Button type="primary" style={{ marginTop: "20px" }} onClick={() => {
                    history.push("/admin/createSpec")
                }}> Aggiungi scheda tecnica</Button>
            }


        </div>
    )
}

export default SpecList