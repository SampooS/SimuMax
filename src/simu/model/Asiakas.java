package simu.model;

import simu.framework.Kello;

/**
 * The Class Asiakas.
 */
public class Asiakas {
	
	/** The arriving time variable. */
	private double saapumisaika;
	
	/** The leaving time variable. */
	private double poistumisaika;
	
	/** The id. */
	private int id;
	
	/** The i. */
	private static int i = 1;
	
	/** The sum. */
	private static long sum = 0;
	
	/** The direction. */
	private int direction;

	
	/** The count . */
	static int kikkeli = 0;


	/**
	 * Instantiates a new student.
	 */
	public Asiakas(){
		
	    id = i++;	    
		saapumisaika = Kello.getInstance().getAika();
		
	}

	/**
	 * Gets the leaving time.
	 *
	 * @return the leaving time
	 */
	public double getPoistumisaika() {return poistumisaika;}

	/**
	 * Sets the leaving time.
	 *
	 * @param poistumisaika the new leaving time
	 */
	public void setPoistumisaika(double poistumisaika) {
		
		this.poistumisaika = poistumisaika;
		
	}

	/**
	 * Gets the arriving time.
	 *
	 * @return the arriving time.
	 */
	public double getSaapumisaika() {return saapumisaika;}

	/**
	 * Sets the arriving time.
	 *
	 * @param saapumisaika the new arriving time.
	 */
	public void setSaapumisaika(double saapumisaika) {
		
		this.saapumisaika = saapumisaika;
		
	}
	
	/**
	 * Sets the direction.
	 *
	 * @param i the new direction
	 */
	public void setDirection(int i) {
		
		this.direction = i;
		
	}
	
	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public int getDirection() {return this.direction;}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {return id;}
	
	/**
	 * Gets the lead time of the student.
	 *
	 * @return the lead time of the student through the system.
	 */
	public double getLapimenoAika_Ri() {
		
		if(poistumisaika == 0) {
			
			return 0;
			
		}
		
		return (poistumisaika-saapumisaika);
		
	}
	
	/**
	 * Gets the average lead time.
	 *
	 * @return the average value of student's lead time.
	 */
	public double getKeskimaarainenLapimenoAika() {
		
		return sum/id;	
		
	}
	
	
	public void raportti(){
		
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		
	}	
}
