package bpp.simulatie.algoritmes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domeinmodel.*;

class EigenAlgoritme implements Algoritme {
	private List<Doos> dozen = new ArrayList<>();
	private List<Product> producten;

	@Override
	public List<Doos> bepaalDozen(List<Product> producten, int grootteDoos) {
		producten = new ArrayList<>(producten);
		dozen.add(new Doos(grootteDoos));
	
		Collections.sort(producten, new Product());
		
		for (Product product : producten) {
			Doos huidigeDoos = dozen.get(dozen.size() - 1);
			if (dozen.size() >= 2) {
				Doos vorigeDoos = dozen.get(dozen.size() - 2);
				if (vorigeDoos.past(product)) {
					vorigeDoos.voegToe(product);
				} else if (huidigeDoos.past(product)) {
					huidigeDoos.voegToe(product);
				} else {
					dozen.add(new Doos(product, grootteDoos));
				}
			} else {
				if (huidigeDoos.past(product)) {
					huidigeDoos.voegToe(product);
				} else {
					dozen.add(new Doos(product, grootteDoos));
				}
			}
		}
		return dozen;
	}


	@Override
	public String getNaam() {
		return "Eigen algoritme";
	}

}
