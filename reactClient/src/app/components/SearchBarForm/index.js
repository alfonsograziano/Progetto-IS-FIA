import React from "react";
import { Input } from 'antd';

const { Search } = Input;

function SearchBarForm({ onSearch }) {
    return (
        <Search placeholder="input search text" onSearch={onSearch} enterButton />

    )
}

export default SearchBarForm