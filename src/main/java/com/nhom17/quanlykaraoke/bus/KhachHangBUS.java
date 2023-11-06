package com.nhom17.quanlykaraoke.bus;

import com.nhom17.quanlykaraoke.dao.KhachHangDAO;
import com.nhom17.quanlykaraoke.entities.KhachHang;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 06-Nov-2023 2:44:31 PM
 */
public class KhachHangBUS {
	private KhachHangDAO khDAO;

	/**
	 * 
	 */
	public KhachHangBUS() {
		this.khDAO = new KhachHangDAO();
	}

	public boolean addKhachHang(KhachHang kh) {
		return khDAO.addKhachHang(kh);
	}

	public KhachHang getKhachHang(String maKH) {
		return khDAO.getKhachHang(maKH);
	}

	public KhachHang getKhachHangBySDT(String sdt) {
		return khDAO.getKhachHangBySDT(sdt);
	}
}
