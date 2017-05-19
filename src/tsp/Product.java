package tsp;


public class Product {
	private int x;
	private int y;
	private String naam;
	
	public Product(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Product(String naam, int x, int y) {
		this.naam = naam;
		this.x = x;
		this.y = y;
	}
	
	public String getLocatie() {
		return x + "-" + y;
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
	public String toString() {
		if (naam != null) {
			return naam;
		} else {
			return "(" + x + "," + y + ")";
		}
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
}
