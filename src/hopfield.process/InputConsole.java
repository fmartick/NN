package hopfield.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputConsole {
	
	public static int[][] Matrix(int m){
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	
		int[][] W = new int [m][m];
		
		for (int z= 0; z<m; z++){
			System.out.print("Geben Sie die "+ (z+1) +".Zeile der Gewichtsmatrix W ein: ");
			String zeile = null;
			try {
				zeile = console.readLine();
				
				Matcher matcher = Pattern.compile( "\\d" ).matcher( zeile );
				
				int last_pos_old = 0;
				int r = 0;
				while ( matcher.find() ){
						int last_pos = matcher.end();
						if (last_pos - last_pos_old > 1 ) {
							W[z][r] = new Integer(zeile.substring(last_pos_old, last_pos_old +2));
							r++;	
						}
						else{
							W[z][r] = new Integer(zeile.substring(last_pos_old, last_pos_old +1));
							r++;
						}
						last_pos_old = last_pos;
				}
				
			} catch (IOException e) {
				// Sollte eigentlich nie passieren
				e.printStackTrace();
			}
		}

		return W;
		
		
		
		
	}

	public static int[] InputVektor(int m){
	
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String eingabe;
		System.out.println("Geben Sie den Input-Vektor ein:");
		
		int[] in_vek = new int [m];
		try {
			eingabe = console.readLine();
			
			int last_pos_old = 0;
			int r = 0;
			
			Matcher matcher = Pattern.compile( "\\d" ).matcher( eingabe );
			while ( matcher.find() ){
					int last_pos = matcher.end();
					if (last_pos - last_pos_old > 1 ) {
						in_vek[r] = new Integer(eingabe.substring(last_pos_old, last_pos_old +2));
						r++;	
					}
					else{
						in_vek[r] = new Integer(eingabe.substring(last_pos_old, last_pos_old +1));
						r++;
					}
					last_pos_old = last_pos;
			}
			
		} catch (IOException e) {
			// Sollte eigentlich nie passieren
			e.printStackTrace();
		}
		return in_vek;

}

	public static int[][] VektorToMatrix( int m){
				
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String eingabe;
		System.out.println("Geben Sie den Vektor ein:");
		
		int[][] W = new int [m][m];
		int[] vek = new int [m];
		try {
			eingabe = console.readLine();
			
			int last_pos_old = 0;
			int r = 0;
			
			Matcher matcher = Pattern.compile( "\\d" ).matcher( eingabe );
			while ( matcher.find() ){
					int last_pos = matcher.end();
					if (last_pos - last_pos_old > 1 ) {
						vek[r] = new Integer(eingabe.substring(last_pos_old, last_pos_old +2));
						r++;	
					}
					else{
						vek[r] = new Integer(eingabe.substring(last_pos_old, last_pos_old +1));
						r++;
					}
					last_pos_old = last_pos;
			}
			
			for (int i = 0; i < m; i++ ){
				for (int j = 0; j < m; j++ ){
					W[i][j] = vek[i] * vek[j];
					if( i == j){
						W[i][j] = 0;
					}
				}
			}
			
			
		} catch (IOException e) {
			// Sollte eigentlich nie passieren
			e.printStackTrace();
		}
		
		
		return W;
		
	}
}