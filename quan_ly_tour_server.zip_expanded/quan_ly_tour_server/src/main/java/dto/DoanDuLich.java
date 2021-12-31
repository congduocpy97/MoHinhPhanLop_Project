package dto;

public class DoanDuLich
{
	private int maDoan;
	public int getMaDoan()
	{
		return this.maDoan;
	}
	public void setMaDoan(int value)
	{
		this.maDoan = value;
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

	private java.sql.Date ngayKhoiHanh;
	public java.sql.Date getNgayKhoiHanh()
	{
		return this.ngayKhoiHanh;
	}
	public void setNgayKhoiHanh(java.sql.Date value)
	{
		this.ngayKhoiHanh = value;
	}

	private java.sql.Date ngayKetThuc;
	public java.sql.Date getNgayKetThuc()
	{
		return this.ngayKetThuc;
	}
	public void setNgayKetThuc(java.sql.Date value)
	{
		this.ngayKetThuc = value;
	}

	private double doanhThu;
	public double getDoanhThu()
	{
		return this.doanhThu;
	}
	public void setDoanhThu(double value)
	{
		this.doanhThu = value;
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


	public DoanDuLich(int MaDoan,int MaTour,java.sql.Date NgayKhoiHanh,java.sql.Date NgayKetThuc,double DoanhThu,String TrangThai)
	{
		this.maDoan = MaDoan;
		this.maTour = MaTour;
		this.ngayKhoiHanh = NgayKhoiHanh;
		this.ngayKetThuc = NgayKetThuc;
		this.doanhThu = DoanhThu;
		this.trangThai = TrangThai;
	}
	public DoanDuLich() {
		super();
	}
	
}
