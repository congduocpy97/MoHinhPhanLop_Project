const NoiDungTour = {
maDoan: -1,
hanhTrinh: null,
khachSan: null,
diaDiemThamQuan: null,
}

const Field = {
    maDoan: {name: "Mã doàn", key: "maDoan"},
    hanhTrinh: {name: "Hành trình", key: "hanhTrinh"},
    khachSan: {name: "Khách sạn", key: "khachSan"},
    diaDiemThamQuan: {name: "Địa diểm tham quan", key: "diaDiemThamQuan"},
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

const table_database = "noidungtour";

export const Model_NoiDungTour = {
    Field,
    NoiDungTour,
    columns,
    table_database,
}