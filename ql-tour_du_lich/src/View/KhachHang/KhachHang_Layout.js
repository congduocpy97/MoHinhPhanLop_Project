/* eslint-disable react/jsx-pascal-case */
import { Button, Col, notification, Row, Space, Table } from "antd";
import React, { Component } from "react";
import { delete_model, find_model, getAll, search } from "../../Controller/Data_Service";
import { Model_KhachHang } from "../../Model/KhachHang";
import { Model_TrangThai } from "../../Model/TrangThai";
import { HeaderString } from "../../Support/Constant";
import MasterLayout from "../Master_Layout";
import KhachHang_Add from "./KhachHang_Add";
import KhachHang_Edit from "./KhachHang_Edit";
import KhachHang_Search from "./KhachHang_Search";
import KhachHang_View from "./KhachHang_View";

const key = "list"

export class KhachHang_Layout extends Component {
    constructor (props){
        super(props);
        const searchModel = JSON.parse(JSON.stringify(Model_KhachHang.KhachHang));
        this.state = {
            searchModel,
            selectedModel: [],
            dataSource:[],
            dataTrangThai: [],
            key: 1
        }
    }

    componentDidMount(){
        Promise.all([getAll(Model_TrangThai.table_database)]).then(result => {
            this.setState({
                dataTrangThai: result[0]
            })
        })
    }

    componentDidUpdate(prevProps){
        if(prevProps !== this.props){
            Promise.all([getAll(Model_TrangThai.table_database)]).then(result => {
                this.setState({
                    dataTrangThai: result[0]
                })
            })
        }
    }


    handleSearchClick = () => {
        Promise.all([search(this.state.searchModel, Model_KhachHang.table_database)]).then(async result =>{
            this.setState({
                dataSource: result[0].map(m => {
                    return {
                        ...m,
                        key: m.maKhachHang, 
                        trangThai: this.state.dataTrangThai.find(f => f.trangThai === m.trangThai).ten,
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
        const searchModel = JSON.parse(JSON.stringify(Model_KhachHang.KhachHang));
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
            Promise.all([find_model(this.state.selectedModel[0],Model_KhachHang.table_database)]).then(model => {
                Promise.all([delete_model(model[0],Model_KhachHang.table_database)]).then(result => {
                    this.openNotification(result[0] === 0 ? -1 : 1);
                })
            })
        }
        
    }

    openNotification = (value) => {
        if(value === 0){
            notification.open({
            key,
            message: 'Th????t ba??i',
            description: 'Vui lo??ng cho??n d???? li????u.',
            });
        }else if (value > 1){
            notification.open({
                key,
                message: 'Th????t ba??i',
                description: 'Cho??n chi?? 1 do??ng d???? li????u.',
            });
        }else if (value === -1){
            notification.open({
                key,
                message: 'Th????t ba??i',
                description: 'Kh??ng th???? l??u d???? li????u.',
            });
        }else{
            this.handleSearchClick();
            notification.open({
                key,
                message: 'Tha??nh c??ng',
                description: 'D???? li????u ??a?? ????????c xo??a.',
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
        Model_KhachHang.columns.forEach(e => {
            x += e.width
        });
        return(
            <div>
                <KhachHang_Search value={searchModel} onChange={this.handleChangeSearchModel} key={key}/>
                <Row>
                    <Col>
                        <Space>
                            <Button type="primary" onClick={this.handleSearchClick}>{HeaderString.search}</Button>
                            <KhachHang_Add name={HeaderString.create} onAdd={this.handleSearchClick}/>
                            <KhachHang_View name={HeaderString.view} selected={selectedModel}/>
                            <KhachHang_Edit name={HeaderString.edit} onEdit={this.handleSearchClick} selected={selectedModel}/>
                            <Button type="primary" onClick={this.handleDeleteButton}>{HeaderString.delete}</Button>
                        </Space>
                    </Col>
                </Row>
                <Table dataSource={this.state.dataSource} columns={Model_KhachHang.columns} bordered scroll={{ x: x }} rowSelection={rowSelection}/>
            </div>
        )
    };


    render() {
        return (
            <MasterLayout component={this.view_diadiem(this.state.key)}  menu={"Kha??ch ha??ng"} name={"Kha??ch"} subMenu={"KhachHang"}/>
        );
    }
}

export default KhachHang_Layout;
