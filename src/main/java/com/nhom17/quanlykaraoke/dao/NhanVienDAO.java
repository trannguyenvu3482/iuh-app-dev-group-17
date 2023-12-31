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

		if (count < 0 || count > 999) {
			return null;
		}

		return idPrefix + String.format("%03d", count + 1);
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

			t.commit();
			return nv;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public NhanVien getNhanVienBySDT(String sdt) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Query<NhanVien> query = session.createNativeQuery(
					"SELECT * FROM NhanVien WHERE soDienThoai = '" + sdt.trim() + "'", NhanVien.class);
			NhanVien nv = query.getSingleResult();

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

	public boolean updateMatKhauNhanVien(NhanVien nhanVien) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			int tt = nhanVien.isTrangThai() ? 1 : 0;
			Query<NhanVien> qr = session.createNativeQuery("update NhanVien set matKhau = '" + nhanVien.getMatKhau()
					+ "' where maNhanVien = '" + nhanVien.getMaNhanVien() + "'", NhanVien.class);
			int hh = qr.executeUpdate();
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	public NhanVien updateNV(NhanVien nv) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			NhanVien updatedNV = session.merge(nv);
			t.commit();
			return nv;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public List<NhanVien> getAllNhanViens() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<NhanVien> listNhanVien = null;
		try {
			String hql = "from NhanVien";
			listNhanVien = session.createQuery(hql, NhanVien.class).getResultList();
			t.commit();
			return listNhanVien;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listNhanVien;
		}
	}

	public List<NhanVien> getNhanViensByDOB(String dateFrom, String dateTo) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<NhanVien> listNhanVien = null;
		String sql;
		try {
			if (dateFrom != "" && dateTo != "") {
				sql = "select * from NhanVien where ngaySinh between '" + dateFrom + "' and '" + dateTo + "'";
			} else if (dateFrom != "") {
				sql = "select * from NhanVien where ngaySinh >= '" + dateFrom + "'";
			} else if (dateTo != "") {
				sql = "select * from NhanVien where ngaySinh <= '" + dateTo + "'";
			} else {
				sql = "select * from NhanVien";
			}

			Query<NhanVien> qr = session.createNativeQuery(sql, NhanVien.class);
			listNhanVien = qr.getResultList();
			t.commit();
			return listNhanVien;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listNhanVien;
		}
	}
}
