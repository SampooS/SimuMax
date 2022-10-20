package simu.model;


import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;

/**
 * The Class RyhmaSaapumisProsessi (groups arriving process).
 */
public class RyhmaSaapumisProsessi {
	
	/** The event list. */
	private Tapahtumalista tapahtumalista;
	
	/** The event type. */
	private TapahtumanTyyppi tyyppi;
	
	/** The gradation time. */
	private double porrastusAika;
	
	/** The time between groups arriving. */
	private double siirtoAika;
	
	
	/**
	 * Instantiates a new group arriving process.
	 *
	 * @param tl the event list
	 * @param tyyppi the event's type
	 * @param porrastusAika the gradation time
	 */
	public RyhmaSaapumisProsessi(Tapahtumalista tl, TapahtumanTyyppi tyyppi, double porrastusAika) {
		
		this.tapahtumalista = tl;
		this.tyyppi = tyyppi;
		this.porrastusAika = porrastusAika;
		this.siirtoAika = porrastusAika;
			
	}
	
	/**
	 * creates new event and adds it to event list.
	 */
	public void generoiSeuraava(){
							
			Tapahtuma t = new Tapahtuma(tyyppi,porrastusAika);
			tapahtumalista.lisaa(t);
			porrastusAika += siirtoAika;
					
	}
}
