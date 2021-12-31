/* eslint-disable react/jsx-pascal-case */
/* eslint-disable no-undef */
import { Card, Col, Collapse, Row } from 'antd'
import Form from 'antd/lib/form/Form'
import FormItem from 'antd/lib/form/FormItem';
import React, { Component } from 'react'
import { Model_ChiPhi } from '../../Model/ChiPhi';
import { GridStyle, HeaderString, InputStyle, LayoutFormat } from '../../Support/Constant';
import DoanDuLich_Select from '../DoanDuLich/DoanDuLich_Select';
import My_InputCurrency from '../Form_Support/My_InputCurrency';
import My_Input_Number from '../Form_Support/My_Input_Number';
import LoaiChiPhi_Select from '../LoaiChiPhi/LoaiChiPhi_Select';

const { Panel } = Collapse;

export default class ChiPhi_Search extends Component {

    constructor (props){
        super(props);
        this.state = {
            data: JSON.parse(JSON.stringify(Model_ChiPhi.ChiPhi))
        }
    }

    handleMaChiPhiChange = e => {
        const searchModel = this.state.data;
        searchModel.maChiPhi = e;
        this.props.onChange(searchModel)
    }

    handleMaDoanChange = e => {
        const searchModel = this.state.data;
        searchModel.maDoan = e;
        this.props.onChange(searchModel)
    }

    handleSoTienChange = e => {
        const searchModel = this.state.data;
        searchModel.soTien = e;
        this.props.onChange(searchModel)
    }
    handleMaLoaiChiPhiChange = e => {
        const searchModel = this.state.data;
        searchModel.maLoaiChiPhi = e;
        this.props.onChange(searchModel)
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
                                        {Model_ChiPhi.Field.maChiPhi.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_Input_Number onChange={this.handleMaChiPhiChange} style={InputStyle} value={searchModel.maChiPhi} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_ChiPhi.Field.maDoan.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <DoanDuLich_Select style={InputStyle} onSelect={this.handleMaDoanChange} value={searchModel.maDoan} />
                                    </FormItem>
                                </Col>
                            </Row>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_ChiPhi.Field.soTien.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_InputCurrency onChange={this.handleSoTienChange} style={InputStyle} value={searchModel.soTien} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_ChiPhi.Field.maLoaiChiPhi.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <LoaiChiPhi_Select style={InputStyle} onSelect={this.handleMaLoaiChiPhiChange} value={searchModel.maLoaiChiPhi} />
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

// const WrappedChiPhi_Search= Wrapper({ name: 'ChiPhi_Search' })(ChiPhi_Search);
// export { WrappedChiPhi_Search as ChiPhi_Search };
