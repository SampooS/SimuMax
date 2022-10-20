package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.model.Tulokset.Palvelupisteet;


/**
 * The Class Palvelupiste (Service point).
 */
public class Palvelupiste {

	/** The queue list. */
	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>();
	
	/** The generator. */
	private ContinuousGenerator generator;
	
	/** The event list. */
	private Tapahtumalista tapahtumalista;
	
	/** The type of event to be scheduled. */
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	
	/** The max queue. */
	private int maxJono;
	
	/** The busy time. */
	private double aktiiviAika;
	
	/** The reserved boolean variable to check the service point/line if it's free. */
	private boolean varattu = false;
	
	/** The served students count. */
	private int palveltu;

	
	
	/**
	 * Instantiates a new palvelupiste (Service point).
	 *
	 * @param generator the generator
	 * @param tapahtumalista the event list
	 * @param tyyppi the type of event
	 */
	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi){
		
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.maxJono = 0;
		this.aktiiviAika = 0;
		this.palveltu = 0;
	}
	
	/**
	 * Instantiates a new palvelupiste (debugging constructor).
	 *
	 * @param aika the time
	 */
	public Palvelupiste(double aika) {
		
		this.aktiiviAika = aika;
		
	}


	/**
	 * adds student to the queue.
	 *
	 * @param a the student
	 */
	public void lisaaJonoon(Asiakas a){
		
		jono.add(a);
		if (jono.size() > maxJono) {maxJono = jono.size();}	
		 
	}
	
	
	
	/**
	 * Gets the queue size.
	 *
	 * @return the queue size
	 */
	public int getJononKoko() {return jono.size();}
	
	/**
	 * Sets the max queue size.
	 *
	 * @param i the new max queue size
	 */
	public void setMaxJononKoko(int i) {
		this.maxJono = i;
	}
	
	/**
	 * Gets the max queue size.
	 *
	 * @return the max queue size
	 */
	public int getMaksimiJononKoko() {return maxJono;}
	

	/**
	 * removes student from the queue.
	 *
	 * @return the removed student from the queue
	 */
	public Asiakas otaJonosta(){
		

		varattu = false;
		return jono.poll();
		
	}

	/**
	 * starts the service for the student.
	 */
	public void aloitaPalvelu(){
		

					int direction = jono.peek().getDirection();
					varattu = true;	
					double palveluaika = generator.sample();
					aktiiviAika += palveluaika;
					tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika() + palveluaika, direction));
		
					palveltu++;
						
	}
	
	/**
	 * Gets the busy time of the service point.
	 *
	 * @return the busy time of the service point
	 */
	public double getActiveTime() {return aktiiviAika;}


	/**
	 * On varattu = reserved: to check if the service point is free or not.
	 *
	 * @return true, if successful
	 */
	public boolean onVarattu(){return varattu;}


	/**
	 * On jonossa = queue is not empty .
	 *
	 * @return true, if successful
	 */
	public boolean onJonossa(){
		
		return jono.size() != 0;
		
	}
	
	/**
	 * Sets the enum.
	 *
	 * @param tyyppi the new enum type
	 */
	public void setEnum(String tyyppi) {		
		switch (tyyppi.toUpperCase()) {
		case "ARR":
			this.skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.ARR1;
			break;
		case "DEP1":
			this.skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.DEP1;
		case "DEP2":
			this.skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.DEP2;
			break;
		case "DEP3":
			this.skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.DEP3;
			break;
		}
	}
	
	/**
	 * Gets the event's type.
	 *
	 * @return the event's type
	 */
	public TapahtumanTyyppi getTyyppi() {
		return this.skeduloitavanTapahtumanTyyppi;
	}
	
	/**
	 * Gets the served students.
	 *
	 * @return the served students
	 */
	public int getPalvellutAsiakkaat() {
		
		return palveltu;		
	}
}
