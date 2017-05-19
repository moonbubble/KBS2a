package tsp.scherm;

import java.util.Comparator;

class MyComparator implements Comparator<Product> {
@Override
public int compare(Product p1, Product p2) {
    if (p1.getArtikelnr() < p2.getArtikelnr()) {
        return -1;
    } else if (p1.getArtikelnr() > p2.getArtikelnr()) {
        return 1;
    }
    return 0;
}}