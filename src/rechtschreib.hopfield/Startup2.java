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
		
		//Anzahl der Buchstaben, welche verwendet werden koennen.
		int buch_anz = 7;
		
		
		//Einlesen eines Wortes welches als zu erkennendes Wort dienen soll. 
		System.out.print("Geben Sie bitte ein richtiges Wort ein:");
		
		//Einlesen wie groß die Matrix sein soll. Die Groesse der Matrix richtet sich dabei nach der Länge des eingegebenen Wortes
		try {
				eingabe = console.readLine();
				m = eingabe.length();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Initialisiere die Matrizen welche die Wörter beinhalten. 
		int[][] wortVek = new int [m][buch_anz]; 		//zu erkennendes Wort
		int[][] falschVek = new int [m][buch_anz];  	//eingegebenes falsches Wort
		
		//Das Muster-Wort wird mittels der folgenden Funktion in eine Matrix konvertiert. Diese besitzt "m"-Spalten (Wortlänge) und 26 Zeilen (Länge des Alphabets)  
		wortVek = Convert.wort_split(eingabe, m);
		
		//Initialisiere die Gewichtsmatrix.
		int[][][] W = new int [m][m][buch_anz];
		
		//Anhand des Muster-Wortes wird die Gewichtsmatrix berechnet
		W=Functions2.VektorToMatrix(m, wortVek, buch_anz);
		
		//Liest das "falsche" Wort ein. Dieses Wort wird versucht auf das "Muster-Wort" zurueckzufuehren
		System.out.print("Welches Wort soll mit richtigem Wort verglichen werden:");
		try {
			eingabe = console.readLine();
			m = eingabe.length();
			falschVek = Convert.wort_split(eingabe, m, buch_anz);
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Initialisiere eine neue Matrix. Diese beinhaltet das zurueckgefuehrte Wort
		int[][] netj = new int [m][4];
		int t= 1;  //Wie oft soll Vektor mit Matrix multipliziert werden. Variable kann variiert werden ,wodurch mehrere Male der Prozess der Zurueckfuehrung duchgefuehrt wird. 
		
		//Versuche das Wort zurueckzufuehren
		netj = Functions2.propagierungsfunktion(falschVek, W, t, m, buch_anz);
		
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

	}

}
