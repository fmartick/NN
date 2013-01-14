package rechtschreib.hopfield;

import hopfield.process.Functions;



public class Functions2 {

	public static int[][][] VektorToMatrix( int m, int[][] wortVek){
		
		int[][][] W = new int [m][m][4];
					
			for (int i = 0; i < m; i++ ){
				for (int j = 0; j < m; j++ ){
					for(int k= 0; k < 4; k++){
						W[i][j][k] = wortVek[i][k] * wortVek[j][k];
						if( i == j){
							W[i][j][k] = 0;
					}
					
					
					}
				}
			}
			
		return W;
	
	
	}

	public static int[][] propagierungsfunktion( int[][] in_vek, int[][][] W, int t, int m ) {
		
		int[][] netj = new int [m][4];		
		int cache;
		int dur = 1;
		while (dur <= t) {
			for(int i = 0; i < m; i++){
				
				for(int k = 0; k < 4; k++){
					cache = 0;
					for(int j= 0; j <m; j++){
						cache =cache+ W[i][j][k] * in_vek[j][k];
					}
					netj[i][k] = cache;
 			
				}
				
				
			}
		netj = Functions2.aktivierungsfunktion(netj, m);
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
	
	public static int[][] aktivierungsfunktion( int[][] in_vek, int m ) {
		
		int[][] out_vek = new int [m][4];
		
		for (int i = 0; i < m; i++){
			for (int k = 0; k < 4; k++){
			
				if(in_vek[i][k] > 0) {
					out_vek[i][k] = 1;
				}
				else{
					out_vek[i][k] = -1;
				}
			}			
		}
		return out_vek;
	}
	
}
	
