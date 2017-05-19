package tspScherm;

public class Product {
	private int x;
	private int y;
	private String naam;
	private int gewicht;
	private int artikelnr;
	private boolean visited;
	
	public Product(int artikelnr, int x, int y, int gewicht, String naam) {
		this.x = x;
		this.y = y;
		this.naam = naam;
		this.gewicht = gewicht;
		this.artikelnr = artikelnr;
		this.visited = false;
		
	}
	
	public void Visited()
	{
		this.visited = true;
	}
	
	public void resetVisited()
	{
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
	
	public int getGewicht() {
		return gewicht;
	}
	
	public int getArtikelnr() {
		return artikelnr;
	}
	
	public boolean getVisited()
	{
		return visited;
	}
	
	@Override
	public String toString()
	{
		return "" + this.artikelnr;
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
}
