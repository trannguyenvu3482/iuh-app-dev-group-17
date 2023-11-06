package com.nhom17.quanlykaraoke.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 06-Nov-2023 2:38:28 PM
 */

@Entity
@Table(name = "KhachHang")
public class KhachHang implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "char(5)")
	private String maKhachHang;

	@Column(columnDefinition = "nvarchar(30)")
	private String hoTen;

	@Column(columnDefinition = "varchar(10)")
	private String soDienThoai;

	@Column(columnDefinition = "varchar(12)")
	private String CCCD;

	/**
	 * 
	 */
	public KhachHang() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param hoTen
	 * @param soDienThoai
	 * @param cCCD
	 */
	public KhachHang(String hoTen, String soDienThoai, String cCCD) {
		super();
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		CCCD = cCCD;
	}

	/**
	 * @param maKhachHang
	 * @param hoTen
	 * @param soDienThoai
	 * @param cCCD
	 */
	public KhachHang(String maKhachHang, String hoTen, String soDienThoai, String cCCD) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		CCCD = cCCD;
	}

	/**
	 * @return the maKhachHang
	 */
	public String getMaKhachHang() {
		return maKhachHang;
	}

	/**
	 * @param maKhachHang the maKhachHang to set
	 */
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	/**
	 * @return the soDienThoai
	 */
	public String getSoDienThoai() {
		return soDienThoai;
	}

	/**
	 * @param soDienThoai the soDienThoai to set
	 */
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	/**
	 * @return the cCCD
	 */
	public String getCCCD() {
		return CCCD;
	}

	/**
	 * @param cCCD the cCCD to set
	 */
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	/**
	 * @return the hoTen
	 */
	public String getHoTen() {
		return hoTen;
	}

	/**
	 * @param hoTen the hoTen to set
	 */
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

}
