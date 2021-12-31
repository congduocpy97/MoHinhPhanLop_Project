package dto;

public class NhanVien
{
	private int maNhanVien;
	public int getMaNhanVien()
	{
		return this.maNhanVien;
	}
	public void setMaNhanVien(int value)
	{
		this.maNhanVien = value;
	}

	private String tenNhanVien;
	public String getTenNhanVien()
	{
		return this.tenNhanVien;
	}
	public void setTenNhanVien(String value)
	{
		this.tenNhanVien = value;
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


	public NhanVien(int MaNhanVien,String TenNhanVien,String TrangThai)
	{
		this.maNhanVien = MaNhanVien;
		this.tenNhanVien = TenNhanVien;
		this.trangThai = TrangThai;
	}
}
