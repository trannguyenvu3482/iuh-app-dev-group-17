package com.nhom17.quanlykaraoke.entities;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 06-Nov-2023 4:14:29 PM
 */
public class PhieuDatPhong {

	/**
	 * 
	 */
	private Phong phong;
	private NhanVien nhanVien;
	private boolean trangThai;
	private KhachHang khachHang;

	/**
	 * 
	 */
	public PhieuDatPhong() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param phong
	 * @param nhanVien
	 * @param trangThai
	 * @param khachHang
	 */
	public PhieuDatPhong(Phong phong, NhanVien nhanVien, boolean trangThai, KhachHang khachHang) {
		super();
		this.phong = phong;
		this.nhanVien = nhanVien;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
	}

	/**
	 * @return the phong
	 */
	public Phong getPhong() {
		return phong;
	}

	/**
	 * @param phong the phong to set
	 */
	public void setPhong(Phong phong) {
		this.phong = phong;
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
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

}
