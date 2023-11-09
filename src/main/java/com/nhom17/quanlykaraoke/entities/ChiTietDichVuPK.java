package com.nhom17.quanlykaraoke.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 08-Nov-2023 11:29:57 PM
 */

@Embeddable
public class ChiTietDichVuPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private String phieuDatPhong;
	private String hangHoa;
	private String phong;

	/**
	 * 
	 */
	public ChiTietDichVuPK() {
		// TODO Auto-generated constructor stub
	}

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
		ChiTietDichVuPK other = (ChiTietDichVuPK) obj;
		return Objects.equals(hangHoa, other.hangHoa) && Objects.equals(phieuDatPhong, other.phieuDatPhong)
				&& Objects.equals(phong, other.phong);
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

	/**
	 * @return the hangHoa
	 */
	public String getHangHoa() {
		return hangHoa;
	}

	/**
	 * @param hangHoa the hangHoa to set
	 */
	public void setHangHoa(String hangHoa) {
		this.hangHoa = hangHoa;
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
}
