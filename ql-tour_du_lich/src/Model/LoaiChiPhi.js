const LoaiChiPhi = {
maLoaiChiPhi: -1,
tenLoaiChiPhi: null,
trangThai: null,
}

const Field = {
    maLoaiChiPhi: {name: "Mã loại chi phí", key: "maLoaiChiPhi"},
    tenLoaiChiPhi: {name: "Tên loại chi phí", key: "tenLoaiChiPhi"},
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

const table_database = "loaichiphi";

export const Model_LoaiChiPhi = {
    Field,
    LoaiChiPhi,
    columns,
    table_database
}