package bpp.simulatie;

import java.util.Comparator;

public class Product implements Comparator<Product>{
	private int grootte;
	private int artikelnummer;
	private int x;
	private int y;
	private String naam;
	
	public Product() {
		
	}

	public Product(int artikelnummer, int x, int y, int grootte, String naam) {
		this.artikelnummer = artikelnummer;
		this.x = x;
		this.y = y;
		this.grootte = grootte;
		this.naam = naam;
	}

	public int getGrootte() {
		return grootte;
	}

	public int getArtikelnummer() {
		return artikelnummer;
	}
	
	public String getNaam() {
		return naam;
	}

	@Override
	public int compare(Product productA, Product productB) {
		return productB.grootte - productA.grootte;
	}
}
