/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Input, notification, Row, Table, Tabs } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { create_model, find_model, getLastIndex } from '../../Controller/Data_Service';
import { Model_DiaDiem } from '../../Model/DiaDiem';
import { Model_ThamQuan } from '../../Model/ThamQuan';
import { Model_TourDuLich } from '../../Model/TourDuLich';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import DiaDiem_Select from '../DiaDiem/DiaDiem_Select';
import LoaiHinhDuLich_Select from '../LoaiHinhDuLich/LoaiHinhDuLich_Select';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'updatable';
const { TabPane } = Tabs;

export class TourDuLich_Add extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            searchModel: JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich)),
            selectDiaDiem:[],
        }
    }

    showModal = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich)),
            isModalVisible: true
        })
    };

    handleOk = () => {
        Promise.all([create_model(this.state.searchModel, Model_TourDuLich.table_database)]).then(result => {
            Promise.all([getLastIndex(Model_TourDuLich.table_database)]).then(code =>{
                Promise.all(this.state.selectDiaDiem.map(m => create_model({maTour: code[0], maDiaDiem: m.maDiaDiem, thuTu: m.thuTu, trangThai: "01-0001"},Model_ThamQuan.table_database))).then(() => {
                    this.setState({
                        searchModel: JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich)),
                        selectDiaDiem:[],
                        isModalVisible: false,
                    });
                })
            })
        });
    };

    handleCancel = () => {
        this.setState({
            searchModel: JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich)),
            isModalVisible: false,
            selectDiaDiem:[],
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


    handleMaTourDuLichChange = e => {
        const searchModel = this.state.searchModel;
        searchModel.maTour = e;
        this.setState({
            searchModel
        })
    }

    handleTenTourDuLichChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.tenGoi =  e.target.value;
        this.setState({
            searchModel
        })
    }
    handleDacDiemChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.dacDiem =  e.target.value;
        this.setState({
            searchModel
        })
    }
    handleMaLoaiHinhChange = e => {
        const  searchModel = this.state.searchModel;
        searchModel.maLoaiHinh =  e;
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

    handleSelectDiaDiem = e => {
        if(!this.state.selectDiaDiem.map(m => m.maDiaDiem).includes(e))
            Promise.all([find_model(e, Model_DiaDiem.table_database)]).then(result => {
                const model = JSON.parse(JSON.stringify(result[0]));
                model.thuTu = this.state.selectDiaDiem.length + 1;
                model.key = model.maDiaDiem;
                this.setState({
                    selectDiaDiem: [...this.state.selectDiaDiem, model],
                })
            })
    }

    handleDeleteDiaDiem = (model, e) => {
        e.preventDefault();
        const arr = this.state.selectDiaDiem.filter(f => f.maDiaDiem !== model.maDiaDiem);
        const change = arr.map(m => { return {...m, thuTu: m.thuTu > model.thuTu ? m.thuTu - 1: m.thuTu }})
        this.setState({
            selectDiaDiem : change
        })
    }
    handleChangeThuTu = (model, e) => {
        const index = this.state.selectDiaDiem.indexOf(model);
        const arr =  this.state.selectDiaDiem
        arr[index].thuTu = e;
        this.setState({
            selectDiaDiem : arr
        });
    }

    render() {
        const searchModel = this.state.searchModel;
        return (
            <span>
                <Button type="primary" onClick={this.showModal}>
                    {this.props.name}
                </Button>
                <Modal title="Thêm tour du lịch" visible={this.state.isModalVisible} onOk={this.handleOk} onCancel={this.handleCancel} width={800}>
                    <Tabs defaultActiveKey="1">
                        <TabPane tab="Thông tin" key="1" >  
                            <Card>
                                <Card.Grid hoverable={false} style={GridStyle}>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_TourDuLich.Field.tenGoi.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <Input style={InputStyle} onChange={this.handleTenTourDuLichChange} value={searchModel.tenGoi} />
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                            <FormItem>
                                                {Model_TourDuLich.Field.dacDiem.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <Input style={InputStyle} onChange={this.handleDacDiemChange} value={searchModel.dacDiem} />
                                            </FormItem>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                {Model_TourDuLich.Field.maLoaiHinh.name}
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <LoaiHinhDuLich_Select style={InputStyle} onSelect={this.handleMaLoaiHinhChange} value={searchModel.maLoaiHinh} />
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                            <FormItem>
                                                {Model_TourDuLich.Field.trangThai.name}
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
                        <TabPane tab="Chi tiết địa điểm" key="2">
                            <Card>
                                <Card.Grid hoverable={false} style={GridStyle}>
                                    <Row>
                                        <Col span={LayoutFormat.ColLabel}>
                                            <FormItem>
                                                Chọn địa diểm
                                            </FormItem>
                                        </Col>
                                        <Col span={LayoutFormat.ColInput}>
                                            <FormItem>
                                                <DiaDiem_Select style={InputStyle} onSelect={this.handleSelectDiaDiem} />
                                            </FormItem>
                                        </Col>
                                    </Row>
                                    <Table key={this.state.update} dataSource={this.state.selectDiaDiem} columns={Model_ThamQuan.columns_tab(this.handleChangeThuTu,this.handleDeleteDiaDiem)} bordered />
                                </Card.Grid>
                            </Card>
                        </TabPane>
                    </Tabs>
                </Modal>
            </span>
        )
    }
}

export default TourDuLich_Add
