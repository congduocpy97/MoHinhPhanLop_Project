/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, notification, Row } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { find_model, update_model } from '../../Controller/Data_Service';
import { Model_ChiPhi } from '../../Model/ChiPhi';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import DoanDuLich_Select from '../DoanDuLich/DoanDuLich_Select';
import My_InputCurrency from '../Form_Support/My_InputCurrency';
import My_Input_Number from '../Form_Support/My_Input_Number';
import LoaiChiPhi_Select from '../LoaiChiPhi/LoaiChiPhi_Select';

const key = 'edit';

export class ChiPhi_Edit extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            selected: [],
            preModel: JSON.parse(JSON.stringify(Model_ChiPhi.ChiPhi)),
            searchModel: JSON.parse(JSON.stringify(Model_ChiPhi.ChiPhi)),
        }
    }

    componentDidUpdate(prevProps){
        if(prevProps !== this.props){
            const selected = this.props.selected;
            this.setState({
                selected
            })
        }
    }

    componentDidMount(){
        const selected = this.props.selected;
        this.setState({
            selected
        })
    }

    showModal = () => {
        if(this.state.selected.length !== 1){
            this.openNotification(this.state.selected.length);
        }else{
            Promise.all([find_model(this.state.selected[0], Model_ChiPhi.table_database)]).then(result => {
                this.setState({
                    searchModel: result[0],
                    preModel: JSON.parse(JSON.stringify(result[0])),
                    isModalVisible: true
                })
            })
        }
    };

    openNotification = (value) => {
        if(value === 0){
            notification.open({
            key,
            message: 'Thất bại',
            description: 'Vui lòng chọn dữ liệu.',
            });
        }else if (value > 1){
            notification.open({
                key,
                message: 'Thất bại',
                description: 'Chọn chỉ 1 dòng dữ liệu.',
            });
        }else if (value === -1){
            notification.open({
                key,
                message: 'Thất bại',
                description: 'Không thể lưu dữ liệu.',
            });
        }else{
            this.props.onEdit();
            notification.open({
                key,
                message: 'Thành công',
                description: 'Dữ liệu đã được chỉnh sửa.',
            });
        }
    }

    handleOk = () => {
        if(JSON.stringify(this.state.preModel) !== JSON.stringify(this.state.searchModel)){
            Promise.all([update_model([this.state.searchModel, this.state.preModel], Model_ChiPhi.table_database)]).then(result => {
                this.openNotification(result[0] === 0 ? -1 : result[0]);
                this.setState({
                    isModalVisible: false
                })
            });
        }
        else{
            this.openNotification(-1)
            this.setState({
                isModalVisible: false
            })
        }

    };

    handleCancel = () => {
        this.setState({
            isModalVisible: false
        })
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
                <Modal title="Chỉnh sửa chi phí" visible={this.state.isModalVisible} width={800} onCancel={this.handleCancel} onOk={this.handleOk}>
                    <Card>
                        <Card.Grid hoverable={false} style={GridStyle}>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_ChiPhi.Field.maChiPhi.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_Input_Number disabled={true} style={InputStyle} value={searchModel.maChiPhi} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_ChiPhi.Field.maDoan.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <DoanDuLich_Select style={InputStyle} onSelect={this.handleMaDoanChange} value={searchModel.maDoan} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_ChiPhi.Field.soTien.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_InputCurrency onChange={this.handleSoTienChange} style={InputStyle} value={searchModel.soTien} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
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

export default ChiPhi_Edit