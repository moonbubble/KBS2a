package bpp.simulatie.algoritmes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domeinmodel.*;

class NextFit implements Algoritme {
	private List<Doos> dozen = new ArrayList<>();
	private List<Product> producten;

	public List<Doos> bepaalDozen(Bestelling bestelling, int grootteDoos) {
		producten = new ArrayList<>(bestelling.getProducten());
		dozen.add(new Doos(grootteDoos));
		for (Product product : producten) {
			Doos huidigeDoos = dozen.get(dozen.size() - 1);
			if (huidigeDoos.past(product)) {
				huidigeDoos.voegToe(product);
			} else {
				dozen.add(new Doos(product, grootteDoos));
			}
		}
		return dozen;
	}

	@Override
	public String getNaam() {
		return "Next-fit";
	}

}
