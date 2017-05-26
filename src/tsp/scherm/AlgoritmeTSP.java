package tsp.scherm;

import java.util.List;

import domeinmodel.Doos;
import domeinmodel.Product;

public interface AlgoritmeTSP {
	List<Product> algoritme();

	String getNaam();
	
	static int getAfstand(List<Product> producten) {
		return 0;
	}
}
