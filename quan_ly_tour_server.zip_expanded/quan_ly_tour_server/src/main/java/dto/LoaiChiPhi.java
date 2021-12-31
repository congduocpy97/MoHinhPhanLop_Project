package dto;

public class LoaiChiPhi
{
	private int maLoaiChiPhi;
	public int getMaLoaiChiPhi()
	{
		return this.maLoaiChiPhi;
	}
	public void setMaLoaiChiPhi(int value)
	{
		this.maLoaiChiPhi = value;
	}

	private String tenLoaiChiPhi;
	public String getTenLoaiChiPhi()
	{
		return this.tenLoaiChiPhi;
	}
	public void setTenLoaiChiPhi(String value)
	{
		this.tenLoaiChiPhi = value;
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


	public LoaiChiPhi(int MaLoaiChiPhi,String TenLoaiChiPhi,String TrangThai)
	{
		this.maLoaiChiPhi = MaLoaiChiPhi;
		this.tenLoaiChiPhi = TenLoaiChiPhi;
		this.trangThai = TrangThai;
	}
}
