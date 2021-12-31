import { Button, Card, Col, Input, notification, Row } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { create_model } from '../../Controller/Data_Service';
import { Model_TrangThai } from '../../Model/TrangThai';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';

const key = 'updatable';

export class TrangThai_Add extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            searchModel: JSON.parse(JSON.stringify(Model_TrangThai.TrangThai)),
        }
    }

    showModal = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_TrangThai.TrangThai)),
            isModalVisible: true
        })
    };

    handleOk = () => {
        Promise.all([create_model(this.state.searchModel, Model_TrangThai.table_database)]).then(result => {
            this.openNotification(result[0]);
            this.props.onAdd();
        })
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_TrangThai.TrangThai)),
            isModalVisible: false
        })
    };

    handleCancel = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_TrangThai.TrangThai)),
            isModalVisible: false
        })
    };

    openNotification = (value) => {
        if(value === 1){
            notification.open({
            key,
            message: 'Thành công',
            description: 'Dữ liệu đã được thêm vào.',
            });
        }else{
            notification.open({
                key,
                message: 'Thất bại',
                description: 'Dữ liệu không được thêm vào.',
            });
        }
    }


    handleTrangThaiChange = e => {
        const searchModel = this.state.searchModel;
        searchModel.trangThai = e.target.value;
        this.setState({
            searchModel
        })
    }

    handleTenChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.ten =  e.target.value;
        this.setState({
            searchModel
        })
    }

    render() {
        const searchModel = this.state.searchModel;
        return (
            <span>
                <Button type="primary" onClick={this.showModal}>
                    {this.props.name}
                </Button>
                <Modal title="Thêm trạng thái" visible={this.state.isModalVisible} onOk={this.handleOk} onCancel={this.handleCancel} width={800}>
                    <Card>
                        <Card.Grid hoverable={false} style={GridStyle}>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_TrangThai.Field.trangThai.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleTrangThaiChange} value={searchModel.trangThai} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_TrangThai.Field.ten.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleTenChange} value={searchModel.ten} />
                                    </FormItem>
                                </Col>
                            </Row>
                        </Card.Grid>
                    </Card>
                </Modal>
            </span>
        )
    }
}

export default TrangThai_Add
