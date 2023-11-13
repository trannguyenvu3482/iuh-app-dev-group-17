package com.nhom17.quanlykaraoke.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom17.quanlykaraoke.entities.ChiTietPhieuDatPhong;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

public class ChiTietPhieuDatPhongDAO {
	private SessionFactory factory = null;

	public ChiTietPhieuDatPhongDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addChiTietPhieuDatPhong(ChiTietPhieuDatPhong chiTietPhieuDatPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			session.persist(chiTietPhieuDatPhong);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	public ChiTietPhieuDatPhong getChiTietPhieuDatPhongByActiveMaPhong(String maPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Query<ChiTietPhieuDatPhong> query = session.createNativeQuery(
					"SELECT * FROM ChiTietPhieuDatPhong WHERE maPhong = '" + maPhong + "' AND thoiGianKetThuc IS NULL",
					ChiTietPhieuDatPhong.class);

			ChiTietPhieuDatPhong ctpdp = query.getSingleResult();
			t.commit();
			return ctpdp;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public List<ChiTietPhieuDatPhong> getAllChiTietPhieuDatPhongs() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<ChiTietPhieuDatPhong> listChiTietPhieuDatPhong = null;
		try {
			String hql = "from ChiTietPhieuDatPhong";
			listChiTietPhieuDatPhong = session.createQuery(hql, ChiTietPhieuDatPhong.class).getResultList();
			t.commit();
			return listChiTietPhieuDatPhong;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listChiTietPhieuDatPhong;
		}
	}

}