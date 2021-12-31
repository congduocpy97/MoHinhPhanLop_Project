/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Input, notification, Row } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { create_model } from '../../Controller/Data_Service';
import { Model_KhachHang } from '../../Model/KhachHang';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'updatable';

export class KhachHang_Add extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            searchModel: JSON.parse(JSON.stringify(Model_KhachHang.KhachHang)),
        }
    }

    showModal = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_KhachHang.KhachHang)),
            isModalVisible: true
        })
    };

    handleOk = () => {
        Promise.all([create_model(this.state.searchModel, Model_KhachHang.table_database)]).then(result => {
            this.openNotification(result[0]);
            this.props.onAdd();
        })
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_KhachHang.KhachHang)),
            isModalVisible: false
        })
    };

    handleCancel = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_KhachHang.KhachHang)),
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


    handleMaKhachHangChange = e => {
        const searchModel = this.state.searchModel;
        searchModel.maKhachHang = e;
        this.setState({
            searchModel
        })
    }

    handleHoTenChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.hoTen =  e.target.value;
        this.setState({
            searchModel
        })
    }

    handleSoCMNDChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.soCMND =  e.target.value;
        this.setState({
            searchModel
        })
    }

    handleDiaChiChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.diaChi =  e.target.value;
        this.setState({
            searchModel
        })
    }

    handleGioiTinhChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.gioiTinh =  e.target.value;
        this.setState({
            searchModel
        })
    }

    handleSDTChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.sdt =  e.target.value;
        this.setState({
            searchModel
        })
    }

    handleQuocTichChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.quocTich =  e.target.value;
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
                <Modal title="Thêm khách hàng" visible={this.state.isModalVisible} onOk={this.handleOk} onCancel={this.handleCancel} width={800}>
                    <Card>
                        <Card.Grid hoverable={false} style={GridStyle}>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.hoTen.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleHoTenChange} value={searchModel.hoTen} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.soCMND.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleSoCMNDChange} value={searchModel.soCMND} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.diaChi.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleDiaChiChange} value={searchModel.diaChi} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.gioiTinh.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleGioiTinhChange} value={searchModel.gioiTinh} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.sdt.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleSDTChange} value={searchModel.sdt} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.quocTich.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleQuocTichChange} value={searchModel.quocTich} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.trangThai.name}
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

export default KhachHang_Add
