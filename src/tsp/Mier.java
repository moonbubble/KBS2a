package tsp;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Mier implements Callable<Mier> {
	private AntColonyOptimization aco;
	private int mierNumb;
	private Route route = null;
	static int ongeldigeProductIndex = -1;
	static int aantalProducten = Driver.initialRoute.size();

	public Route getRoute() {
		return route;
	}
	
	public Mier(AntColonyOptimization aco, int mierNumb) {
		this.aco = aco;
		this.mierNumb = mierNumb;
	}

	@Override
	public Mier call() throws Exception {
		System.out.println("Mier.call() aangeroepen");
		int eersteProductIndex = ThreadLocalRandom.current().nextInt(aantalProducten);
		ArrayList<Product> routeProducten = new ArrayList<Product>(aantalProducten);
		return null;
	}
	public int getMierNumb() {
		return mierNumb;
	}

}
