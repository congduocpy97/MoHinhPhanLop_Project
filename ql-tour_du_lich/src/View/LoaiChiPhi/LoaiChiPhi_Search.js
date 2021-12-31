/* eslint-disable react/jsx-pascal-case */
/* eslint-disable no-undef */
import { Card, Col, Collapse, Input, Row } from 'antd'
import Form from 'antd/lib/form/Form'
import FormItem from 'antd/lib/form/FormItem';
import React, { Component } from 'react'
import { Model_LoaiChiPhi } from '../../Model/LoaiChiPhi';
import { GridStyle, HeaderString, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_Input_Number from '../Form_Support/My_Input_Number';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const { Panel } = Collapse;

export default class LoaiChiPhi_Search extends Component {

    constructor (props){
        super(props);
        this.state = {
            data: JSON.parse(JSON.stringify(Model_LoaiChiPhi.LoaiChiPhi))
        }
    }

    handleMaLoaiChiPhiChange = e => {
        const searchModel = this.state.data;
        searchModel.maLoaiChiPhi = e;
        this.props.onChange(searchModel);
    }

    handleTenChange = e => {
        const  searchModel = this.state.data;
        searchModel.tenLoaiChiPhi =  e.target.value;
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
                                            {Model_LoaiChiPhi.Field.maLoaiChiPhi.name}
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColInput}>
                                        <FormItem>
                                            <My_Input_Number style={InputStyle} onChange={this.handleMaLoaiChiPhiChange} value={searchModel.maLoaiChiPhi}/>
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                        <FormItem>
                                            {Model_LoaiChiPhi.Field.tenLoaiChiPhi.name}
                                        </FormItem>
                                    </Col>
                                    <Col span={LayoutFormat.ColInput}>
                                        <FormItem>
                                            <Input style={InputStyle} onChange={this.handleTenChange} value={searchModel.tenLoaiChiPhi}/>
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span={LayoutFormat.ColLabel}>
                                        <FormItem>
                                            {Model_LoaiChiPhi.Field.trangThai.name}
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

// const WrappedLoaiChiPhi_Search= Wrapper({ name: 'LoaiChiPhi_Search' })(LoaiChiPhi_Search);
// export { WrappedLoaiChiPhi_Search as LoaiChiPhi_Search };
