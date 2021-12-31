/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Row, Table, Tabs } from "antd";
import FormItem from "antd/lib/form/FormItem";
import React, { Component } from "react";
import { getThongKeTour } from "../../Controller/ThongKe_Service";
import { GridStyle, InputStyle, LayoutFormat } from "../../Support/Constant";
import My_InputCurrency from "../Form_Support/My_InputCurrency";
import My_RangePicker from "../Form_Support/My_RangePicker";
import LoaiChiPhi_Select from "../LoaiChiPhi/LoaiChiPhi_Select";
import MasterLayout from "../Master_Layout";
import NhanVien_Select from "../NhanVien/NhanVien_Select";
import TourDuLich_Select from "../TourDuLich/TourDuLich_Select";


const { TabPane } = Tabs;
export class ThongKe_Layout extends Component {
    constructor (props){
        super(props);
        this.state = {
            maTour: -1,
            maNhanVien: -1,
            maLoaiChiPhi: -1,
            key: "1",
            start:{},
            end:{},
            col:[],
            data:[],
            tongDoanhThu:0,
        }
    }

    tabPosition = e =>{
        const key = e;
        this.setState({
            key,
        })
    }

    handleChangeDate = (m,s) =>{
        this.setState({
            start: s[0] !== "" ? m[0] : {},
            end: s[1] !== "" ? m[1] : {},
        })
    }

    handleChangeMaTour = e =>{
        this.setState({
            maTour: e
        })
    }

    handleChangeMaNhanVien = e => {
        this.setState({
            maNhanVien: e
        })
    }

    handleChangeMaLoaiChiPhi = e =>{
        this.setState({
            maLoaiChiPhi: e
        })
    }

    handleClick = async e =>{
        Promise.all([getThongKeTour(this.state.key,this.state.maTour,this.state.maNhanVien,this.state.maLoaiChiPhi,this.state.start,this.state.end)]).then(result => {
            console.log(result);
            this.setState({
                col: result[0].columns,
                data: result[0].data,
                tongDoanhThu: result[0].tongDoanhThu,
            })
        })
    }

    view = () => {


        return(
            <div>   
                <Card>
                    <Card.Grid  hoverable={false} style={GridStyle}>
                        <Row>
                            <Col span={LayoutFormat.ColLabel}>
                                <FormItem>
                                    Chọn thời gian
                                </FormItem>
                            </Col>
                            <Col span={LayoutFormat.ColInput}>
                                <FormItem>
                                    <My_RangePicker style={InputStyle} onChange={this.handleChangeDate} />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Button type="primary" onClick={this.handleClick}>Thống kê</Button>
                        </Row>
                        <Row>
                            <Col span={LayoutFormat.ColLabel}>
                                <FormItem>
                                    Tổng doanh thu
                                </FormItem>
                            </Col>
                            <Col span={LayoutFormat.ColInput}>
                                <FormItem>
                                    <My_InputCurrency style={InputStyle} disabled={true} value={this.state.tongDoanhThu} />
                                </FormItem>
                            </Col>
                        </Row>
                    </Card.Grid>
                </Card>
                <Card>
                    <Card.Grid  hoverable={false} style={GridStyle}>
                        <Tabs defaultActiveKey="1" onChange={this.tabPosition}> 
                            <TabPane tab="Thống kê theo tour" key="1" > 
                                <Row>
                                    <Col span={LayoutFormat.ColLabel}>
                                        <FormItem>
                                            Chọn tour
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColInput}>
                                        <FormItem>
                                            <TourDuLich_Select null style={InputStyle} onSelect={this.handleChangeMaTour} />
                                        </FormItem>
                                    </Col>
                                </Row>
                            </TabPane>
                            <TabPane tab="Thông kê chi phí" key="2">
                                <Row>
                                    <Col span={LayoutFormat.ColLabel}>
                                        <FormItem>
                                            Chọn loại chi phí
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColInput}>
                                        <FormItem>
                                            <LoaiChiPhi_Select null style={InputStyle} onSelect={this.handleChangeMaLoaiChiPhi} />
                                        </FormItem>
                                    </Col>
                                </Row>
                            </TabPane>
                            <TabPane tab="Thống kê nhân viên" key="3">
                                <Row>
                                    <Col span={LayoutFormat.ColLabel}>
                                        <FormItem>
                                            Chọn nhân viên
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColInput}>
                                        <FormItem>
                                            <NhanVien_Select null style={InputStyle} onSelect={this.handleChangeMaNhanVien} />
                                        </FormItem>
                                    </Col>
                                </Row>
                            </TabPane>
                        </Tabs>
                    </Card.Grid>
                    </Card>
                <Table columns={this.state.col} dataSource={this.state.data}/>
            </div>
        );
    }

    render() {
        return (
            <MasterLayout component={this.view()} menu={"Thống kê"} name={"Thống kê"} subMenu={"ThongKe"}/>
        );
    }
}

export default ThongKe_Layout;
