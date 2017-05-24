package tsp.scherm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import domeinmodel.Bestelling;
import domeinmodel.Product;

public class AntColonyOptimization {
	static final int AANTAL_MIEREN = 500;
	static final double PROCESSING_CYCLE_PROBABILITY = 0.8;
	static ExecutorService eS = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	static ExecutorCompletionService<Mier> eCS = new ExecutorCompletionService<Mier>(eS);
	private Route shortestRoute = null;
	private int actieveMieren = 0;
	static List<Product> initialRoute;

	public AntColonyOptimization(Bestelling order) {
		initialRoute = new ArrayList<>(order.getProducten());
	}
	
	public List<Product> algoritme(){
		ACODriver aco = new ACODriver();
		for (int i = 1; i < AANTAL_MIEREN; i++) { 
			eCS.submit(new Mier(aco, i));
			actieveMieren++;
			if (Math.random() > PROCESSING_CYCLE_PROBABILITY) {
				procesMieren();
			}
		}
		procesMieren();
		eS.shutdownNow();
		System.out.println("\nOptimale Route : " + shortestRoute.getOrder());
		System.out.println("Afstand   : " + shortestRoute.getAfstand());
		return shortestRoute.getOrder();
	}
	
	private void procesMieren() {
		while (actieveMieren > 0) {
			try {
				Mier mier = eCS.take().get();
				Route currentRoute = mier.getRoute();
				if (shortestRoute == null || currentRoute.getAfstand() < shortestRoute.getAfstand()) {
					shortestRoute = currentRoute;
					StringBuffer afstand = new StringBuffer(
							"      " + String.format("%.2f", currentRoute.getAfstand()));
					for (int i = 0; i < (20 - afstand.length()); i++) {
						afstand.append(" ");
					}
					System.out.println(shortestRoute.getOrder() + "|" + afstand + "| "
							+ mier.getMierNumb());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			actieveMieren--;
		}
	}
}
