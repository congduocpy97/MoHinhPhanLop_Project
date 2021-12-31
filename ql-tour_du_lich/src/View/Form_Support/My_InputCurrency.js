import { InputNumber } from 'antd';
import React, { Component } from 'react'

export default class My_InputCurrency extends Component {

    onChange = (value) => {
        this.props.onChange(value);
    }

    render() {
        const { value } = this.props;
        return (
            <InputNumber
                disabled={this.props.disabled !== undefined && this.props.disabled ? this.props.disabled : false}
                value={value >= 0 ? value : ""}
                style={this.props.style}
                formatter={value => `${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
                parser={value => value.replace(/đ\s?|(,*)/g, '')}
                onChange={this.onChange}
                addonAfter={"đ"}
                />
        )
    }
}
