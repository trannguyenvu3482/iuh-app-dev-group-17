package com.nhom17.quanlykaraoke.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 08-Nov-2023 11:34:33 PM
 */
@Embeddable
public class ChiTietPhieuDatPhongPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private String phong;
	private String phieuDatPhong;

	/**
	 * 
	 */
	public ChiTietPhieuDatPhongPK() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the phong
	 */
	public String getPhong() {
		return phong;
	}

	/**
	 * @param phong the phong to set
	 */
	public void setPhong(String phong) {
		this.phong = phong;
	}

	/**
	 * @return the phieuDatPhong
	 */
	public String getPhieuDatPhong() {
		return phieuDatPhong;
	}

	/**
	 * @param phieuDatPhong the phieuDatPhong to set
	 */
	public void setPhieuDatPhong(String phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(phieuDatPhong, phong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietPhieuDatPhongPK other = (ChiTietPhieuDatPhongPK) obj;
		return Objects.equals(phieuDatPhong, other.phieuDatPhong) && Objects.equals(phong, other.phong);
	}

}
