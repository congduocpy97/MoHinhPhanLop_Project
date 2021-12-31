package dto;

public class TrangThai
{
	private String trangThai;
	public String getTrangThai()
	{
		return this.trangThai;
	}
	public void setTrangThai(String value)
	{
		this.trangThai = value;
	}

	private String ten;
	public String getTen()
	{
		return this.ten;
	}
	public void setTen(String value)
	{
		this.ten = value;
	}


	public TrangThai(String TrangThai,String Ten)
	{
		this.trangThai = TrangThai;
		this.ten = Ten;
	}
}
