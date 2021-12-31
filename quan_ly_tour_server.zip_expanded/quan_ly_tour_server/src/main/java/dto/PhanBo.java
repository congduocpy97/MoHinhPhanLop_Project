package dto;

public class PhanBo
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

	private int maDoan;
	public int getMaDoan()
	{
		return this.maDoan;
	}
	public void setMaDoan(int value)
	{
		this.maDoan = value;
	}

	private String nhiemVu;
	public String getNhiemVu()
	{
		return this.nhiemVu;
	}
	public void setNhiemVu(String value)
	{
		this.nhiemVu = value;
	}


	public PhanBo(int MaNhanVien,int MaDoan,String NhiemVu)
	{
		this.maNhanVien = MaNhanVien;
		this.maDoan = MaDoan;
		this.nhiemVu = NhiemVu;
	}
}