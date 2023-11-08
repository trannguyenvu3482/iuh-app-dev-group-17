package com.nhom17.quanlykaraoke.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom17.quanlykaraoke.entities.KhachHang;
import com.nhom17.quanlykaraoke.entities.KhachHang;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 06-Nov-2023 2:44:17 PM
 */
public class KhachHangDAO {
	private SessionFactory factory = null;

	public KhachHangDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addKhachHang(KhachHang kh) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			String maKH = getNextMaKH();
			kh.setMaKhachHang(maKH);
			session.persist(kh);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	private String getNextMaKH() {
		String idPrefix = "KH";

		int count = countKhachHang();

		if (count < 1 || count > 999) {
			return null;
		}

		return idPrefix + String.format("%03d", count);
	}

	private int countKhachHang() {
		Session session = factory.getCurrentSession();

		try {
			String hql = "From KhachHang";
			Query<KhachHang> query = session.createQuery(hql, KhachHang.class);
			List<KhachHang> listKhachHang = query.getResultList();

			return listKhachHang.size();

		} catch (Exception e) {
			return -1;
		}
	}

	public KhachHang getKhachHang(String maKH) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			System.out.println("Searching...");
			KhachHang kh = session.get(KhachHang.class, maKH);

			t.commit();
			return kh;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public KhachHang getKhachHangBySDT(String soDienThoai) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Query<KhachHang> query = session
					.createNativeQuery("SELECT * FROM KhachHang WHERE soDienThoai = " + soDienThoai, KhachHang.class);

			System.out.println(query.getQueryString());
			@SuppressWarnings("deprecation")
			KhachHang kh = (KhachHang) query.getResultList().get(0);

			t.commit();
			return kh;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

}
