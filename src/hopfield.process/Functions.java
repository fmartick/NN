package hopfield.process;

import hopfield.process.Functions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Functions {
	
	public static int[] propagierungsfunktion( int[] in_vek, int[][] W, int t, int m ) {
		
		int[] netj = new int [m];		
		int cache;
		int dur = 1;
		while (dur <= t) {
			for(int i = 0; i < m; i++){
				cache = 0;
				for(int j = 0; j < m; j++){
				
				cache =cache+ W[i][j] * in_vek[j]; 			
				}
				netj[i] = cache;
				
			}
		netj = Functions.aktivierungsfunktion(netj, m);
//			if (t!=1){
//				String ausgabe = "";
//				for(int i = 0; i < m; i++){
//					ausgabe += " " + netj[i] ;
//					in_vek[i] = netj[i];
//				}
//				
//				System.out.println("[" + ausgabe + "]");
//			}
			
			dur++;
		}
				
		return netj;
	}
	
	public static int[] aktivierungsfunktion( int[] in_vek, int m ) {
		
		int[] out_vek = new int [m];
		
		for (int i = 0; i < m; i++){
			
			if(in_vek[i] >= 0) {
				out_vek[i] = 1;
			}
			else{
				out_vek[i] = -1;
			}
			
		}
		return out_vek;
	}
	public static void alleVektoren( int[][] W, int m ){
		
		int[][] allVek = new int [(int) Math.pow(2,m)][m];
		int[] outVek = new int [m];
		int[] inVek = new int [m];
		int[] newVek = new int [m];
		
		Functions.schreiben(inVek, outVek, newVek, m, W, 1);
		
		for (int i = 0; i < (int)Math.pow(2,m); i++ ){
			
			if (i == 0){
				allVek[0][0] = -1;
				allVek[0][1] = -1;
				allVek[0][2] = -1;
				allVek[0][3] = -1;
				
			}
			else{
				allVek[i][3] = allVek[i-1][3] + 2;
				if(allVek[i][3] > 1){
					allVek[i][3] = -1;	
					allVek[i][2] = allVek[i-1][2] + 2;
					
					if(allVek[i][2] > 1){
						allVek[i][2] = -1;
						allVek[i][1] = allVek[i-1][1] + 2;
						
						if(allVek[i][1] > 1){
							allVek[i][1] = -1;
							allVek[i][0] = allVek[i-1][0] + 2;
							if(allVek[i][0] > 1){
								return;
							}
						}
						else {
							allVek[i][0] = allVek[i-1][0];
						}
					}
					else{
						allVek[i][0] = allVek[i-1][0];
						allVek[i][1] = allVek[i-1][1];	
					}
				}
				else{
					allVek[i][0] = allVek[i-1][0];
					allVek[i][1] = allVek[i-1][1];
					allVek[i][2] = allVek[i-1][2];
				}
			}
			System.out.println(allVek[i][0] + "\t" + allVek[i][1] + "\t" + allVek[i][2] + "\t" + allVek[i][3]);
			for (int j = 0; j<m; j++){
				inVek[j] = allVek[i][j];				
			}
			outVek = Functions.propagierungsfunktion(inVek, W, 1, m);
			
			newVek = Functions.delta_energie(inVek, outVek, m, W);			
			Functions.schreiben(inVek, outVek, newVek, m, W, 2);
			
			
			
		}
		return ;
	} 

	public static boolean schreiben(int[] inVek, int[] outVek, int[] neVek, int m, int[][] W, int option){
		FileWriter writer;
		File file;
		
		double E;
		
		// File anlegen
	     file = new File("Vektoren_Hopfield.txt");
	     try {

	       writer = new FileWriter(file ,true);

	       
	       if (option == 1){
	    	   
	    	   writer.write("Folgende Gewichtsmatrix dient als Grundlage:");
	           writer.write(System.getProperty("line.separator"));
	    	   String matrix = "";
	    	   for(int i = 0; i < m; i++){
	    		   matrix = "";
	    		   if (i == 0)
		    		   matrix += "[";
	    		   for (int j = 0; j < m; j++){
		    		   matrix += W[i][j];
		    		   if(i < m){
		    			   matrix += "\t";
		    		   }
	    		   }

	    		   if(i == m-1)
	    			   matrix += "]";
			       writer.write(matrix);
			       writer.write(System.getProperty("line.separator"));

	    	   }
	    	  
		       
	       }
	       
	       
	       else{
		       String ausgabeVek = "[";
		       String eingabeVek = "[";
		       String newVek = "[";
		       			
		       for(int i = 0; i < m; i++){
		    	   
		    	   ausgabeVek += outVek[i];
		    	   eingabeVek += inVek[i];
		    	   newVek += neVek[i];
		    	   
		    	   if(i < m-1){
		    		   ausgabeVek += "\t";
		    		   eingabeVek += "\t";
		    		   newVek += "\t";
		    	   }
		    	   
		       }
		       ausgabeVek += "]";
		       eingabeVek += "]";
		       newVek += "]";
		       
		       writer.write(System.getProperty("line.separator"));
		       writer.write("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		   
		       writer.write(System.getProperty("line.separator"));
		       
		       E  = Functions.energiefunktion(inVek, m, outVek, W);
			   System.out.println("Die Energie beläuft sich auf: " + E);
		       writer.write(eingabeVek + "\t-->\t" + ausgabeVek + "\t-->\t" + newVek  + "\t E = " + E);
	       }
	       

	       writer.flush();
	       writer.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		
		return true;
	}

	public static double energiefunktion(int[] in_vek, int m, int[] out_vek, int[][] W){
		
		double E = 0;
		int sum = 0;
		
		for (int i = 0; i <m;  i++){
			
			for (int j = 0; j<m; j++){
			
				sum += W[i][j] * out_vek[i] * out_vek[j]; 
			}
		}
		
		E = sum * -0.5;
		
		return E;
	}
	
	public static int[] delta_energie(int[] in_vek, int[] out_vek, int m , int[][] W) {
		
		int[] new_vek = new int[m];
		int sum;
		for (int i = 0; i <m;  i++){
			
				sum = 0;
			for (int j = 0; j<m; j++){
			
				sum += W[i][j] * out_vek[i];
			}
			
			if (out_vek[i] <= 0 && sum >= 1){
				new_vek[i] = 1;
			}  	 		
			else if (out_vek[i] >= 1 && sum <= 0){
				new_vek[i] = -1;
			}
			else{
				new_vek[i] = 0;	
			}
			
			
		}
		return new_vek;
	}
	
}
