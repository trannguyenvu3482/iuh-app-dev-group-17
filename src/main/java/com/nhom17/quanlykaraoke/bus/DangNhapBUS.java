package com.nhom17.quanlykaraoke.bus;

import com.nhom17.quanlykaraoke.dao.NhanVienDAO;

public class DangNhapBUS {
	private NhanVienDAO nhanVienDAO;

	public boolean checkDangNhap(String maNV, String matKhau) {
		return nhanVienDAO.checkDangNhap(maNV, matKhau);
	}

	public DangNhapBUS() {
		nhanVienDAO = new NhanVienDAO();
	}
}
