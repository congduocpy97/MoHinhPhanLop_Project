/* eslint-disable react/jsx-pascal-case */
import React, { Component } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ChiPhi_Layout from './View/ChiPhi/ChiPhi_Layout';
import DiaDiem_Layout from './View/DiaDiem/DiaDiem_Layout';
import DoanDuLich_Layout from './View/DoanDuLich/DoanDuLich_Layout';
import GiaTour_Layout from './View/GiaTour/GiaTour_Layout';
import KhachHang_Layout from './View/KhachHang/KhachHang_Layout';
import LoaiChiPhi_Layout from './View/LoaiChiPhi/LoaiChiPhi_Layout';
import LoaiHinhDuLich_Layout from './View/LoaiHinhDuLich/LoaiHinhDuLich_Layout';
import MasterLayout from './View/Master_Layout';
import NhanVien_Layout from './View/NhanVien/NhanVien_Layout';
import ThongKe_Layout from './View/ThongKe/ThongKe_Layout';
import TourDuLich_Layout from './View/TourDuLich/TourDuLich_Layout';
import TrangThai_Layout from './View/TrangThai/TrangThai_Layout';


export class RouterApp extends Component {
    render() {
        return (
            <div>
                <BrowserRouter>
                    <Routes>
                        <Route path="/" element={<MasterLayout component={(<div></div>)} />} />
                        <Route path="chiphi" element={<ChiPhi_Layout />} />
                        <Route path="trangthai" element={<TrangThai_Layout />} />
                        <Route path="diadiem" element={<DiaDiem_Layout />} />
                        <Route path="loaihinhdulich" element={<LoaiHinhDuLich_Layout />} />
                        <Route path="loaichiphi" element={<LoaiChiPhi_Layout />} />
                        <Route path="tourdulich" element={<TourDuLich_Layout />} />
                        <Route path="khachhang" element={<KhachHang_Layout />} />
                        <Route path="nhanvien" element={<NhanVien_Layout />} />
                        <Route path="giatour" element={<GiaTour_Layout />} />
                        <Route path="doandulich" element={<DoanDuLich_Layout />} />
                        <Route path="thongke" element={<ThongKe_Layout/>} />
                    </Routes>
                </BrowserRouter>
            </div>
        )
    }
}

export default RouterApp
