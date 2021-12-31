import { formatterCurrency } from "../View/Form_Support/My_Format";

const GiaTour = {
maGia: -1,
maTour: -1,
thanhTien: -1,
thoiGianBatDau: null,
thoiGianKetThuc: null,
trangThai: null,
}

const Field = {
    maGia: {name: "Mã giá", key: "maGia"},
    maTour: {name: "Tên tour", key: "maTour"},
    thanhTien: {name: "Thành tiền", key: "thanhTien"},
    thoiGianBatDau: {name: "Thời gian bắt đầu", key: "thoiGianBatDau"},
    thoiGianKetThuc: {name: "Thời gian kết thúc", key: "thoiGianKetThuc"},
    trangThai: {name: "Trạng thái", key: "trangThai"},
}

const columns = Object.keys(Field).map(m => {
    const length = Object.keys(Field).length;
    return {
        title: Field[m].name,
        dataIndex: Field[m].key,
        key: Field[m].key,
        width: `${100/length}%`,
        render: Field[m].key === "thanhTien" ? soTien => (
            <div style={{ textAlign: 'right' }}>{formatterCurrency.format(soTien)}</div>
        ) : undefined
    }
})

const table_database = "giatour";

export const Model_GiaTour = {
    Field,
    GiaTour,
    columns,
    table_database,
}