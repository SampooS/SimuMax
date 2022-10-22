package simu.model;

import simu.framework.Kello;

public class Asiakas {
	
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	private int direction;


	public Asiakas(){
		
	    id = i++;	    
		saapumisaika = Kello.getInstance().getAika();
		
	}

	public double getPoistumisaika() {return poistumisaika;}

	public void setPoistumisaika(double poistumisaika) {
		
		this.poistumisaika = poistumisaika;
		
	}

	public double getSaapumisaika() {return saapumisaika;}

	public void setSaapumisaika(double saapumisaika) {
		
		this.saapumisaika = saapumisaika;
		
	}
	
	public void setDirection(int i) {
		
		this.direction = i;
		
	}
	
	public int getDirection() {return this.direction;}
	
	public int getId() {return id;}
	
	public double getLapimenoAika_Ri() {
		
		if(poistumisaika == 0) {
			
			return 0;
			
		}
		
		return (poistumisaika-saapumisaika);
		
	}
	
	public double getKeskimaarainenLapimenoAika() {
		
		return sum/id;	
		
	}
	
	public void raportti(){
		
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		
	}	
}
