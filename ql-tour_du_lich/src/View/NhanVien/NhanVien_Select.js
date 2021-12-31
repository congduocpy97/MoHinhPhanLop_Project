import { Select } from 'antd';
import React, { PureComponent } from 'react';
import { getAll } from '../../Controller/Data_Service';
import { Model_NhanVien } from '../../Model/NhanVien';
import { InputStyle } from '../../Support/Constant';

const { Option } = Select;

export class NhanVien_Select extends PureComponent {
    constructor(props){
        super(props)
        this.state = {
            list: []
        }
    }

    componentDidMount(){
        Promise.all([getAll(Model_NhanVien.table_database)]).then(result => {
            this.setState({
                list: result[0]
            })
        })
    }

    componentDidUpdate(prevProps){
        if(prevProps !== this.props){
            Promise.all([getAll(Model_NhanVien.table_database)]).then(result => {
                this.setState({
                    list: result[0]
                })
            })
        }
    }

    handleChangeValue = (e) =>{
        this.props.onSelect(e);
    }


    render() {
        return (
            <Select
                showSearch
                placeholder="Tìm và chọn"
                optionFilterProp="children"
                filterOption={(input, option) =>
                    option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                }
                filterSort={(optionA, optionB) =>
                    optionA.children.toLowerCase().localeCompare(optionB.children.toLowerCase())
                }
                onSelect={this.handleChangeValue}
                style={InputStyle}
                defaultValue={this.props.value === -1 ? undefined :this.props.value}
                disabled={this.props.disabled !== undefined && this.props.disabled ? this.props.disabled : false}
            >
                 {!!this.props.null ? (<Option key={"null"} value={-1} >{" "}</Option>) : ""}
                {this.state.list.map(m =>{
                    return (
                        <Option key={m.maNhanVien} value={m.maNhanVien} >{m.tenNhanVien}</Option>
                    )
                })}
            </Select>
        )
    }
}

export default NhanVien_Select
