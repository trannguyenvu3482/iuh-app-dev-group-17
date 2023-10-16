package com.nhom17.quanlykaraoke.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nhom17.quanlykaraoke.entities.ChucVu;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

public class ChucVuDAO {
	private SessionFactory factory = null;

	public ChucVuDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addChucVu(ChucVu cv) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {

			session.persist(cv);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	public ChucVu getChucVu(String maChucVu) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			ChucVu cv = session.get(ChucVu.class, maChucVu);

			t.commit();
			return cv;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

}
