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
	@Column(name = "maPhieuDatPhong", columnDefinition = "char(7)")
	private String maPhieuDatPhong;

	@ManyToOne
	@JoinColumn(name = "maNhanVien", columnDefinition = "char(6)")
	private NhanVien nhanVien;

	@Column(columnDefinition = "bit")
	private boolean trangThai;

	@ManyToOne
	@JoinColumn(name = "maKhachHang", columnDefinition = "char(5)")
	private KhachHang khachHang;

	@Column(columnDefinition = "money")
	private double tongTien;

	public PhieuDatPhong() {
		// TODO Auto-generated constructor stub
	}

	public PhieuDatPhong(String maPhieuDatPhong, NhanVien nhanVien, boolean trangThai, KhachHang khachHang) {
		this.maPhieuDatPhong = maPhieuDatPhong;
		this.nhanVien = nhanVien;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
	}

	/**
	 * @return the nhanVien
	 */
	public NhanVien getNhanVien() {
		return nhanVien;
	}

	/**
	 * @param nhanVien the nhanVien to set
	 */
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	/**
	 * @return the trangThai
	 */
	public boolean isTrangThai() {
		return trangThai;
	}

	/**
	 * @param trangThai the trangThai to set
	 */
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	/**
	 * @return the khachHang
	 */
	public KhachHang getKhachHang() {
		return khachHang;
	}

	/**
	 * @param khachHang the khachHang to set
	 */

	public String getMaPhieuDatPhong() {
		return maPhieuDatPhong;
	}

	public void setMaPhieuDatPhong(String maPhieuDatPhong) {
		this.maPhieuDatPhong = maPhieuDatPhong;
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

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

}
