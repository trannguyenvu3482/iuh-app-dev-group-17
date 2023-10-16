package com.nhom17.quanlykaraoke.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
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
 * @created 10-Oct-2023 13:36:00
 */
@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "char(6)")
	private String maNhanVien;

	@Column(columnDefinition = "nvarchar(30)")
	private String hoTen;

	private int gioiTinh;

	@Column(columnDefinition = "varchar(60)")
	private String matKhau;

	@Column(columnDefinition = "date")
	private LocalDate ngaySinh;

	@ManyToOne
	@JoinColumn(name = "maChucVu", columnDefinition = "char(5)")
	private ChucVu chucVu;

	@Column(columnDefinition = "varchar(10)")
	private String soDienThoai;

	@Column(columnDefinition = "varchar(12)")
	private String CCCD;

	@Column(columnDefinition = "varbinary(max)")
	private byte[] anhDaiDien;

	@Column(columnDefinition = "bit")
	private boolean trangThai;

	public NhanVien() {
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String hoTen, int gioiTinh, String matKhau, LocalDate ngaySinh, ChucVu chucVu, String soDienThoai,
			String cCCD, byte[] anhDaiDien, boolean trangThai) {
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.matKhau = matKhau;
		this.ngaySinh = ngaySinh;
		this.chucVu = chucVu;
		this.soDienThoai = soDienThoai;
		this.CCCD = cCCD;
		this.anhDaiDien = anhDaiDien;
		this.trangThai = trangThai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public int getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public byte[] getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(byte[] anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", matKhau="
				+ matKhau + ", ngaySinh=" + ngaySinh + ", chucVu=" + chucVu + ", soDienThoai=" + soDienThoai + ", CCCD="
				+ CCCD + ", anhDaiDien=" + Arrays.toString(anhDaiDien) + ", trangThai=" + trangThai + "]";
	}
}
