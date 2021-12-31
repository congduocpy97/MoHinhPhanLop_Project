import { formatterCurrency } from "../View/Form_Support/My_Format";

const DoanDuLich = {
maDoan: -1,
maTour: -1,
ngayKhoiHanh: null,
ngayKetThuc: null,
doanhThu: -1,
trangThai: null,
}

const Field = {
    maDoan: {name: "Mã đoàn", key: "maDoan"},
    maTour: {name: "Tên tour", key: "maTour"},
    ngayKhoiHanh: {name: "Ngày khởi hành", key: "ngayKhoiHanh"},
    ngayKetThuc: {name: "Ngày kết thúc", key: "ngayKetThuc"},
    doanhThu: {name: "Doanh thu", key: "doanhThu"},
    trangThai: {name: "Trạng thái", key: "trangThai"},
}

const columns = Object.keys(Field).map(m => {
    const length = Object.keys(Field).length;
    return {
        title: Field[m].name,
        dataIndex: Field[m].key,
        key: Field[m].key,
        width: `${100/length}%`,
        render: Field[m].key === "doanhThu" ? soTien => (
            <div style={{ textAlign: 'right' }}>{formatterCurrency.format(soTien)}</div>
        ) : undefined
    }
})

const table_database = "doandulich";

export const Model_DoanDuLich = {
    Field,
    DoanDuLich,
    columns,
    table_database,
}