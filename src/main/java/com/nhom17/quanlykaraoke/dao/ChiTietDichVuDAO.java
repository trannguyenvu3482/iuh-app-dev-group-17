package com.nhom17.quanlykaraoke.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nhom17.quanlykaraoke.entities.ChiTietDichVu;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

public class ChiTietDichVuDAO {

	private SessionFactory factory = null;

	public ChiTietDichVuDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addChiTietDichVu(ChiTietDichVu chiTietDichVu) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			session.persist(chiTietDichVu);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	public List<ChiTietDichVu> getChiTietDichVuByMaPDPAndMaPhong(String maPDP, String maPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<ChiTietDichVu> listChiTietDichVu = null;
		try {
			String sql = "SELECT * FROM ChiTietDichVu WHERE maPhieuDatPhong = '" + maPDP + "' AND maPhong = '" + maPhong
					+ "'";
			listChiTietDichVu = session.createNativeQuery(sql, ChiTietDichVu.class).getResultList();

			t.commit();
			return listChiTietDichVu;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listChiTietDichVu;
		}
	}

	public double getTongTienDichVuByMaPDP(String maPDP) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<ChiTietDichVu> listChiTietDichVu = null;
		double tongTienDichVu = 0;
		try {
			String sql = "SELECT * FROM ChiTietDichVu WHERE maPhieuDatPhong = '" + maPDP + "'";
			listChiTietDichVu = session.createNativeQuery(sql, ChiTietDichVu.class).getResultList();

			for (ChiTietDichVu chiTietDichVu : listChiTietDichVu) {
				tongTienDichVu += chiTietDichVu.getThanhTienDichVu();
			}

			t.commit();
			return tongTienDichVu;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return tongTienDichVu;
		}
	}

	public List<ChiTietDichVu> getAllChiTietDichVus() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<ChiTietDichVu> listChiTietDichVu = null;
		try {
			String hql = "from ChiTietDichVu";
			listChiTietDichVu = session.createQuery(hql, ChiTietDichVu.class).getResultList();
			t.commit();
			return listChiTietDichVu;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listChiTietDichVu;
		}
	}

	public ChiTietDichVu updateChiTietDichVu(ChiTietDichVu chiTietDichVu) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			ChiTietDichVu updatedchiChiTietDichVu = session.merge(chiTietDichVu);

			t.commit();
			return updatedchiChiTietDichVu;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public boolean deleteChiTietDichVu(ChiTietDichVu chiTietDichVu) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		try {
			session.remove(chiTietDichVu);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

}
