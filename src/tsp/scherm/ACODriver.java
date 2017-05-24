package tsp.scherm;

import java.util.List;
import java.util.Random;

import domeinmodel.Product;

public class ACODriver {
	private AtomicDouble[][] feromoonLevelsMatrix = null;
	private double[][] afstandsMatrix = null;
	private List<Product> order = AntColonyOptimization.initialRoute;
	private int orderGrootte = AntColonyOptimization.initialRoute.size();
	
	public ACODriver(){
		initialiseerAfstanden();
		initialiseerFeromoonLevels();
	}
	
	public AtomicDouble[][] getFeromoonLevelsMatrix() {
		return feromoonLevelsMatrix;
	}

	public double[][] getAfstandsMatrix() {
		return afstandsMatrix;
	}
	private void initialiseerAfstanden(){
		afstandsMatrix = new double[orderGrootte][orderGrootte];
		for (int i = 0; i < orderGrootte; i++) {
			Product productY = order.get(i);
			for (int j = 0; j < orderGrootte; j++) {
				afstandsMatrix[i][j] = productY.meetAfstand(order.get(j));
			}
		}
	}
	private void initialiseerFeromoonLevels() {
		feromoonLevelsMatrix = new AtomicDouble[orderGrootte][orderGrootte];
		Random random = new Random();
		for (int i = 0; i < orderGrootte; i++) {
			for (int j = 0; j < orderGrootte; j++) {
				feromoonLevelsMatrix[i][j] = new AtomicDouble(random.nextDouble());  
			}
		}
	}
}
