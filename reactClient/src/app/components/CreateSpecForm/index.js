import React from "react";
import { Form, Input, Button } from 'antd';

const layout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 16 },
};
const tailLayout = {
    wrapperCol: { offset: 8, span: 16 },
};

function CreateSpecForm({
    onFinish = () => { },
    onFinishFailed = () => { }
}) {
    return (
        <Form
            {...layout}
            name="basic"
            initialValues={{ remember: true }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
        >
            <Form.Item
                label="Device name"
                name="deviceName"
                rules={[{ required: true }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="Release date"
                name="releaseDate"
                rules={[{ required: true }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="SO"
                name="OS"
                rules={[{ required: true }]}
            >
                <Input />
            </Form.Item>


            <Form.Item
                label="CPU"
                name="CPU"
                rules={[{ required: true }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="Chipset"
                name="chipset"
                rules={[{ required: true }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="GPU"
                name="GPU"
                rules={[{ required: true }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="RAM"
                name="RAM"
                rules={[{ required: true }]}
            >
                <Input placeholder="in MB or GB"/>
            </Form.Item>


            <Form.Item
                label="Internal Memory"
                name="internalMemory"
                rules={[{ required: true }]}
            >
                <Input placeholder="in MB or GB"/>
            </Form.Item>

            <Form.Item
                label="Battery"
                name="battery"
                rules={[{ required: true }]}
            >
                <Input placeholder="Insert number only"/>
            </Form.Item>

            <Form.Item
                label="Display"
                name="displayInches"
                rules={[{ required: true }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="Price"
                name="price"
                rules={[{ required: true }]}
            >
                <Input placeholder="Insert number only"/>
            </Form.Item>

            <Form.Item
                label="Image url"
                name="image"
                rules={[{ required: true }]}
            >
                <Input />
            </Form.Item>

            <Form.Item {...tailLayout}>
                <Button type="primary" htmlType="submit">
                    Submit
          </Button>
            </Form.Item>
        </Form>
    )
}

export default CreateSpecForm