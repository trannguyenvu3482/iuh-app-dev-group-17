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

	public boolean finishPhieuDatPhong(String maPhong, double tienDichVu, double tienPhong) {
		return pdpDAO.finishPhieuDatPhong(maPhong.trim(), tienDichVu, tienPhong);

	}

	public List<PhieuDatPhong> getAllPhieuDatPhongFromDate(LocalDateTime fromDate, LocalDateTime toDate) {
		return pdpDAO.getAllPhieuDatPhongFromDate(fromDate, toDate);
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongFromDateByNhanVien(String maNV, LocalDateTime fromDate,
			LocalDateTime toDate) {
		return pdpDAO.getAllPhieuDatPhongFromDateByNhanVien(maNV, fromDate, toDate);
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongFromDateByKhachHang(String maKH, LocalDateTime fromDate,
			LocalDateTime toDate) {
		return pdpDAO.getAllPhieuDatPhongFromDateByKhachHang(maKH, fromDate, toDate);
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongByMonth(int month) {
		return pdpDAO.getAllPhieuDatPhongByMonth(month);
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongByMonthByNhanVien(String maNV, int month) {
		return pdpDAO.getAllPhieuDatPhongByMonthByNhanVien(maNV, month);
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongByMonthByKhachHang(String maKH, int month) {
		return pdpDAO.getAllPhieuDatPhongByMonthByKhachHang(maKH, month);
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongByYear(int year) {
		return pdpDAO.getAllPhieuDatPhongByYear(year);
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongByYearByNhanVien(String maNV, int year) {
		return pdpDAO.getAllPhieuDatPhongByYearByNhanVien(maNV, year);
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongByYearByKhachHang(String maKH, int year) {
		return pdpDAO.getAllPhieuDatPhongByYearByKhachHang(maKH, year);
	}

	public boolean changeRoomForPhieuDatPhong(String currentMaPhong, String moveToMaPhong) {
		ChiTietPhieuDatPhong ctpdp = ctpdpDAO.getChiTietPhieuDatPhongByActiveMaPhong(currentMaPhong);
		Phong p = pDAO.getPhong(moveToMaPhong);

		if (ctpdpDAO.addChiTietPhieuDatPhong(
				new ChiTietPhieuDatPhong(p, ctpdp.getPhieuDatPhong(), LocalDateTime.now(), null))) {
			return pdpDAO.finishPhieuDatPhong(currentMaPhong, 0, 0);
		}

		return false;
	}
}
