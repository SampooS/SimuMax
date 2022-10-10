package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.model.Tulokset.Palvelupisteet;


public class Palvelupiste {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>();
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	private int maxJono;
	private double aktiiviAika;
	private boolean varattu = false;

	
	
	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi){
		
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.maxJono = 0;
		this.aktiiviAika = 0;
			
	}
	
	public Palvelupiste(int aika) {
		
		this.aktiiviAika = aika;
		
	}


	public void lisaaJonoon(Asiakas a){
		
		jono.add(a);
		if (jono.size() > maxJono) {maxJono = jono.size();}	
		 
	
		
	}
	
	public int getJononKoko() {return jono.size();}
	
	public int getMaksimiJononKoko() {return maxJono;}
	

	public Asiakas otaJonosta(){
		

		varattu = false;
		return jono.poll();
		
	}

	public void aloitaPalvelu(){
		

					int direction = jono.peek().getDirection();
					varattu = true;	
					double palveluaika = generator.sample();
					aktiiviAika += palveluaika;
					tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika() + palveluaika, direction));			
			
		
	}
	
	public double getActiveTime() {return aktiiviAika;}


	public boolean onVarattu(){return varattu;}


	public boolean onJonossa(){
		
		return jono.size() != 0;
		
	}
	
	public int getPalvellutAsiakkaat() {
		
		return Tulokset.getInstance().getRuokaAsiakas();
	}
}
