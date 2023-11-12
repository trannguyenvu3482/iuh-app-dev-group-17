package com.nhom17.quanlykaraoke.entities;

import java.io.Serializable;
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
 * @created 31-Oct-2023 1:33:43 PM
 */

@Entity
@Table(name = "Phong")
public class Phong implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "char(4)")
	private String maPhong;

	@ManyToOne
	@JoinColumn(name = "maLoaiPhong", columnDefinition = "char(5)")
	private LoaiPhong loaiPhong;

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

	@Column(columnDefinition = "bit")
	private boolean trangThai;

	public Phong() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param maPhong
	 * @param loaiPhong
	 * @param trangThai
	 */
	public Phong(String maPhong, LoaiPhong loaiPhong, boolean trangThai) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.trangThai = trangThai;
	}

	/**
	 * @return the maPhong
	 */
	public String getMaPhong() {
		return maPhong;
	}

	/**
	 * @param maPhong the maPhong to set
	 */
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	/**
	 * @param trangThai the trangThai to set
	 */
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	/**
	 * @return the trangThai
	 */
	public boolean isTrangThai() {
		return trangThai;
	}

	/**
	 * @return the loaiPhong
	 */
	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	/**
	 * @param loaiPhong the loaiPhong to set
	 */
	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", loaiPhong=" + loaiPhong + ", trangThai=" + trangThai + "]";
	}
	
	
}
