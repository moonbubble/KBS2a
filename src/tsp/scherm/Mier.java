package tsp.scherm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import domeinmodel.Product;

public class Mier implements Callable<Mier> { 
	public static final double pL = 0.2; //parameter die gebruikt wordt om level van feromoonverdamping te veranderen (waarde is tussen 0 en 1) 
	public static final double pH = 0.0005; //parameter die gebruikt wordt om de hoeveelheid feromoon aan te passen (waarde is tussen 0 en 1)
	public static final double ALPHA = 0.01; // parameter die gebruikt wordt om te controleren hoe belangrijk het feromoon spoor is (waarde >= 0
	public static final double BETA = 9.5; // parameter die gebruikt wordt om te contoleren hoe belangrijk de afstand is tussen een beginpunt en eindpunt (waarde >= 1)
	private ACODriver aco;
	private Route route = null;
	static int ongeldigeProductIndex = -1;
	static int aantalProducten = AntColonyOptimization.initialRoute.size();

	public Route getRoute() {
		return route;
	}
	
	public Mier(ACODriver aco) {
		this.aco = aco;
	}

	@Override
	public Mier call() throws Exception {
		System.out.println(aantalProducten);
		int eersteProductIndex = ThreadLocalRandom.current().nextInt(aantalProducten);
		ArrayList<Product> routeProducten = new ArrayList<Product>(aantalProducten);
		HashMap<Product, Boolean> bezochteProducten = new HashMap<Product, Boolean>(aantalProducten);
		for (int i = 0; i < aantalProducten; i++) {
			bezochteProducten.put(AntColonyOptimization.initialRoute.get(i), false);
		}
		int aantalBezochteProducten = 0;
		bezochteProducten.put(AntColonyOptimization.initialRoute.get(eersteProductIndex), true);
		int routeAfstand = 0;
		int x = eersteProductIndex;
		int y = ongeldigeProductIndex;
		if (aantalBezochteProducten != aantalProducten) {
			y = getY(x, bezochteProducten);
		}
		while (y != ongeldigeProductIndex) {
			routeProducten.add(aantalBezochteProducten++, AntColonyOptimization.initialRoute.get(x));
			routeAfstand += aco.getAfstandsMatrix()[x][y];
			aanpassenFeromoonLevel(x, y, routeAfstand);
			bezochteProducten.put(AntColonyOptimization.initialRoute.get(y), true);
			x = y;
			if (aantalBezochteProducten != aantalProducten) { 
				y = getY(x, bezochteProducten); // y wordt de index van het volgende product
			} else {
				y = ongeldigeProductIndex; 
			}
		}
		routeProducten.add(aantalBezochteProducten, AntColonyOptimization.initialRoute.get(x)); 
		route = new Route(routeProducten, routeAfstand);
		return this;
	}
	private void aanpassenFeromoonLevel(int x, int y, double afstand) {
		boolean flag = false;
		while (!flag) {
			double ditFeromoonLevel = aco.getFeromoonLevelsMatrix()[x][y].doubleValue();
			double updatedFeromoonLevel = (1-pL) * ditFeromoonLevel + pH/afstand;
			if (updatedFeromoonLevel < 0.00) {
				flag = aco.getFeromoonLevelsMatrix()[x][y].compareAndSet(0); //als er geen feromoon meer is (verdampt) tussen de producten (x) en (y), dan wordt feromoonLevel 0
			} else {
				flag = aco.getFeromoonLevelsMatrix()[x][y].compareAndSet(updatedFeromoonLevel); // als er nog feromoon is, dan wodt feromoon tussen de producten updateferomoonlevel
			}
		}
	}
	private int getY(int x, HashMap<Product, Boolean> bezochteProducten) { // dit returnt de index van het volgende product dat moet worden bezocht
		int returnY = ongeldigeProductIndex;
		double random = ThreadLocalRandom.current().nextDouble(); 
		ArrayList<Double> transitionProbabilities = getTransitionProbabilities(x, bezochteProducten);
		for (int i = 0; i < aantalProducten; i++) { 
			if (transitionProbabilities.get(i) > random) {
				returnY = i;
				break; 
			} else {
				random -= transitionProbabilities.get(i);
			}
		}
		return returnY;
	}
	private ArrayList<Double> getTransitionProbabilities(int x, HashMap<Product, Boolean> bezochteProducten) {
		ArrayList<Double> transitionProbabilities = new ArrayList<Double>(aantalProducten);
		for (int k = 0; k < aantalProducten; k++) {
			transitionProbabilities.add(0.0);
		}
		double denominator = getTPDenominator(transitionProbabilities, x, bezochteProducten);
		for (int h = 0; h < aantalProducten; h++) {
			transitionProbabilities.set(h, transitionProbabilities.get(h)/denominator);
		}
		return transitionProbabilities;
	}
	private double getTPDenominator(ArrayList<Double> transitionProbabilities, int x, HashMap<Product, Boolean> bezochteProducten) { 
		double denominator = 0.0;
		for (int i = 0; i < aantalProducten; i++) {
			if (!bezochteProducten.get(AntColonyOptimization.initialRoute.get(i))) {
				if(x == i) {
					transitionProbabilities.set(i, 0.0); // je mag niet bewegen van en naar dezelfde producten
				} else {
					transitionProbabilities.set(i, getTPNumerator(x, i));
				}
				denominator += transitionProbabilities.get(i);
			}
		}
		return denominator;
	}
	
	private double getTPNumerator(int x, int y) { // teller
		double numerator = 0.0;
		double feromoonLevel = aco.getFeromoonLevelsMatrix()[x][y].doubleValue();
		if (feromoonLevel != 0.0) {
			numerator = Math.pow(feromoonLevel, ALPHA) * Math.pow(1/aco.getAfstandsMatrix()[x][y], BETA);
		}
		return numerator;
	}

//	public int getMierNumb() {
//		return mierNumb;
//	}

}
