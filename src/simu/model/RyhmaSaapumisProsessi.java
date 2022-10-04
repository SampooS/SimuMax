package simu.model;


import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;

public class RyhmaSaapumisProsessi {
	
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi tyyppi;
	private double porrastusAika;
	private double siirtoAika;
	
	
	public RyhmaSaapumisProsessi(Tapahtumalista tl, TapahtumanTyyppi tyyppi, double porrastusAika) {
		
		this.tapahtumalista = tl;
		this.tyyppi = tyyppi;
		this.porrastusAika = porrastusAika;
		this.siirtoAika = porrastusAika;
			
	}
	
	public void generoiSeuraava(){
							
			Tapahtuma t = new Tapahtuma(tyyppi,porrastusAika);
			tapahtumalista.lisaa(t);
			porrastusAika += siirtoAika;
					
	}
}
