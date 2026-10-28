package Learning.Amostra.Amostra.Amostra;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Grafo implements Serializable{
	private int dim;
	public int [][] matriz;

public Grafo(int d) {
	super();
	this.dim = d;
	this.matriz = new int[d][d];
}



@Override
public String toString() {
	return "Grafo [dim=" + dim + ", matriz=" + Arrays.toString(matriz) + "]";
}



public int getDim() {
	return dim;
}



public void setDim(int dim) {
	this.dim = dim;
}



public int[][] getMatriz() {
	return matriz;
}



public void setMatriz(int[][] matriz) {
	this.matriz = matriz;
}



public void add_edge(int a, int b) {
	matriz[a][b] = 1;
	}
	
public void remove_edge(int a, int b) {
	matriz[a][b] = 0;
	}


public void invert_edge(int a, int b) {
	if (matriz[a][b] == 1) {
		matriz[a][b]=0;
		matriz[b][a]=1;
	}
	}

public boolean edgeQ(int a, int b) {
	boolean r = false;
	if(matriz[a][b]==1) {
		r=true;
	}
	return r;
}


public int[] parents(int a) {
    int[] v1 = null;
    int count = 0;  

    for (int i = 0; i < dim; i++) {
        if (matriz[i][a] == 1) {
            if (v1 == null) {
                v1 = new int[1];
            } else {
                int[] temp = new int[v1.length + 1];
                System.arraycopy(v1, 0, temp, 0, v1.length);
                v1 = temp;
            }

            v1[count] = i;
            count++;
        }
    }

    return v1;
}



public double teta(Amostra a, int i) {

int teta =0;
	int [] w = new int[1]; w[0]=i;
	int [] pais = parents(i);
	
	int q = 1;
	for(int p =0; p< pais.length-1;p++) {
		int [] paiaux = new int[1];
		paiaux[0] = pais[p];
		q = q*a.domain(paiaux)[0];
	}
	teta = ((a.domain(w)[0]-1)*q*2);
return teta;
}


public double it(Amostra a, int i) {
	
	double m = a.length();
	double it = 0;
		int[] var = new int[1];
		var[0]=i;
		int[] pais = parents(i);

	    int[] vars1 = new int[pais.length + 1];
	    System.arraycopy(pais, 0, vars1, 0, pais.length);
	    vars1[pais.length] = i;
	    
		for (int d=0; d < a.domain(var)[0];d++){
			//if parents length ==1, it=0 so add 0
			if (pais.length ==2){
				int [] p1 = new int[1];
				p1[0] = pais[0];
				for(int w=0; w<a.domain(p1)[0];w++) {
					for (int c=0; c<2;c++) {
						
						int[] val1 = new int [pais.length+1]; val1[0]=w; val1[1]= c; val1[2]=d;
						double count1 = a.count(vars1, val1)/m; 
						if (count1 ==0) {}
						else {
						int[] val2 = new int [2]; val2[0]=d;val2[1]=c;
						int[] vars2 = new int[2]; vars2[0]=i;  vars2[1] = a.numvar()-1; 
						double count2 = a.count(vars2,val2)/m;
						int[] vals3 = new int[2]; vals3[0]=w; vals3[1]=c;
						double count3 = a.count(pais, vals3)/m;
						int [] vals4 = new int[1]; vals4[0]=c;
						int[] vars4 = new int[1]; vars4[0] = a.numvar()-1; 
						double  count4 = a.count(vars4,vals4)/m;
						
						it = it + count1* (Math.log((count1*count4)/(count2*count3))/Math.log(2));
					}
					}
				}
			}
			if (pais.length == 3) {
				
				int [] p1 = new int[1]; p1[0] = pais[0];
				int [] p2 = new int[1]; p2[0] = pais[1];
				for(int w1=0; w1<a.domain(p1)[0];w1++) {
					for(int w2 =0; w2<a.domain(p2)[0];w2++) {
					    for (int c=0; c<2;c++) {
						
						int[] val1 = new int [pais.length+1]; val1[0]=w1; val1[1]= w2; val1[2]=c; val1[3]=d;
						
						double count1 = a.count(vars1, val1)/m;
						if (count1 ==0) {}
						else {
						int[] val2 = new int [2]; val2[0]=d;val2[1]=c;
						int[] vars2 = new int[2]; vars2[0]=i;  vars2[1] = a.numvar()-1; 
						double count2 = a.count(vars2,val2)/m;
						int[] vals3 = new int[3]; vals3[0]=w1; vals3[1]=w2; vals3[2]=c;
						double count3 = a.count(pais, vals3)/m;
						int [] vals4 = new int[1]; vals4[0]=c;
						int[] vars4 = new int[1]; vars4[0] = a.numvar()-1; 
						double  count4 = a.count(vars4,vals4)/m;
						
						it= it + count1* (Math.log((count1*count4)/(count2*count3))/Math.log(2));
						}
					    }
					}
				}
	    }
		}
		return it;
		}
	

public double MDL (Amostra a) {
	double m = a.length();
	double T=0;
	double I=0;
	for(int i=0; i<dim-1;i++){
		T = T + teta(a,i);
		I = I + it(a,i);
	}
	int[] dom = new int[1]; dom[0]=a.numvar()-1;
	T= T+a.domain(dom)[0]-1;
	return m*I-((Math.log(m)/Math.log(2))/2)*T;
	
}
			
	public double MDLdelta(Amostra a, int o, int d, int p) {
        int m=a.length();
        double r = 0;
        double ad =0;
        double var=0;  //if operation not possible, returns 0
        if(o!=d) { //check if is the same vertex
        if(p==0){ //remove
          if(edgeQ(o,d)){
          r=((Math.log(m)/Math.log(2))/2)*teta(a,d)-m*it(a,d);
          remove_edge(o,d);
          ad=m*it(a,d)-((Math.log(m)/Math.log(2))/2)*teta(a,d);
          add_edge(o,d);
          }
          var=r+ad;  
        }
        else if(p==2){ //add
        	if (parents(d).length<=2 && !connected(d,o) && !edgeQ(o,d)) {
        		r=((Math.log(m)/Math.log(2))/2)*teta(a,d)-m*it(a,d);
                add_edge(o,d);
                ad=m*it(a,d)-((Math.log(m)/Math.log(2))/2)*teta(a,d);
                remove_edge(o,d);
                var=r+ad;
        	}
        } 
        else if(p==1 && edgeQ(o,d) && parents(o).length<=2){ //invert
        	remove_edge(o,d);
        	boolean ciclo = connected(o,d);
        	add_edge(o,d);
        	if(!ciclo){
        		 r=((Math.log(m)/Math.log(2))/2)*(teta(a,d)+teta(a,o))-m*(it(a,d)+it(a,o));
                 invert_edge(o,d);
                 ad= m*(it(a,d)+it(a,o))-((Math.log(m)/Math.log(2))/2)*(teta(a,d)+teta(a,o));
                 invert_edge(d,o);
                 var=r+ad;
        	} 
        }     
        }
		return var;
	}
	
	
	
	public boolean connected(int o, int d) {
		boolean con=false;
		LinkedList<Integer> visited = new LinkedList <Integer> ();
		Queue <Integer> q = new LinkedList <Integer> ();
		q.add(d);
		while (!q.isEmpty() && !con) {
			int n = q.remove();
			if(n==o && visited.size()>0)
				con=true;
			else if (!visited.contains(n)) {
				visited.add(n);
				if(parents(n)!=null) {
				for(int i =0; i<parents(n).length;i++) {
				q.add(parents(n)[i]);
				}		
			}
			}
	}
		return con;
	}	
		
	
}

	


