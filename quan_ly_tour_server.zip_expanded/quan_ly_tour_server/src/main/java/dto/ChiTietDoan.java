package dto;

public class ChiTietDoan {
	private int maDoan;
	public int getMaDoan()
	{
		return this.maDoan;
	}
	public void setMaDoan(int value)
	{
		this.maDoan = value;
	}

	private int maKhachHang;
	public int getMaKhachHang()
	{
		return this.maKhachHang;
	}
	public void setMaKhachHang(int value)
	{
		this.maKhachHang = value;
	}


	public ChiTietDoan(int MaDoan,int MaKhachHang)
	{
		this.maDoan = MaDoan;
		this.maKhachHang = MaKhachHang;
	}
	public ChiTietDoan() {
		super();
	}
	
	
}
