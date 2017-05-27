package tsp.scherm;

import java.util.List;

import domeinmodel.Product;

public interface AlgoritmeTSP {
	List<Product> algoritme();

	String getNaam();

	static int getAfstand(List<Product> producten) {
		int afstand = 0;
		if (producten.size() > 1) {
			for (int i = 0; i < producten.size() - 1; i++) {
				Product product1 = producten.get(i);
				Product product2 = producten.get(i + 1);
				afstand += product1.meetAfstand(product2);
			}
		}
		return afstand;
	}
}
