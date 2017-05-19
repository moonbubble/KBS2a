package domeinmodel;

import java.util.ArrayList;
import java.util.List;

public class Bestelling {
	private int ordernr;
	private String datum;
	private List<Product> orderList = new ArrayList<>();
	private Klant klant;

	public Bestelling() {
		this.ordernr = 999;
		this.datum = "01-01-2000";
		this.klant = new Klant("John", "Doe", "JohnDoeStreet 1", "1234 JD", "JohnDoeVille");
	}

	public Bestelling(int ordernr, String datum, Klant klant) {
		this.ordernr = ordernr;
		this.datum = datum;
		this.klant = klant;
	}

	public void resetProducten() {
		for (int i = 0; i < orderList.size(); i++) {
			orderList.get(i).resetVisited();
		}
	}

	public void voegProductToe(Product p) {
		orderList.add(p);
	}

	public void setProducten(List<Product> o) {
		this.orderList = o;
	}

	public List<Product> getProducten() {
		return orderList;
	}

	public int getOrdernr() {
		return ordernr;
	}

	public Klant getKlant() {
		return klant;
	}

	public String getDatum() {
		return datum;
	}
}
