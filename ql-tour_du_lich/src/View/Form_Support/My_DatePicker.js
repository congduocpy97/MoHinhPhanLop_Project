import { DatePicker } from 'antd';
import moment from 'moment';
import React, { Component } from 'react';

export class My_DatePicker extends Component {
    onChange = (value) => {
        this.props.onChange(value);
    }

    render() {
        let {value} = this.props;
        if(typeof value === typeof "String")
            value = moment(value);
        return (
            <DatePicker  
            disabled={this.props.disabled !== undefined && this.props.disabled ? this.props.disabled : false}
            value={value}
            defaultValue={moment(new Date())}
            style={this.props.style}
            onChange={this.onChange}
            />
        )
    }
}

export default My_DatePicker
