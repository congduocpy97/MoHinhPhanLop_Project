import { formatterCurrency } from "../View/Form_Support/My_Format";

const TourDuLich = {
maTour: -1,
tenGoi: null,
dacDiem: null,
maLoaiHinh: -1,
trangThai: null,
}

const Field = {
    maTour: {name: "Mã tour", key: "maTour"},
    tenGoi: {name: "Tên gọi", key: "tenGoi"},
    dacDiem: {name: "Đặc điểm", key: "dacDiem"},
    maLoaiHinh: {name: "Loại hình", key: "maLoaiHinh"},
    trangThai: {name: "Trạng thái", key: "trangThai"},
}

const columns = [...Object.keys(Field).map(m => {
    const length = Object.keys(Field).length + 1;
    return {
        title: Field[m].name,
        dataIndex: Field[m].key,
        key: Field[m].key,
        width: `${100/length}%`,
    }
}), {
    title: "Giá tour",
    dataIndex: "giaTour",
    key: "giaTour",
    width: `${100/(6)}%`,
    render: giaTour => (
        <div style={{ textAlign: 'right' }}>{typeof (giaTour) !== "number" ? giaTour : formatterCurrency.format(giaTour)}</div>
    ),
}]

const table_database = "tourdulich";

export const Model_TourDuLich = {
    Field,
    TourDuLich,
    columns,
    table_database,
}