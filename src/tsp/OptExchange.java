import java.util.ArrayList;

public class OptExchange {
	private ArrayList<Product> order;
	
	public OptExchange(Order o) {
		this.order = o.getOrder();
	}
	
	public void algoritme() {
		for (int i = 0; i < order.size(); i++) {
			if (order.get(i).getX() == 1) {
				System.out.println("X is 1");
			}
		}
	}
}
