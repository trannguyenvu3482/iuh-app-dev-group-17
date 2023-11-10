package com.nhom17.quanlykaraoke.bus;

import java.util.List;

import com.nhom17.quanlykaraoke.dao.LoaiHangHoaDAO;
import com.nhom17.quanlykaraoke.entities.LoaiHangHoa;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;

public class LoaiHangHoaBUS {
	private LoaiHangHoaDAO lhhDAO;

	public LoaiHangHoaBUS() {
		this.lhhDAO = new LoaiHangHoaDAO();
	}

	public boolean addLoaiHangHoa(LoaiHangHoa lhh) {
		return lhhDAO.addLoaiHangHoa(lhh);
	}
	
	public List<LoaiHangHoa> getAllLoaiHangHoas() {
		return lhhDAO.getAllLoaiHangHoas();
	}
}
