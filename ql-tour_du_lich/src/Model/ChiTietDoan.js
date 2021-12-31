const ChiTietDoan = {
maDoan: -1,
maKhachHang: -1,
}

const Field = {
    maDoan: {name: "Mã đoàn", key: "maDoan"},
    maKhachHang: {name: "Mã khách hàng", key: "maKhachHang"},
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

const table_database = "chitietdoan";

export const Model_ChiTietDoan = {
    Field,
    ChiTietDoan,
    columns,
    table_database
}