package bpp.simulatie;

import java.util.ArrayList;
import java.util.List;

public class Bestelling {
	private List<Product> producten;
	private int ordernummer;
	private String datum;
	private Klant klant;

	public Bestelling(int ordernummer, String string, Klant klant) {
		this.ordernummer = ordernummer;
		this.datum = string;
		this.klant = klant;
	}


	public List<Product> getProducten() {
		return producten;
	}
	
	public void voegProductToe(Product product) {
		producten.add(product);
	}


	public void setOrder(List<Product> producten) {
		this.producten = new ArrayList<>(producten);
	}
}
