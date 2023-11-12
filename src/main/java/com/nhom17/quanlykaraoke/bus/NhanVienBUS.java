package com.nhom17.quanlykaraoke.bus;

import com.nhom17.quanlykaraoke.dao.NhanVienDAO;
import com.nhom17.quanlykaraoke.entities.NhanVien;

public class NhanVienBUS {
	private NhanVienDAO nvDAO;

	public NhanVienBUS() {
		this.nvDAO = new NhanVienDAO();
	}

	public boolean addNhanVien(NhanVien nv) {
		return nvDAO.addNhanVien(nv);
	}

	public NhanVien getNhanVien(String maNV) {
		return nvDAO.getNhanVien(maNV);
	}
	public NhanVien updateNhanVien(String maNV) {
		return nvDAO.getNhanVien(maNV);
	}
}
