package dto;

public class TourDuLich
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

	private String tenGoi;
	public String getTenGoi()
	{
		return this.tenGoi;
	}
	public void setTenGoi(String value)
	{
		this.tenGoi = value;
	}

	private String dacDiem;
	public String getDacDiem()
	{
		return this.dacDiem;
	}
	public void setDacDiem(String value)
	{
		this.dacDiem = value;
	}

	private int maLoaiHinh;
	public int getMaLoaiHinh()
	{
		return this.maLoaiHinh;
	}
	public void setMaLoaiHinh(int value)
	{
		this.maLoaiHinh = value;
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


	public TourDuLich(int MaTour,String TenGoi,String DacDiem,int MaLoaiHinh,String TrangThai)
	{
		this.maTour = MaTour;
		this.tenGoi = TenGoi;
		this.dacDiem = DacDiem;
		this.maLoaiHinh = MaLoaiHinh;
		this.trangThai = TrangThai;
	}
}
