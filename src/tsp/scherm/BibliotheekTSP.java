package tsp.scherm;

import java.util.ArrayList;
import java.util.List;

import domeinmodel.Bestelling;

public class BibliotheekTSP {

	private List<AlgoritmeTSP> algoritmes = new ArrayList<>();

	public BibliotheekTSP(Bestelling bestelling) {
		algoritmes.add(new BruteForce(bestelling));
		algoritmes.add(new NearestNeighbour(bestelling));
		algoritmes.add(new RandomPath(bestelling));
		algoritmes.add(new AntColonyOptimization(bestelling));
	}

	public AlgoritmeTSP getAlgoritme(int index) {
		return algoritmes.get(index);
	}
}
