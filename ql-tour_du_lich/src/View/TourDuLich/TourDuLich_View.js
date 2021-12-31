/* eslint-disable react/jsx-pascal-case */
import { Button, Card, Col, Input, notification, Row, Table, Tabs } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { find_model, getAll, search } from '../../Controller/Data_Service';
import { Model_DiaDiem } from '../../Model/DiaDiem';
import { Model_ThamQuan } from '../../Model/ThamQuan';
import { Model_TourDuLich } from '../../Model/TourDuLich';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';
import My_Input_Number from '../Form_Support/My_Input_Number';
import LoaiHinhDuLich_Select from '../LoaiHinhDuLich/LoaiHinhDuLich_Select';
import TrangThai_Select from '../TrangThai/TrangThai_Select';

const key = 'view';
const { TabPane } = Tabs;

export class TourDuLich_View extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            selected: [],
            selectDiaDiem: [],
            listDiaDiem: [],
            searchModel: JSON.parse(JSON.stringify(Model_TourDuLich.TourDuLich)),
        }
    }

    componentDidUpdate(prevProps){
        if(prevProps !== this.props){
            const selected = this.props.selected;
            this.setState({
                selected
            })
        }
    }

    componentDidMount(){
        const selected = this.props.selected;
        this.setState({
            selected
        })
    }

    showModal = () => {
        if(this.state.selected.length !== 1){
            this.openNotification(this.state.selected.length);
        }else{
            Promise.all([getAll(Model_DiaDiem.table_database)]).then(data => {
                this.setState({
                    listDiaDiem: data[0]
                });
                const searchThamQuan = JSON.parse(JSON.stringify(Model_ThamQuan.ThamQuan));
                searchThamQuan.maTour = this.state.selected[0];
                Promise.all([find_model(this.state.selected[0], Model_TourDuLich.table_database), search(searchThamQuan, Model_ThamQuan.table_database)]).then(result => {
                    const thamquan = result[1].map(m => {return {...m, key: `${m.maDiaDiem}` + m.thuTu, ten: this.state.listDiaDiem.find(f => f.maDiaDiem === m.maDiaDiem).ten }});
                    this.setState({
                        searchModel: result[0],
                        selectDiaDiem: JSON.parse(JSON.stringify(thamquan)),
                        isModalVisible: true
                    })
                })
            });
        }
    };
    openNotification = (value) => {
        if(value === 0){
            notification.open({
            key,
            message: 'Thất bại',
            description: 'Vui lòng chọn dữ liệu.',
            });
        }else{
            notification.open({
                key,
                message: 'Thất bại',
                description: 'Chọn chỉ 1 dòng dữ liệu.',
            });
        }
    }

    handleOk = () => {
        this.setState({
            isModalVisible: false
        })
    };

    render() {
        const searchModel = this.state.searchModel;
        return (
            <span>
                <Button type="primary" onClick={this.showModal}>
                    {this.props.name}
                </Button>
                <Modal title="Xem thông tin tour du lịch" visible={this.state.isModalVisible} width={800} onCancel={this.handleOk}
                    footer={[
                    <Button key='cancel' type='primary' onClick={this.handleOk}>
                        Đóng
                    </Button>]}>
                    <Tabs defaultActiveKey="1">
                    <TabPane tab="Thông tin" key="1" > 
                    <Card>
                        <Card.Grid hoverable={false} style={GridStyle}>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_TourDuLich.Field.maTour.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <My_Input_Number disabled={true} style={InputStyle} value={searchModel.maTour} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_TourDuLich.Field.tenGoi.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input disabled={true} style={InputStyle}  value={searchModel.tenGoi} />
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
                                        <Input disabled={true} style={InputStyle} value={searchModel.dacDiem} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_TourDuLich.Field.maLoaiHinh.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <LoaiHinhDuLich_Select disabled={true} style={InputStyle}  value={searchModel.maLoaiHinh} />
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
                                        <TrangThai_Select disabled={true} style={InputStyle} value={searchModel.trangThai} />
                                    </FormItem>
                                </Col>
                            </Row>
                        </Card.Grid>
                    </Card>
                    </TabPane>
                    <TabPane tab="Chi tiết địa điểm" key="2">
                            <Card>
                                <Card.Grid hoverable={false} style={GridStyle}>
                                    <Table key={this.state.update} dataSource={this.state.selectDiaDiem} columns={Model_ThamQuan.columns_tab_view} bordered />
                                </Card.Grid>
                            </Card>
                    </TabPane>
                    </Tabs>
                </Modal>
            </span>
        )
    }
}

export default TourDuLich_View