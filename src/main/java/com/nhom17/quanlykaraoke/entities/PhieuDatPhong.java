package com.nhom17.quanlykaraoke.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 06-Nov-2023 4:14:29 PM
 */
@Entity
@Table(name = "PhieuDatPhong")
public class PhieuDatPhong {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "maPhieuDatPhong",columnDefinition = "char(7)")
	private String maPhieuDatPhong;
	
	@ManyToOne
	@JoinColumn(name = "maNhanVien",columnDefinition = "char(6)")
	private NhanVien nhanVien;
	
	@Column(columnDefinition = "bit")
	private boolean trangThai;
	
	@ManyToOne
	@JoinColumn(name = "maKhachHang",columnDefinition = "char(5)")
	private KhachHang khachHang;
	
	public PhieuDatPhong(String maPhieuDatPhong, NhanVien nhanVien, boolean trangThai, KhachHang khachHang) {
		this.maPhieuDatPhong = maPhieuDatPhong;
		this.nhanVien = nhanVien;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
	}
	
	public String getMaPhieuDatPhong() {
		return maPhieuDatPhong;
	}
	public void setMaPhieuDatPhong(String maPhieuDatPhong) {
		this.maPhieuDatPhong = maPhieuDatPhong;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhieuDatPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDatPhong other = (PhieuDatPhong) obj;
		return Objects.equals(maPhieuDatPhong, other.maPhieuDatPhong);
	}

	@Override
	public String toString() {
		return "PhieuDatPhong [maPhieuDatPhong=" + maPhieuDatPhong + ", nhanVien=" + nhanVien + ", trangThai="
				+ trangThai + ", khachHang=" + khachHang + "]";
	}
	
}
