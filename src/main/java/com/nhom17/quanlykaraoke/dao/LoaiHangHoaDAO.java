package com.nhom17.quanlykaraoke.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nhom17.quanlykaraoke.entities.HangHoa;
import com.nhom17.quanlykaraoke.entities.KhachHang;
import com.nhom17.quanlykaraoke.entities.LoaiHangHoa;
import com.nhom17.quanlykaraoke.entities.LoaiHangHoa;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

public class LoaiHangHoaDAO {
	private SessionFactory factory = null;

	public LoaiHangHoaDAO() {
		factory = HibernateUtil.getMySessionFactory();
	}

	public LoaiHangHoa getLoaiHangHoa(String maLoaiHangHoa) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			LoaiHangHoa lp = session.get(LoaiHangHoa.class, maLoaiHangHoa);
			t.commit();
			return lp;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public boolean addLoaiHangHoa(LoaiHangHoa lhh) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			String maLHH = getNextMaLHH();
			lhh.setMaLoaiHangHoa(maLHH);
			session.persist(lhh);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	private String getNextMaLHH() {
		String idPrefix = "LHH";

		int count = countLoaiHangHoa();

		if (count < 0 || count > 999) {
			return null;
		}

		return idPrefix + String.format("%03d", count + 1);
	}

	private int countLoaiHangHoa() {
		Session session = factory.getCurrentSession();

		try {
			String hql = "From LoaiHangHoa";
			Query<LoaiHangHoa> query = session.createQuery(hql, LoaiHangHoa.class);
			List<LoaiHangHoa> listLoaiHangHoa = query.getResultList();

			return listLoaiHangHoa.size();

		} catch (Exception e) {
			return -1;
		}
	}

	public List<LoaiHangHoa> getAllLoaiHangHoas() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<LoaiHangHoa> listLoaiHangHoa = null;
		try {
			String hql = "from LoaiHangHoa";
			listLoaiHangHoa = session.createQuery(hql, LoaiHangHoa.class).getResultList();
			t.commit();
			return listLoaiHangHoa;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listLoaiHangHoa;
		}
	}

	public LoaiHangHoa getLoaiHangHoaByName(String name) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Query<LoaiHangHoa> query = session.createNativeQuery(
					"select * FROM LoaiHangHoa WHERE tenLoaiHangHoa = N'" + name + "'", LoaiHangHoa.class);
			LoaiHangHoa lhh = query.getResultList().get(0);
			t.commit();
			return lhh;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}
}
