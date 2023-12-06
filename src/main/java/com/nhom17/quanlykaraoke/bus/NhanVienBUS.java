package com.nhom17.quanlykaraoke.bus;

import java.time.LocalDate;
import java.util.Date;
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
	
	public boolean updateMatKhauNhanVien(NhanVien nv) {
		return nvDAO.updateMatKhauNhanVien(nv);
	}
	
	public NhanVien updateNV(NhanVien nv) {
		return nvDAO.updateNV(nv);
	}
	
	public List<NhanVien> getNhanViensByDOB(String dateFrom,String dateTo) {
		return nvDAO.getNhanViensByDOB(dateFrom, dateTo);
	}
}
