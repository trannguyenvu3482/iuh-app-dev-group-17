package com.nhom17.quanlykaraoke.bus;

import com.nhom17.quanlykaraoke.dao.ChiTietPhieuDatPhongDAO;
import com.nhom17.quanlykaraoke.entities.ChiTietPhieuDatPhong;

public class ChiTietPhieuDatPhongBUS {
	private ChiTietPhieuDatPhongDAO ctpdpDAO;

	public ChiTietPhieuDatPhongBUS() {
		ctpdpDAO = new ChiTietPhieuDatPhongDAO();
	}

	public ChiTietPhieuDatPhong getChiTietPhieuDatPhongByActiveMaPhong(String maPhong) {
		return ctpdpDAO.getChiTietPhieuDatPhongByActiveMaPhong(maPhong);
	}
}
