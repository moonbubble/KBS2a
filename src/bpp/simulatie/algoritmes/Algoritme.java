package bpp.simulatie.algoritmes;

import java.util.List;

import domeinmodel.Bestelling;
import domeinmodel.Doos;

public interface Algoritme {
	List<Doos> bepaalDozen(Bestelling bestelling, int grootteDoos);

	String getNaam();
}
