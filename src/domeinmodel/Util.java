package domeinmodel;

import java.util.ArrayList;
import java.util.List;

public class Util {
	
	private Util() {
		// Geen instanties van maken.
	}
	
	public static List<Product> wisselArray(List<Product> producten) {
		List<Product> gewisseldeProducten = new ArrayList<>(producten); 
		Product p1 = null;
		Product p2 = null;
		Product p3;
		for (int i = 0; i < producten.size(); i++) {
			if (i % 3 == 0) {
				p1 = producten.get(i);
			} else if (i % 3 == 1) {
				p2 = producten.get(i);
			} else if (i % 3 == 2) {
				p3 = producten.get(i);
				gewisseldeProducten.set(i, p1);
				gewisseldeProducten.set(i - 1, p2);
				gewisseldeProducten.set(i - 2, p3);
			}
		}
		
		if ((producten.size() -1)  % 3 == 1){
			p1 = producten.get(producten.size() -2);
			p2 = producten.get(producten.size() -1);
			
			gewisseldeProducten.set(producten.size() -1, p1);
			gewisseldeProducten.set(producten.size() -2, p2);
		}
		return gewisseldeProducten;
		
	}
}
