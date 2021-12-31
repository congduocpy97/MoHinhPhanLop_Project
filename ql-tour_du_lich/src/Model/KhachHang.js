import { Button } from "antd";

const KhachHang = {
maKhachHang: -1,
hoTen: null,
soCMND: null,
diaChi: null,
gioiTinh: null,
sdt: null,
quocTich: null,
trangThai: null,
}

const Field = {
    maKhachHang: {name: "Mã khách hàng", key: "maKhachHang"},
    hoTen: {name: "Họ tên", key: "hoTen"},
    soCMND: {name: "Số CMND", key: "soCMND"},
    diaChi: {name: "Địa chỉ", key: "diaChi"},
    gioiTinh: {name: "Giới tính", key: "gioiTinh"},
    sdt: {name: "Số điện thoại", key: "sdt"},
    quocTich: {name: "Quốc tịch", key: "quocTich"},
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

const columns_tab = (action) => { 
    return [
        {
            title: Field.maKhachHang.name,
            dataIndex: Field.maKhachHang.key,
            key: Field.maKhachHang.key,
            width: "33%"
        },
        {
            title: Field.hoTen.name,
            dataIndex: Field.hoTen.key,
            key: Field.hoTen.key,
            width: "33%"
        },
        {
            title: "Hủy bỏ",
            dataIndex: '',
            key: 'delete',
            width: "33%",
            render: (text, record) => <Button onClick={(e) => { action(record.maKhachHang, e); }} size={'small'} type={'primary'}>Xóa</Button>
        }
    ]
    ;
}

const columns_tab_view = () => { 
    return [
        {
            title: Field.maKhachHang.name,
            dataIndex: Field.maKhachHang.key,
            key: Field.maKhachHang.key,
            width: "40%"
        },
        {
            title: Field.hoTen.name,
            dataIndex: Field.hoTen.key,
            key: Field.hoTen.key,
            width: "60%"
        },
    ]
    ;
}

const table_database = "khachhang";

export const Model_KhachHang = {
    Field,
    KhachHang,
    columns,
    table_database,
    columns_tab,
    columns_tab_view,
}