/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, notification, Row } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { create_model } from '../../Controller/Data_Service';
import { Model_ChiPhi } from '../../Model/ChiPhi';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import DoanDuLich_Select from '../DoanDuLich/DoanDuLich_Select';
import My_InputCurrency from '../Form_Support/My_InputCurrency';
import LoaiChiPhi_Select from '../LoaiChiPhi/LoaiChiPhi_Select';

const key = 'updatable';

export class ChiPhi_Add extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            searchModel: JSON.parse(JSON.stringify(Model_ChiPhi.ChiPhi)),
        }
    }

    showModal = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_ChiPhi.ChiPhi)),
            isModalVisible: true
        })
    };

    handleOk = () => {
        Promise.all([create_model(this.state.searchModel, Model_ChiPhi.table_database)]).then(result => {
            this.openNotification(result[0]);
            this.props.onAdd();
        })
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_ChiPhi.ChiPhi)),
            isModalVisible: false
        })
    };

    handleCancel = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_ChiPhi.ChiPhi)),
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


    handleMaChiPhiChange = e => {
        const searchModel = this.state.searchModel;
        searchModel.maChiPhi = e;
        this.setState({
            searchModel
        })
    }

    handleMaDoanChange = e => {
        const searchModel = this.state.searchModel;
        searchModel.maDoan = e;
        this.setState({
            searchModel
        })
    }

    handleSoTienChange = e => {
        const searchModel = this.state.searchModel;
        searchModel.soTien = e;
        this.setState({
            searchModel
        })
    }
    handleMaLoaiChiPhiChange = e => {
        const searchModel = this.state.searchModel;
        searchModel.maLoaiChiPhi = e;
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
                <Modal title="Thêm chi phí" visible={this.state.isModalVisible} onOk={this.handleOk} onCancel={this.handleCancel} width={800}>
                    <Card>
                        <Card.Grid hoverable={false} style={GridStyle}>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_ChiPhi.Field.maDoan.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <DoanDuLich_Select style={InputStyle} onSelect={this.handleMaDoanChange} value={searchModel.maDoan} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_ChiPhi.Field.soTien.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_InputCurrency style={InputStyle} onChange={this.handleSoTienChange} value={searchModel.soTien} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_ChiPhi.Field.maLoaiChiPhi.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <LoaiChiPhi_Select style={InputStyle} onSelect={this.handleMaLoaiChiPhiChange} value={searchModel.maLoaiChiPhi} />
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

export default ChiPhi_Add
