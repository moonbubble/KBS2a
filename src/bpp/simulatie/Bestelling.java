package bpp.simulatie;

import java.util.ArrayList;
import java.util.List;

import applicatie.Main;

public class Bestelling {
	private List<Product> producten;
	private int bestellingnummer;
	private String datum;
	private Klant klant;

	public Bestelling() {
		this.producten = new ArrayList<>();
	}

	public Bestelling(int bestellingnummer, String string, Klant klant) {
		this.bestellingnummer = bestellingnummer;
		this.datum = string;
		this.klant = klant;
	}

	public List<Product> getProducten() {
		return producten;
	}

	public void voegProductToe(Product product) {
		producten.add(product);
	}

	public void setBestelling(List<Product> producten) {
		this.producten = new ArrayList<>(producten);
	}

	public List<Product> getBestelling() {
		return producten;
	}
}
