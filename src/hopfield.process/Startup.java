package hopfield.process;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hopfield.process.*;

/* Diese Klasse dient ledigiglich zur Ein-und ausgabe von Daten für das Hopfield-Netzwerk */

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
		
		String ausgabe = "[";
		for (int i = 0; i<m; i++){
			for (int j = 0; j<m; j++){
				 int x = W[i][j];
			
				ausgabe += x;
				if(j<m-1)
					ausgabe += "\t";
			}
			if(i<m-1)
				ausgabe += "\n";
		}
		ausgabe += "]";
		System.out.println("Folgende Gewichtsmatrix wurde hinterlegt:\n" + ausgabe);
		
		
		System.out.print("Vektor überprüfen(1) oder alle Vektoren und deren Outputvektoren berechnen(2):");
		auswahl = 1;
		try {
			eingabe = console.readLine();
			auswahl = new Integer(eingabe);
		} catch (IOException e) {
			// Sollte eigentlich nie passieren
			e.printStackTrace();
		}
		if (auswahl == 2 ){
			Functions.alleVektoren(W, m);
		}
		else if (auswahl == 1){
			int[] in_vek = new int [m];
			int[] en_vek = new int [m];
			double E;
			in_vek = InputConsole.InputVektor(m);
			ausgabe = "[";
			for (int i = 0; i<m; i++){
					 int x = in_vek[i];
					ausgabe +=  x;
					if(i<m-1)
						ausgabe += ", ";
			}
			ausgabe += "]";
			System.out.println("Eingabevektor lautet: " +ausgabe);
			
			
			
			int[] netj = new int [m];
			int t= 1;  //Wie oft soll Vektor mit Matrix multipliziert werden
			netj = Functions.propagierungsfunktion(in_vek, W, t, m);
			
			ausgabe = "";
			for (int i = 0; i<m; i++){
				int y = netj[i];
				ausgabe +=  y + "\n";
			}
			System.out.println(ausgabe);
			
			E  = Functions.energiefunktion(in_vek, m, netj, W);
			
			en_vek = Functions.delta_energie(netj, in_vek, m, W);
						
			System.out.println("Die Energie beläuft sich auf: " + E);
			
			ausgabe = "";
			for (int i = 0; i<m; i++){
				int y = en_vek[i];
				ausgabe +=  y + "\n";
			}
			System.out.println(ausgabe);			
			
			System.out.println("Berechnung der delta_energie: \n" + ausgabe);			
			
			
		}
		else {
			return;
		}
		
		
				
		
	}

}
