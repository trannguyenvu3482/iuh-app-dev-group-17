package com.nhom17.quanlykaraoke.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom17.quanlykaraoke.entities.ChiTietPhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

public class PhieuDatPhongDAO {
	private SessionFactory factory = null;

	public PhieuDatPhongDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addPhieuDatPhong(PhieuDatPhong pdp, Phong p) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle add PhieuDatPhong
			String maPDP = getNextMaPDP();

			pdp.setMaPhieuDatPhong(maPDP);
			session.persist(pdp);

			// Handle add ChiTietPhieuDatPhong
			ChiTietPhieuDatPhong ctpdp = new ChiTietPhieuDatPhong(p, pdp, LocalDateTime.now(), null);
			session.persist(ctpdp);

			// Finish
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongFromDate(LocalDateTime fromDate, LocalDateTime toDate) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong inner join ChiTietPhieuDatPhong then query for
			// thoiGianBatDau and thoiGianKetThuc
			String sql = "SELECT p.* FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE p.maPhieuDatPhong NOT IN (SELECT p.maPhieuDatPhong FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE (c.thoiGianKetThuc IS NULL)) "
					+ "AND (c.thoiGianBatDau BETWEEN :fromDate AND :toDate) OR (c.thoiGianKetThuc BETWEEN :fromDate AND :toDate)";
			Query<PhieuDatPhong> query = session.createNativeQuery(sql, PhieuDatPhong.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			List<PhieuDatPhong> listPhieuDatPhong = query.getResultList();

			// Finish
			t.commit();
			return listPhieuDatPhong;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongFromDateByNhanVien(String maNV, LocalDateTime fromDate,
			LocalDateTime toDate) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong inner join ChiTietPhieuDatPhong then query for
			// thoiGianBatDau and thoiGianKetThuc
			String sql = "SELECT p.* FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE p.maPhieuDatPhong NOT IN (SELECT p.maPhieuDatPhong FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "AND (c.thoiGianKetThuc IS NULL)) AND (p.maNhanVien = :maNV) "
					+ "AND ((c.thoiGianBatDau BETWEEN :fromDate AND :toDate) OR (c.thoiGianKetThuc BETWEEN :fromDate AND :toDate))";
			Query<PhieuDatPhong> query = session.createNativeQuery(sql, PhieuDatPhong.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("maNV", maNV);

			System.out.println("Query: " + query.getQueryString());

			List<PhieuDatPhong> listPhieuDatPhong = query.getResultList();

			// Finish
			t.commit();
			return listPhieuDatPhong;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongFromDateByKhachHang(String maKH, LocalDateTime fromDate,
			LocalDateTime toDate) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong inner join ChiTietPhieuDatPhong then query for
			// thoiGianBatDau and thoiGianKetThuc
			String sql = "SELECT p.* FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE p.maPhieuDatPhong NOT IN (SELECT p.maPhieuDatPhong FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "AND (c.thoiGianKetThuc IS NULL)) AND (p.maKhachHang = :maKH) "
					+ "AND ((c.thoiGianBatDau BETWEEN :fromDate AND :toDate) OR (c.thoiGianKetThuc BETWEEN :fromDate AND :toDate))";
			Query<PhieuDatPhong> query = session.createNativeQuery(sql, PhieuDatPhong.class);
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
			query.setParameter("maKH", maKH);

			System.out.println("Query: " + query.getQueryString());

			List<PhieuDatPhong> listPhieuDatPhong = query.getResultList();

			// Finish
			t.commit();
			return listPhieuDatPhong;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	// Get all PhieuDatPhong by month
	public List<PhieuDatPhong> getAllPhieuDatPhongByMonth(int month) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong inner join ChiTietPhieuDatPhong then query for
			// thoiGianBatDau and thoiGianKetThuc
			String sql = "SELECT p.* " + "FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE p.maPhieuDatPhong NOT IN (SELECT p.maPhieuDatPhong FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE (c.thoiGianKetThuc IS NULL)) "
					+ "AND (MONTH(c.thoiGianBatDau) = :month OR MONTH(c.thoiGianKetThuc) = :month)";
			Query<PhieuDatPhong> query = session.createNativeQuery(sql, PhieuDatPhong.class);
			query.setParameter("month", month);
			List<PhieuDatPhong> listPhieuDatPhong = query.getResultList();

			// Finish
			t.commit();
			return listPhieuDatPhong;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongByMonthByNhanVien(String maNV, int month) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong inner join ChiTietPhieuDatPhong then query for
			// thoiGianBatDau and thoiGianKetThuc
			String sql = "SELECT p.* " + "FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE p.maPhieuDatPhong NOT IN (SELECT p.maPhieuDatPhong FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE (c.thoiGianKetThuc IS NULL)) AND (p.maNhanVien = :maNV)"
					+ "AND (MONTH(c.thoiGianBatDau) = :month OR MONTH(c.thoiGianKetThuc) = :month)";
			Query<PhieuDatPhong> query = session.createNativeQuery(sql, PhieuDatPhong.class);
			query.setParameter("month", month);
			query.setParameter("maNV", maNV);
			List<PhieuDatPhong> listPhieuDatPhong = query.getResultList();

			// Finish
			t.commit();
			return listPhieuDatPhong;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongByMonthByKhachHang(String maKH, int month) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong inner join ChiTietPhieuDatPhong then query for
			// thoiGianBatDau and thoiGianKetThuc
			String sql = "SELECT p.* " + "FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE p.maPhieuDatPhong NOT IN (SELECT p.maPhieuDatPhong FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE (c.thoiGianKetThuc IS NULL)) AND (p.maKhachHang = :maKH)"
					+ "AND (MONTH(c.thoiGianBatDau) = :month OR MONTH(c.thoiGianKetThuc) = :month)";
			Query<PhieuDatPhong> query = session.createNativeQuery(sql, PhieuDatPhong.class);
			query.setParameter("month", month);
			query.setParameter("maKH", maKH);
			List<PhieuDatPhong> listPhieuDatPhong = query.getResultList();

