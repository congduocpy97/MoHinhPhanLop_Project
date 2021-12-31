package dto;

public class LoaiHinhDuLich
{
	private int maLoaiHinh;
	public int getMaLoaiHinh()
	{
		return this.maLoaiHinh;
	}
	public void setMaLoaiHinh(int value)
	{
		this.maLoaiHinh = value;
	}

	private String tenLoaiHinh;
	public String getTenLoaiHinh()
	{
		return this.tenLoaiHinh;
	}
	public void setTenLoaiHinh(String value)
	{
		this.tenLoaiHinh = value;
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


	public LoaiHinhDuLich(int MaLoaiHinh,String TenLoaiHinh,String TrangThai)
	{
		this.maLoaiHinh = MaLoaiHinh;
		this.tenLoaiHinh = TenLoaiHinh;
		this.trangThai = TrangThai;
	}
}
