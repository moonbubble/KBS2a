package bpp.simulatie.algoritmes;

import java.util.List;

import domeinmodel.Bestelling;
import domeinmodel.Doos;
import domeinmodel.Product;

public interface Algoritme {
	List<Doos> bepaalDozen(List<Product> producten, int grootteDoos);

	String getNaam();
}
