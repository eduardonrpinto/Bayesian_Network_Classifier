package Learning;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class RN implements Serializable{
	private double  [][][][][] rede;
	private int maxlen;
	private Grafo grafo;
	private int domclass;
	private int numvar;
	
	
	
	

static int [] convert (String line) {
		String cvsSplitBy = ",";
		String[] strings     = line.split(cvsSplitBy);
		int[] stringToIntVec = new int[strings.length];
		for (int i = 0; i < strings.length; i++)
			stringToIntVec[i] = Integer.parseInt(strings[i]);
		return stringToIntVec;
		}


// Constructor that takes a file path
public RN(String txtFile) {
    BufferedReader br = null;

    try {
        br = new BufferedReader(new FileReader(txtFile));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line);
        }
        
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

	
public RN(Grafo g, Amostra a, double S) {
	
	//Check the greatest domain
	
	
	
	int [] check = new int[a.numvar()] ;
	for(int i=0;i<a.numvar();i++) {
		check[i]=i;
	}
	int [] domains = a.domain(check);
	
	int check2 = domains[0];
	for (int j =1; j<domains.length;j++) {
		if(domains[j]>check2) {
			check2=domains[j];
		}
	}
	
	this.maxlen=check2+1;
	this.grafo = g;
	this.rede = new double [grafo.getDim()][maxlen][maxlen][maxlen][maxlen];
	this.numvar = a.numvar();

	
	int[] w1 = new int[1]; w1[0] = grafo.getDim()-1;
	this.domclass = a.domain(w1)[0];
	
	
	
	//Probability of values of class
	
	
	for(int i=0; i<domclass;i++) {
		int[] w2 = new int[1]; w2[0] = i;
		double r1 = a.count(w1,w2) + S;
		double l = a.length() + S*a.domain(w1)[0];	
		rede[grafo.getDim()-1][i][maxlen-1][maxlen-1][maxlen-1]= r1/l;
		
		
	}
	
	
	//DFOS
	
	
	for(int i=0; i<a.numvar()-1;i++){
		int[] var = new int[1];
		var[0]=i;
		for(int j=0; j<=a.domain(var)[0];j++) {
			int[] pais = g.parents(i);
			
		
     
		    int[] aux = new int[pais.length + 1];
		    System.arraycopy(pais, 0, aux, 0, pais.length);
		    aux[pais.length] = i;
		    
		  
			
			
			if(pais.length == 1) {
				for(int d=0; d<domclass;d++){
	 				int[] valn = new int[2];
	 				valn[0] = d; valn[1] = j;
	 				int[] vald = new int[1];
	 				vald[0] = d; 
	 						
	 				double num = a.count(aux,valn) + S;
	 				double deno = a.count(pais, vald)+ (S*(a.domain(var)[0]));
	 				rede[i][j][maxlen-1][maxlen-1][d] = (num/deno);
				
	 				}
			}
			if(pais.length == 2) {
				int [] p1 = new int[1]; p1[0]=pais[0];
	 			for(int b=0;b<=a.domain(p1)[0];b++){
	 					for(int d=0; d<domclass;d++){
	 						int[] valn = new int[3];
	 						valn[0] = b; valn[1] = d; valn[2] = j;
	 						int[] vald = new int[2];
	 						vald[0] = b; vald[1] = d;
	 						
	 						double num = a.count(aux,valn) + S;
	 						double deno = a.count(pais, vald)+ (S*(a.domain(var)[0]));
	 						rede[i][j][maxlen-1][b][d] = (num/deno);
	 					}
	 				}
	 			}
			   if(pais.length == 3) {
	 		   int [] p1 = new int[1]; p1[0]=pais[0]; int [] p2 = new int[1];p2[0]=pais[1];
	 		   for(int b=0;b<=a.domain(p1)[0];b++){
	 				for(int c=0;c<=a.domain(p2)[0];c++){
	 					for(int d=0; d<domclass;d++){
	 						int[] valn = new int[4];
	 						valn[0] = b; valn[1] = c; valn[2] = d; valn[3] = j;
	 						int[] vald = new int[3];
	 						vald[0] = b; vald[1] = c; vald[2] = d;
	 						
	 						double num = a.count(aux,valn) + S;
	 						double deno = a.count(pais, vald)+ (S*(a.domain(var)[0]));
	 						rede[i][j][b][c][d] = (num/deno);
	 			}	
	 			}
	 			}
	 		}
	
}
}
}

public static RN read(String path) {
	FileInputStream fs;
	try {
		fs = new FileInputStream(path);
		ObjectInputStream os = new ObjectInputStream(fs);
		RN res = (RN) os.readObject();
		os.close();
		return res;
	} catch (IOException | ClassNotFoundException e) {
		e.printStackTrace();
		return null;
	}
}

public void write(String path) {
	try {
		FileOutputStream fs = new FileOutputStream(path);
		ObjectOutputStream os = new ObjectOutputStream(fs);
		os.writeObject(this);
		os.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public int getNumvar() {
	return numvar;
}

public int dclass() {
	return domclass;
}


public double prob(int[] v) {
	double somaprob =0;
	double prob =0;
	//run through all values of C
	for(int j=0; j<domclass;j++){
		double p = rede[grafo.getDim()-1][j][maxlen-1][maxlen-1][maxlen-1];
		//probability for each
		for(int i = 0; i<v.length-1;i++) {
			int[] pais = grafo.parents(i);
			for(int d=0; d<pais.length;d++) {
			}
			
			if(pais.length == 1) {
				p = p*rede[i][v[i]][maxlen-1][maxlen-1][j];
				
		    }
			if(pais.length == 2) {
				p = p*rede[i][v[i]][maxlen-1][v[pais[0]]][j];
		    }
			if(pais.length == 3) {
				p = p*rede[i][v[i]][v[pais[0]]][v[pais[1]]][j];
			}
	    }
		if(j==v[v.length-1]){
			prob=p;
		}
        somaprob = somaprob+p;
		
	 }
	return prob/somaprob;
}


@Override
public String toString() {
	return "RN [rede=" + Arrays.toString(rede) + "]";
}



}