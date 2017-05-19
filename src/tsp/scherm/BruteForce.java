package tsp.scherm;

import java.util.ArrayList;
import java.util.Collections;

public class BruteForce {
	static int permutationNumb = 1;
	private int returnValue;
	ArrayList<Route> shortestRoutes = new ArrayList<Route>();
	private static ArrayList<Product> initialRoute;
	
	public BruteForce() {
	}
	
	public BruteForce(ArrayList<Product> orderList) {
		BruteForce.initialRoute = orderList;
	}

	public ArrayList<Product> algoritme() {
		BruteForce bruteForce = new BruteForce();
		Route currentRoute = new Route(initialRoute);
		return ontvangRoute(bruteForce, bruteForce.permuteProducts(0, currentRoute, new Route(currentRoute)));
	}
	
	public ArrayList<Route> permuteProducts(int z, Route currentRoute, Route shortestRoute) {
		currentRoute.getOrder().stream().filter(y -> currentRoute.getOrder().indexOf(y) >= z).forEach(y -> {
			int indexOfY = currentRoute.getOrder().indexOf(y); //indexOfY is de index van de order die bij item y hoort
			Collections.swap(currentRoute.getOrder(), indexOfY, z); 
			permuteProducts(z + 1, currentRoute, shortestRoute);
			Collections.swap(currentRoute.getOrder(), z, indexOfY);
		});
		if (z == currentRoute.getOrder().size() - 1) {
//			if (Driver.VERBOSE_FLAG) { 
//				System.out.print(currentRoute + " |      " + getTotaleAfstand(currentRoute));
//			}
			if ((int)berekenTotaleAfstand(currentRoute) <= (int)berekenTotaleAfstand(shortestRoute)) {
				shortestRoute.getOrder().clear();
				shortestRoute.getOrder().addAll(currentRoute.getOrder());
				voegToeAanKortsteRoutes(new Route(shortestRoute));
			}
//			if (Driver.VERBOSE_FLAG) {
//				System.out.println("      |    " + getTotaleAfstand(shortestRoute) + "     | " + permutationNumb++);
//			}
		}
		return shortestRoutes;
	}

	public int berekenTotaleAfstand(Route route) {
		int orderGrootte = route.getOrder().size();	 // orderGrootte is hoeveel items er in de order zitten
		return (int) (route.getOrder().stream().mapToDouble(x -> { // DIT SNAP IK DUS NIET HELEMAAL
			int productIndex = route.getOrder().indexOf(x); //productIndex is de index van de order die bij item x van de order hoort
			returnValue = 0; //returnvalue is 0 (dit was eerst een double)
			if (productIndex < orderGrootte - 1) { //als de index kleiner is dan de grootte van de order-1
				returnValue = x.meetAfstand(route.getOrder().get(productIndex + 1)); //returnvalue wordt de afstand vanaf item x tot het item dat na x in de order komt
			}
			return returnValue;
		}).sum() 
//				+ route.getOrder().get(0).meetAfstand(route.getOrder().get(orderGrootte - 1)) //hier wordt de afstand gemeten tussen het eerste item uit de route en het laatste
						);
	}
	public String getTotaleAfstand(Route route) {  // wordt gewoon wat aan de value veranderd, namelijk een spatie
		String returnValue = Integer.toString(berekenTotaleAfstand(route));
		return returnValue;
	}
	
	public void voegToeAanKortsteRoutes(Route route) {
		shortestRoutes.removeIf(x -> berekenTotaleAfstand(x) > berekenTotaleAfstand(route)); //als de totale afstand van x groter is dan de totale afstand van de route wordt  
			//eerst stond er removeIf(x -> (int)berekenTotaleAfstand(x) > (int)berekenTotaleAfstand(route))
		shortestRoutes.add(route);
	}
	
	public ArrayList<Product> ontvangRoute(BruteForce bruteForce, ArrayList<Route> shortestRoutes) {
		int index = 0;
		ArrayList<Product> route = new ArrayList<Product>();
		for (Route r : shortestRoutes) {
			if(index == 0) {
				route = r.getOrder();
			}
//			System.out.println((index++) + ": " + r);
		}
//		System.out.println(route + " | " + bruteForce.getTotaleAfstand(shortestRoutes.get(0)));
		return route;
	}
}
