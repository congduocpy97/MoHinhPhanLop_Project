/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Input, notification, Row } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { find_model } from '../../Controller/Data_Service';
import { Model_KhachHang } from '../../Model/KhachHang';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_Input_Number from '../Form_Support/My_Input_Number';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'view';

export class KhachHang_View extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            selected: [],
            searchModel: JSON.parse(JSON.stringify(Model_KhachHang.KhachHang)),
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
            Promise.all([find_model(this.state.selected[0], Model_KhachHang.table_database)]).then(result => {
                this.setState({
                    searchModel: result[0],
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
        }else{
            notification.open({
                key,
                message: 'Thất bại',
                description: 'Chọn chỉ 1 dòng dữ liệu.',
            });
        }
    }

    handleOk = () => {
        this.setState({
            isModalVisible: false
        })
    };

    render() {
        const searchModel = this.state.searchModel;
        return (
            <span>
                <Button type="primary" onClick={this.showModal}>
                    {this.props.name}
                </Button>
                <Modal title="Xem thông tin khách hàng" visible={this.state.isModalVisible} width={800} onCancel={this.handleOk}
                    footer={[
                    <Button key='cancel' type='primary' onClick={this.handleOk}>
                        Đóng
                    </Button>]}>
                    <Card>
                        <Card.Grid hoverable={false} style={GridStyle}>
                        <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.maKhachHang.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_Input_Number disabled={true} style={InputStyle} value={searchModel.maKhachHang} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.hoTen.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} disabled={true} value={searchModel.hoTen} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.soCMND.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} disabled={true} value={searchModel.soCMND} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.diaChi.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} disabled={true} value={searchModel.diaChi} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.gioiTinh.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} disabled={true}value={searchModel.gioiTinh} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.sdt.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} disabled={true} value={searchModel.sdt} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                            <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.quocTich.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} disabled={true} value={searchModel.quocTich} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.trangThai.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <TrangThai_Select style={InputStyle} disabled={true} value={searchModel.trangThai} />
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

export default KhachHang_View