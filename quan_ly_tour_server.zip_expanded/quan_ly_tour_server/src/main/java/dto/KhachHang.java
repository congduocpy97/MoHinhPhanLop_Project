package dto;

public class KhachHang
{
	private int maKhachHang;
	public int getMaKhachHang()
	{
		return this.maKhachHang;
	}
	public void setMaKhachHang(int value)
	{
		this.maKhachHang = value;
	}

	private String hoTen;
	public String getHoTen()
	{
		return this.hoTen;
	}
	public void setHoTen(String value)
	{
		this.hoTen = value;
	}

	private String soCMND;
	public String getSoCMND()
	{
		return this.soCMND;
	}
	public void setSoCMND(String value)
	{
		this.soCMND = value;
	}

	private String diaChi;
	public String getDiaChi()
	{
		return this.diaChi;
	}
	public void setDiaChi(String value)
	{
		this.diaChi = value;
	}

	private String gioiTinh;
	public String getGioiTinh()
	{
		return this.gioiTinh;
	}
	public void setGioiTinh(String value)
	{
		this.gioiTinh = value;
	}

	private String sDT;
	public String getSDT()
	{
		return this.sDT;
	}
	public void setSDT(String value)
	{
		this.sDT = value;
	}

	private String quocTich;
	public String getQuocTich()
	{
		return this.quocTich;
	}
	public void setQuocTinh(String value)
	{
		this.quocTich = value;
	}

	private String trangThai;
	public String getTrangThai()
	{
		return this.trangThai;
	}
	public void setTrangThai(String value)
	{
		this.trangThai = value;
	}


	public KhachHang(int MaKhachHang,String HoTen,String SoCMND,String DiaChi,String GioiTinh,String SDT,String QuocTich,String TrangThai)
	{
		this.maKhachHang = MaKhachHang;
		this.hoTen = HoTen;
		this.soCMND = SoCMND;
		this.diaChi = DiaChi;
		this.gioiTinh = GioiTinh;
		this.sDT = SDT;
		this.quocTich = QuocTich;
		this.trangThai = TrangThai;
	}
}
