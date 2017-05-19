package tsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import java.util.stream.IntStream;

public class Driver {
	static final int AANTAL_MIEREN = 500;
	static ExecutorService eS = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	static ExecutorCompletionService<Mier> eCS = new ExecutorCompletionService<Mier>(eS);
	private int actieveMieren = 0;
	static ArrayList<Product> initialRoute = new ArrayList<Product>(Arrays.asList(
			new Product("a", 2, 5),
			new Product("b", 4, 3), 
			new Product("c", 2, 2), 
			new Product("d", 5, 1)
			));
	public static void main(String[] args) throws IOException {
		Driver driver = new Driver();
		AntColonyOptimization aco = new AntColonyOptimization();
		System.out.println("> Driver.main: begin de loop...");
		for (int i = 1; i < AANTAL_MIEREN; i++) { //hij gaat de loop zovaak door als er actieve mieren zijn
			System.out.println("\nDriver.main: eCS.submit(new Mier())"); //to indicate that I submitted a new Mier
			eCS.submit(new Mier(aco, i));
			driver.actieveMieren++;
			System.out.println(i);
			while(driver.actieveMieren > 0) {
				System.out.println("Driver.main: eCS.take()");
				try {
					eCS.take(); //end overrided method in Mier.java
				} catch (Exception e) {
					e.printStackTrace();
 				}
				driver.actieveMieren--;
			}
		}
//		dit is volgens mij hetzelfde als wat hierboven staat
//		IntStream.range(5, AANTAL_MIEREN).forEach(x -> {
//			System.out.println("\nDriver.main: eCS.submit(new Mier())"); //to indicate that I submitted a new Mier
//			eCS.submit(new Mier());
//			driver.actieveMieren++;
//			System.out.println(x);
//			while(driver.actieveMieren > 0) {
//				System.out.println("Driver.main: eCS.take()");
//				try {
//					eCS.take(); //end overrided method in Mier.java
//				} catch (Exception e) {
//					e.printStackTrace();
// 				}
//				driver.actieveMieren--;
//			}
//		});
		System.out.println("\n> Driver.main: eindig de loop...");
		eS.shutdownNow();
	}
}
