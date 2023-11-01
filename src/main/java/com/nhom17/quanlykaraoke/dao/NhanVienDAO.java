package com.nhom17.quanlykaraoke.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;
import com.nhom17.quanlykaraoke.utils.PasswordUtil;

public class NhanVienDAO {
	private SessionFactory factory = null;

	public NhanVienDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addNhanVien(NhanVien nv) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			String maNV = getNextMaNV();
			System.out.println("MaNV: " + getNextMaNV());
			nv.setMaNhanVien(maNV);
			session.persist(nv);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	private String getNextMaNV() {
		String idPrefix = "NV";

		int count = countNhanVien();
		System.out.println("COUNT: " + String.valueOf(count));

		if (count < 1 || count > 999) {
			return null;
		}

		return idPrefix + String.format("%03d", count);
	}

	private int countNhanVien() {
		Session session = factory.getCurrentSession();

		try {
			String hql = "From NhanVien";
			Query<NhanVien> query = session.createQuery(hql, NhanVien.class);
			List<NhanVien> listNhanVien = query.getResultList();

			return listNhanVien.size();

		} catch (Exception e) {
			return -1;
		}
	}

	public NhanVien getNhanVien(String maNV) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			NhanVien nv = session.get(NhanVien.class, maNV);

			System.out.println("Tên: " + nv.getHoTen());

			t.commit();
			return nv;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public boolean checkDangNhap(String maNV, String password) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			NhanVien nv = session.get(NhanVien.class, maNV);

			if (nv != null) {
				t.commit();
				return PasswordUtil.check(password, nv.getMatKhau());
			} else {
				t.rollback();
				return false;
			}
		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

}
