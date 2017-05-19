package tsp.scherm;

import java.util.Comparator;

import domeinmodel.Product;

class MyComparator implements Comparator<Product> {
	@Override
	public int compare(Product p1, Product p2) {
		if (p1.getArtikelnummer() < p2.getArtikelnummer()) {
			return -1;
		} else if (p1.getArtikelnummer() > p2.getArtikelnummer()) {
			return 1;
		}
		return 0;
	}
}