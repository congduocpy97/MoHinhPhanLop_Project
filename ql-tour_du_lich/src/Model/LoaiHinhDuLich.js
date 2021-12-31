const LoaiHinhDuLich = {
maLoaiHinh: -1,
tenLoaiHinh: null,
trangThai: null,
}

const Field = {
    maLoaiHinh: {name: "Mã loại hình", key: "maLoaiHinh"},
    tenLoaiHinh: {name: "Tên loại hình", key: "tenLoaiHinh"},
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

const table_database = "loaihinhdulich";

export const Model_LoaiHinhDuLich = {
    Field,
    LoaiHinhDuLich,
    columns,
    table_database,
}