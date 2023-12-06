package com.nhom17.quanlykaraoke.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom17.quanlykaraoke.entities.ChucVu;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

public class ChucVuDAO {
	private SessionFactory factory = null;

	public ChucVuDAO() {
		factory = HibernateUtil.getMySessionFactory();
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

	public List<ChucVu> getAllChucVus() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<ChucVu> listChucVu = null;
		try {
			String hql = "from ChiTietDichVu";
			listChucVu = session.createQuery(hql, ChucVu.class).getResultList();
			t.commit();
			return listChucVu;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listChucVu;
		}
	}

	public ChucVu getChucVuByName(String name) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Query<ChucVu> query = session.createNativeQuery("select * FROM ChucVu WHERE tenChucVu = N'" + name + "'",
					ChucVu.class);
			ChucVu cv = query.getResultList().get(0);
			t.commit();
			return cv;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}
}
