import { Button, Card, Col, Input, notification, Row } from 'antd';
import FormItem from 'antd/lib/form/FormItem';
import Modal from 'antd/lib/modal/Modal'
import React, { Component } from 'react'
import { find_model, update_model } from '../../Controller/Data_Service';
import { Model_TrangThai } from '../../Model/TrangThai';
import { GridStyle, InputStyle, LayoutFormat } from '../../Support/Constant';

const key = 'edit';

export class TrangThai_Edit extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isModalVisible: false,
            selected: [],
            preModel: JSON.parse(JSON.stringify(Model_TrangThai.TrangThai)),
            searchModel: JSON.parse(JSON.stringify(Model_TrangThai.TrangThai)),
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
            Promise.all([find_model(this.state.selected[0], Model_TrangThai.table_database)]).then(result => {
                this.setState({
                    searchModel: result[0],
                    preModel: JSON.parse(JSON.stringify(result[0])),
                    isModalVisible: true
                })
            })
        }
    };

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
            this.props.onEdit();
            notification.open({
                key,
                message: 'Thành công',
                description: 'Dữ liệu đã được chỉnh sửa.',
            });
        }
    }

    handleOk = () => {
        if(JSON.stringify(this.state.preModel) !== JSON.stringify(this.state.searchModel)){
            Promise.all([update_model([this.state.searchModel, this.state.preModel], Model_TrangThai.table_database)]).then(result => {
                this.openNotification(result[0] === 0 ? -1 : result[0]);
                this.setState({
                    isModalVisible: false
                })
            });
        }
        else{
            this.openNotification(-1)
            this.setState({
                isModalVisible: false
            })
        }

    };

    handleCancel = () => {
        this.setState({
            isModalVisible: false
        })
    }

    handleTenChange = (e) => {
        const searchModel = this.state.searchModel;
        searchModel.ten = e.target.value;
        this.setState({
            searchModel
        })
    }

    render() {
        const searchModel = this.state.searchModel;
        return (
            <span>
                <Button type="primary" onClick={this.showModal}>
                    {this.props.name}
                </Button>
                <Modal title="Chỉnh sửa trạng thái" visible={this.state.isModalVisible} width={800} onCancel={this.handleCancel} onOk={this.handleOk}>
                    <Card>
                        <Card.Grid hoverable={false} style={GridStyle}>
                            <Row>
                                <Col span={LayoutFormat.ColLabel}>
                                    <FormItem>
                                        {Model_TrangThai.Field.trangThai.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input disabled={true} style={InputStyle} value={searchModel.trangThai} />
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColLabel} offset={LayoutFormat.ColOffset}>
                                    <FormItem>
                                        {Model_TrangThai.Field.ten.name}
                                    </FormItem>
                                </Col>
                                <Col span={LayoutFormat.ColInput}>
                                    <FormItem>
                                        <Input style={InputStyle} onChange={this.handleTenChange} value={searchModel.ten} />
                                    </FormItem>
                                </Col>
                            </Row>
                        </Card.Grid>
                    </Card>
                </Modal>
            </span>
        )
    }
}

export default TrangThai_Edit