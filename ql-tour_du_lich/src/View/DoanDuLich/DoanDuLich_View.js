/* eslint-disable react/jsx-pascal-case */
// eslint-disable-next-line no-unused-vars
import { Button, Card, Col, Input, notification, Row, Table, Tabs } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import TextArea from 'antd/lib/input/TextArea';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { find_model, getAll, search } from '../../Controller/Data_Service';
import { Model_ChiTietDoan } from '../../Model/ChiTietDoan';
import { Model_DoanDuLich } from '../../Model/DoanDuLich';
import { Model_KhachHang } from '../../Model/KhachHang';
import { Model_NhanVien } from '../../Model/NhanVien';
import { Model_NoiDungTour } from '../../Model/NoiDungTour';
import { Model_PhanBo } from '../../Model/PhanBo';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_DatePicker from '../Form_Support/My_DatePicker';
import My_InputCurrency from '../Form_Support/My_InputCurrency';
import My_Input_Number from '../Form_Support/My_Input_Number';
import TourDuLich_Select from '../TourDuLich/TourDuLich_Select';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'view';
const { TabPane } = Tabs

export class DoanDuLich_View extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            selected: [],
            selectKhachHang: [],
            selectNhanVien: [],
            listKhachHang:[],
            listNhanVien: [],
            modelNoidung: JSON.parse(JSON.stringify(Model_NoiDungTour.NoiDungTour)),
            searchModel: JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich)),
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
            Promise.all([getAll(Model_KhachHang.table_database), getAll(Model_NhanVien.table_database)]).then(list => {
                this.setState({
                    listKhachHang: list[0],
                    listNhanVien: list[1],
                })
                const chitietdoanModel = JSON.parse(JSON.stringify(Model_ChiTietDoan.ChiTietDoan));
                chitietdoanModel.maDoan = this.state.selected[0];
                const phanboModel = JSON.parse(JSON.stringify(Model_PhanBo.PhanBo));
                phanboModel.maDoan = this.state.selected[0];
                Promise.all([find_model(this.state.selected[0], Model_DoanDuLich.table_database),search( chitietdoanModel, Model_ChiTietDoan.table_database),
                search(phanboModel, Model_PhanBo.table_database), find_model(this.state.selected[0], Model_NoiDungTour.table_database)]).then(result => {
                    const khachhang = result[1].map(m => {return {...m, key:m.maKhachHang, hoTen: this.state.listKhachHang.find(f => f.maKhachHang === m.maKhachHang).hoTen}})
                    const nhanvien = result[2].map(m => {return {...m, key:m.maNhanVien, tenNhanVien: this.state.listNhanVien.find(f => f.maNhanVien === m.maNhanVien).tenNhanVien}})
                    const noidung = !!result[3] ? JSON.parse(JSON.stringify(result[3])) : JSON.parse(JSON.stringify(Model_NoiDungTour.NoiDungTour));
                    this.setState({
                        searchModel: result[0],
                        selectKhachHang:khachhang,
                        selectNhanVien: nhanvien,
                        modelNoidung: noidung,
                        isModalVisible: true
                    })
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
        const  modelNoidung = this.state.modelNoidung
        return (
            <span>
                <Button type="primary" onClick={this.showModal}>
                    {this.props.name}
                </Button>
                <Modal title="Xem thông tin đoàn du lịch" visible={this.state.isModalVisible} width={800} onCancel={this.handleOk}
                    footer={[
                    <Button key='cancel' type='primary' onClick={this.handleOk}>
                        Đóng
                    </Button>]}>
                    <Tabs>
                        <TabPane tab="Thông tin" key="1">
                            <Card>
                                <Card.Grid hoverable={false} style={GridStyle}>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.maDoan.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <My_Input_Number disabled={true} style={InputStyle} value={searchModel.maDoan} />  
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.maTour.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <TourDuLich_Select style={InputStyle} disabled={true} value={searchModel.maTour} />                                        
                                            </FormItem>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.ngayKhoiHanh.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <My_DatePicker style={InputStyle} disabled={true} value={searchModel.ngayKhoiHanh} />                                  
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.ngayKetThuc.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <My_DatePicker style={InputStyle} disabled={true} value={searchModel.ngayKetThuc} />  
                                            </FormItem>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.doanhThu.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <My_InputCurrency disabled={true} style={InputStyle} value={searchModel.doanhThu} />
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.trangThai.name}
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
                        </TabPane>
                        <TabPane tab="Chi tiết hành khách" key="2">
                            <Card>
                                <Card.Grid hoverable={false} style={GridStyle}>
                                    <Table dataSource={this.state.selectKhachHang} columns={Model_KhachHang.columns_tab_view()} bordered />
                                </Card.Grid>
                            </Card>
                        </TabPane>
                        <TabPane tab="Nhân viên phân bố" key="3">
                            <Card>
                                <Card.Grid hoverable={false} style={GridStyle}>
                                    <Table dataSource={this.state.selectNhanVien} columns={Model_NhanVien.columns_tab_view()} bordered />
                                </Card.Grid>
                            </Card>
                        </TabPane>
                        <TabPane tab="Nội dung" key="4">
                            <Card>
                                <Card.Grid hoverable={false} style={GridStyle}>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_NoiDungTour.Field.khachSan.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                            <Input style={InputStyle} disabled={true} value={modelNoidung.khachSan}/>
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                            <FormItem>
                                                {Model_NoiDungTour.Field.diaDiemThamQuan.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                <Input style={InputStyle} disabled={true} value={modelNoidung.diaDiemThamQuan}/>
                                            </FormItem>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_NoiDungTour.Field.hanhTrinh.name}
                                            </FormItem>
                                        </Col>
                                        <TextArea rows={3} style={InputStyle} disabled={true} value={modelNoidung.hanhTrinh}/>
                                    </Row>
                                </Card.Grid>
                            </Card>
                        </TabPane>
                    </Tabs>
                </Modal>
            </span>
        )
    }
}

export default DoanDuLich_View