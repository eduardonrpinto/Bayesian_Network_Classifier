package Learning;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
	

public class Algoritmo{
	
public Algoritmo() {
	
}
	

public Grafo gerar( int n) {
	Grafo graph = new Grafo(n);
	for(int i=0; i<n-1;i++) { 
		graph.add_edge(n-1, i);
	}
	for(int i=0; i<n-1; i++) {
		 Random random = new Random();
		 int numeropais = random.nextInt(3);
	     int[] pais = new int[numeropais];
	     for(int j=0; j<numeropais;j++) {
	    	 pais[j]=random.nextInt(n-1);
	     }
	     for(int w=0;w<pais.length;w++){
	    	 Grafo ngraph = graph;
	    	 if(pais[w]!=i) {
	    	 ngraph.add_edge(pais[w], i);
	    	 if (!ngraph.connected(i,i)){
	    		 graph=ngraph;}
	     }
	 }
	
}
	return graph;
}



	
	
public RN algoritmo(Amostra a, int n, double S) {
	List<Grafo> graphList = new ArrayList<>();
	List<Grafo> finalgraphs = new ArrayList<>();
	int numvars = a.numvar();
	//Generate random graph
    for(int i=1; i<=n; i++){
		graphList.add(gerar(numvars));
	}
   
    
    
	//Generate empty graph
    Grafo grafovazio = new Grafo(numvars);
    for (int i =0; i<numvars-1;i++) {
    	grafovazio.add_edge(numvars-1,i);
    }
	
    graphList.add(grafovazio);
	
	
	for(int j=0; j<graphList.size();j++) {
		Grafo g = graphList.get(j);
	    boolean cond = true;
	    double maxMDLDelta = 0;
	    
	  
	    while(cond==true) {
	    	
		maxMDLDelta = 0;
		int[] bestoperation = new int[3];
		
		
		//All combinations
		for(int i = 0; i<numvars-1; i++) {
    	for(int w=0; w<numvars-1;w++){
    		
    		if (g.edgeQ(i,w)==false && g.MDLdelta(a,i,w,2)>maxMDLDelta){
    			    bestoperation[0]=2; bestoperation[1]=i; bestoperation[2]=w;
    				maxMDLDelta = g.MDLdelta(a,i,w,2);
    			}
    		
    		if (g.edgeQ(i,w)==true && g.MDLdelta(a,i,w,1)>maxMDLDelta){
    				bestoperation[0]=1; bestoperation[1]=i; bestoperation[2]=w;
    				maxMDLDelta = g.MDLdelta(a,i,w,1);
    			}
                
    	    if(g.edgeQ(i,w)==true && g.MDLdelta(a,i,w,0)>maxMDLDelta) {
    				bestoperation[0]=0; bestoperation[1]=i; bestoperation[2]=w;
        			maxMDLDelta = g.MDLdelta(a,i,w,0);
    			}
            }   
        
    	}
	
		for(int i =0; i<bestoperation.length;i++) {
	
		}
	
		if (maxMDLDelta==0) {
			cond=false;
			
		} else {
			//Execute best operation
			if (bestoperation[0]==0) {
				g.remove_edge(bestoperation[1],bestoperation[2]);
			}
			else if (bestoperation[0]==1) {
				g.invert_edge(bestoperation[1],bestoperation[2]);
			}
			else if (bestoperation[0]==2) {
				g.add_edge(bestoperation[1],bestoperation[2]);
			}
		}
	
		}
	    
	    finalgraphs.add(g);
        } 

	    
	    Grafo grafofinal = finalgraphs.get(0);
	    for(int b=1; b<finalgraphs.size();b++) {
	    	if(finalgraphs.get(b).MDL(a) > grafofinal.MDL(a)) {
	    		grafofinal=finalgraphs.get(b);
	    	}
	    }
	    
	    RN redetreinada = new RN(grafofinal,a,S);
	    return redetreinada;
	    
}

}


	

