/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Input, notification, Row } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { create_model } from '../../Controller/Data_Service';
import { Model_DiaDiem } from '../../Model/DiaDiem';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_Input_Number from '../Form_Support/My_Input_Number';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'updatable';

export class DiaDiem_Add extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            searchModel: JSON.parse(JSON.stringify(Model_DiaDiem.DiaDiem)),
        }
    }

    showModal = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_DiaDiem.DiaDiem)),
            isModalVisible: true
        })
    };

    handleOk = () => {
        Promise.all([create_model(this.state.searchModel, Model_DiaDiem.table_database)]).then(result => {
            this.openNotification(result[0]);
            this.props.onAdd();
        })
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_DiaDiem.DiaDiem)),
            isModalVisible: false
        })
    };

    handleCancel = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_DiaDiem.DiaDiem)),
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


    handleMaDiaDiemChange = e => {
        const searchModel = this.state.searchModel;
        searchModel.maDiaDiem = e;
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
                <Modal title="Thêm địa điểm" visible={this.state.isModalVisible} onOk={this.handleOk} onCancel={this.handleCancel} width={800}>
                    <Card>
                        <Card.Grid hoverable={false} style={GridStyle}>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_DiaDiem.Field.maDiaDiem.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_Input_Number disabled={true} style={InputStyle} onChange={this.handleMaDiaDiemChange} value={searchModel.maDiaDiem} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_DiaDiem.Field.ten.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleTenChange} value={searchModel.ten} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_DiaDiem.Field.trangThai.name}
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

export default DiaDiem_Add
