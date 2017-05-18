package bpp.simulatie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DoosAfbeelding extends JPanel {
	private Doos doos;
	private List<Product> producten;
	private int COy;
	private List<ProductAfbeelding> productAfbeeldingen;

	public DoosAfbeelding(Doos doos) {
		this.doos = doos;
		this.producten = doos.getProducten();
		productAfbeeldingen = new ArrayList<ProductAfbeelding>();
		COy = 0;

		setSize(new Dimension(201, 301));
		setBackground(Color.WHITE);
		setLayout(null);

		for (Product product : producten) {
			ProductAfbeelding productAfbeelding = new ProductAfbeelding(product, doos);
			productAfbeelding.setLocation(0, COy);
			productAfbeeldingen.add(productAfbeelding);

			COy += productAfbeelding.getHoogteAfbeelding();
		}
	}

	public void paintComponent(Graphics g) {
		g.drawRect(0, 0, 200, 300);
	}

	public void tekenProduct(int index) {
		add(productAfbeeldingen.get(index));
		repaint();
	}

	public void tekenAlleProducten() {
		for (ProductAfbeelding productAfbeelding : productAfbeeldingen) {
			add(productAfbeelding);
		}
		repaint();
	}

	public List<ProductAfbeelding> getProductAfbeeldingen() {
		return productAfbeeldingen;
	}
}
