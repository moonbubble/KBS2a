package tsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AntColonyOptimization {
	private AtomicDouble[][] feromoonLevelsMatrix = null;
	private double[][] afstandsMatrix = null;
	private ArrayList<Product> order = Driver.initialRoute;
	private int orderGrootte = Driver.initialRoute.size();
	
	public AntColonyOptimization() throws IOException {
		initialiseerAfstanden();
		initialiseerFeromoonLevels();
	}
	
	public AtomicDouble[][] getferomoonLevelsMatrix() {
		return feromoonLevelsMatrix;
	}

	public double[][] getafstandsMatrix() {
		return afstandsMatrix;
	}
	private void initialiseerAfstanden() throws IOException {
		afstandsMatrix = new double[orderGrootte][orderGrootte];
		for (int i = 0; i < orderGrootte; i++) {
			Product productY = order.get(i);
			for (int j = 0; j < orderGrootte; j++) {
				afstandsMatrix[i][j] = productY.meetAfstand(order.get(j)); // meet de afstand tussen de producten
			}
		}
	}
	private void initialiseerFeromoonLevels() {
		feromoonLevelsMatrix = new AtomicDouble[orderGrootte][orderGrootte];
		Random random = new Random();
		for (int i = 0; i < orderGrootte; i++) {
			for (int j = 0; j < orderGrootte; j++) {
				feromoonLevelsMatrix[i][j] = new AtomicDouble(random.nextDouble());  // initialiseer random de feromoonlevels
			}
		}
	}
}
