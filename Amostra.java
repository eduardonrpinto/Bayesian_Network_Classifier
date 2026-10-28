/*Esta classe Amostra tem um construtor que lê um ficheiro csv e constrói uma lista de arrays
 correspondente às linhas desse ficheiro. Faltam terminar várias funções do enunciado do projeto
de AMC de 2023. Neste exemplo, o ficheiro bcancer.csv deve estar na pasta do projeto para funcionar.
Não é na pasta src, é na pasta acima.
fmd
*/
//Acrescentar o package se tiver criado um package
package Learning.Amostra.Amostra.Amostra;


import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Amostra implements Serializable{
	
	private static final long serialVersionUTD = 1L;
	
	private ArrayList<int []> lista; // Lista de amostras
	private int [] domain = null;    // Array de domínios, deve ser actualizado no método add.

	public Amostra() {
		this.lista = new ArrayList<int []>();
	}

	static int [] convert (String line) {
		String cvsSplitBy = ",";
		String[] strings     = line.split(cvsSplitBy);
		int[] stringToIntVec = new int[strings.length];
		for (int i = 0; i < strings.length; i++)
			stringToIntVec[i] = Integer.parseInt(strings[i]);
		return stringToIntVec;
		}
	
	public Amostra(String csvFile) {
		this.lista = new ArrayList<int []>();;
		this.domain = null;
		BufferedReader br = null;
		String line = "";
		

		try {br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {	
				add(convert(line));

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

	public void add (int[] v){
		lista.add(v);
		if(domain==null) {
			domain = new int[v.length]; 
			for(int i=0; i<v.length; i++) {
				domain[i] = v[i] + 1;
			}
		}
		else
			for(int i=0;i<domain.length;i++) {
				if(domain[i]<=v[i])
					domain[i] = v[i]+1;
			}
	}
	
	public int numvar() {
		return lista.get(0).length;
	}
	
	public void remove(int v) {
		lista.remove(v);
		
	}
	
	public int length (){
		return lista.size();
	}
	
	public int [] element (int i){
		return lista.get(i);
	}
	
	
	public double count(int[] var, int[] val){
		int r=0;
		for (int i = 0; i< length(); i++) {
			boolean check = true;
			for (int j=0; j< val.length ; j++){
				if ( element(i)[var[j]]!=val[j]) {
					check =false;
				}
			}
			if (check) {
				r++;}
			}
			
		return r;
	}
	
	
	
	
	public int[] domain (int var[]){
		int[] r = new int[var.length];
		for(int i=0; i< var.length; i++){
			r[i]=domain[var[i]];
			}
		return r;
	}
	
	@Override
	public String toString() {
		String s="\n[\n";
		if (lista.size()>0) s+=Arrays.toString(lista.get(0));
		for (int i=1; i<lista.size();i++)
			s+="\n"+Arrays.toString(lista.get(i));
		s+="\n]";
		
		return "Amostra " + s;
	}
	

	
}

