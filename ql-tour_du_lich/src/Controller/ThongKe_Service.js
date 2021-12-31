import moment from "moment";
import { Model_ChiPhi } from "../Model/ChiPhi";
import { Model_ChiTietDoan } from "../Model/ChiTietDoan";
import { Model_DoanDuLich } from "../Model/DoanDuLich";
import { Model_LoaiChiPhi } from "../Model/LoaiChiPhi";
import { Model_NhanVien } from "../Model/NhanVien";
import { Model_PhanBo } from "../Model/PhanBo";
import { Model_TourDuLich } from "../Model/TourDuLich";
import { formatterCurrency } from "../View/Form_Support/My_Format";
import { getAll } from "./Data_Service"

export const getThongKeTour = async (key, maTour, maNhanVien, maLoaiChiPhi, batDau, ketThuc) => {
    const tour = await getAll(Model_TourDuLich.table_database);
    const doan = await getAll(Model_DoanDuLich.table_database);
    const chiphi = await getAll(Model_ChiPhi.table_database);
    const loaichiphi = await getAll(Model_LoaiChiPhi.table_database);
    const chitiet = await getAll(Model_ChiTietDoan.table_database);
    const nhanvien = await getAll(Model_NhanVien.table_database);
    const phanbo = await getAll(Model_PhanBo.table_database);

    let startTime = Number.MIN_SAFE_INTEGER;
    let endTime = Number.MAX_SAFE_INTEGER;

    if(moment.isMoment(batDau) && !!batDau && moment.isMoment(ketThuc) && !!ketThuc){
        startTime = batDau.valueOf();
        endTime = ketThuc.valueOf();
    }

    const filterDoan = [...new Set(doan.filter(f => moment(f.ngayKhoiHanh).valueOf() > startTime && moment(f.ngayKhoiHanh).valueOf() < endTime))];
    const filterChiPhi = [...new Set(chiphi.filter(f => !!filterDoan.find(e => e.maDoan === f.maDoan)))];
    const filterTour = [...new Set(tour.filter(f => !!filterDoan.find(e => e.maTour === f.maTour)))];
    const filterChiTiet = [...new Set(chitiet.filter(f => !!filterDoan.find(e => e.maDoan === f.maDoan)))];
    const filterPhanBo =[...new Set(phanbo.filter(f => !!filterDoan.find(e => e.maDoan === f.maDoan)))];
    const filterNhanVien = [...new Set(nhanvien.filter(f => !!filterPhanBo.find(e => e.maNhanVien === f.maNhanVien)))];
    const filterLoai = [...new Set(loaichiphi.filter(f => !!filterChiPhi.find(e => e.maLoaiChiPhi === f.maLoaiChiPhi)))];
    
    let tongDoanhThu = 0;

    let result = {};

    if(key === "1"){
        const dataTour = [...new Set(filterTour.map(ft => {
            const arrDoanTour = [...new Set(filterDoan.filter(fd => fd.maTour === ft.maTour))];
            const arrKhachTour = [...new Set(filterChiTiet.filter(fct => !!arrDoanTour.find(f => f.maDoan === fct.maDoan)))];
            const arrChiPhiTour = [...new Set(filterChiPhi.filter(fcp => !!arrDoanTour.find(f => f.maDoan === fcp.maDoan)))]
            let moreOnceTime = 0;
            let doanhthu = 0;
            let chiphiT = 0;
            arrDoanTour.forEach(adt => { doanhthu += adt.doanhThu});
            arrChiPhiTour.forEach(acpt => {chiphiT += acpt.soTien});
            moreOnceTime += arrKhachTour.filter((value, index, array) => index !== array.findIndex(t => (
                t.maKhachHang === value.maKhachHang
            ))).length;
            return{
                tenGoi: ft.tenGoi,
                maTour: ft.maTour,
                arrDoanTour,
                arrKhachTour,
                arrChiPhiTour,
                moreOnceTime,
                doanhthu,
                chiphi: chiphiT,
            }
        }))];
        
        const columns = [
            {
                title: "Tên tour",
                dataIndex: "tenGoi",
                width: "20%",
            },
            {
                title: "Số lượng đoàn",
                dataIndex: "soLuongDoan",
                width: "15%",
                sorter: (a, b) => a.soLuongDoan - b.soLuongDoan,
            },
            {
                title: "Tổng doanh thu",
                dataIndex: 'doanhThu',
                width: "20%",
                sorter: (a, b) => a.doanhThu - b.doanhThu,
                render: value => (
                    <div style={{ textAlign: 'right' }}>{formatterCurrency.format(value)}</div>
                ),
            },
            {
                title: "Tổng chi phí",
                dataIndex: 'chiPhi',
                width: "15%",
                sorter: (a, b) => a.chiPhi - b.chiPhi,
                render: value => (
                    <div style={{ textAlign: 'right' }}>{formatterCurrency.format(value)}</div>
                ),
            },
            {
                title: "Lượng khách hàng",
                dataIndex: 'tongKhach',
                sorter: (a, b) => a.tongKhach - b.tongKhach,
                width: "15%",
            },
            {
                title: "Tham gia lại",
                dataIndex: 'thamGiaLai',
                width: "15%",
                sorter: (a, b) => a.thamGiaLai - b.thamGiaLai,
            }
        ];
        
        if(maTour === -1){
            dataTour.forEach(f =>{
                tongDoanhThu += typeof f.doanhthu === "number" ? f.doanhthu : 0;
            });
            const data = dataTour.map(m => {
                return{
                    key: m.maTour,
                    tenGoi: m.tenGoi,
                    soLuongDoan: m.arrDoanTour.length,
                    doanhThu:m.doanhthu,
                    chiPhi: m.chiphi,
                    tongKhach: m.arrKhachTour.length,
                    thamGiaLai: m.moreOnceTime,
                }
            })
            result = { columns, data , tongDoanhThu};
        }else{
            const element = [dataTour.find(f => f.maTour === maTour)];
            tongDoanhThu += typeof element[0].doanhthu === "number" ? element[0].doanhthu : 0;
            const data = element.map(m => {
                return{
                    key: maTour,
                    tenGoi: m.tenGoi,
                    soLuongDoan: m.arrDoanTour.length,
                    doanhThu:m.doanhthu,
                    chiPhi: m.chiphi,
                    tongKhach: m.arrKhachTour.length,
                    thamGiaLai: m.moreOnceTime,
                }
            })
            result = { columns, data , tongDoanhThu};
        }

    }

    if(key === "2"){
        const columns = [
            {
                title: "Tên loại phí",
                dataIndex: 'tenLoaiChiPhi',
                width: '30%',
            },
            {
                title: "Tổng số tiền chi",
                dataIndex: 'tongChi',
                width: '40%',
                render: value => (
                    <div style={{ textAlign: 'right' }}>{formatterCurrency.format(value)}</div>
                ),
                sorter: (a, b) => a.tongChi - b.tongChi,
            },
            {
                title: "Số lần áp dụng",
                dataIndex: 'soLan',
                width: '30%',
                sorter: (a, b) => a.soLan - b.soLan,
            },
        ]

        const dataLoaiChiPhi = [...new Set(filterLoai.map(m => {
            const arrChiPhi = [...new Set(filterChiPhi.filter(fcp => m.maLoaiChiPhi === fcp.maLoaiChiPhi))];

            let chiphiL = 0;
            arrChiPhi.forEach(acp => {
                chiphiL += acp.soTien;
            });
            return{
                tenLoaiChiPhi: m.tenLoaiChiPhi,
                maLoaiChiPhi: m.maLoaiChiPhi,
                arrChiPhi,
                chiphiL,
            }
        }))];
        
        if(maLoaiChiPhi === -1){
            const data = dataLoaiChiPhi.map(m => {
                return{ 
                    key: m.maLoaiChiPhi,
                    tenLoaiChiPhi: m.tenLoaiChiPhi,
                    tongChi: m.chiphiL,
                    soLan: m.arrChiPhi.length,
                }
            })
            result ={ columns, data, tongDoanhThu };
        }else{
            const data = [dataLoaiChiPhi.find(f=> f.maLoaiChiPhi === maLoaiChiPhi)].map(m => {
                return{ 
                    key: maLoaiChiPhi,
                    tenLoaiChiPhi: m.tenLoaiChiPhi,
                    tongChi: m.chiphiL,
                    soLan: m.arrChiPhi.length,
                }
            })
            result ={ columns, data, tongDoanhThu };
        }
        

        
    }

    if(key === "3"){
        const columns = [
            {
                title: "Tên nhân viên",
                dataIndex: 'tenNhanVien',
                width: '60%',
            },
            {
                title: "Tổng số lần đi",
                dataIndex: 'tongLan',
                width: '40%',
                sorter: (a, b) => a.tongLan - b.tongLan,
            },
        ]

        const dataNhanVien = [...new Set(filterNhanVien.map(fnv => {
            const arrPhanBo = [...new Set(filterPhanBo.filter(fpb => fnv.maNhanVien === fpb.maNhanVien))];
            return{
                maNhanVien: fnv.maNhanVien,
                tenNhanVien: fnv.tenNhanVien,
                arrPhanBo,
            }
        }))];
        
        if(maNhanVien === -1){    
            const data = dataNhanVien.map(m => {
                return{ 
                    key: m.maNhanVien,
                    tenNhanVien: m.tenNhanVien,
                    tongLan: m.arrPhanBo.length,
                }
            })
            

            result = { columns, data, tongDoanhThu };
        }else{
            const data = [dataNhanVien.find(f => f.maNhanVien === maNhanVien)].map(m => {
                return{ 
                    key: maNhanVien,
                    tenNhanVien: m.tenNhanVien,
                    tongLan: m.arrPhanBo.length,
                }
            })
            

            result = { columns, data, tongDoanhThu };
        }
    }
    return result;

}