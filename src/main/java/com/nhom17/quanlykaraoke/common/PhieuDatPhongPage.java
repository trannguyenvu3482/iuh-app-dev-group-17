package com.nhom17.quanlykaraoke.common;

import java.util.List;

/**
 * Một list các trang của phiếu đặt phòng.
 *
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 03-Nov-2023 12:41:53 PM
 */
public class PhieuDatPhongPage<Phong> {

	private final int page;

	private final List<Phong> items;

	private int originalSize;

	/**
	 * Instantiates a new phieu dat phong page.
	 *
	 * @param page   the page
	 * @param source the source
	 */
	public PhieuDatPhongPage(int page, List<Phong> source) {
		this.page = page;

		int pageSize = 8;
		int fromIndex = pageSize * (page - 1);
		int toIndex = fromIndex + 8;
		this.setOriginalSize(source.size());

		// If there is less than 8 items on next page, only get the maximum allowed
		if (toIndex > source.size() - 1) {
			toIndex = source.size();
		}

		this.items = source.subList(fromIndex, toIndex);

	}

	/**
	 * Trả về số thứ tự trang hiện tại
	 *
	 * @return the page number
	 */
	public int getPageNumber() {
		return page;
	}

	/**
	 * Lấy các items bên trong
	 *
	 * @return the items
	 */
	public List<Phong> getItems() {
		return items;
	}

	/**
	 * Kiểm tra xem trang hiện tại phải trang đầu tiên không
	 *
	 * @return true, if is first page
	 */
	public boolean isFirstPage() {
		return page == 1;
	}

	/**
	 * Logic chuyển sang page tiếp theo.
	 *
	 * @return the next PhieuDatPhongPage
	 */
	public PhieuDatPhongPage<Phong> nextPage() {
		return new PhieuDatPhongPage<Phong>(page + 1, items);
	}

	/**
	 * Logic chuyển về phiếu đặt phòng trước đó.
	 *
	 * @return the previous PhieuDatPhongPage
	 */
	public PhieuDatPhongPage<Phong> prevPage() {
		return new PhieuDatPhongPage<Phong>(page - 1, items);
	}

	/**
	 * Trả về số lượng phần tử mảng gốc.
	 *
	 * @return the original size
	 */
	public int getOriginalSize() {
		return originalSize;
	}

	/**
	 * Sets the original size.
	 *
	 * @param originalSize the new original size
	 */
	public void setOriginalSize(int originalSize) {
		this.originalSize = originalSize;
	}

}
