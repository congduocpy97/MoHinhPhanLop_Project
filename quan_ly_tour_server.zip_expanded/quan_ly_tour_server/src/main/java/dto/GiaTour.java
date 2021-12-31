package dto;

public class GiaTour
{
	private int maGia;
	public int getMaGia()
	{
		return this.maGia;
	}
	public void setMaGia(int value)
	{
		this.maGia = value;
	}

	private int maTour;
	public int getMaTour()
	{
		return this.maTour;
	}
	public void setMaTour(int value)
	{
		this.maTour = value;
	}

	private double thanhTien;
	public double getThanhTien()
	{
		return this.thanhTien;
	}
	public void setThanhTien(double value)
	{
		this.thanhTien = value;
	}

	private java.sql.Date thoiGianBatDau;
	public java.sql.Date getThoiGianBatDau()
	{
		return this.thoiGianBatDau;
	}
	public void setThoiGianBatDau(java.sql.Date value)
	{
		this.thoiGianBatDau = value;
	}

	private java.sql.Date thoiGianKetThuc;
	public java.sql.Date getThoiGianKetThuc()
	{
		return this.thoiGianKetThuc;
	}
	public void setThoiGianKetThuc(java.sql.Date value)
	{
		this.thoiGianKetThuc = value;
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


	public GiaTour(int MaGia,int MaTour,double ThanhTien,java.sql.Date ThoiGianBatDau,java.sql.Date ThoiGianKetThuc,String TrangThai)
	{
		this.maGia = MaGia;
		this.maTour = MaTour;
		this.thanhTien = ThanhTien;
		this.thoiGianBatDau = ThoiGianBatDau;
		this.thoiGianKetThuc = ThoiGianKetThuc;
		this.trangThai = TrangThai;
	}
}