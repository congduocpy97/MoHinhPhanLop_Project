/* eslint-disable react/jsx-pascal-case */
/* eslint-disable no-undef */
import { Card, Col, Collapse, Row } from 'antd'
import Form from 'antd/lib/form/Form'
import FormItem from 'antd/lib/form/FormItem';
import React, { Component } from 'react'
import { Model_DoanDuLich } from '../../Model/DoanDuLich';
import { GridStyle, HeaderString, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_DatePicker from '../Form_Support/My_DatePicker';
import My_InputCurrency from '../Form_Support/My_InputCurrency';
import My_Input_Number from '../Form_Support/My_Input_Number';
import TourDuLich_Select from '../TourDuLich/TourDuLich_Select';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const { Panel } = Collapse;

export default class DoanDuLich_Search extends Component {

    constructor (props){
        super(props);
        this.state = {
            data: JSON.parse(JSON.stringify(Model_DoanDuLich.DoanDuLich))
        }
    }

    handleMaDoanChange = e => {
        const searchModel = this.state.data;
        searchModel.maDoan = e;
        this.props.onChange(searchModel);
    }

    handleMaTourChange = e => {
        const  searchModel = this.state.data;
        searchModel.maTour =  e;
        this.props.onChange(searchModel);
    }
    handleNgayKhoiHanhChange = e => {
        const  searchModel = this.state.data;
        searchModel.ngayKhoiHanh =  e;
        this.props.onChange(searchModel);
    }
    handleNgayKetThucChange = e => {
        const  searchModel = this.state.data;
        searchModel.ngayKetThuc =  e;
        this.props.onChange(searchModel);
    }
    handleDoanhThuChange = e => {
        const  searchModel = this.state.data;
        searchModel.doanhThu =  e;
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
                                            {Model_DoanDuLich.Field.maDoan.name}
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColInput}>
                                        <FormItem>
                                            <My_Input_Number onChange={this.handleMaDoanChange} style={InputStyle} value={searchModel.maDoan} />  
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                        <FormItem>
                                            {Model_DoanDuLich.Field.maTour.name}
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColInput}>
                                        <FormItem>
                                            <TourDuLich_Select null style={InputStyle} onSelect={this.handleMaTourChange} value={searchModel.maTour} />                                        
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
                                            <My_InputCurrency  style={InputStyle} onChange={this.handleDoanhThuChange} value={searchModel.doanhThu} />
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                        <FormItem>
                                            {Model_DoanDuLich.Field.trangThai.name}
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

// const WrappedDoanDuLich_Search= Wrapper({ name: 'DoanDuLich_Search' })(DoanDuLich_Search);
// export { WrappedDoanDuLich_Search as DoanDuLich_Search };
