package com.nhom17.quanlykaraoke.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 31-Oct-2023 2:01:06 PM
 */
public class PhongDAO {
	private SessionFactory factory = null;

	public PhongDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addPhong(Phong p) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			String maPhong = getNextMaPhong();
			p.setMaPhong(maPhong);
			session.persist(maPhong);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	private String getNextMaPhong() {
		String idPrefix = "P";

		int count = countPhong();

		if (count < 1 || count > 999) {
			return null;
		}

		return idPrefix + String.format("%03d", count);
	}

	private int countPhong() {
		Session session = factory.getCurrentSession();

		try {
			String hql = "From Phong";
			Query<Phong> query = session.createQuery(hql, Phong.class);
			List<Phong> listPhong = query.getResultList();

			return listPhong.size();

		} catch (Exception e) {
			return -1;
		}
	}

	public List<Phong> getAllPhongs() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<Phong> listPhong = null;
		try {
			String hql = "from Phong";
			listPhong = session.createQuery(hql, Phong.class).getResultList();

			t.commit();
			return listPhong;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listPhong;
		}
	}

	public List<Phong> getAllEmptyPhongs() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<Phong> listPhong = null;
		try {
			Query<Phong> query = session.createNativeQuery(
					"SELECT * from Phong p where p.maPhong not in (SELECT maPhong from ChiTietPhieuDatPhong ctpdp where thoiGianKetThuc is null)",
					Phong.class);

			listPhong = query.getResultList();

			t.commit();
			return listPhong;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return new ArrayList<Phong>();
		}
	}

	public boolean isRoomEmpty(Phong p) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<Phong> listPhong = null;
		try {
			Query<Phong> query = session.createNativeQuery("SELECT * from Phong p where p.maPhong = '" + p.getMaPhong()
					+ "' AND p.maPhong not in (SELECT maPhong from ChiTietPhieuDatPhong ctpdp where thoiGianKetThuc is null)",
					Phong.class);

			listPhong = query.getResultList();

			t.commit();
			return listPhong.contains(p);
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return false;
		}
	}

	public Phong updatePhong(Phong phong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Phong updatedPhong = session.merge(phong);

			t.commit();
			return updatedPhong;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public Phong getPhong(String maPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Phong p = session.get(Phong.class, maPhong);

			t.commit();
			return p;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}
//	public List<Phong> getPhongPage(int page) {
//		Session session = factory.getCurrentSession();
//		Transaction t = session.beginTransaction();
//		List<Phong> listPhong = null;
//		try {
//			String hql = "from Phong";
//			int startIndex = 0;
//			if (page != 1) {
//				startIndex = (page - 1) * 8;
//			}
//
//			System.out.println("Start Index: " + startIndex);
//			listPhong = session.createQuery(hql, Phong.class).setFirstResult(startIndex).setMaxResults(8)
//					.getResultList();
//
//			t.commit();
//			return listPhong;
//		} catch (Exception e) {
//			System.out.println("ROLLBACK!");
//			t.rollback();
//			return listPhong;
//		}
//	}
}
