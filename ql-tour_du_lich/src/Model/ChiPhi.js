import { formatterCurrency } from "../View/Form_Support/My_Format"

const ChiPhi = {
    maChiPhi : -1,
    maDoan: -1,
    soTien: -1,
    maLoaiChiPhi: -1,
}

const Field = {
    maChiPhi : {name: "Mã chi phí", key: "maChiPhi"},
    maDoan: {name: "Mã đoàn", key: "maDoan"},
    soTien: {name: "Số tiền", key: "soTien"},
    maLoaiChiPhi: {name: "Loại chi phí", key: "maLoaiChiPhi"},
}

const arrField = [
    {name: "Mã chi phí", key: "maChiPhi"},
    {name: "Mã đoàn", key: "maDoan"},
    {name: "Số tiền", key: "soTien"},
    {name: "Loại chi phí", key: "maLoaiChiPhi"},
]

const columns = Object.keys(Field).map(m => {
    const length = Object.keys(Field).length;
    return {
        title: Field[m].name,
        dataIndex: Field[m].key,
        key: Field[m].key,
        width: `${100/length}%`,
        render: Field[m].key === "soTien" ? soTien => (
            <div style={{ textAlign: 'right' }}>{formatterCurrency.format(soTien)}</div>
        ) : undefined
    }
})

const table_database = "chiphi";

export const Model_ChiPhi = {
    arrField,
    Field,
    columns,
    ChiPhi,
    table_database,
}