package bpp.simulatie.algoritmes;

import java.util.ArrayList;
import java.util.List;

import domeinmodel.*;

class BestFit implements Algoritme {
	private List<Doos> dozen = new ArrayList<>();
	private List<Product> producten;

	@Override
	public List<Doos> bepaalDozen(Bestelling bestelling, int grootteDoos) {
		producten = new ArrayList<>(bestelling.getProducten());
		int inhoudHuidigeDoos = 0;
		int inhoudVorigeDoos = 0;

		dozen.add(new Doos(grootteDoos));
		for (Product product : producten) {
			Doos huidigeDoos = dozen.get(dozen.size() - 1);
			if (dozen.size() >= 2) {
				Doos vorigeDoos = dozen.get(dozen.size() - 2);
				if (vorigeDoos.past(product)) {
					inhoudVorigeDoos = vorigeDoos.getInhoud();
				} else if (huidigeDoos.past(product)) {
					inhoudHuidigeDoos = huidigeDoos.getInhoud();
				}

				if (inhoudHuidigeDoos > inhoudVorigeDoos && inhoudVorigeDoos != 0) {
					vorigeDoos.voegToe(product);
				} else if (inhoudVorigeDoos > inhoudHuidigeDoos && inhoudHuidigeDoos != 0) {
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
		return "Best-Fit";
	}
}
