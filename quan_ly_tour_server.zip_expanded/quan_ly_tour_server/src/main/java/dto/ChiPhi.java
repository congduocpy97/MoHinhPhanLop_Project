package dto;

public class ChiPhi {
	private int maChiPhi;

	public int getMaChiPhi() {
		return this.maChiPhi;
	}

	public void setMaChiPhi(int value) {
		this.maChiPhi = value;
	}

	private int maDoan;

	public int getMaDoan() {
		return this.maDoan;
	}

	public void setMaDoan(int value) {
		this.maDoan = value;
	}

	private double soTien;

	public double getSoTien() {
		return this.soTien;
	}

	public void setSoTien(double value) {
		this.soTien = value;
	}

	private int maLoaiChiPhi;

	public int getMaLoaiChiPhi() {
		return this.maLoaiChiPhi;
	}

	public void setMaLoaiChiPhi(int value) {
		this.maLoaiChiPhi = value;
	}

	public ChiPhi(int MaChiPhi, int MaDoan, double SoTien, int MaLoaiChiPhi) {
		this.maChiPhi = MaChiPhi;
		this.maDoan = MaDoan;
		this.soTien = SoTien;
		this.maLoaiChiPhi = MaLoaiChiPhi;
	}

	public ChiPhi() {
		super();
	}

}
