package dto;

public class DiaDiem
{
	private int maDiaDiem;
	public int getMaDiaDiem()
	{
		return this.maDiaDiem;
	}
	public void setMaDiaDiem(int value)
	{
		this.maDiaDiem = value;
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

	private String trangThai;
	public String getTrangThai()
	{
		return this.trangThai;
	}
	public void setTrangThai(String value)
	{
		this.trangThai = value;
	}


	public DiaDiem(int MaDiaDiem,String Ten,String TrangThai)
	{
		this.maDiaDiem = MaDiaDiem;
		this.ten = Ten;
		this.trangThai = TrangThai;
	}
	public DiaDiem() {
		super();
	}
	
}
