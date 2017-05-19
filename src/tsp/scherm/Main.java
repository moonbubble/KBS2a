package tsp.scherm;

import javax.swing.JFrame;

public class Main
{
	public static String database[][] = new String[25][5];
		
	public static void main(String[] args)
	{
		XML parser = new XML();
		parser.getDatabase(database);		
		
		TspScherm scherm = new TspScherm();
		scherm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
