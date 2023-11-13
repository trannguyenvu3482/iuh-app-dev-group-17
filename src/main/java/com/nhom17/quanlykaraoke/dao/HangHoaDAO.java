package com.nhom17.quanlykaraoke.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom17.quanlykaraoke.entities.HangHoa;
import com.nhom17.quanlykaraoke.entities.HangHoa;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

public class HangHoaDAO {
	private SessionFactory factory = null;

	public HangHoaDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addHangHoa(HangHoa hh) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			String maHH = getNextMaHH();

			hh.setMaHangHoa(maHH);
			session.persist(hh);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	private String getNextMaHH() {
		String idPrefix = "HH";

		int count = countHangHoa();

		if (count < 0 || count > 999) {
			return null;
		}

		return idPrefix + String.format("%03d", count + 1);
	}

	private int countHangHoa() {
		Session session = factory.getCurrentSession();

		try {
			String hql = "From HangHoa";
			Query<HangHoa> query = session.createQuery(hql, HangHoa.class);
			List<HangHoa> listHangHoa = query.getResultList();

			return listHangHoa.size();

		} catch (Exception e) {
			return -1;
		}
	}

	public List<HangHoa> getAllHangHoas() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<HangHoa> listHangHoa = null;
		try {
			String hql = "from HangHoa";
			listHangHoa = session.createQuery(hql, HangHoa.class).getResultList();
			t.commit();
			return listHangHoa;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listHangHoa;
		}
	}

	public boolean updateHangHoa(HangHoa hangHoa) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		
		try {
			int tt = hangHoa.isTrangThai()?1:0;
			Query<HangHoa> qr = session.createNativeQuery("update HangHoa set tenHangHoa = N'" + hangHoa.getTenHangHoa() +
					"' ,maLoaiHangHoa = '" + hangHoa.getLoaiHangHoa().getMaLoaiHangHoa() + 
					"' ,donGia = " + hangHoa.getDonGia() + " ,soLuongTon = " + hangHoa.getSoLuongTon() + 
					" ,trangThai = " + tt + " where maHangHoa = '" + hangHoa.getMaHangHoa()+"'",HangHoa.class);
			int hh = qr.executeUpdate();
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}
	public boolean updateSoLuongTon(HangHoa hangHoa, int sl) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		
		try {
			int tt = hangHoa.isTrangThai()?1:0;
			int slt = hangHoa.getSoLuongTon() + sl;
			Query<HangHoa> qr = session.createNativeQuery("update HangHoa set soLuongTon = " + slt + 
				" where maHangHoa = '" + hangHoa.getMaHangHoa()+"'",HangHoa.class);
			int hh = qr.executeUpdate();
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}
}
