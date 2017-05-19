package domeinmodel;

import java.util.Comparator;

public class Product implements Comparator<Product>{
	private int x;
	private int y;
	private String naam;
	private int grootte;
	private int artikelnummer;
	private boolean visited;
	
	public Product() {
	}

	public Product(int artikelnummer, int x, int y, int grootte, String naam) {
		this.x = x;
		this.y = y;
		this.grootte = grootte;
		this.naam = naam;
		this.artikelnummer = artikelnummer;
		this.visited = false;

	}

	public void Visited() {
		this.visited = true;
	}

	public void resetVisited() {
		this.visited = false;
	}

	public String getLocatie() {
		return x + "-" + y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getNaam() {
		return naam;
	}

	public int getGrootte() {
		return grootte;
	}

	public int getArtikelnummer() {
		return artikelnummer;
	}

	public boolean getVisited() {
		return visited;
	}

	@Override
	public String toString() {
		return "" + this.artikelnummer;
	}

	public int meetAfstand(Product product) {
		int deltaLongitude = 0;
		int deltaLatitude = 0;
		if (product.getY() > this.getY()) {
			deltaLongitude = (product.getY() - this.getY());
		} else {
			deltaLongitude = (this.getY() - product.getY());
		}

		if (product.getX() > this.getX()) {
			deltaLatitude = (product.getX() - this.getX());
		} else {
			deltaLatitude = (this.getX() - product.getX());
		}
		return deltaLongitude + deltaLatitude;
	}
	
	@Override
	public int compare(Product productA, Product productB) {
		return productB.grootte - productA.grootte;
	}
}
