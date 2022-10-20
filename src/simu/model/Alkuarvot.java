package simu.model;

/**
 * The Class Alkuarvot :starting values of the simulator (food lines, cashiers, student's count, groups, gradation time).
 */
public class Alkuarvot {

	
	/**  The food line count. */
	private int ruokalinja;
	
	/** The cashier's count variable. */
	private int kassat;
	
	/** The student's count variable. */
	private int asiakkaat;
	
	/** The group of student count variable */
	private int ryhmienMaara;
	

	private int muuttuvaRyhma;
	
	/** The gradation time. */
	private double porrastusMaara;

	/**
	 * Instantiates a new starting values for the simulator.
	 *
	 * @param ruokalinja the food line
	 * @param kassat the cashier's count 
	 * @param asiakkaat the student's count
	 * @param ryhmienMaara the group of student count 
	 * @param porrastusMaara the gradation time
	 */
	public Alkuarvot(int ruokalinja, int kassat, int asiakkaat,int ryhmienMaara, double porrastusMaara) {
		
		this.ruokalinja = ruokalinja;
		this.kassat = kassat;
		this.asiakkaat = asiakkaat;
		this.ryhmienMaara = ryhmienMaara;
		this.porrastusMaara = porrastusMaara;
		
	}
	
	/**
	 * Gets the food line's count.
	 *
	 * @return the food line's count
	 */
	public int getRuokalinja() {
		return ruokalinja;
	}



	/**
	 * Gets the cashier's count.
	 *
	 * @return the cashier's count
	 */
	public int getKassat() {
		return kassat;
	}



	/**
	 * Gets the students count.
	 *
	 * @return the students count
	 */
	public int getAsiakkaat() {
		return asiakkaat;
	}



	/**
	 * Gets the groups count.
	 *
	 * @return the groups count
	 */
	public int getRyhmienMaara() {
		return ryhmienMaara;
	}



	/**
	 * Gets the gradation time.
	 *
	 * @return the gradation time
	 */
	public double getPorrastusMaara() {
		return porrastusMaara;
	}
	
	
	
	/**
	 * Sets the gradation time.
	 */
	public void setPorrastusMaara() {
		
		
		
	}
}