			// Finish
			t.commit();
			return listPhieuDatPhong;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	// Get all PhieuDatPhong by year
	public List<PhieuDatPhong> getAllPhieuDatPhongByYear(int year) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong inner join ChiTietPhieuDatPhong then query for
			// thoiGianBatDau and thoiGianKetThuc
			String sql = "SELECT p.* " + "FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE p.maPhieuDatPhong NOT IN (SELECT p.maPhieuDatPhong FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE (c.thoiGianKetThuc IS NULL)) "
					+ "AND (YEAR(c.thoiGianBatDau) = :year OR YEAR(c.thoiGianKetThuc) = :year)";
			Query<PhieuDatPhong> query = session.createNativeQuery(sql, PhieuDatPhong.class);
			query.setParameter("year", year);
			List<PhieuDatPhong> listPhieuDatPhong = query.getResultList();

			// Finish
			t.commit();
			return listPhieuDatPhong;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongByYearByNhanVien(String maNV, int year) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong inner join ChiTietPhieuDatPhong then query for
			// thoiGianBatDau and thoiGianKetThuc
			String sql = "SELECT p.* " + "FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE p.maPhieuDatPhong NOT IN (SELECT p.maPhieuDatPhong FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE (c.thoiGianKetThuc IS NULL)) AND (p.maNhanVien = :maNV) "
					+ "AND (YEAR(c.thoiGianBatDau) = :year OR YEAR(c.thoiGianKetThuc) = :year)";
			Query<PhieuDatPhong> query = session.createNativeQuery(sql, PhieuDatPhong.class);
			query.setParameter("year", year);
			query.setParameter("maNV", maNV);
			List<PhieuDatPhong> listPhieuDatPhong = query.getResultList();

			// Finish
			t.commit();
			return listPhieuDatPhong;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongByYearByKhachHang(String maKH, int year) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong inner join ChiTietPhieuDatPhong then query for
			// thoiGianBatDau and thoiGianKetThuc
			String sql = "SELECT p.* " + "FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE p.maPhieuDatPhong NOT IN (SELECT p.maPhieuDatPhong FROM PhieuDatPhong p "
					+ "INNER JOIN ChiTietPhieuDatPhong c ON p.maPhieuDatPhong = c.maPhieuDatPhong "
					+ "WHERE (c.thoiGianKetThuc IS NULL)) AND (p.maKhachHang = :maKH) "
					+ "AND (YEAR(c.thoiGianBatDau) = :year OR YEAR(c.thoiGianKetThuc) = :year)";
			Query<PhieuDatPhong> query = session.createNativeQuery(sql, PhieuDatPhong.class);
			query.setParameter("year", year);
			query.setParameter("maKH", maKH);
			List<PhieuDatPhong> listPhieuDatPhong = query.getResultList();

			// Finish
			t.commit();
			return listPhieuDatPhong;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	public boolean changeRoomForPhieuDatPhong(String currentMaPhong, String moveToMaPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong
			Query<ChiTietPhieuDatPhong> query3 = session.createNativeQuery("SELECT * FROM ChiTietPhieuDatPhong",
					ChiTietPhieuDatPhong.class);

			ChiTietPhieuDatPhong ctpdp = query3.getSingleResult();
			ctpdp.setThoiGianKetThuc(LocalDateTime.now());
			session.merge(ctpdp);

			PhieuDatPhong pdp = ctpdp.getPhieuDatPhong();
			pdp.setTrangThai(true);
			session.merge(pdp);

			// Finish
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}
	}

	public boolean finishPhieuDatPhong(String maPhong, double tienDichVu, double tienPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// Handle PhieuDatPhong
			Query<ChiTietPhieuDatPhong> query3 = session
					.createNativeQuery("SELECT * FROM ChiTietPhieuDatPhong c WHERE (c.maPhong = '" + maPhong
							+ "') AND (c.thoiGianKetThuc IS NULL)", ChiTietPhieuDatPhong.class);

			ChiTietPhieuDatPhong ctpdp = query3.getSingleResult();
			ctpdp.setThoiGianKetThuc(LocalDateTime.now());
			session.merge(ctpdp);

			PhieuDatPhong pdp = ctpdp.getPhieuDatPhong();
			pdp.setTienDichVu(tienDichVu);
			pdp.setTienPhong(tienPhong);
			pdp.setTrangThai(true);
			session.merge(pdp);

			// Finish
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}
	}

	private String getNextMaPDP() {
		String idPrefix = "PDP";

		int count = countPhieuDatPhong();

		if (count < 0 || count > 9999) {
			return null;
		}

		return idPrefix + String.format("%04d", count + 1);
	}

	private int countPhieuDatPhong() {
		Session session = factory.getCurrentSession();

		try {
			String hql = "From PhieuDatPhong";
			Query<PhieuDatPhong> query = session.createQuery(hql, PhieuDatPhong.class);
			List<PhieuDatPhong> listPhieuDatPhong = query.getResultList();

			return listPhieuDatPhong.size();

		} catch (Exception e) {
			return -1;
		}
	}

	public List<PhieuDatPhong> getAllPhieuDatPhongs() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<PhieuDatPhong> listPhieuDatPhong = null;
		try {
			String hql = "from PhieuDatPhong";
			listPhieuDatPhong = session.createQuery(hql, PhieuDatPhong.class).getResultList();
			t.commit();
			return listPhieuDatPhong;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listPhieuDatPhong;
		}
	}
}
