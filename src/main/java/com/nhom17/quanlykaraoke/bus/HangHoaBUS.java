package com.nhom17.quanlykaraoke.bus;

import java.util.List;

import com.nhom17.quanlykaraoke.dao.HangHoaDAO;
import com.nhom17.quanlykaraoke.entities.HangHoa;

public class HangHoaBUS {
	private HangHoaDAO hhDAO;

	public HangHoaBUS() {
		this.hhDAO = new HangHoaDAO();
	}

	public boolean addHangHoa(HangHoa kh) {
		return hhDAO.addHangHoa(kh);
	}

	public List<HangHoa> getAllHangHoas() {
		return hhDAO.getAllHangHoas();
	}
}
