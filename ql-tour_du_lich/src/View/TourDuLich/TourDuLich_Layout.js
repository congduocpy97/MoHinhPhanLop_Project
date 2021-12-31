/* eslint-disable react/jsx-pascal-case */
import { Button, Col, notification, Row, Space, Table } from "antd";
import React, { Component } from "react";
import { delete_model, getAll, search } from "../../Controller/Data_Service";
import { Model_GiaTour } from "../../Model/GiaTour";
import { Model_LoaiHinhDuLich } from "../../Model/LoaiHinhDuLich";
import { Model_ThamQuan } from "../../Model/ThamQuan";
import { Model_TourDuLich } from "../../Model/TourDuLich";
import { Model_TrangThai } from "../../Model/TrangThai";
import { HeaderString } from "../../Support/Constant";
import MasterLayout from "../Master_Layout";
import TourDuLich_Add from "./TourDuLich_Add";
import TourDuLich_Edit from "./TourDuLich_Edit";
import TourDuLich_Search from "./TourDuLich_Search";
import TourDuLich_View from "./TourDuLich_View";

const key = "list"

export class TourDuLich_Layout extends Component {
    constructor (props){
        super(props);
        const searchModel = JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich));
        this.state = {
            searchModel,
            selectedModel: [],
            dataSource:[],
            dataTrangThai: [],
            dataLoaiHinh: [],
            dataGiaTour: [],
            key: 1
        }
    }

    componentDidMount(){
        Promise.all([getAll(Model_TrangThai.table_database), getAll(Model_LoaiHinhDuLich.table_database), getAll(Model_GiaTour.table_database)]).then(result => {
            this.setState({
                dataTrangThai: result[0],
                dataLoaiHinh: result[1],
                dataGiaTour: result[2],
            })
        })
    }

    componentDidUpdate(prevProps){
        if(prevProps !== this.props){
            Promise.all([getAll(Model_TrangThai.table_database), getAll(Model_LoaiHinhDuLich.table_database), getAll(Model_GiaTour.table_database)]).then(result => {
                this.setState({
                    dataTrangThai: result[0],
                    dataLoaiHinh: result[1],
                    dataGiaTour: result[2],
                })
            })
        }
    }


    handleSearchClick = () => {
        Promise.all([search(this.state.searchModel, Model_TourDuLich.table_database)]).then(async result =>{
            const now = Date.now();
            this.setState({
                dataSource: result[0].map(m => {
                    const e = this.state.dataGiaTour.find(f => f.maTour === m.maTour && (now > new Date(f.thoiGianBatDau).getTime() && now < new Date(f.thoiGianKetThuc).getTime()));
                    return {
                        ...m,
                        key: m.maTour,
                        giaTour: e !== undefined && !!e ? e.thanhTien : "Chưa đặt giá",
                        maLoaiHinh: this.state.dataLoaiHinh.find(f => f.maLoaiHinh === m.maLoaiHinh).tenLoaiHinh,
                        trangThai: this.state.dataTrangThai.find(f => f.trangThai === m.trangThai).ten,
                    }
                })
            })
        })
    }

    handleAfterAction = () =>{
        const searchModel = JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich));
        this.setState({
            searchModel,
            selectedModel: [],
        })
        this.handleSearchClick();
    }
    
    handleChangeSearchModel = (value) => {
        this.setState({
            searchModel: value
        })
    }

    handleUnForcus = () => {
        if(this.state.key === 1){
           this.setState({
               key : 2
           })
        }else{
            this.setState({
                key : 1
            })
        }
    }

    reloadPage = () => {
        const searchModel = JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich));
        this.setState({
            searchModel,
            selectedModel: [],
        })
        this.handleUnForcus();
    }



    onChangeData = () => {
        this.setState({
            data: this.state.data + 1
        })
    }

    handleDeleteButton = () => {
        if(this.state.selectedModel.length !== 1){
            this.openNotification(this.state.selectedModel.length)
        }else{
            const tourModel = JSON.parse(JSON.stringify(Model_ThamQuan.ThamQuan));
            const thamQuanModel = JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich));
            tourModel.maTour = this.state.selectedModel[0];
            thamQuanModel.maTour = this.state.selectedModel[0];
            
            Promise.all([delete_model(thamQuanModel, Model_ThamQuan.table_database)]).then(()=>{
                Promise.all([delete_model(tourModel, Model_TourDuLich.table_database)]).then(rs =>{
                    this.openNotification(rs[0]);
                    this.handleAfterAction();
                })
            })
        }
        
    }

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
            this.handleSearchClick();
            notification.open({
                key,
                message: 'Thành công',
                description: 'Dữ liệu đã được xóa.',
            });
        }
    }

    view_diadiem = (key) => {
        const rowSelection = {
            selectedRowKeys : this.state.selectedModel,
            onChange: (selectedRowKeys) => {
                this.setState({
                    selectedModel : selectedRowKeys
                })
            },
            columnWidth: 32
        };
        const searchModel = this.state.searchModel;
        const selectedModel = this.state.selectedModel;
        let x = 0;
        Model_TourDuLich.columns.forEach(e => {
            x += e.width
        });
        return(
            <div>
                <TourDuLich_Search value={searchModel} onChange={this.handleChangeSearchModel} key={key}/>
                <Row>
                    <Col>
                        <Space>
                            <Button type="primary" onClick={this.handleSearchClick}>{HeaderString.search}</Button>
                            <TourDuLich_Add name={HeaderString.create} onAdd={this.handleAfterAction}/>
                            <TourDuLich_View name={HeaderString.view} selected={selectedModel}/>
                            <TourDuLich_Edit name={HeaderString.edit} onEdit={this.handleAfterAction} selected={selectedModel}/>
                            <Button type="primary" onClick={this.handleDeleteButton}>{HeaderString.delete}</Button>
                        </Space>
                    </Col>
                </Row>
                <Table dataSource={this.state.dataSource} columns={Model_TourDuLich.columns} bordered scroll={{ x: x }} rowSelection={rowSelection}/>
            </div>
        )
    };


    render() {
        return (
            <MasterLayout component={this.view_diadiem(this.state.key)} menu={"Dịch vụ"} name={"Tour du lịch"} subMenu={"TourDuLich"}/>
        );
    }
}

export default TourDuLich_Layout;
