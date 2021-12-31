/* eslint-disable react/jsx-pascal-case */
import { Button, Col, notification, Row, Space, Table } from "antd";
import React, { Component } from "react";
import { delete_model, getAll, search } from "../../Controller/Data_Service";
import { Model_DoanDuLich } from "../../Model/DoanDuLich";
import { Model_TrangThai } from "../../Model/TrangThai";
import { HeaderString } from "../../Support/Constant";
import MasterLayout from "../Master_Layout";
import DoanDuLich_Add from "./DoanDuLich_Add";
import DoanDuLich_Edit from "./DoanDuLich_Edit";
import DoanDuLich_Search from "./DoanDuLich_Search";
import DoanDuLich_View from "./DoanDuLich_View";
import { Model_TourDuLich } from "../../Model/TourDuLich";
import { Model_PhanBo } from "../../Model/PhanBo";
import { Model_ChiTietDoan } from "../../Model/ChiTietDoan";
import { Model_NoiDungTour } from "../../Model/NoiDungTour";

const key = "list"

export class DoanDuLich_Layout extends Component {
    constructor (props){
        super(props);
        const searchModel = JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich));
        this.state = {
            searchModel,
            selectedModel: [],
            dataSource:[],
            dataTrangThai: [],
            count: 0,
            dataTour: [],
            key: 1
        }
    }

    componentDidMount(){
        Promise.all([getAll(Model_TrangThai.table_database), getAll(Model_TourDuLich.table_database)]).then(result => {
            this.setState({
                dataTrangThai: result[0],
                dataTour: result[1],
            })
        })
    }

    componentDidUpdate(prevProps){
        if(prevProps !== this.props){
            Promise.all([getAll(Model_TrangThai.table_database), getAll(Model_TourDuLich.table_database)]).then(result => {
                this.setState({
                    dataTrangThai: result[0],
                    dataTour: result[1],
                })
            })
        }
    }


    handleSearchClick = () => {
        Promise.all([search(this.state.searchModel, Model_DoanDuLich.table_database)]).then(async result =>{
            this.setState({
                dataSource: result[0].map(m => {
                    
                    return {
                        ...m,
                        key: m.maDoan,
                        maTour: this.state.dataTour.find(f => f.maTour === m.maTour).tenGoi,
                        trangThai: this.state.dataTrangThai.find(f => f.trangThai === m.trangThai).ten,
                    }
                })
            })
        })
    }

    handleAfterAction = () =>{
        const searchModel = JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich));
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
        const searchModel = JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich));
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
            const deleteModel = JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich));
            const modelPhanBo = JSON.parse(JSON.stringify(Model_PhanBo.PhanBo));
            const modelChiTiet =JSON.parse(JSON.stringify(Model_ChiTietDoan.ChiTietDoan));
            const modelNoiDung =JSON.parse(JSON.stringify(Model_NoiDungTour.NoiDungTour));
            deleteModel.maDoan = this.state.selectedModel[0];
            modelPhanBo.maDoan = this.state.selectedModel[0];
            modelChiTiet.maDoan = this.state.selectedModel[0];
            modelNoiDung.maDoan = this.state.selectedModel[0];
            Promise.all([delete_model(modelChiTiet, Model_ChiTietDoan.table_database)]).then(()=>{
                Promise.all([delete_model(modelPhanBo, Model_PhanBo.table_database)]).then(()=>{
                    Promise.all([delete_model(modelNoiDung, Model_NoiDungTour.table_database)]).then(()=>{
                        Promise.all([delete_model(deleteModel, Model_DoanDuLich.table_database)]).then(rs =>{
                            this.openNotification(rs[0]);
                            this.handleAfterAction();
                        })
                    })
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
        Model_DoanDuLich.columns.forEach(e => {
            x += e.width
        });
        return(
            <div>
                <DoanDuLich_Search value={searchModel} onChange={this.handleChangeSearchModel} key={key}/>
                <Row>
                    <Col>
                        <Space>
                            <Button type="primary" onClick={this.handleSearchClick}>{HeaderString.search}</Button>
                            <DoanDuLich_Add name={HeaderString.create} onAdd={this.handleAfterAction}/>
                            <DoanDuLich_View name={HeaderString.view} selected={selectedModel}/>
                            <DoanDuLich_Edit name={HeaderString.edit} onEdit={this.handleAfterAction} selected={selectedModel}/>
                            <Button type="primary" onClick={this.handleDeleteButton}>{HeaderString.delete}</Button>
                        </Space>
                    </Col>
                </Row>
                <Table dataSource={this.state.dataSource} columns={Model_DoanDuLich.columns} bordered scroll={{ x: x }} rowSelection={rowSelection}/>
            </div>
        )
    };


    render() {
        return (
            <MasterLayout component={this.view_diadiem(this.state.key)} menu={"Dịch vụ"} name={"Đoàn du lịch"} subMenu={"DoanDuLich"}/>
        );
    }
}

export default DoanDuLich_Layout;
