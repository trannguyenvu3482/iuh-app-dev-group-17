package com.nhom17.quanlykaraoke.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ChiTietDichVu")
public class ChiTietDichVu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "maPhieuDatPhong", columnDefinition = "char(7)")
	private PhieuDatPhong phieuDatPhong;

	@Id
	@ManyToOne
	@JoinColumn(name = "maPhong", columnDefinition = "char(4)")
	private Phong phong;

	@Id
	@ManyToOne
	@JoinColumn(name = "maHangHoa", columnDefinition = "char(5)")
	private HangHoa hangHoa;

	@Column(columnDefinition = "int")
	private int soLuong;

	@Override
	public int hashCode() {
		return Objects.hash(hangHoa, phieuDatPhong, phong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietDichVu other = (ChiTietDichVu) obj;
		return Objects.equals(hangHoa, other.hangHoa) && Objects.equals(phieuDatPhong, other.phieuDatPhong)
				&& Objects.equals(phong, other.phong);
	}

	public ChiTietDichVu() {

	}

	public ChiTietDichVu(PhieuDatPhong phieuDatPhong, HangHoa hangHoa, Phong phong, int soLuong) {
		this.phieuDatPhong = phieuDatPhong;
		this.hangHoa = hangHoa;
		this.phong = phong;
		this.soLuong = soLuong;
	}

	public PhieuDatPhong getPhieuDatPhong() {
		return phieuDatPhong;
	}

	public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
	}

	public HangHoa getHangHoa() {
		return hangHoa;
	}

	public void setHangHoa(HangHoa hangHoa) {
		this.hangHoa = hangHoa;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "ChiTietDichVu [phieuDatPhong=" + phieuDatPhong + ", hangHoa=" + hangHoa + ", phong=" + phong
				+ ", soLuong=" + soLuong + "]";
	}

}
