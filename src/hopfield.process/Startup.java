package hopfield.process;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hopfield.process.*;

/* Diese Klasse dient ledigiglich zur Ein-und ausgabe von Daten für das Hopfield-Netzwerk */

public class Startup {
	
	public static void main(String args[]) {
		
		System.out.println("Diesem Programm dient zum Darstellen von Hopfield-Netzen");
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Geben Sie die Größe der Matrix an:");
		String eingabe = null;
		int m = 0;
		
		//Einlesen wie groß die Matrix sein soll
		try {
				eingabe = console.readLine();
				m = new Integer(eingabe);
		} catch (IOException e) {
			// Sollte eigentlich nie passieren
			e.printStackTrace();
		}
		
		//Variante der Eingabe der Matrix
		System.out.print("Gewichtsmatrix mittels Vektor(1) oder manuell(2) eingeben:");
		int auswahl = 1;
		try {
			eingabe = console.readLine();
			auswahl = new Integer(eingabe);
		} catch (IOException e) {
			// Sollte eigentlich nie passieren
			e.printStackTrace();
		}
		int[][] W = new int [m][m];
		if (auswahl == 1 ){
			W=InputConsole.VektorToMatrix(m);
		}
		else if (auswahl == 2){
			W = InputConsole.Matrix(m);
		}
		else {
			return;
		}
		
		String ausgabe = "";
		for (int i = 0; i<m; i++){
			for (int j = 0; j<m; j++){
				 int x = W[i][j];
			
				ausgabe += " " + x;
			}
			ausgabe += "\n";
		}
		
		int[] in_vek = new int [m];
		in_vek = InputConsole.InputVektor(m);
		
		System.out.println(ausgabe);
				ausgabe = "";
		for (int i = 0; i<m; i++){
				 int x = in_vek[i];
				ausgabe += " " + x;
		}
		System.out.println(ausgabe);
		
		int[] netj = new int [m];
		int t= 1;  //Wie oft soll Vektor mit Matrix multipliziert werden
		netj = Functions.propagierungsfunktion(in_vek, W, t, m);
		
		ausgabe = "";
		for (int i = 0; i<m; i++){
			int y = netj[i];
			ausgabe +=  y + "\n";
		}
		System.out.println(ausgabe);
		
		
	}

}
