package bpp.simulatie;

import java.util.ArrayList;
import java.util.List;

public class Doos {
	private int grootte;
	private List<Product> producten = new ArrayList<>();

	public Doos(int grootte) {
		this.grootte = grootte;
	}

	public Doos(Product product, int grootte) {
		this.grootte = grootte;
		producten.add(product);
	}

	public void voegToe(Product product) {
		producten.add(product);
	}

	public int getGrootteDoos() {
		return grootte;
	}

	public int getInhoud() {
		int inhoud = 0;
		for (int i = 0; i < producten.size(); i++) {
			Product a = producten.get(i);
			inhoud += a.getGrootte();
		}
		return inhoud;
	}
	
	public List<Product> getProducten() {
		return producten;
	}

	public boolean past(Product product) {
		return getGrootteDoos() >= (getInhoud() + product.getGrootte());
	}
	
}
