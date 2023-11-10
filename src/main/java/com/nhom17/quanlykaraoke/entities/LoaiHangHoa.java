package com.nhom17.quanlykaraoke.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LoaiHangHoa")
public class LoaiHangHoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "char(6)")
	private String maLoaiHangHoa;

	@Column(columnDefinition = "nvarchar(30)")
	private String tenLoaiHangHoa;
	
	public LoaiHangHoa() {
		// TODO Auto-generated constructor stub
	}
	
	public LoaiHangHoa(String maLoaiHangHoa, String tenLoaihangHoa) {
		this.maLoaiHangHoa = maLoaiHangHoa;
		this.tenLoaiHangHoa = tenLoaihangHoa;
	}

	public String getMaLoaiHangHoa() {
		return maLoaiHangHoa;
	}

	public void setMaLoaiHangHoa(String maLoaiHangHoa) {
		this.maLoaiHangHoa = maLoaiHangHoa;
	}

	public String getTenLoaiHangHoa() {
		return tenLoaiHangHoa;
	}

	public void setTenLoaihangHoa(String tenLoaihangHoa) {
		this.tenLoaiHangHoa = tenLoaihangHoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiHangHoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiHangHoa other = (LoaiHangHoa) obj;
		return Objects.equals(maLoaiHangHoa, other.maLoaiHangHoa);
	}

	@Override
	public String toString() {
		return "LoaiHangHoa [maLoaiHangHoa=" + maLoaiHangHoa + ", tenLoaihangHoa=" + tenLoaiHangHoa + "]";
	}

}
