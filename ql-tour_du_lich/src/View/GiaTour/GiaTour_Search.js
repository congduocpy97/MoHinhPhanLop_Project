/* eslint-disable react/jsx-pascal-case */
/* eslint-disable no-undef */
import { Card, Col, Collapse, Row } from 'antd'
import Form from 'antd/lib/form/Form'
import FormItem from 'antd/lib/form/FormItem';
import React, { Component } from 'react'
import { Model_GiaTour } from '../../Model/GiaTour';
import { GridStyle, HeaderString, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_DatePicker from '../Form_Support/My_DatePicker';
import My_InputCurrency from '../Form_Support/My_InputCurrency';
import My_Input_Number from '../Form_Support/My_Input_Number';
import TourDuLich_Select from '../TourDuLich/TourDuLich_Select';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const { Panel } = Collapse;

export default class GiaTour_Search extends Component {

    constructor (props){
        super(props);
        this.state = {
            data: JSON.parse(JSON.stringify(Model_GiaTour.GiaTour))
        }
    }

    handleMaGiaChange = e => {
        const searchModel = this.state.data;
        searchModel.maGia = e;
        this.props.onChange(searchModel);
    }

    handleMaTourChange = e => {
        const  searchModel = this.state.data;
        searchModel.maTour =  e;
        this.props.onChange(searchModel);
    }

    handleThanhTienChange = e => {
        const  searchModel = this.state.data;
        searchModel.thanhTien =  e;
        this.props.onChange(searchModel);
    }
    
    handleThoiGianBatDauChange = e => {
        const  searchModel = this.state.data;
        searchModel.thoiGianBatDau =  e;
        this.props.onChange(searchModel);
    }
    handleThoiGianKetThucChange = e => {
        const  searchModel = this.state.data;
        searchModel.thoiGianKetThuc =  e;
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
                                        {Model_GiaTour.Field.maGia.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_Input_Number disable={true} style={InputStyle} onChange={this.handleMaGiaChange} value={searchModel.maGia} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_GiaTour.Field.maTour.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <TourDuLich_Select null style={InputStyle} onSelect={this.handleMaTourChange} value={searchModel.maTour} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel} >
                                    <FormItem>
                                        {Model_GiaTour.Field.thanhTien.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_InputCurrency style={InputStyle} onChange={this.handleThanhTienChange} value={searchModel.thanhTien} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_GiaTour.Field.thoiGianBatDau.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_DatePicker style={InputStyle} onChange={this.handleThoiGianBatDauChange} value={searchModel.thoiGianBatDau} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel} >
                                    <FormItem>
                                        {Model_GiaTour.Field.thoiGianKetThuc.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_DatePicker style={InputStyle} onChange={this.handleThoiGianKetThucChange} value={searchModel.thoiGianKetThuc} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_GiaTour.Field.trangThai.name}
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

// const WrappedGiaTour_Search= Wrapper({ name: 'GiaTour_Search' })(GiaTour_Search);
// export { WrappedGiaTour_Search as GiaTour_Search };
