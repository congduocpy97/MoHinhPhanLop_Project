package dto;

public class NoiDungTour
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

	private String hanhTrinh;
	public String getHanhTrinh()
	{
		return this.hanhTrinh;
	}
	public void setHanhTrinh(String value)
	{
		this.hanhTrinh = value;
	}

	private String khachSan;
	public String getKhachSan()
	{
		return this.khachSan;
	}
	public void setKhachSan(String value)
	{
		this.khachSan = value;
	}

	private String diaDiemThamQuan;
	public String getDiaDiemThamQuan()
	{
		return this.diaDiemThamQuan;
	}
	public void setDiaDiemThamQuan(String value)
	{
		this.diaDiemThamQuan = value;
	}


	public NoiDungTour(int MaDoan,String HanhTrinh,String KhachSan,String DiaDiemThamQuan)
	{
		this.maDoan = MaDoan;
		this.hanhTrinh = HanhTrinh;
		this.khachSan = KhachSan;
		this.diaDiemThamQuan = DiaDiemThamQuan;
	}
}