/* eslint-disable react/jsx-pascal-case */
/* eslint-disable no-undef */
import { Card, Col, Collapse, Input, Row } from 'antd'
import Form from 'antd/lib/form/Form'
import FormItem from 'antd/lib/form/FormItem';
import React, { Component } from 'react'
import { Model_KhachHang } from '../../Model/KhachHang';
import { GridStyle, HeaderString, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_Input_Number from '../Form_Support/My_Input_Number';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const { Panel } = Collapse;

export default class KhachHang_Search extends Component {

    constructor (props){
        super(props);
        this.state = {
            data: JSON.parse(JSON.stringify(Model_KhachHang.KhachHang))
        }
    }

    handleMaKhachHangChange = e => {
        const searchModel = this.state.data;
        searchModel.maKhachHang = e;
        this.props.onChange(searchModel);
    }

    handleHoTenChange = e => {
        const  searchModel = this.state.data;
        searchModel.hoTen =  e.target.value;
        this.props.onChange(searchModel);
    }

    handleSoCMNDChange = e => {
        const  searchModel = this.state.data;
        searchModel.soCMND =  e.target.value;
        this.props.onChange(searchModel);
    }

    handleDiaChiChange = e => {
        const  searchModel = this.state.data;
        searchModel.diaChi =  e.target.value;
        this.props.onChange(searchModel);
    }

    handleGioiTinhChange = e => {
        const  searchModel = this.state.data;
        searchModel.gioiTinh =  e.target.value;
        this.props.onChange(searchModel);
    }

    handleSDTChange = e => {
        const  searchModel = this.state.data;
        searchModel.sdt =  e.target.value;
        this.props.onChange(searchModel);
    }

    handleQuocTichChange = e => {
        const  searchModel = this.state.data;
        searchModel.quocTich =  e.target.value;
        this.props.onChange(searchModel);
    }

    handleTrangThaiChange = e => {
        const  searchModel = this.state.data;
        searchModel.trangThai =  e;
        this.props.onChange(searchModel);
    }

    render() {
        const searchModel = this.state.data;
        const formItemLayout = {
            labelCol: {
                xs: { span: 24 },
                sm: { span: 22 },
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 22 },
            },
        };
        return (
            <Form {...formItemLayout}>
                <Collapse
                    defaultActiveKey={["1"]}
                    expandIconPosition={"right"}
                >
                    <Panel header={<span>{HeaderString.searchModal}</span>} key="1">
                        <Card>
                            <Card.Grid  hoverable={false} style={GridStyle}>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.maKhachHang.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_Input_Number onChange={this.handleMaKhachHangChange} style={InputStyle} value={searchModel.maKhachHang} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.hoTen.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleHoTenChange} value={searchModel.hoTen} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.soCMND.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleSoCMNDChange} value={searchModel.soCMND} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.diaChi.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleDiaChiChange} value={searchModel.diaChi} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.gioiTinh.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleGioiTinhChange} value={searchModel.gioiTinh} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.sdt.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleSDTChange} value={searchModel.sdt} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                            <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_KhachHang.Field.quocTich.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleQuocTichChange} value={searchModel.quocTich} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_KhachHang.Field.trangThai.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <TrangThai_Select null style={InputStyle} onSelect={this.handleTrangThaiChange} value={searchModel.trangThai} />
                                    </FormItem>
                                </Col>
                            </Row>
                            </Card.Grid>
                        </Card>
                    </Panel>
                </Collapse>
            </Form>
        )
    }
}

// const WrappedKhachHang_Search= Wrapper({ name: 'KhachHang_Search' })(KhachHang_Search);
// export { WrappedKhachHang_Search as KhachHang_Search };
