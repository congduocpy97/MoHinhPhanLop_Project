/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Input, notification, Row } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { create_model } from '../../Controller/Data_Service';
import { Model_NhanVien } from '../../Model/NhanVien';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'updatable';

export class NhanVien_Add extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            searchModel: JSON.parse(JSON.stringify(Model_NhanVien.NhanVien)),
        }
    }

    showModal = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_NhanVien.NhanVien)),
            isModalVisible: true
        })
    };

    handleOk = () => {
        Promise.all([create_model(this.state.searchModel, Model_NhanVien.table_database)]).then(result => {
            this.openNotification(result[0]);
            this.props.onAdd();
        })
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_NhanVien.NhanVien)),
            isModalVisible: false
        })
    };

    handleCancel = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_NhanVien.NhanVien)),
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


    handleMaNhanVienChange = e => {
        const searchModel = this.state.searchModel;
        searchModel.maNhanVien = e;
        this.setState({
            searchModel
        })
    }

    handleTenNhanVienChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.tenNhanVien =  e.target.value;
        this.setState({
            searchModel
        })
    }

    handleTrangThaiChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.trangThai =  e;
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
                <Modal title="Thêm nhân viên" visible={this.state.isModalVisible} onOk={this.handleOk} onCancel={this.handleCancel} width={800}>
                    <Card>
                        <Card.Grid hoverable={false} style={GridStyle}>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_NhanVien.Field.tenNhanVien.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleTenNhanVienChange} value={searchModel.tenNhanVien} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_NhanVien.Field.trangThai.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <TrangThai_Select style={InputStyle} onSelect={this.handleTrangThaiChange} value={searchModel.trangThai} />
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

export default NhanVien_Add
