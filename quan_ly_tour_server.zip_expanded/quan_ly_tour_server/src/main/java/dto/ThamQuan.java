package dto;

public class ThamQuan
{
	private int maTour;
	public int getMaTour()
	{
		return this.maTour;
	}
	public void setMaTour(int value)
	{
		this.maTour = value;
	}

	private int maDiaDiem;
	public int getMaDiaDiem()
	{
		return this.maDiaDiem;
	}
	public void setMaDiaDiem(int value)
	{
		this.maDiaDiem = value;
	}

	private int thuTu;
	public int getThuTu()
	{
		return this.thuTu;
	}
	public void setThuTu(int value)
	{
		this.thuTu = value;
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


	public ThamQuan(int MaTour,int MaDiaDiem,int ThuTu,String TrangThai)
	{
		this.maTour = MaTour;
		this.maDiaDiem = MaDiaDiem;
		this.thuTu = ThuTu;
		this.trangThai = TrangThai;
	}
}
