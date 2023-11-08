package com.nhom17.quanlykaraoke.bus;

import com.nhom17.quanlykaraoke.dao.ChucVuDAO;
import com.nhom17.quanlykaraoke.entities.ChucVu;

public class ChucVuBUS {
	private ChucVuDAO cvDAO;

	public ChucVuBUS() {
		this.cvDAO = new ChucVuDAO();
	}

	public ChucVu getChucVu(String maCV) {
		return cvDAO.getChucVu(maCV);
	}
	
}
