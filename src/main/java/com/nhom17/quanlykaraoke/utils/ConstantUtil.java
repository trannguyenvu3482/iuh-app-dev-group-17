package com.nhom17.quanlykaraoke.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.nhom17.quanlykaraoke.entities.NhanVien;

public class ConstantUtil {
	public static final Color MAIN_COLOR = new Color(20, 20, 20);
	public static final int MAXIMUM_PAGE_SIZE = 8;
	public static NhanVien currentNhanVien = null;
	public static final double DAYTIME_HOUR_PRICE = 45000;
	public static final double NIGHTTIME_HOUR_PRICE = 60000;

	public static final double getStandardHourPrice(LocalDateTime time) {
		if (time.getHour() < 12) {
			return DAYTIME_HOUR_PRICE;
		} else {
			return NIGHTTIME_HOUR_PRICE;
		}
	}

	public static final ImageIcon byteArrayToImageIcon(byte[] data) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int width = img.getWidth(null);
		int height = img.getHeight(null);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int circleDiameter = Math.min(width, height);
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, circleDiameter, circleDiameter);
		g2.setClip(circle);
		g2.drawImage(img, 0, 0, null);

		// Display circular image
		return new ImageIcon(bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
	}

	public static final byte[] getDefaultMaleAvatar() {
		try {
			return Files.readAllBytes(Paths.get("src/main/resources/images/male-avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static final ImageIcon getDefaultMaleAvatarIcon() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/main/resources/images/male-avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int width = img.getWidth(null);
		int height = img.getHeight(null);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int circleDiameter = Math.min(width, height);
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, circleDiameter, circleDiameter);
		g2.setClip(circle);
		g2.drawImage(img, 0, 0, null);

		// Display circular image
		return new ImageIcon(bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
	}

	public static final byte[] getDefaultFemaleAvatar() {
		try {
			return Files.readAllBytes(Paths.get("src/main/resources/images/female-avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static final ImageIcon getDefaultFemaleAvatarIcon() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/main/resources/images/female-avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int width = img.getWidth(null);
		int height = img.getHeight(null);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int circleDiameter = Math.min(width, height);
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, circleDiameter, circleDiameter);
		g2.setClip(circle);
		g2.drawImage(img, 0, 0, null);

		// Display circular image
		return new ImageIcon(bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
	}
}
