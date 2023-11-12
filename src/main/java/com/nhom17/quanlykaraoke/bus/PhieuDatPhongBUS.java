package com.nhom17.quanlykaraoke.bus;

import java.util.List;

import com.nhom17.quanlykaraoke.dao.PhieuDatPhongDAO;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.Phong;

public class PhieuDatPhongBUS {
	private PhieuDatPhongDAO pdpDAO;

	public PhieuDatPhongBUS() {
		this.pdpDAO = new PhieuDatPhongDAO();
	}

	public boolean addPhieuDatPhong(PhieuDatPhong pdp, Phong p) {
		return pdpDAO.addPhieuDatPhong(pdp, p);
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongs() {
		return pdpDAO.getAllPhieuDatPhongs();
	}

	public boolean finishPhieuDatPhong(String maPhong) {
		return pdpDAO.finishPhieuDatPhong(maPhong.trim());

	}
}
