package com.nhom17.quanlykaraoke.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ChiTietDichVu")
public class ChiTietDichVu implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "maPhieuDatPhong",columnDefinition = "char(7)")
	private PhieuDatPhong phieuDatPhong;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "maHangHoa",columnDefinition = "char(5)")
	private HangHoa hangHoa;
	
	@Column(columnDefinition = "int")
	private int soLuong;
	
	public ChiTietDichVu(PhieuDatPhong phieuDatPhong, HangHoa hangHoa, int soLuong) {
		this.phieuDatPhong = phieuDatPhong;
		this.hangHoa = hangHoa;
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

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "ChiTietDichVu [phieuDatPhong=" + phieuDatPhong + ", hangHoa=" + hangHoa + ", soLuong=" + soLuong + "]";
	}
	
}
