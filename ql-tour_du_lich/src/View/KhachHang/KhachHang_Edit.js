/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Input, notification, Row } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { find_model, update_model } from '../../Controller/Data_Service';
import { Model_KhachHang } from '../../Model/KhachHang';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_Input_Number from '../Form_Support/My_Input_Number';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'edit';

export class KhachHang_Edit extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            selected: [],
            preModel: JSON.parse(JSON.stringify(Model_KhachHang.KhachHang)),
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
            Promise.all([update_model([this.state.searchModel, this.state.preModel], Model_KhachHang.table_database)]).then(result => {
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
                <Modal title="Chỉnh sửa khách hàng" visible={this.state.isModalVisible} width={800} onCancel={this.handleCancel} onOk={this.handleOk}>
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
                                        <Input style={InputStyle} onChange={this.handleTenChange} value={searchModel.hoTen} />
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
                                        <Input style={InputStyle} onChange={this.handleSoCMNDChange} value={searchModel.soCMND} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.diaChi.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleDiaChiChange} value={searchModel.diaChi} />
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
                                        <Input style={InputStyle} onChange={this.handleGioiTinhChange} value={searchModel.gioiTinh} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.sdt.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleSDTChange} value={searchModel.sdt} />
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
                                        <Input style={InputStyle} onChange={this.handleQuocTichChange} value={searchModel.quocTich} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
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

export default KhachHang_Edit