package com.nhom17.quanlykaraoke.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

	public boolean addPhong(Phong phong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			session.persist(phong);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
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

	public List<Phong> getPhongPage(int page) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<Phong> listPhong = null;
		try {
			String hql = "from Phong";
			int startIndex = 0;
			if (page != 1) {
				startIndex = (page - 1) * 8;
			}

			System.out.println("Start Index: " + startIndex);
			listPhong = session.createQuery(hql, Phong.class).setFirstResult(startIndex).setMaxResults(8)
					.getResultList();

			t.commit();
			return listPhong;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listPhong;
		}
	}
}
