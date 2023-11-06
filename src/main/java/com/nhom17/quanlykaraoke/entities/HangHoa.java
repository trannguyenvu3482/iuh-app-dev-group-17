package com.nhom17.quanlykaraoke.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HangHoa")
public class HangHoa {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "char(5)")
	private String maHangHoa;
	
	@Column(columnDefinition = "nvarchar(30)")
	private String tenHanghoa;
	
	@ManyToOne
	@JoinColumn(name = "maLoaiHangHoa",columnDefinition = "char(6)")
	private LoaiHangHoa loaiHangHoa;
	
	@Column(columnDefinition = "int")
	private int soLuongTon;
	
	@Column(columnDefinition = "money")
	private double donGia;
	
	@Column(columnDefinition = "bit")
	private boolean trangThai;
	
	public HangHoa(String maHangHoa, String tenHanghoa, LoaiHangHoa loaiHangHoa, int soLuongTon, double donGia,
			boolean trangThai) {
		this.maHangHoa = maHangHoa;
		this.tenHanghoa = tenHanghoa;
		this.loaiHangHoa = loaiHangHoa;
		this.soLuongTon = soLuongTon;
		this.donGia = donGia;
		this.trangThai = trangThai;
	}

	public String getMaHangHoa() {
		return maHangHoa;
	}

	public void setMaHangHoa(String maHangHoa) {
		this.maHangHoa = maHangHoa;
	}

	public LoaiHangHoa getLoaiHangHoa() {
		return loaiHangHoa;
	}

	public void setLoaiHangHoa(LoaiHangHoa loaiHangHoa) {
		this.loaiHangHoa = loaiHangHoa;
	}

	public String getTenHanghoa() {
		return tenHanghoa;
	}

	public void setTenHanghoa(String tenHanghoa) {
		this.tenHanghoa = tenHanghoa;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHangHoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HangHoa other = (HangHoa) obj;
		return Objects.equals(maHangHoa, other.maHangHoa);
	}

	@Override
	public String toString() {
		return "HangHoa [maHangHoa=" + maHangHoa + ", tenHanghoa=" + tenHanghoa + ", loaiHangHoa=" + loaiHangHoa
				+ ", soLuongTon=" + soLuongTon + ", donGia=" + donGia + ", trangThai=" + trangThai + "]";
	}
	
}
