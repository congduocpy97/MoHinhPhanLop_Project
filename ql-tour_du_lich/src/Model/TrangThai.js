const TrangThai = {
trangThai: null,
ten: null,
}

const Field = {
    trangThai: {name: "Trạng thái", key: "trangThai"},
    ten: {name: "Tên", key: "ten"},
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

const table_database = "trangthai";

export const Model_TrangThai = {
    Field,
    TrangThai,
    columns,
    table_database
}