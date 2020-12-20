import React from "react";
import { Input } from 'antd';
import { AudioOutlined } from '@ant-design/icons';

const { Search } = Input;

function SearchBarForm({ onSearch }) {
    return (
        <Search placeholder="input search text" onSearch={onSearch} enterButton />

    )
}

export default SearchBarForm