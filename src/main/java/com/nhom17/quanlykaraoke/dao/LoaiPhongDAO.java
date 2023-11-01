package com.nhom17.quanlykaraoke.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nhom17.quanlykaraoke.entities.LoaiPhong;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 31-Oct-2023 2:01:12 PM
 */
public class LoaiPhongDAO {
	private SessionFactory factory = null;

	public LoaiPhongDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public LoaiPhong getLoaiPhong(String maLoaiPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			LoaiPhong lp = session.get(LoaiPhong.class, maLoaiPhong);

			t.commit();
			return lp;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public boolean addLoaiPhong(LoaiPhong loaiPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			session.persist(loaiPhong);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}
}
