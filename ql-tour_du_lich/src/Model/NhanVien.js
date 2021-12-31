import { Button, Input } from "antd";
import { InputStyle } from "../Support/Constant";

const NhanVien = {
maNhanVien: -1,
tenNhanVien: null,
trangThai: null,
}

const Field = {
    maNhanVien: {name: "Mã nhân viên", key: "maNhanVien"},
    tenNhanVien: {name: "Tên nhân viên", key: "tenNhanVien"},
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
            title: Field.maNhanVien.name,
            dataIndex: Field.maNhanVien.key,
            key: Field.maNhanVien.key,
            width: "20%"
        },
        {
            title: Field.tenNhanVien.name,
            dataIndex: Field.tenNhanVien.key,
            key: Field.tenNhanVien.key,
            width: "25%"
        },
        {
            title: "Nhiệm vụ",
            dataIndex: 'nhiemVu',
            key: 'delete',
            width: "40%",
            render: (text, record) => <Input style={InputStyle} onChange={(e) => { action1(record, e); }} value={text} />
        },
        {
            title: "Hủy bỏ",
            dataIndex: '',
            key: 'delete',
            width: "15%",
            render: (text, record) => <Button onClick={(e) => { action2(record.maNhanVien, e); }} size={'small'} type={'primary'}>Xóa</Button>
        },
    ]
    ;
}

const columns_tab_view = () => { 
    return [
        {
            title: Field.maNhanVien.name,
            dataIndex: Field.maNhanVien.key,
            key: Field.maNhanVien.key,
            width: "30%"
        },
        {
            title: Field.tenNhanVien.name,
            dataIndex: Field.tenNhanVien.key,
            key: Field.tenNhanVien.key,
            width: "30%"
        },
        {
            title: "Nhiệm vụ",
            dataIndex: "nhiemVu",
            key: "nhiemVu",
            width: "40%"
        },
    ]
    ;
}


const table_database = "nhanvien";

export const Model_NhanVien = {
    Field,
    NhanVien,
    columns,
    table_database,
    columns_tab,
    columns_tab_view,
}