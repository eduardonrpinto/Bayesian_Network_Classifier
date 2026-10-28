package Learning.Amostra.Amostra.Amostra;

import java.util.ArrayList;
import java.util.List;

public class Classificador {
	
	

public Classificador() {
	
}
	

public int classificador(RN rede,int[] v) {
	int numvari = rede.getNumvar();
	int res = 0;
	
	int [] aux = new int[v.length+1];
	for(int i =0; i<aux.length-1;i++) {
		aux[i]=v[i];
	}
	
	aux[v.length]=0;
	double probabilidade = rede.prob(aux);
	for(int i=0; i<rede.dclass();i++) {
		aux[v.length]=i;
		if(rede.prob(aux)>probabilidade) {
			res = i;
			probabilidade = rede.prob(aux);
			
		}
	}
   return res;
}

}
	
