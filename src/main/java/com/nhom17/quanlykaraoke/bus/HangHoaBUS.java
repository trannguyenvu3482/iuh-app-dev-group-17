package com.nhom17.quanlykaraoke.bus;

import java.util.List;

import com.nhom17.quanlykaraoke.dao.HangHoaDAO;
import com.nhom17.quanlykaraoke.entities.HangHoa;

public class HangHoaBUS {
	private HangHoaDAO hhDAO;

	public HangHoaBUS() {
		this.hhDAO = new HangHoaDAO();
	}

	public boolean addHangHoa(HangHoa hh) {
		return hhDAO.addHangHoa(hh);
	}

	public List<HangHoa> getAllHangHoas() {
		return hhDAO.getAllHangHoas();
	}

	public HangHoa getHangHoa(String maHH) {
		return hhDAO.getHangHoa(maHH);
	}

	public boolean updateHangHoa(HangHoa hh) {
		return hhDAO.updateHangHoa(hh);
	}

	public boolean updateSoLuongTon(HangHoa hh, int sl) {
		return hhDAO.updateSoLuongTon(hh, sl);
	}
}
