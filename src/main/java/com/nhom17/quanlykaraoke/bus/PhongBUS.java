package com.nhom17.quanlykaraoke.bus;

import java.util.List;

import com.nhom17.quanlykaraoke.dao.PhongDAO;
import com.nhom17.quanlykaraoke.entities.Phong;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 01-Nov-2023 2:36:11 PM
 */
public class PhongBUS {
	private PhongDAO pDAO;

	public PhongBUS() {
		this.pDAO = new PhongDAO();
	}

	public boolean addPhong(Phong p) {
		return pDAO.addPhong(p);
	}

	public List<Phong> getAllPhongs() {
		return pDAO.getAllPhongs();
	}

	public List<Phong> getPhongPage(int page) {
		return pDAO.getPhongPage(page);
	}
}
