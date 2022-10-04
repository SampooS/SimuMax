package simu.framework;
import eduni.distributions.*;
import simu.model.TapahtumanTyyppi;
public class Saapumisprosessi {
	
	private ContinuousGenerator generaattori;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi tyyppi;
	private int ryhmaKokonen;
	private int ryhma;

	public Saapumisprosessi(ContinuousGenerator g, Tapahtumalista tl, TapahtumanTyyppi tyyppi, int ryhmaKoko){
		
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
}
