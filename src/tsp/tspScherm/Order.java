package tspScherm;

import java.util.ArrayList;

public class Order
{
	private int ordernr;
	private String datum;
	private ArrayList<Product> orderList = new ArrayList<>();
	private Klant klant;
	
	public Order()
	{
		this.ordernr = 999;
		this.datum = "01-01-2000";
		this.klant = new Klant("John", "Doe", "JohnDoeStreet 1", "1234 JD", "JohnDoeVille");
	}
	
	public Order(int ordernr, String datum, Klant klant)
	{
		this.ordernr = ordernr;
		this.datum = datum;
		this.klant = klant;
	}
	
	public void resetOrderList()
	{
		for(int i = 0; i < orderList.size();i++)
		{
			orderList.get(i).resetVisited();
		}
	}
	
	public void voegProductToe(Product p)
	{
		orderList.add(p);
	}
	
	public void setOrderList(ArrayList<Product> o)
	{
		this.orderList = o;
	}
	
	public ArrayList<Product> getOrderList()
	{
		return orderList;
	}
	
	public int getOrdernr()
	{
		return ordernr;
	}
	
	public Klant getKlant()
	{
		return klant;
	}
	
	public String getDatum()
	{
		return datum;
	}
}
