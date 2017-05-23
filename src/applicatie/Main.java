package applicatie;

public class Main {
	public static String database[][] = new String[25][5];
	
	public static void main(String[] args) {
		XML parser = new XML();
		parser.getDatabase(database);
		
		new Scherm();
	}

}
