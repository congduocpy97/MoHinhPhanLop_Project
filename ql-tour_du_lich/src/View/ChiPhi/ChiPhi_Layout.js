/* eslint-disable react/jsx-pascal-case */
import { Button, Col, notification, Row, Space, Table } from "antd";
import React, { Component } from "react";
import { delete_model, find_model, getAll, search } from "../../Controller/Data_Service";
import { Model_ChiPhi } from "../../Model/ChiPhi";
import { Model_LoaiChiPhi } from "../../Model/LoaiChiPhi";
import { HeaderString } from "../../Support/Constant";
import MasterLayout from "../Master_Layout";
import ChiPhi_Add from "./ChiPhi_Add";
import ChiPhi_Edit from "./ChiPhi_Edit";
import ChiPhi_Search from "./ChiPhi_Search";
import ChiPhi_View from "./ChiPhi_View";

const key = "list"

export class ChiPhi_Layout extends Component {
    constructor (props){
        super(props);
        const searchModel = JSON.parse(JSON.stringify(Model_ChiPhi.ChiPhi));
        this.state = {
            searchModel,
            selectedModel: [],
            dataSource:[],
            dataLoai: [],
            key: 1
        }
    }

    handleSearchClick = () => {
        Promise.all([search(this.state.searchModel, Model_ChiPhi.table_database)]).then(async result =>{
            this.setState({
                dataSource: result[0].map(m => {
                    return {
                        ...m,
                        key: m.maChiPhi,
                        maLoaiChiPhi: this.state.dataLoai.find(f => m.maLoaiChiPhi === f.maLoaiChiPhi).tenLoaiChiPhi,
                    }
                })
            })
        })
    }
    componentDidMount(){
        Promise.all([getAll(Model_LoaiChiPhi.table_database)]).then(result => {
            this.setState({
                dataLoai: result[0],
            })
        })
    }

    componentDidUpdate(prevProps){
        if(prevProps !== this.props){
            Promise.all([getAll(Model_LoaiChiPhi.table_database)]).then(result => {
                this.setState({
                    dataLoai: result[0],
                })
            })
        }
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
        const searchModel = JSON.parse(JSON.stringify(Model_ChiPhi.ChiPhi));
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
            Promise.all([find_model(this.state.selectedModel[0],Model_ChiPhi.table_database)]).then(model => {
                Promise.all([delete_model(model[0],Model_ChiPhi.table_database)]).then(result => {
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
        Model_ChiPhi.columns.forEach(e => {
            x += e.width
        });
        return(
            <div>
                <ChiPhi_Search value={searchModel} onChange={this.handleChangeSearchModel} key={key}/>
                <Row>
                    <Col>
                        <Space>
                            <Button type="primary" onClick={this.handleSearchClick}>{HeaderString.search}</Button>
                            <ChiPhi_Add name={HeaderString.create} onAdd={this.handleSearchClick}/>
                            <ChiPhi_View name={HeaderString.view} selected={selectedModel}/>
                            <ChiPhi_Edit name={HeaderString.edit} onEdit={this.handleSearchClick} selected={selectedModel}/>
                            <Button type="primary" onClick={this.handleDeleteButton}>{HeaderString.delete}</Button>
                        </Space>
                    </Col>
                </Row>
                <Table dataSource={this.state.dataSource} columns={Model_ChiPhi.columns} bordered scroll={{ x: x }} rowSelection={rowSelection}/>
            </div>
        )
    };


    render() {
        return (
            <MasterLayout component={this.view_diadiem(this.state.key)} menu={"Tài chính"} name={"Chi phí"} subMenu={"ChiPhi"}/>
        );
    }
}

export default ChiPhi_Layout;
