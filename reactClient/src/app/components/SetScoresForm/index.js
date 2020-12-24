import React from "react";
import { Form, Input, Button } from 'antd';

const layout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 16 },
};
const tailLayout = {
    wrapperCol: { offset: 8, span: 16 },
};

function SetScoresForm({
    onFinish = () => { },
    onFinishFailed = () => { }
}) {
    return (
        <Form
            {...layout}
            name="basic"
            initialValues={{}}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
        >
            <Form.Item
                label="Performance"
                name="performance"
                rules={[{ required: true, message: 'Please input the performance!' }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="Camera"
                name="camera"
                rules={[{ required: true, message: 'Please input the camera!' }]}
            >
                <Input />
            </Form.Item>

            <Form.Item
                label="Display"
                name="display"
                rules={[{ required: true, message: 'Please input the display!' }]}
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

export default SetScoresForm