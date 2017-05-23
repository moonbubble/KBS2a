package bpp.simulatie.algoritmes;

import java.util.List;

import domeinmodel.Bestelling;
import domeinmodel.Doos;
import domeinmodel.Product;

public interface Algoritme {
	List<Doos> bepaalDozen(List<Product> producten, int grootteDoos);

	String getNaam();
	
	static List<Product> wisselArray(List<Product> producten) {
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
				producten.set(i, p1);
				producten.set(i - 1, p2);
				producten.set(i - 2, p3);
			}
		}
		
		if ((producten.size() -1)  % 3 == 1){
			p1 = producten.get(producten.size() -2);
			p2 = producten.get(producten.size() -1);
			
			producten.set(producten.size() -1, p1);
			producten.set(producten.size() -2, p2);
		}
		return producten;
	}
}
