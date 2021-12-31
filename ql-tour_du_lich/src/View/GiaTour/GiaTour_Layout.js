/* eslint-disable react/jsx-pascal-case */
import { Button, Col, notification, Row, Space, Table } from "antd";
import React, { Component } from "react";
import { delete_model, find_model, getAll, search } from "../../Controller/Data_Service";
import { Model_GiaTour } from "../../Model/GiaTour";
import { Model_TourDuLich } from "../../Model/TourDuLich";
import { Model_TrangThai } from "../../Model/TrangThai";
import { HeaderString } from "../../Support/Constant";
import MasterLayout from "../Master_Layout";
import GiaTour_Add from "./GiaTour_Add";
import GiaTour_Edit from "./GiaTour_Edit";
import GiaTour_Search from "./GiaTour_Search";
import GiaTour_View from "./GiaTour_View";

const key = "list"

export class GiaTour_Layout extends Component {
    constructor (props){
        super(props);
        const searchModel = JSON.parse(JSON.stringify(Model_GiaTour.GiaTour));
        this.state = {
            searchModel,
            selectedModel: [],
            dataSource:[],
            dataTrangThai: [],
            dataTour: [],
            key: 1
        }
    }

    componentDidMount(){
        Promise.all([getAll(Model_TrangThai.table_database), getAll(Model_TourDuLich.table_database)]).then(result => {
            this.setState({
                dataTrangThai: result[0],
                dataTour: result[1]
            })
        })
    }

    componentDidUpdate(prevProps){
        if(prevProps !== this.props){
            Promise.all([getAll(Model_TrangThai.table_database), getAll(Model_TourDuLich.table_database)]).then(result => {
                this.setState({
                    dataTrangThai: result[0],
                    dataTour: result[1]
                })
            })
        }
    }


    handleSearchClick = () => {
        Promise.all([search(this.state.searchModel, Model_GiaTour.table_database)]).then(async result =>{
            this.setState({
                dataSource: result[0].map(m => {
                    return {
                        ...m,
                        key: m.maGia, 
                        trangThai: this.state.dataTrangThai.find(f => f.trangThai === m.trangThai).ten,
                        maTour: this.state.dataTour.find(f => f.maTour === m.maTour).tenGoi,
                    }
                })
            })
        })
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
        const searchModel = JSON.parse(JSON.stringify(Model_GiaTour.GiaTour));
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
        console.log(this.state.data);
    }

    handleDeleteButton = () => {
        if(this.state.selectedModel.length !== 1){
            this.openNotification(this.state.selectedModel.length)
        }else{
            Promise.all([find_model(this.state.selectedModel[0],Model_GiaTour.table_database)]).then(model => {
                Promise.all([delete_model(model[0],Model_GiaTour.table_database)]).then(result => {
                    this.openNotification(result[0] === 0 ? -1 : 1);
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
                    ...this.state.selectedModel,
                    selectedModel : selectedRowKeys
                })
            },
            columnWidth: 32
        };
        const searchModel = this.state.searchModel;
        const selectedModel = this.state.selectedModel;
        let x = 0;
        Model_GiaTour.columns.forEach(e => {
            x += e.width
        });
        return(
            <div>
                <GiaTour_Search value={searchModel} onChange={this.handleChangeSearchModel} key={key}/>
                <Row>
                    <Col>
                        <Space>
                            <Button type="primary" onClick={this.handleSearchClick}>{HeaderString.search}</Button>
                            <GiaTour_Add name={HeaderString.create} onAdd={this.handleSearchClick}/>
                            <GiaTour_View name={HeaderString.view} selected={selectedModel}/>
                            <GiaTour_Edit name={HeaderString.edit} onEdit={this.handleSearchClick} selected={selectedModel}/>
                            <Button type="primary" onClick={this.handleDeleteButton}>{HeaderString.delete}</Button>
                        </Space>
                    </Col>
                </Row>
                <Table dataSource={this.state.dataSource} columns={Model_GiaTour.columns} bordered scroll={{ x: x }} rowSelection={rowSelection}/>
            </div>
        )
    };


    render() {
        return (
            <MasterLayout component={this.view_diadiem(this.state.key)} menu={"Tài chính"} name={"Giá tour"} subMenu={"GiaTour"}/>
        );
    }
}

export default GiaTour_Layout;
