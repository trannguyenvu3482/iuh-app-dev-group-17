package com.nhom17.quanlykaraoke.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "maNV", nullable = false, columnDefinition = "nvarchar(255)")
	private String maNV;

	private String hoTen;

	private String matKhau;

	public NhanVien() {
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String hoTen, String matKhau) {
		this.hoTen = hoTen;
		this.matKhau = matKhau;
	}

	public NhanVien(String maNV, String hoTen, String matKhau) {
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.matKhau = matKhau;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", matKhau=" + matKhau + "]";
	}

}
