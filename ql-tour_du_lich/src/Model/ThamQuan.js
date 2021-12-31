/* eslint-disable react/jsx-pascal-case */
import { Button } from "antd";
import My_Input_Number from "../View/Form_Support/My_Input_Number";

const ThamQuan = {
maTour: -1,
maDiaDiem: -1,
thuTu: -1,
trangThai: null,
}

const Field = {
    maTour: {name: "Tên tour", key: "maTour"},
    maDiaDiem: {name: "Địa điểm", key: "maDiaDiem"},
    thuTu: {name: "Thứ tự", key: "thuTu"},
    trangThai: {name: "Trạng thái", key: "trangThai"},
}

const columns = Object.keys(Field).map(m => {
    const length = Object.keys(Field).length;
    return {
        title: Field[m].name,
        dataIndex: Field[m].key,
        key: Field[m].key,
        width: `${100/length}%`,
    }
})

const columns_tab = (action1, action2) => { 
    return [
        {
            title: Field.thuTu.name,
            dataIndex: Field.thuTu.key,
            key: Field.thuTu.key,
            width: "15%",
            sorter: (a, b) => a.thuTu - b.thuTu,
            render: (text, record) => <span><My_Input_Number value={text} onChange={(e) => { action1(record, e); }} size={'small'}/></span>
        },
        {
            title: Field.maDiaDiem.name,
            dataIndex: "ten",
            key: "ten",
            width: "70%"
        },
        {
            title: "Hủy bỏ",
            dataIndex: '',
            key: 'delete',
            width: "15%",
            render: (text, record) => <Button onClick={(e) => { action2(record, e); }} size={'small'} type={'primary'}>Xóa</Button>
        }
    ]
    ;
}

const columns_tab_view = [
    {
        title: Field.thuTu.name,
        dataIndex: Field.thuTu.key,
        key: Field.thuTu.key,
        width: "15%",
    },
    {
        title: Field.maDiaDiem.name,
        dataIndex: "ten",
        key: "ten",
        width: "85%"
    },
]
    

const table_database = "thamquan";

export const Model_ThamQuan = {
    Field,
    ThamQuan,
    columns,
    table_database,
    columns_tab_view,
    columns_tab,

}