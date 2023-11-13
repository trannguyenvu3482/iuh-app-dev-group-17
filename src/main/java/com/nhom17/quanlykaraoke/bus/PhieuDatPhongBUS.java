package com.nhom17.quanlykaraoke.bus;

import java.time.LocalDateTime;
import java.util.List;

import com.nhom17.quanlykaraoke.dao.ChiTietPhieuDatPhongDAO;
import com.nhom17.quanlykaraoke.dao.PhieuDatPhongDAO;
import com.nhom17.quanlykaraoke.dao.PhongDAO;
import com.nhom17.quanlykaraoke.entities.ChiTietPhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.Phong;

public class PhieuDatPhongBUS {
	private PhieuDatPhongDAO pdpDAO;
	private ChiTietPhieuDatPhongDAO ctpdpDAO;
	private PhongDAO pDAO;

	public PhieuDatPhongBUS() {
		this.pdpDAO = new PhieuDatPhongDAO();
		this.ctpdpDAO = new ChiTietPhieuDatPhongDAO();
		this.pDAO = new PhongDAO();
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

	public boolean changeRoomForPhieuDatPhong(String currentMaPhong, String moveToMaPhong) {
		ChiTietPhieuDatPhong ctpdp = ctpdpDAO.getChiTietPhieuDatPhongByActiveMaPhong(currentMaPhong);
		Phong p = pDAO.getPhong(moveToMaPhong);

		if (ctpdpDAO.addChiTietPhieuDatPhong(
				new ChiTietPhieuDatPhong(p, ctpdp.getPhieuDatPhong(), LocalDateTime.now(), null))) {
			return pdpDAO.finishPhieuDatPhong(currentMaPhong);
		}

		return false;
	}
}
