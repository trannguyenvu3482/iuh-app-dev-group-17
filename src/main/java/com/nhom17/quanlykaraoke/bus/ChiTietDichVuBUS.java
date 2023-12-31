package com.nhom17.quanlykaraoke.bus;

import java.util.List;

import com.nhom17.quanlykaraoke.dao.ChiTietDichVuDAO;
import com.nhom17.quanlykaraoke.entities.ChiTietDichVu;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ChiTietDichVuBUS {
	private ChiTietDichVuDAO ctdvDAO;

	public ChiTietDichVuBUS() {
		this.ctdvDAO = new ChiTietDichVuDAO();
	}

	public boolean addChiTietDichVu(ChiTietDichVu ctdv) {
		return ctdvDAO.addChiTietDichVu(ctdv);
	}

	public List<ChiTietDichVu> getAllChiTietDichVus() {
		return ctdvDAO.getAllChiTietDichVus();
	}

	public List<ChiTietDichVu> getChiTietDichVuByMaPDPAndMaPhong(String maPDP, String maPhong) {
		return ctdvDAO.getChiTietDichVuByMaPDPAndMaPhong(maPDP, maPhong);
	}

	public ChiTietDichVu updateChiTietDichVu(ChiTietDichVu ctdv) {
		return ctdvDAO.updateChiTietDichVu(ctdv);
	}

	public double getTongTienDichVuByMaPDP(String maPDP) {
		return ctdvDAO.getTongTienDichVuByMaPDP(maPDP);
	}

	public boolean deleteChiTietDichVu(ChiTietDichVu chiTietDichVu) {
		return ctdvDAO.deleteChiTietDichVu(chiTietDichVu);
	}
}
