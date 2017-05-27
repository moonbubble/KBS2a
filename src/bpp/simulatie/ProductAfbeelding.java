package bpp.simulatie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import domeinmodel.*;

public class ProductAfbeelding extends JPanel {
	private Product product;
	private int hoogteAfbeelding;
	private int grootteProduct;

	public ProductAfbeelding(Product product, Doos doos) {
		this.product = product;

		int grootteDoos = doos.getGrootteDoos();
		grootteProduct = product.getGrootte();
		hoogteAfbeelding = 300 / grootteDoos * grootteProduct;

		setSize(new Dimension(200, hoogteAfbeelding + 1));
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
	}

	public void paintComponent(Graphics g) {

		g.setFont(new Font("Verdana", Font.PLAIN, 12));

		if (grootteProduct == 1) {
			// Sunsphere
			g.setColor(new Color(254, 213, 53));
			g.setColor(new Color(244, 149, 244));
		} else if (grootteProduct == 2) {
			// Leconte
			g.setColor(new Color(141, 32, 72));
			g.setColor(new Color(249, 102, 194));
		} else if (grootteProduct == 3) {
			// Torch
			g.setColor(new Color(230, 89, 51));
			g.setColor(new Color(247, 75, 111));
		} else if (grootteProduct == 4) {
			// Valley
			g.setColor(new Color(0, 116, 111));
			g.setColor(new Color(244, 178, 62));
		} else if (grootteProduct == 5) {
			// Globe
			g.setColor(new Color(0, 108, 147));
			g.setColor(new Color(207, 245, 25));
		} else if (grootteProduct == 6) {
			// Regalia
			g.setColor(new Color(117, 74, 126));
			g.setColor(new Color(89, 204, 51));
		} else if (grootteProduct == 7) {
			// Summit
			g.setColor(new Color(185, 225, 226));
			g.setColor(new Color(147, 240, 211));
		} else if (grootteProduct == 8) {
			// Fountain
			g.setColor(new Color(33, 151, 169));
			g.setColor(new Color(74, 203, 189));
		} else if (grootteProduct == 9) {
			// Eureka
			g.setColor(new Color(235, 234, 100));
			g.setColor(new Color(81, 143, 198));
		} else if (grootteProduct == 10) {
			// Energy
			g.setColor(new Color(238, 62, 128));
			g.setColor(new Color(91, 87, 193));
		}
		g.drawRect(0, 0, 200, hoogteAfbeelding);
		g.fillRect(0, 0, 200, hoogteAfbeelding);

		g.setColor(Color.black);
		g.drawLine(0, 0, 200, 0);
		g.drawLine(0, 0, 0, hoogteAfbeelding);
		g.drawLine(0, hoogteAfbeelding, 200, hoogteAfbeelding);
		g.drawLine(200, 0, 200, hoogteAfbeelding);

		g.drawString(product.getNaam() + " (" + product.getGrootte() + ")", 5, (hoogteAfbeelding - 5));

	}

	public int getHoogteAfbeelding() {
		return hoogteAfbeelding;
	}

}
