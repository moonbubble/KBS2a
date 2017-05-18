package bpp.simulatie.algoritmes;

import java.util.List;

import bpp.simulatie.Bestelling;
import bpp.simulatie.Doos;

public interface Algoritme {
	List<Doos> bepaalDozen(Bestelling bestelling, int grootteDoos);

	String getNaam();
}
