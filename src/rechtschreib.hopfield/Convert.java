package rechtschreib.hopfield;

import java.util.HashMap;

public class Convert {
	

    public static int b_to_binaer(String Buchstabe) {
        
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

    public static int[][] wort_split(String wort, int m){
    	
    	int wortlaenge = wort.length(); 
		int max_array_length = m;
		int wort_array[][] = new int[max_array_length][4];
		 
		for(int k=0; k<max_array_length; k++){
			 for(int l=0; l<4; l++){  
			 	wort_array[k][l] = -1;
		 	}
		}    	  
	 	String array ="";
	 	int i = 0;
	 	int wert;
	 	//System.out.println(wort + " besteht aus folgenden Buchstaben:");
	 	while(i< wortlaenge){
	  
			String buchstabe = wort.substring(i, i+1);	  
		  	wert = b_to_binaer(buchstabe);
		  	//System.out.println("- " +buchstabe + " Es ist der " + wert + ". Buchstabe im Alphabet.");
		 		  
		  	wort_array[i][wert-1] = 1;
		  
		  	i++;
		}
		return wort_array;
    }


}
