package com.nhom17.quanlykaraoke.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.nhom17.quanlykaraoke.utils.ConstantUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 06-Nov-2023 4:14:38 PM
 */

@Entity
@Table(name = "ChiTietPhieuDatPhong")
public class ChiTietPhieuDatPhong implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "maPhong", columnDefinition = "char(4)")
	private Phong phong;

	@Id
	@ManyToOne
	@JoinColumn(name = "maPhieuDatPhong", columnDefinition = "char(7)")
	private PhieuDatPhong phieuDatPhong;

	@Column(columnDefinition = "datetime")
	private LocalDateTime thoiGianBatDau;

	@Column(columnDefinition = "datetime")
	private LocalDateTime thoiGianKetThuc;

	public ChiTietPhieuDatPhong() {
		// TODO Auto-generated constructor stub
	}

	public ChiTietPhieuDatPhong(Phong phong, PhieuDatPhong phieuDatPhong, LocalDateTime thoiGianBatDau,
			LocalDateTime thoiGianKetThuc) {
		this.phong = phong;
		this.phieuDatPhong = phieuDatPhong;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public PhieuDatPhong getPhieuDatPhong() {
		return phieuDatPhong;
	}

	public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
	}

	public LocalDateTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public LocalDateTime getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public double getTienPhongAndPhuPhi() {
		if (thoiGianBatDau.getHour() < ConstantUtil.NIGHTTIME_BEGIN_HOUR) {
			double tienPhong = ChronoUnit.HOURS.between(thoiGianBatDau, thoiGianKetThuc)
					* ConstantUtil.DAYTIME_HOUR_PRICE;
			double tienPhuPhi = phong.getLoaiPhong().getPhuPhi();

			return tienPhong + tienPhuPhi;
		} else {
			double tienPhong = ChronoUnit.HOURS.between(thoiGianBatDau, thoiGianKetThuc)
					* ConstantUtil.NIGHTTIME_HOUR_PRICE;
			double tienPhuPhi = phong.getLoaiPhong().getPhuPhi();

			return tienPhong + tienPhuPhi;
		}
	}

	@Override
	public String toString() {
		return "ChiTietPhieuDatPhong [phong=" + phong + ", phieuDatPhong=" + phieuDatPhong + ", thoiGianBatDau="
				+ thoiGianBatDau + ", thoiGianKetThuc=" + thoiGianKetThuc + "]";
	}

}
