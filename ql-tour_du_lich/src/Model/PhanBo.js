const PhanBo = {
maNhanVien: -1,
maDoan: -1,
nhiemVu: null,
}

const Field = {
    maNhanVien: {name: "Nhân viên", key: "maNhanVien"},
    maDoan: {name: "Mã đoàn", key: "maDoan"},
    nhiemVu: {name: "Nhiệm vụ", key: "nhiemVu"},
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

const table_database = "phanbo";

export const Model_PhanBo = {
    Field,
    PhanBo,
    columns,
    table_database,
}