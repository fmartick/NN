package hopfield.process;

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
			if (t!=1){
				String ausgabe = "";
				for(int i = 0; i < m; i++){
					ausgabe += " " + netj[i] ;
					in_vek[i] = netj[i];
				}
				
				System.out.println("[" + ausgabe + "]");
			}
			
			dur++;
		}
				
		return netj;
	}

}
