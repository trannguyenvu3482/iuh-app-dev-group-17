package com.nhom17.quanlykaraoke.bus;

import java.util.List;

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

	public List<NhanVien> getAllNhanViens() {
		// TODO Auto-generated method stub
		return nvDAO.getAllNhanViens();
	}
	
	public boolean updateNhanVien(NhanVien nv) {
		return nvDAO.updateNhanVien(nv);
	}
}
