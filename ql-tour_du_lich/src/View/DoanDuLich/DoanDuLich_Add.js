/* eslint-disable react/jsx-no-duplicate-props */
/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Input, notification, Row, Table, Tabs } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import moment from 'moment';
import React, { Component } from 'react'
import { create_model, delete_model, find_model, getLastIndex } from '../../Controller/Data_Service';
import { getGiaTour } from '../../Controller/TourDuLich_Service';
import { Model_ChiTietDoan } from '../../Model/ChiTietDoan';
import { Model_DoanDuLich } from '../../Model/DoanDuLich';
import { Model_KhachHang } from '../../Model/KhachHang';
import { Model_NhanVien } from '../../Model/NhanVien';
import { Model_NoiDungTour } from '../../Model/NoiDungTour';
import { Model_PhanBo } from '../../Model/PhanBo';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_DatePicker from '../Form_Support/My_DatePicker';
import My_InputCurrency from '../Form_Support/My_InputCurrency';
import KhachHang_Select from '../KhachHang/KhachHang_Select';
import NhanVien_Select from '../NhanVien/NhanVien_Select';
import TourDuLich_Select from '../TourDuLich/TourDuLich_Select';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'updatable';
const { TabPane } = Tabs;
const { TextArea } = Input

export class DoanDuLich_Add extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            searchModel: JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich)),
            selectKhachHang: [],
            selectNhanVien: [],
            modelNoidung: JSON.parse(JSON.stringify(Model_NoiDungTour.NoiDungTour)),
            thanhTien: 0,
        }
    }

    showModal = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich)),
            modelNoidung: JSON.parse(JSON.stringify(Model_NoiDungTour.NoiDungTour)),
            isModalVisible: true
        })
    };

    handleOk = () => {
        let flag = 1;
        Promise.all([create_model(this.state.searchModel, Model_DoanDuLich.table_database)]).then(result => {
            if(result[0] !== 0){
                flag = result[0];
                Promise.all([getLastIndex(Model_DoanDuLich.table_database)]).then(code => {
                    if(code[0] !== 0){
                        Promise.all(this.state.selectKhachHang.map(e => create_model({maDoan: code[0], maKhachHang: e.maKhachHang}, Model_ChiTietDoan.table_database))).then(khachhang => {
                            khachhang.forEach(kh => {
                                if(kh === 0){
                                    Promise.all([delete_model({maDoan: code[0], maNhanVien: -1, nhiemVu: ""},Model_PhanBo.table_database)]);
                                    Promise.all([delete_model({maDoan: code[0], maKhachHang: -1},Model_ChiTietDoan.table_database)]);
                                    const model = JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich));
                                    model.maTour = code[0];
                                    Promise.all([delete_model(model, Model_DoanDuLich.table_database)]);
                                    flag = 0;
                                }
                            })
                            Promise.all(this.state.selectNhanVien.map(e => create_model({maDoan: code[0], maNhanVien: e.maNhanVien, nhiemVu: e.nhiemVu}, Model_PhanBo.table_database))).then(nhanvien => {
                                nhanvien.forEach(kh => {
                                    if(kh === 0){
                                        Promise.all([delete_model({maDoan: code[0], maNhanVien: -1, nhiemVu: ""},Model_PhanBo.table_database)]);
                                        Promise.all([delete_model({maDoan: code[0], maKhachHang: -1},Model_ChiTietDoan.table_database)]);
                                        const model = JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich));
                                        model.maTour = code[0];
                                        Promise.all([delete_model(model,  Model_DoanDuLich.table_database)]);
                                        flag = 0;
                                    }
                                });
                                const noidung = JSON.parse(JSON.stringify(this.state.modelNoidung));
                                noidung.maDoan = code[0];
                                Promise.all([create_model(noidung, Model_NoiDungTour.table_database)]).then(()=>{
                                    this.openNotification(flag);
                                    this.props.onAdd();
                                    this.setState({
                                        isModalVisible: false,
                                    })
                                })

                            })
                        });
                    }else{
                        this.openNotification(flag);
                        this.props.onAdd();
                    }
                        
                })
            }else{
                this.openNotification(0);
                this.props.onAdd();
            }
        })
    };

    handleCancel = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich)),
            modelNoidung: JSON.parse(JSON.stringify(Model_NoiDungTour.NoiDungTour)),
            isModalVisible: false,
            selectKhachHang:[],
            selectNhanVien:[],
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
        this.props.onAdd();
    }

    handleSelectKhachHang = (e) => {
        if(!this.state.selectKhachHang.map(m => m.maKhachHang).includes(e))
            Promise.all([find_model(e, Model_KhachHang.table_database)]).then(result => {
                const searchModel = this.state.searchModel;
                this.setState({
                    selectKhachHang: [...this.state.selectKhachHang, result[0]],
                })
                searchModel.doanhThu = this.state.thanhTien * this.state.selectKhachHang.length;
                this.setState({
                    searchModel,
                })
            })
    }
    handleSelectNhanVien = (e) => {
        if(!this.state.selectNhanVien.map(m => m.maNhanVien).includes(e))
            Promise.all([find_model(e, Model_NhanVien.table_database)]).then(result => {
                this.setState({
                    selectNhanVien: [...this.state.selectNhanVien, result[0]],
                })
            })
    }

    handleDeleteKhachHang = (key, e) => {
        e.preventDefault();
        const arr = this.state.selectKhachHang.filter(f => f.maKhachHang !== key);
        this.setState({
            selectKhachHang : arr
        })
    }

    handleDeleteNhanVien = (key, e) => {
        e.preventDefault();
        const arr = this.state.selectNhanVien.filter(f => f.maNhanVien !== key);
        this.setState({
            selectNhanVien : arr
        })
    }
    handleChangeTextNhanVien = (key, e) => {
        const index = this.state.selectNhanVien.indexOf(key);
        const arr =  this.state.selectNhanVien
        arr[index].nhiemVu = e.target.value;
        this.setState({
            selectNhanVien : arr
        });
        
    }


    handleMaTourChange = e => {
        const searchModel = this.state.searchModel;
        searchModel.maTour = e;
        Promise.all([getGiaTour(e, !!searchModel.ngayKhoiHanh ? searchModel.ngayKhoiHanh : moment())]).then(result => {
            const thanhTien = result[0] !== undefined ? result[0] : 0;
            searchModel.doanhThu = thanhTien * this.state.selectKhachHang.length;
            this.setState({
                searchModel,
                thanhTien,
            })
        })
    }

    handleNgayKhoiHanhChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.ngayKhoiHanh =  e;
        Promise.all([getGiaTour(searchModel.maTour, e)]).then(result => {
            const thanhTien = result[0] !== undefined ? result[0] : 0;
            searchModel.doanhThu = thanhTien * this.state.selectKhachHang.length;
            this.setState({
                searchModel,
                thanhTien,
            })
        })
    }
    handleNgayKetThucChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.ngayKetThuc = e;
        this.setState({
            searchModel
        })
    }
    handleDoanhThuChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.doanhThu =  e;
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
    handleHanhTrinhChange = e => {
        
        const  modelNoidung = this.state.modelNoidung;
        modelNoidung.hanhTrinh =  e.target.value;
        this.setState({
            modelNoidung
        })
    }
    handleKhachSanChange = e => {
        
        const  modelNoidung = this.state.modelNoidung;
        modelNoidung.khachSan =  e.target.value;
        this.setState({
            modelNoidung
        })
    }
    handleDiaDiemThamQuanChange = e => {
        
        const  modelNoidung = this.state.modelNoidung;
        modelNoidung.diaDiemThamQuan =  e.target.value;
        this.setState({
            modelNoidung
        })
    }

    render() {

        const searchModel = this.state.searchModel;
        const modelNoidung = this.state.modelNoidung;

        return (
            <span>
                <Button type="primary" onClick={this.showModal}>
                    {this.props.name}
                </Button>
                <Modal title="Thêm loại đoàn du lịch" visible={this.state.isModalVisible} onOk={this.handleOk} onCancel={this.handleCancel} width={800}>
                    <Tabs defaultActiveKey="1">
                        <TabPane tab="Thông tin" key="1" >  
                            <Card>
                                <Card.Grid hoverable={false} style={GridStyle}>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.maTour.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <TourDuLich_Select style={InputStyle} onSelect={this.handleMaTourChange} value={searchModel.maTour} />
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.ngayKhoiHanh.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <My_DatePicker style={InputStyle} onChange={this.handleNgayKhoiHanhChange} value={searchModel.ngayKhoiHanh} />
                                            </FormItem>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.ngayKetThuc.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <My_DatePicker style={InputStyle} onChange={this.handleNgayKetThucChange} value={searchModel.ngayKetThuc} />                                    </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.doanhThu.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <My_InputCurrency disabled={true} style={InputStyle} onChange={this.handleDoanhThuChange} value={searchModel.doanhThu} />
                                            </FormItem>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.trangThai.name}
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
                        </TabPane>
                        <TabPane tab="Chi tiết hành khách" key="2">
                            <Card>
                                <Card.Grid hoverable={false} style={GridStyle}>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                Chọn khách hàng
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                            <KhachHang_Select style={InputStyle} onSelect={this.handleSelectKhachHang} />
                                            </FormItem>
                                        </Col>
                                    </Row>
                                    <Table dataSource={this.state.selectKhachHang} columns={Model_KhachHang.columns_tab(this.handleDeleteKhachHang)} bordered />
                                </Card.Grid>
                            </Card>
                        </TabPane>
                        <TabPane tab="Nhân viên phân bố" key="3">
                            <Card>
                                <Card.Grid hoverable={false} style={GridStyle}>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                Chọn nhân viên
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <NhanVien_Select style={InputStyle} onSelect={this.handleSelectNhanVien} />
                                            </FormItem>
                                        </Col>
                                    </Row>
                                    <Table dataSource={this.state.selectNhanVien} columns={Model_NhanVien.columns_tab(this.handleChangeTextNhanVien,this.handleDeleteNhanVien)} bordered />
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
                                            <Input style={InputStyle} onChange={this.handleKhachSanChange} value={modelNoidung.khachSan}/>
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                            <FormItem>
                                                {Model_NoiDungTour.Field.diaDiemThamQuan.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                <Input style={InputStyle} onChange={this.handleDiaDiemThamQuanChange} value={modelNoidung.diaDiemThamQuan}/>
                                            </FormItem>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_NoiDungTour.Field.hanhTrinh.name}
                                            </FormItem>
                                        </Col>
                                        <TextArea rows={3} style={InputStyle} onChange={this.handleHanhTrinhChange} value={modelNoidung.hanhTrinh}/>
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

export default DoanDuLich_Add
