package tspScherm;
import java.util.ArrayList;
import java.util.Collections;

public class RandomPath
{
	private ArrayList<Product> orderList;

	public RandomPath(ArrayList<Product> orderList)
	{
		this.orderList = orderList;
	}

	public void printRoute() {
		System.out.println("--- ROUTE ---");
		for (Product p : orderList)
		{
			System.out.println(p.getLocatie());
		}
	}

	public void printOrder()
	{
		System.out.println("--- ORDER ---");
		for (Product p : orderList)
		{
			System.out.println(p.getLocatie());
		}
	}

	public ArrayList<Product> algoritme()
	{
		Collections.shuffle(orderList);
		return orderList;
	}

}
