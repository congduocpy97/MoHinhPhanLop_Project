-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 31, 2021 at 06:38 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ql_tour_dulich`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietdoan`
--

CREATE TABLE `chitietdoan` (
  `MaDoan` int(11) NOT NULL,
  `MaKhachHang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietdoan`
--

INSERT INTO `chitietdoan` (`MaDoan`, `MaKhachHang`) VALUES
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(3, 9),
(3, 10),
(3, 11),
(3, 12),
(4, 13),
(4, 14),
(4, 15),
(4, 16),
(5, 11),
(5, 12),
(5, 7),
(5, 3),
(6, 10),
(6, 14),
(6, 5),
(6, 15),
(7, 1),
(7, 6),
(7, 15),
(7, 9),
(7, 4),
(7, 13),
(8, 11),
(8, 15),
(8, 9),
(8, 2),
(13, 3),
(13, 4),
(14, 6),
(14, 15),
(15, 7),
(16, 1),
(17, 2),
(17, 4),
(18, 4),
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `diadiem`
--

CREATE TABLE `diadiem` (
  `MaDiaDiem` int(11) NOT NULL,
  `TenDiaDiem` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `diadiem`
--

INSERT INTO `diadiem` (`MaDiaDiem`, `TenDiaDiem`) VALUES
(1, 'Cần Giờ'),
(2, '	Phú Quốc'),
(3, 'Đà Lạt'),
(4, '	Sa Pa'),
(5, 'Vũng Tàu'),
(6, 'Nha Trang'),
(7, 'Đà Nẵng'),
(8, 'Côn Đảo'),
(9, '	Phan Thiết'),
(10, 'Huế');

-- --------------------------------------------------------

--
-- Table structure for table `doandulich`
--

CREATE TABLE `doandulich` (
  `MaDoan` int(11) NOT NULL,
  `MaTour` int(11) NOT NULL,
  `NgayKhoiHanh` date NOT NULL,
  `NgayKetThuc` date NOT NULL,
  `MaLoaiChiPhi` int(11) NOT NULL,
  `DoanhThu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doandulich`
--

INSERT INTO `doandulich` (`MaDoan`, `MaTour`, `NgayKhoiHanh`, `NgayKetThuc`, `MaLoaiChiPhi`, `DoanhThu`) VALUES
(1, 2, '2021-12-28', '2021-12-30', 1, 0),
(2, 1, '2022-01-03', '2022-01-05', 1, 0),
(3, 3, '2021-12-25', '2021-12-27', 1, 0),
(4, 4, '2022-01-05', '2022-01-08', 1, 0),
(5, 5, '2022-01-12', '2022-01-14', 1, 0),
(6, 6, '2022-01-10', '2022-01-12', 1, 0),
(7, 7, '2021-11-14', '2021-11-16', 1, 0),
(8, 8, '2022-01-13', '2022-01-15', 1, 0),
(13, 9, '2022-01-16', '2022-01-18', 1, 0),
(14, 10, '2021-12-03', '2021-12-05', 1, 0),
(15, 2, '2021-12-28', '2021-12-30', 2, 0),
(16, 2, '2021-12-28', '2021-12-30', 3, 0),
(17, 1, '2022-01-01', '2022-01-03', 2, 0),
(18, 1, '2022-01-01', '2022-01-03', 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `giatour`
--

CREATE TABLE `giatour` (
  `MaGia` int(11) NOT NULL,
  `MaTour` int(11) NOT NULL,
  `ThoiGIanBatDau` date NOT NULL,
  `ThoiGianKetThuc` date NOT NULL,
  `ThanhTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `giatour`
--

INSERT INTO `giatour` (`MaGia`, `MaTour`, `ThoiGIanBatDau`, `ThoiGianKetThuc`, `ThanhTien`) VALUES
(1, 9, '2021-12-21', '2022-01-19', 1500000),
(2, 3, '2021-12-20', '2022-01-15', 2000000),
(3, 1, '2021-12-31', '2022-01-10', 3000000),
(4, 4, '2021-12-30', '2022-01-15', 4000000),
(5, 5, '2021-01-22', '2022-12-15', 2000000),
(6, 6, '2022-01-05', '2022-01-20', 2000000),
(7, 7, '2021-11-10', '2021-11-20', 2500000),
(8, 8, '2022-01-05', '2022-01-15', 3000000),
(9, 10, '2021-12-01', '2021-12-15', 2500000),
(10, 2, '2021-12-23', '2021-12-30', 2000000);

-- --------------------------------------------------------

--
-- Table structure for table `khach`
--

CREATE TABLE `khach` (
  `MaKhachHang` int(11) NOT NULL,
  `HoTen` varchar(20) NOT NULL,
  `SoCMND` varchar(12) NOT NULL,
  `DiaChi` varchar(50) NOT NULL,
  `GioiTinh` varchar(3) NOT NULL,
  `SDT` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khach`
--

INSERT INTO `khach` (`MaKhachHang`, `HoTen`, `SoCMND`, `DiaChi`, `GioiTinh`, `SDT`) VALUES
(1, '	Lê Công Được', '221442360', '156 võ thị sáu, Đông Hòa, Dĩ An Bình Dương', 'Nam', 868521234),
(2, 'Trần Ngọc Hải', '	07920000226', '223 An Dương Vương P3 Q5 TPHCM', 'Nam', 868965317),
(3, 'Lý Vĩ Hào', '7894561230', '123 đông minh, Đông Hòa, Dĩ An Bình Dương', 'Nam', 824565445),
(4, 'Phạm Đàm Thoại', '	2658956789', '156 nguyễn hữu cảnh, Đông Hòa, Dĩ An Bình Dương', 'Nam', 824565427),
(5, 'Nguyễn Thị Bé Ba', '	3636454568', '78 lý thường kiệt, Đông Hòa, Dĩ An, Bình Dương', 'Nữ', 824264100),
(6, 'Lê Hữu Nghĩa', '265895123456', '156 lý thường kiệt, Đông Hòa, Dĩ An Bình Dương', 'Nam', 824578945),
(7, 'Bùi Hữu Bằng', '2658789456', '45 lý thường kiệt, Đông Hòa, Dĩ An Bình Dương', 'Nam', 824569900),
(8, 'Lê Bá Phước', '	265890088', '12 võ thị sáu, Đông Hòa, Dĩ An Bình Dương', 'Nam', 824560088),
(9, 'Lê Quang Lâm', '079200004012', '55 Sư Vạn Hạnh P2 Q10 TPHCM', 'Nam', 739121241),
(10, 'Nguyên Ngọc Lan', '079200008941', '18 Lê Đại Hành P4 Q11 TPHCM', 'Nữ', 908821231),
(11, '	Tạ Nguyên Hiếu', '079200001234', '2/12 Tôn Đức Thắng P2 Q1 TPHCM', 'Nam', 868343242),
(12, 'Lý Gia Linh', '079200000921', '123/32 Phạm 9Phú Thứ P9 Q6 TPHCM', 'Nữ', 794231269),
(13, 'Phạm Đàm Thoại', '	261419989', '156 võ thị sáu, Đông Hòa, Dĩ An Bình Dương', 'Nam', 824565458),
(14, '	Lê Thị Thúy Liễu', '3636454569', '89 võ thị sáu, Đông Hòa, Dĩ An Bình Dương', 'Nữ', 824264198),
(15, 'Lê Bá Ôn', '2658956784', '154 đông minh, Đông Hòa, Dĩ An Bình Dương', 'Nam', 824561234),
(16, 'Phan Xích Long', '4567891230', '56 lý thường kiệt, Đông Hòa, Dĩ An, Bình Dương', 'Nam', 907651234);

-- --------------------------------------------------------

--
-- Table structure for table `loaichiphi`
--

CREATE TABLE `loaichiphi` (
  `MaLoaiChiPhi` int(11) NOT NULL,
  `TenLoaiChiPhi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loaichiphi`
--

INSERT INTO `loaichiphi` (`MaLoaiChiPhi`, `TenLoaiChiPhi`) VALUES
(1, 'Phí di chuyển'),
(2, 'Phí khách sạn'),
(3, 'Phí ăn uống');

-- --------------------------------------------------------

--
-- Table structure for table `loaihinhdulich`
--

CREATE TABLE `loaihinhdulich` (
  `MaLoaiHinh` int(11) NOT NULL,
  `TenLoaiHinh` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loaihinhdulich`
--

INSERT INTO `loaihinhdulich` (`MaLoaiHinh`, `TenLoaiHinh`) VALUES
(1, 'Du lịch biển'),
(2, 'Du Lịch Tham Quan'),
(3, 'Du Lịch Văn Hóa'),
(4, 'Du Lịch Nghỉ Dưỡng'),
(5, 'Du Lịch Ẩm Thực');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNhanVien` int(11) NOT NULL,
  `TenNhanVien` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNhanVien`, `TenNhanVien`) VALUES
(1, 'Lê Thị Nam '),
(2, 'Lê Công Được'),
(3, 'Võ Chí Thiện'),
(4, '	Hoàng Ngọc Thắng');

-- --------------------------------------------------------

--
-- Table structure for table `phanbonhanvien_doan`
--

CREATE TABLE `phanbonhanvien_doan` (
  `MaDoan` int(11) NOT NULL,
  `MaNhanVien` int(11) NOT NULL,
  `NhiemVu` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phanbonhanvien_doan`
--

INSERT INTO `phanbonhanvien_doan` (`MaDoan`, `MaNhanVien`, `NhiemVu`) VALUES
(1, 1, 'Hướng dẫn viên'),
(2, 2, 'Hướng dẫn viên'),
(3, 3, 'Hướng dẫn viên'),
(4, 4, 'Hướng dẫn viên'),
(1, 4, 'Hướng dẫn viên'),
(2, 3, 'Hướng dẫn viên'),
(5, 4, 'Hướng dẫn viên'),
(6, 3, 'Hướng dẫn viên'),
(7, 2, 'Hướng dẫn viên'),
(8, 2, 'Hướng dẫn viên'),
(13, 4, 'Hướng dẫn viên'),
(14, 1, 'Hướng dẫn viên');

-- --------------------------------------------------------

--
-- Table structure for table `tourdulich`
--

CREATE TABLE `tourdulich` (
  `MaTour` int(11) NOT NULL,
  `TenGoi` varchar(100) NOT NULL,
  `MaLoaiHinh` int(11) NOT NULL,
  `MaDiaDiem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tourdulich`
--

INSERT INTO `tourdulich` (`MaTour`, `TenGoi`, `MaLoaiHinh`, `MaDiaDiem`) VALUES
(1, 'Hồ Chí Minh - Đà Lạt', 4, 3),
(2, '	Quận 1 - Cần Giờ', 2, 1),
(3, 'Hồ Chí Minh - Phan Thiết', 5, 9),
(4, '	Hồ chí minh - Sapa', 2, 4),
(5, 'Hồ Chí Minh - Phú Quốc', 1, 2),
(6, 'Hồ Chí Minh - Vũng Tàu', 1, 5),
(7, 'Hồ Chí Minh - Nha Trang', 2, 6),
(8, 'Hồ Chí Minh - Đà Nẵng', 2, 7),
(9, 'Hồ Chí Minh - Côn Đảo', 1, 8),
(10, 'Hồ Chí Minh - Huế', 3, 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietdoan`
--
ALTER TABLE `chitietdoan`
  ADD KEY `chitietdoan_ibfk_1` (`MaDoan`),
  ADD KEY `chitietdoan_ibfk_2` (`MaKhachHang`);

--
-- Indexes for table `diadiem`
--
ALTER TABLE `diadiem`
  ADD PRIMARY KEY (`MaDiaDiem`);

--
-- Indexes for table `doandulich`
--
ALTER TABLE `doandulich`
  ADD PRIMARY KEY (`MaDoan`),
  ADD KEY `doandulich_ibfk_1` (`MaTour`),
  ADD KEY `doandulich_ibfk_2` (`MaLoaiChiPhi`);

--
-- Indexes for table `giatour`
--
ALTER TABLE `giatour`
  ADD PRIMARY KEY (`MaGia`),
  ADD KEY `giatour_ibfk_1` (`MaTour`);

--
-- Indexes for table `khach`
--
ALTER TABLE `khach`
  ADD PRIMARY KEY (`MaKhachHang`);

--
-- Indexes for table `loaichiphi`
--
ALTER TABLE `loaichiphi`
  ADD PRIMARY KEY (`MaLoaiChiPhi`);

--
-- Indexes for table `loaihinhdulich`
--
ALTER TABLE `loaihinhdulich`
  ADD PRIMARY KEY (`MaLoaiHinh`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNhanVien`);

--
-- Indexes for table `phanbonhanvien_doan`
--
ALTER TABLE `phanbonhanvien_doan`
  ADD KEY `phanbonhanvien_doan_ibfk_1` (`MaDoan`),
  ADD KEY `phanbonhanvien_doan_ibfk_2` (`MaNhanVien`);

--
-- Indexes for table `tourdulich`
--
ALTER TABLE `tourdulich`
  ADD PRIMARY KEY (`MaTour`),
  ADD KEY `tourdulich_ibfk_1` (`MaLoaiHinh`),
  ADD KEY `tourdulich_ibfk_2` (`MaDiaDiem`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `diadiem`
--
ALTER TABLE `diadiem`
  MODIFY `MaDiaDiem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `doandulich`
--
ALTER TABLE `doandulich`
  MODIFY `MaDoan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `giatour`
--
ALTER TABLE `giatour`
  MODIFY `MaGia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `khach`
--
ALTER TABLE `khach`
  MODIFY `MaKhachHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `loaichiphi`
--
ALTER TABLE `loaichiphi`
  MODIFY `MaLoaiChiPhi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `loaihinhdulich`
--
ALTER TABLE `loaihinhdulich`
  MODIFY `MaLoaiHinh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNhanVien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tourdulich`
--
ALTER TABLE `tourdulich`
  MODIFY `MaTour` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietdoan`
--
ALTER TABLE `chitietdoan`
  ADD CONSTRAINT `chitietdoan_ibfk_1` FOREIGN KEY (`MaDoan`) REFERENCES `doandulich` (`MaDoan`),
  ADD CONSTRAINT `chitietdoan_ibfk_2` FOREIGN KEY (`MaKhachHang`) REFERENCES `khach` (`MaKhachHang`);

--
-- Constraints for table `doandulich`
--
ALTER TABLE `doandulich`
  ADD CONSTRAINT `doandulich_ibfk_1` FOREIGN KEY (`MaTour`) REFERENCES `tourdulich` (`MaTour`),
  ADD CONSTRAINT `doandulich_ibfk_2` FOREIGN KEY (`MaLoaiChiPhi`) REFERENCES `loaichiphi` (`MaLoaiChiPhi`);

--
-- Constraints for table `giatour`
--
ALTER TABLE `giatour`
  ADD CONSTRAINT `giatour_ibfk_1` FOREIGN KEY (`MaTour`) REFERENCES `tourdulich` (`MaTour`);

--
-- Constraints for table `phanbonhanvien_doan`
--
ALTER TABLE `phanbonhanvien_doan`
  ADD CONSTRAINT `phanbonhanvien_doan_ibfk_1` FOREIGN KEY (`MaDoan`) REFERENCES `doandulich` (`MaDoan`),
  ADD CONSTRAINT `phanbonhanvien_doan_ibfk_2` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`);

--
-- Constraints for table `tourdulich`
--
ALTER TABLE `tourdulich`
  ADD CONSTRAINT `tourdulich_ibfk_1` FOREIGN KEY (`MaLoaiHinh`) REFERENCES `loaihinhdulich` (`MaLoaiHinh`),
  ADD CONSTRAINT `tourdulich_ibfk_2` FOREIGN KEY (`MaDiaDiem`) REFERENCES `diadiem` (`MaDiaDiem`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
