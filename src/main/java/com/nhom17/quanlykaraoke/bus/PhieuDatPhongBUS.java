package com.nhom17.quanlykaraoke.bus;

import java.util.List;

import com.nhom17.quanlykaraoke.dao.PhieuDatPhongDAO;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;

public class PhieuDatPhongBUS {
	private PhieuDatPhongDAO pdpDAO;

	public PhieuDatPhongBUS() {
		this.pdpDAO = new PhieuDatPhongDAO();
	}

	public boolean addPhieuDatPhong(PhieuDatPhong pdp) {
		return pdpDAO.addPhieuDatPhong(pdp);
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongs() {
		return pdpDAO.getAllPhieuDatPhongs();
	}
	
}
