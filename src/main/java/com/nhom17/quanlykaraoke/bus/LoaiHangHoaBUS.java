package com.nhom17.quanlykaraoke.bus;

import com.nhom17.quanlykaraoke.dao.LoaiHangHoaDAO;
import com.nhom17.quanlykaraoke.entities.LoaiHangHoa;

public class LoaiHangHoaBUS {
	private LoaiHangHoaDAO lhhDAO;

	public LoaiHangHoaBUS() {
		this.lhhDAO = new LoaiHangHoaDAO();
	}

	public boolean addLoaiHangHoa(LoaiHangHoa lhh) {
		return lhhDAO.addLoaiHangHoa(lhh);
	}

}
