/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Input, notification, Row, Table, Tabs } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { create_model, delete_model, find_model, getAll, search, update_model } from '../../Controller/Data_Service';
import { Model_DiaDiem } from '../../Model/DiaDiem';
import { Model_ThamQuan } from '../../Model/ThamQuan';
import { Model_TourDuLich } from '../../Model/TourDuLich';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import DiaDiem_Select from '../DiaDiem/DiaDiem_Select';
import My_Input_Number from '../Form_Support/My_Input_Number';
import LoaiHinhDuLich_Select from '../LoaiHinhDuLich/LoaiHinhDuLich_Select';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'edit';
const { TabPane } = Tabs;

export class TourDuLich_Edit extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            selected: [],
            selectDiaDiem: [],
            dataDiaDiem: [],
            listDiaDiem: [],
            preModel: JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich)),
            searchModel: JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich)),
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
            Promise.all([getAll(Model_DiaDiem.table_database)]).then(data => {
                this.setState({
                    listDiaDiem: data[0]
                });
                const searchThamQuan = JSON.parse(JSON.stringify(Model_ThamQuan.ThamQuan));
                searchThamQuan.maTour = this.state.selected[0];
                Promise.all([find_model(this.state.selected[0], Model_TourDuLich.table_database), search(searchThamQuan, Model_ThamQuan.table_database)]).then(result => {
                    const thamquan = result[1].map(m => {return {...m, key: `${m.maDiaDiem}` + m.thuTu, ten: this.state.listDiaDiem.find(f => f.maDiaDiem === m.maDiaDiem).ten }});
                    this.setState({
                        searchModel: result[0],
                        preModel: JSON.parse(JSON.stringify(result[0])),
                        dataDiaDiem: JSON.parse(JSON.stringify(thamquan)),
                        selectDiaDiem: JSON.parse(JSON.stringify(thamquan)),
                        isModalVisible: true
                    })
                })
            });
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

    updateElement = () => {
        const arrBef = [...new Set(this.state.dataDiaDiem.map(m => {return { maTour: this.state.selected[0], maDiaDiem: m.maDiaDiem, thuTu: m.thuTu, trangThai: "01-0001"}}))];
        const arrAft = [...new Set(this.state.selectDiaDiem.map(m => {return { maTour: this.state.selected[0], maDiaDiem: m.maDiaDiem, thuTu: m.thuTu, trangThai: "01-0001"}}))];
        if(JSON.stringify(arrBef) !== JSON.stringify(arrAft)){
            Promise.all(arrBef.map(m => delete_model(m, Model_ThamQuan.table_database))).then(()=>{
                Promise.all(arrAft.map(m => create_model(m, Model_ThamQuan.table_database))).then((result)=>{
                    this.openNotification(result[0] === 0 ? -1 : result[0]);
                    this.setState({
                        isModalVisible: false
                    })
                })
            })
        }
    }
    

    handleOk = () => {
        if(JSON.stringify(this.state.preModel) !== JSON.stringify(this.state.searchModel)){
            Promise.all([update_model([this.state.searchModel, this.state.preModel], Model_TourDuLich.table_database)]).then(result => {
                this.updateElement();
            });
        }
        else{
            this.updateElement();
        }

    };

    handleCancel = () => {
        this.setState({
            isModalVisible: false
        })
    }

    handleTenChange = (e) => {
        const searchModel = this.state.searchModel;
        searchModel.tenGoi = e.target.value;
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
    handleTrangThaiChange = (e) => {
        const searchModel = this.state.searchModel;
        searchModel.trangThai = e;
        this.setState({
            searchModel
        })
    }

    handleSelectDiaDiem = e => {
        Promise.all([find_model(e, Model_DiaDiem.table_database)]).then(result => {
            const model = JSON.parse(JSON.stringify(result[0]));
            model.thuTu = this.state.selectDiaDiem.length + 1;
            model.key = (`${model.maDiaDiem}` + model.thuTu);
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
                <Modal title="Chỉnh sửa tour du lịch" visible={this.state.isModalVisible} width={800} onCancel={this.handleCancel} onOk={this.handleOk}>
                <Tabs defaultActiveKey="1">
                    <TabPane tab="Thông tin" key="1" > 
                    <Card>
                        <Card.Grid hoverable={false} style={GridStyle}>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_TourDuLich.Field.maTour.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_Input_Number disabled={true} style={InputStyle} value={searchModel.maTour} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_TourDuLich.Field.tenGoi.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleTenChange} value={searchModel.tenGoi} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_TourDuLich.Field.dacDiem.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleDacDiemChange} value={searchModel.dacDiem} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_TourDuLich.Field.maLoaiHinh.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <LoaiHinhDuLich_Select style={InputStyle} onSelect={this.handleMaLoaiHinhChange} value={searchModel.maLoaiHinh} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
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

export default TourDuLich_Edit