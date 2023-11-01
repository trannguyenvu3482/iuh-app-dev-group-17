package com.nhom17.quanlykaraoke.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 31-Oct-2023 1:38:44 PM
 */

@Entity
@Table(name = "LoaiPhong")
public class LoaiPhong implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "char(5)")
	private String maLoaiPhong;

	@Column(columnDefinition = "nvarchar(30)")
	private String tenLoaiPhong;

	@Column(columnDefinition = "int")
	private int kichThuoc;

	@Column(columnDefinition = "money")
	private double phuPhi;

	public LoaiPhong() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param maLoaiPhong
	 * @param tenLoaiPhong
	 * @param kichThuoc
	 * @param phuPhi
	 */
	public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, int kichThuoc, double phuPhi) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.kichThuoc = kichThuoc;
		this.phuPhi = phuPhi;
	}

	/**
	 * @return the maLoaiPhong
	 */
	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}

	/**
	 * @param maLoaiPhong the maLoaiPhong to set
	 */
	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}

	/**
	 * @return the kichThuoc
	 */
	public int getKichThuoc() {
		return kichThuoc;
	}

	/**
	 * @param kichThuoc the kichThuoc to set
	 */
	public void setKichThuoc(int kichThuoc) {
		this.kichThuoc = kichThuoc;
	}

	/**
	 * @return the phuPhi
	 */
	public double getPhuPhi() {
		return phuPhi;
	}

	/**
	 * @param phuPhi the phuPhi to set
	 */
	public void setPhuPhi(double phuPhi) {
		this.phuPhi = phuPhi;
	}

	/**
	 * @return the tenLoaiPhong
	 */
	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}

	/**
	 * @param tenLoaiPhong the tenLoaiPhong to set
	 */
	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}
}
