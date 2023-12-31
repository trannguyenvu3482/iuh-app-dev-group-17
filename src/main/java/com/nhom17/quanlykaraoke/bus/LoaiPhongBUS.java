package com.nhom17.quanlykaraoke.bus;

import java.util.List;

import com.nhom17.quanlykaraoke.dao.LoaiPhongDAO;
import com.nhom17.quanlykaraoke.entities.HangHoa;
import com.nhom17.quanlykaraoke.entities.LoaiPhong;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 01-Nov-2023 3:57:17 PM
 */
public class LoaiPhongBUS {
	private LoaiPhongDAO lpDAO;

	/**
	 * 
	 */
	public LoaiPhongBUS() {
		this.lpDAO = new LoaiPhongDAO();
	}

	public List<LoaiPhong> getAllLoaiPhongs() {
		return lpDAO.getAllLoaiPhongs();
	}
	
	public LoaiPhong getLoaiPhong(String tenLP, int kt) {
		return lpDAO.getLoaiPhong(tenLP, kt);
	}
}
