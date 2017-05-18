package bpp.simulatie;

public class Test {
	public static String database[][] = new String[25][5];

	public static void main(String[] args) {
		XML parser = new XML();
		parser.getDatabase(database);
		
		new BPPsimulatie();

		
	}
}