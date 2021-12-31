/* eslint-disable react/jsx-pascal-case */
/* eslint-disable no-undef */
import { Card, Col, Collapse, Input, Row } from 'antd'
import Form from 'antd/lib/form/Form'
import FormItem from 'antd/lib/form/FormItem';
import React, { Component } from 'react'
import { Model_TourDuLich } from '../../Model/TourDuLich';
import { GridStyle, HeaderString, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_Input_Number from '../Form_Support/My_Input_Number';
import LoaiHinhDuLich_Select from '../LoaiHinhDuLich/LoaiHinhDuLich_Select';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const { Panel } = Collapse;

export default class TourDuLich_Search extends Component {

    constructor (props){
        super(props);
        this.state = {
            data: JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich))
        }
    }

    handleMaTourDuLichChange = e => {
        const searchModel = this.state.data;
        searchModel.maTour = e;
        this.props.onChange(searchModel);
    }

    handleTenGoiChange = e => {
        const  searchModel = this.state.data;
        searchModel.tenGoi =  e.target.value;
        this.props.onChange(searchModel);
    }
    handleDacDiemChange = e => {
        const  searchModel = this.state.data;
        searchModel.dacDiem =  e.target.value;
        this.props.onChange(searchModel);
    }
    handleMaLoaiHinhChange = e => {
        const  searchModel = this.state.data;
        searchModel.maLoaiHinh =  e;
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
                                            {Model_TourDuLich.Field.maTour.name}
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColInput}>
                                        <FormItem>
                                            <My_Input_Number style={InputStyle} onChange={this.handleMaTourDuLichChange} value={searchModel.maTour}/>
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                        <FormItem>
                                            {Model_TourDuLich.Field.tenGoi.name}
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColInput}>
                                        <FormItem>
                                            <Input style={InputStyle} onChange={this.handleTenGoiChange} value={searchModel.tenGoi}/>
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
                                            <Input style={InputStyle} onChange={this.handleDacDiemChange} value={searchModel.dacDiem}/>
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                        <FormItem>
                                            {Model_TourDuLich.Field.maLoaiHinh.name}
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColInput}>
                                        <FormItem>
                                            <LoaiHinhDuLich_Select null style={InputStyle} onSelect={this.handleMaLoaiHinhChange} value={searchModel.tenGoi}/>
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
                                            <TrangThai_Select null style={InputStyle} onSelect={this.handleTrangThaiChange} value={searchModel.trangThai}/>
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

// const WrappedTourDuLich_Search= Wrapper({ name: 'TourDuLich_Search' })(TourDuLich_Search);
// export { WrappedTourDuLich_Search as TourDuLich_Search };
