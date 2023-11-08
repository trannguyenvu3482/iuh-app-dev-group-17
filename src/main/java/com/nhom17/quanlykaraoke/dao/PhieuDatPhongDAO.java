package com.nhom17.quanlykaraoke.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

public class PhieuDatPhongDAO {
	private SessionFactory factory = null;

	public PhieuDatPhongDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addPhieuDatPhong(PhieuDatPhong pdp) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			String maPDP = getNextMaPDP();
			pdp.setMaPhieuDatPhong(maPDP);
			session.persist(pdp);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	private String getNextMaPDP() {
		String idPrefix = "PDP";

		int count = countPhieuDatPhong();

		if (count < 1 || count > 9999) {
			return null;
		}

		return idPrefix + String.format("%04d", count);
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
