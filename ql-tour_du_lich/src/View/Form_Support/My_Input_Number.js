import { InputNumber } from 'antd';
import React, { Component } from 'react'

export default class My_Input_Number extends Component {

    onChange = (value) => {
        if(!value){
            this.props.onChange(-1);
        }
        else{
            this.props.onChange(value);
        }
    }

    render() {
        const { value } = this.props;
        return (
            <InputNumber
                disabled={this.props.disabled !== undefined && this.props.disabled ? this.props.disabled : false}
                value={value > 0 ? value : ""}
                style={this.props.style}
                onChange={this.onChange}
                />
        )
    }
}