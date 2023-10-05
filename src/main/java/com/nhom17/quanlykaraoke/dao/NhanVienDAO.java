package com.nhom17.quanlykaraoke.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

			session.persist(nv);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
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