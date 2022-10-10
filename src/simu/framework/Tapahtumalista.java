package simu.framework;

import java.util.PriorityQueue;
import testi.Simulaattori;

public class Tapahtumalista {
	
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();
	
	public Tapahtumalista(){}
	
	public Tapahtuma poista(){

		return lista.remove();
		
	}
	
	public void lisaa(Tapahtuma t){

		lista.add(t);
	}
	
	public double getSeuraavanAika(){
		
		double aika;
		
		try {
			
			aika = lista.peek().getAika(); 
			return aika;
			
		}catch(Exception e) {
			
			System.out.println("Ei seuraavaa tapahtumaa");
			
		}
		
		return Simulaattori.getEndTime();
		
	}
	
	public boolean onkoTapahtumia() {return !lista.isEmpty();}
}
