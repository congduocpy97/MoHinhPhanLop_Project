const DiaDiem = {
maDiaDiem: -1,
ten: null,
trangThai: null}

const Field = {
    maDiaDiem: {name: "Mã địa điểm", key: "maDiaDiem"},
    ten: {name: "Tên", key: "ten"},
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

const table_database = "diadiem";

export const Model_DiaDiem = {
    Field,
    DiaDiem,
    columns,
    table_database,
}