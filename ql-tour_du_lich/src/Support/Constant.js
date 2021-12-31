import {
	UserOutlined,
	CustomerServiceOutlined,
	CarOutlined,
	DollarOutlined,
	SettingOutlined,
	AccountBookOutlined,
} from '@ant-design/icons';

export const Data_Base_Table = {
    ChiPhi : "0100",
	ChiTietDoan : "0200",
	DiaDiem : "0300",
	DoanDuLich : "0400",
	GiaTour : "0500",
	KhachHang : "0600",
	LoaiChiPhi : "0700",
	LoaiHinhDuLich : "0800",
	NhanVien : "0900",
	NoiDungTour : "1000",
	PhanBo : "1100",
	ThamQuan : "1200",
	TourDuLich : "1300",
	TrangThai : "1400",
}
export const Menu_List = [
	{name: "Dịch vụ", icon: <CarOutlined />, view: [{name: "Tour du lịch", key: "TourDuLich"},{name: "Đoàn du lịch", key: "DoanDuLich"}]},
	{name: "Khách hàng", icon: <CustomerServiceOutlined />, view: [{name: "Khách hàng", key: "KhachHang"}]},
	{name: "Tài chính", icon: <DollarOutlined />, view: [{name: "Giá tour", key: "GiaTour"}, {name: "Chi phí", key: "ChiPhi"}]},
	{name: "Nhân sự", icon: <UserOutlined/>, view: [{name: "Nhân viên", key: "NhanVien"}]},
	{name: "Hệ thống", icon: <SettingOutlined />, view: [{name: "Địa điểm", key: "DiaDiem"}, {name: "Loại hình du lịch", key: "LoaiHinhDuLich"} ,{name: "Loại chi phí", key: "LoaiChiPhi"} ,{name: "Trạng thái", key: "TrangThai"}]},
	{name: "Thống kê", icon: <AccountBookOutlined /> , view: [{name: "Thống kê", key: "ThongKe"}]}
]
export const LayoutFormat = {
    ColLabel: 4,
    ColInput: 7,
	ColOffset: 2,
    ColCardLabel: 8,
    ColCardInput: 16,
};

export const HeaderString = {
	searchModal: "Thông tin tìm kiếm",
    search: "Tìm kiếm",
	create: "Thêm mới",
	view: "Xem",
	edit: "Chỉnh sửa",
	delete: "Xóa",
	hide: "Hủy hiển thị"

};

export const GridStyle = {
	width: '100%',
};
export const InputStyle = {
   width: '100%',
}
export const APIServer = "http://localhost:8083/"
// export const APIServer = "http://14.176.204.153:8083/"
export const dateTimeReviver = function (key, value) {
    var a;
    if (typeof value === 'string') {
        a = /\/Date\((\d*)\)\//.exec(value);
        if (a) {
            return new Date(+a[1]);
        }
    }
    return value;
}
