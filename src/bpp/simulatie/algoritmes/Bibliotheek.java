package bpp.simulatie.algoritmes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bibliotheek {

	private List<Algoritme> algoritmes = new ArrayList<>();

	public Bibliotheek() {
		algoritmes.add(new BestFit());
		algoritmes.add(new EigenAlgoritme());
		algoritmes.add(new FirstFit());
		algoritmes.add(new NextFit());
	}

	public Algoritme getAlgoritme(int index) {
		return algoritmes.get(index);
	}

	public List<String> getAlgoritmeNamen() {
		return algoritmes.stream().map(Algoritme::getNaam).collect(Collectors.toList());
	}
}
