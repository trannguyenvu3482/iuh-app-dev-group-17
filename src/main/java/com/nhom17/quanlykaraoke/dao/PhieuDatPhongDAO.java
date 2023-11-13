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

	public boolean finishPhieuDatPhong(String maPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			System.out.println("maPhong: " + maPhong);
			// Handle PhieuDatPhong
			Query<ChiTietPhieuDatPhong> query3 = session
					.createNativeQuery("SELECT * FROM ChiTietPhieuDatPhong c WHERE (c.maPhong = '" + maPhong
							+ "') AND (c.thoiGianKetThuc IS NULL)", ChiTietPhieuDatPhong.class);

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
