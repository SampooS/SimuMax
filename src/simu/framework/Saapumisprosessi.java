package simu.framework;
import java.util.ArrayList;

import eduni.distributions.*;
import simu.model.TapahtumanTyyppi;
public class Saapumisprosessi {
	
	private ContinuousGenerator generaattori;
	private Tapahtumalista tapahtumalista;
	private ArrayList<Tapahtuma> tapahtuneet;
	private TapahtumanTyyppi tyyppi;
	private int ryhmaKokonen;
	private int ryhma;

	public Saapumisprosessi(ContinuousGenerator g, Tapahtumalista tl, TapahtumanTyyppi tyyppi, int ryhmaKoko){
		
		this.tapahtuneet = new ArrayList<>();
		this.generaattori = g;
		this.tapahtumalista = tl;
		this.tyyppi = tyyppi;
		this.ryhmaKokonen = ryhmaKoko;
		this.ryhma = ryhmaKoko;
		
	}

	public void generoiSeuraava(){
		
		if(ryhma > 0) {
			
			Tapahtuma t = new Tapahtuma(tyyppi, Kello.getInstance().getAika()+generaattori.sample());
			tapahtumalista.lisaa(t);
			ryhma--;
			
		}
	}
	
	public void setUusiRyhma() {
		
		ryhma = ryhmaKokonen;
		
	}
	
	public ArrayList<Tapahtuma> getTapahtumat() {
		return this.tapahtuneet;
	}
}
