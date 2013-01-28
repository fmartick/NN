package rechtschreib.hopfield;

import java.util.HashMap;

public class Convert {
	

    public static int b_to_binaer(String Buchstabe) {
        
    	//Hashmap, welche zu jedem Buchstaben die Position des Buchstabens im lphabet beinhaltet 
    	int wert = -1;
    	Buchstabe = Buchstabe.toLowerCase(); 
    	HashMap<String,Number> h = new HashMap<String,Number>();
    	h.put("a", 1);
    	h.put("b", 2);
    	h.put("c", 3);
    	h.put("d", 4);
    	h.put("e", 5);
    	h.put("f", 6);
    	h.put("g", 7);
    	h.put("h", 8);
    	h.put("i", 9);
    	h.put("j", 10);
    	h.put("k", 11);
    	h.put("l", 12);
    	h.put("m", 13);
    	h.put("n", 14);
    	h.put("o", 15);
    	h.put("p", 16);
    	h.put("q", 17);
    	h.put("r", 18);
    	h.put("s", 19);
    	h.put("t", 20);
    	h.put("u", 21);
    	h.put("v", 22);
    	h.put("w", 23);
    	h.put("x", 24);
    	h.put("y", 25);
    	h.put("z", 26);
    	
    	Number number = h. get(Buchstabe);
    	if ( number != null )
    		wert = number.intValue();	
    	
    	return wert;
    }
    
private static String netjVektor2Buchstabe(String vektor){
		
		//Hashmap gibt zu den Vektoren den richtigen Buchstaben aus
		HashMap<String, String> h = new HashMap<String, String>();
		h.put("1-1-1-1-1-1-1", "a");
		h.put("-11-1-1-1-1-1", "b");
		h.put("-1-11-1-1-1-1", "c");
		h.put("-1-1-11-1-1-1", "d");
		h.put("-1-1-1-11-1-1", "e");
		h.put("-1-1-1-1-11-1", "f");
		h.put("-1-1-1-1-1-11", "g");
		
		return h.get(vektor);
	}

	public static String ausgabe2Text(int[][] netj) {
		//Wandelt eine Matrix in mehrere lesbare Vektoren um und führt diese auf eine nBuchstaben zurueck
		String temp ="";
		String result = "";
		for(int i =0; i< netj.length; i++){
			for(int j = 0; j<netj[0].length;j++){
				temp+=netj[i][j];				
			}
			System.out.println("current temp is: " + temp);
			result+= netjVektor2Buchstabe(temp);
			temp="";
		}
		return result;
	}

    public static int[][] wort_split(String wort, int m, int bla){
    	//Wandelt das uebergebene Wort in eine Matrix um. Dabei steht pro Zeile ein Buchstabe des Wortes. 
    	//Die Zeilen geben dabei an, welcher Buchstabe an der Stelle steht (an der zigten Position des Buchstabes im Alphabet steht eine "1" sonst "0" 
    	
    	
    	int wortlaenge = wort.length(); 
		int max_array_length = m;
		int wort_array[][] = new int[max_array_length][bla];
		 
		for(int k=0; k<max_array_length; k++){
			 for(int l=0; l<4; l++){
				 //Fuelle zunaechst das Array mit dem Wert "-1"
			 	wort_array[k][l] = -1;
		 	}
		}    	  
	 	String array ="";
	 	int i = 0;
	 	int wert;
	 	while(i< wortlaenge){
	  
			String buchstabe = wort.substring(i, i+1);
			//setze den Wert "1" an der Stelle des Arrays, wo der Buchstabe im Alphabet steht.
			//position des Buchstabens is in einer hash-Map in der Funktion b_to_binaer gespeichert.
		  	wert = b_to_binaer(buchstabe);		 		  
		  	wort_array[i][wert-1] = 1;
		  
		  	i++;
		}
		return wort_array;
    }


}
