/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Input, notification, Row, Table, Tabs } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import TextArea from 'antd/lib/input/TextArea';
import Modal from 'antd/lib/modal/Modal'
import moment from 'moment';
import React, { Component } from 'react'
import { create_model, delete_model, find_model, getAll, search, update_model } from '../../Controller/Data_Service';
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
import My_Input_Number from '../Form_Support/My_Input_Number';
import KhachHang_Select from '../KhachHang/KhachHang_Select';
import NhanVien_Select from '../NhanVien/NhanVien_Select';
import TourDuLich_Select from '../TourDuLich/TourDuLich_Select';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'edit';
const { TabPane } = Tabs;

export class DoanDuLich_Edit extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            selected: [],
            selectKhachHang: [],
            selectNhanVien: [],
            dataKhachHang: [],
            dataNhanVien: [],
            listKhachHang: [],
            listNhanVien: [],
            preModel: JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich)),
            searchModel: JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich)),
            preNoiDung: JSON.parse(JSON.stringify(Model_NoiDungTour.NoiDungTour)),
            modelNoidung: JSON.parse(JSON.stringify(Model_NoiDungTour.NoiDungTour)),
            thanhTien: 0,
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
                    const model = {...result[0], ngayKhoiHanh: moment(result[0].ngayKhoiHanh), ngayKetThuc: moment(result[0].ngayKetThuc)};
                    const khachhang = result[1].map(m => {return {...m, key:m.maKhachHang, hoTen: this.state.listKhachHang.find(f => f.maKhachHang === m.maKhachHang).hoTen}})
                    const nhanvien = result[2].map(m => {return {...m, key:m.maNhanVien, tenNhanVien: this.state.listNhanVien.find(f => f.maNhanVien === m.maNhanVien).tenNhanVien}})
                    Promise.all([getGiaTour(result[0].maTour, moment(result[0].ngayKhoiHanh))]).then(gia => {

                        this.setState({
                            searchModel: model,
                            preModel: JSON.parse(JSON.stringify(result[0])),
                            selectKhachHang: JSON.parse(JSON.stringify(khachhang)),
                            dataKhachHang: JSON.parse(JSON.stringify(khachhang)),
                            selectNhanVien: JSON.parse(JSON.stringify(nhanvien)),
                            dataNhanVien: JSON.parse(JSON.stringify(nhanvien)),
                            preNoiDung: !!result[3] ? JSON.parse(JSON.stringify(result[3])) : JSON.parse(JSON.stringify(Model_NoiDungTour.NoiDungTour)),
                            modelNoidung: !!result[3] ? JSON.parse(JSON.stringify(result[3])) : JSON.parse(JSON.stringify(Model_NoiDungTour.NoiDungTour)),
                            isModalVisible: true,
                            thanhTien: gia[0],
                        })
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
            notification.open({
                key,
                message: 'Thành công',
                description: 'Dữ liệu đã được chỉnh sửa.',
            });
        }
        this.props.onEdit();
    }

    updateElement = (result) =>{
        const listSelectIdKhachHang = [...new Set(this.state.selectKhachHang.map(m => m.maKhachHang))];
        const listPreSelectIdKhachHang = [...new Set(this.state.dataKhachHang.map(m => m.maKhachHang))];
        const listDeleteKhachHang = this.state.listKhachHang.filter(f => !listSelectIdKhachHang.includes(f.maKhachHang));
        const listCreateKhachHang = listSelectIdKhachHang.filter(f => !listPreSelectIdKhachHang.includes(f));


        Promise.all(listDeleteKhachHang.map(m => delete_model({maDoan: this.state.preModel.maDoan, maKhachHang: m.maKhachHang}, Model_ChiTietDoan.table_database))).then( () => {
            Promise.all(listCreateKhachHang.map(m => create_model({maDoan: this.state.preModel.maDoan, maKhachHang: m}, Model_ChiTietDoan.table_database))).then(() =>{
                if(JSON.stringify(this.state.selectNhanVien) !== JSON.stringify(this.state.dataNhanVien)){
                    Promise.all(this.state.dataNhanVien.map(m => delete_model({maDoan: this.state.preModel.maDoan, maNhanVien: m.maNhanVien}, Model_PhanBo.table_database))).then(() =>{
                        Promise.all(this.state.selectNhanVien.map(m => create_model({maDoan: this.state.preModel.maDoan, maNhanVien: m.maNhanVien, nhiemVu: m.nhiemVu}, Model_PhanBo.table_database))).then(()=>{
                            this.openNotification(result[0] === 0 ? -1 : result[0]);
                            this.setState({
                                isModalVisible: false
                            })
                        })
                    })
                }else{
                    this.openNotification(result[0] === 0 ? -1 : result[0]);
                    this.setState({
                        isModalVisible: false
                    })
                    
                }
            })
        })
    }

    updateNoiDung = () => {
        if(this.state.preNoiDung.maDoan === -1){
            const model = this.state.modelNoidung;
            model.maDoan = this.state.preModel.maDoan;
            Promise.all([create_model(model, Model_NoiDungTour.table_database)]).then(()=>{
                this.updateElement([1]);
            })
        }else if(JSON.stringify(this.state.preNoiDung) !== JSON.stringify(this.state.modelNoidung)){
            Promise.all([update_model([this.state.modelNoidung, this.state.preNoiDung], Model_NoiDungTour.table_database)]).then(()=>{
                this.updateElement([1]);
            })
        }
       
    }


    handleOk = () => {
        if(JSON.stringify(this.state.preModel) !== JSON.stringify(this.state.searchModel)){
            Promise.all([update_model([this.state.searchModel, this.state.preModel], Model_DoanDuLich.table_database)]).then(result => {
                if(result[0] !== 0 ){
                    this.updateNoiDung();
                }else{
                    this.openNotification(result[0] === 0 ? -1 : result[0]);
                    this.setState({
                        isModalVisible: false
                    })
                }
            });
        }
        else{
            this.updateNoiDung();
        }
    };

    handleCancel = () => {
        this.setState({
            isModalVisible: false
        })
    }

    handleSelectKhachHang = (e) => {
        if(!this.state.selectKhachHang.map(m => m.maKhachHang).includes(e))
            Promise.all([find_model(e, Model_KhachHang.table_database)]).then(result => {
                const searchModel = this.state.searchModel;
                const model = {...result[0], key: result[0].maKhachHang};
                this.setState({
                    selectKhachHang: [...this.state.selectKhachHang, model],
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
                const model = {...result[0], key: result[0].maNhanVien};
                this.setState({
                    selectNhanVien: [...this.state.selectNhanVien, model],
                })
            })
    }

    handleDeleteKhachHang = (key, e) => {
        e.preventDefault();
        const searchModel = this.state.searchModel;
        const arr = this.state.selectKhachHang.filter(f => f.maKhachHang !== key);
        this.setState({
            selectKhachHang : arr
        })
        searchModel.doanhThu = this.state.thanhTien * arr.length;
        this.setState({
            searchModel,
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
        console.log(searchModel.ngayKhoiHanh);
        Promise.all([getGiaTour(e, !!searchModel.ngayKhoiHanh ? searchModel.ngayKhoiHanh : moment())]).then(result => {
            console.log(result)
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
        console.log(this.state.modelNoidung)
    }



    render() {
        const searchModel = this.state.searchModel;
        const modelNoidung = this.state.modelNoidung;
        return (
            <span>
                <Button type="primary" onClick={this.showModal}>
                    {this.props.name}
                </Button>
                <Modal title="Chỉnh sửa đoàn du lịch" visible={this.state.isModalVisible} width={800} onCancel={this.handleCancel} onOk={this.handleOk}>
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
                                                <TourDuLich_Select style={InputStyle} onSelect={this.handleMaTourChange} value={searchModel.maTour} />                                        
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
                                                <My_DatePicker style={InputStyle} onChange={this.handleNgayKhoiHanhChange} value={searchModel.ngayKhoiHanh} />                                  
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                            <FormItem>
                                                {Model_DoanDuLich.Field.ngayKetThuc.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <My_DatePicker style={InputStyle} onChange={this.handleNgayKetThucChange} value={searchModel.ngayKetThuc} />  
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
                                                <My_InputCurrency disabled={true} style={InputStyle} onChange={this.handleDoanhThuChange} value={searchModel.doanhThu} />
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
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

export default DoanDuLich_Edit