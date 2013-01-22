package rechtschreib.hopfield;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rechtschreib.hopfield.Convert;

import hopfield.process.*;

/* Diese Klasse dient ledigiglich zur Ein-und ausgabe von Daten für das Hopfield-Netzwerk */

public class Startup2 {
	
	public static void main(String args[]) {
		
		System.out.println("Diesem Programm ist ein Rechtschreibprogramm welches zur Worterkennng ein Hopfield-netzwerk verwendet");
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String eingabe = null;
		int m = 0;
		
		
		System.out.print("Geben Sie bitte ein richtiges Wort ein:");
		//Einlesen wie groß die Matrix sein soll
		try {
				eingabe = console.readLine();
				m = eingabe.length();
				
				
		} catch (IOException e) {
			// Sollte eigentlich nie passieren
			e.printStackTrace();
		}
		
		int[][] wortVek = new int [m][4];
		int[][] falschVek = new int [m][4];
		wortVek = Convert.wort_split(eingabe, m);
		
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
		int[][][] W = new int [m][m][4];
		
		W=Functions2.VektorToMatrix(m, wortVek);
		
		
		System.out.print("Welches Wort soll mit richtigem Wort verglichen werden:");
		auswahl = 1;
		try {
			eingabe = console.readLine();
			m = eingabe.length();
			falschVek = Convert.wort_split(eingabe, m);
	
		} catch (IOException e) {
			// Sollte eigentlich nie passieren
			e.printStackTrace();
		}
		//if (auswahl == 2 ){
			//Functions.alleVektoren(W, m);
		//}
		//else if (auswahl == 1){
			
			int[][] netj = new int [m][4];
			int t= 1;  //Wie oft soll Vektor mit Matrix multipliziert werden
			
			netj = Functions2.propagierungsfunktion(falschVek, W, t, m);
			
			String ausgabe = "";
			
			// Erstelle lesbare Ausgabe / Wandle Vektoren in Buchstaben um:
			String textAusgabe = Convert.ausgabe2Text(netj);
			
			for (int k = 0; k < 4; k++){
				for (int i = 0; i<m; i++){
					int y = netj[i][k];
					ausgabe +=  y + "\t";
				}
				ausgabe +=  "\n";
			}
			System.out.println("Ergebnis: " + textAusgabe);

		//}
//		else {
//			return;
//		}
		
		
				
		
	}

}
