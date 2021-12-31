import { DatePicker } from 'antd';
import React, { Component } from 'react'

const { RangePicker } = DatePicker;

export class My_RangePicker extends Component {

    onChange = (value1, value2) => {
        this.props.onChange(value1, value2);
    }

    render() {
        const {value} = this.props;
        return (
            <RangePicker  
                disabled={this.props.disabled !== undefined && this.props.disabled ? this.props.disabled : false}
                value={value}
                style={this.props.style}
                onChange={this.onChange}
            />
        )
    }
}

export default My_RangePicker
