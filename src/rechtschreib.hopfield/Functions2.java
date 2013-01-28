package rechtschreib.hopfield;

import hopfield.process.Functions;

public class Functions2 {

	public static int[][][] VektorToMatrix(int m, int[][] wortVek, int buch_anz) {
		// Die Funktion berechnet aus einem Muster-Vektor die Gewichtsmatrix.
		// Die "normale" Gewichtsmatrix eines Hopfield-Netzes besteht aus 2
		// Dimensionen.
		// Die Worterkennung benoetigt eine 3. Dimension. Durch diese Dimension
		// wird die Kodierung des Buchstabens mittels eines Vekotrs
		// festgehalten.

		int[][][] W = new int[m][m][buch_anz];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < buch_anz; k++) {
					// Die Muster-Matrix wird mit sich selber multipliziert, um
					// so die Gewichtsmatrix zu erhalten.
					// Dabei wird jede spalte mit jeder Zeile multipliziert.
					// die 3.Dimension (for-Schleife über k) dient zum Speichern
					// der Buchstaben.
					W[i][j][k] = wortVek[i][k] * wortVek[j][k];
					if (i == j) {
						// Die Diagonale der Gewichtsmatrix muss bei einem
						// Hopfieldnetz laut Definition immer "0" sein
						W[i][j][k] = 0;
					}

				}
			}
		}

		return W;

	}

	//Diese Funktion dient Zurueckfuehrung von "falschen" Woerten auf ein Muster-Wort.
	//Alternative bezeichnung dieser Funktion ist "Inputfunktion"
	public static int[][] propagierungsfunktion(int[][] in_vek, int[][][] W, int t, int m, int buch_anz) {
		
		int[][] netj = new int[m][buch_anz];
		int cache;
		
		// Die Variable "dur"(aktueller Durchlauf) und "t" (maximale Durchläufe) wurden implementiert um mehrere Durchgaenge ermoeglichen zu koennen. 
		//Dabei wird die Ausgabe-Matrix des ersten Schritt als Eingabe-Matrix im 2. Schritt verwendet.
		//Ist aber durch die feste Definition von t=1 im "Startup2" deaktiviert.
		
		int dur = 1;
		while (dur <= t) {
			
			//Die Gewichtsmatrix wird mit dem "falschen" Vektor multipliziert. Das Ergebnis ist eine weitere Matrix, welche das zurueckgefuehrte Wort beinhaltet
			for (int i = 0; i < m; i++) {

				for (int k = 0; k < buch_anz; k++) {
					cache = 0;
					for (int j = 0; j < m; j++) {
						//Die Variable ist lediglich ein Zwischenspeicher der Summenformel
						cache = cache + W[i][j][k] * in_vek[j][k];
					}
					netj[i][k] = cache;

				}

			}
			//Die nachfolgende Funktion beinhaltet die Sigma-Funktion, welche die Werte auf bipolare Werte zurueckfuehrt.
			netj = Functions2.aktivierungsfunktion(netj, m, buch_anz);
			// if (t!=1){
			// String ausgabe = "";
			// for(int i = 0; i < m; i++){
			// ausgabe += " " + netj[i] ;
			// in_vek[i] = netj[i];
			// }
			//
			// System.out.println("[" + ausgabe + "]");
			// }

			dur++;
		}

		return netj;
	}

	public static int[][] aktivierungsfunktion(int[][] in_vek, int m, int buch_anz) {
		//Die Funktion beinhaltet die Sigma-Funktion, welche die Werte auf bipolare Werte zurueckfuehrt.
		//Hierbei sitzt der Schwellwert bei 0. Alle Werte groesser 0 werden dem Wert "1" zugeordnet. Der Rest erhaelt "-1"

		int[][] out_vek = new int[m][buch_anz];

		for (int i = 0; i < m; i++) {
			for (int k = 0; k < buch_anz; k++) {

				if (in_vek[i][k] > 0) {
					out_vek[i][k] = 1;
				} else {
					out_vek[i][k] = -1;
				}
			}
		}
		return out_vek;
	}

}
