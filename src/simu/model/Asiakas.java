package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;


public class Asiakas {
	
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	private int direction;
	static int kikkeli = 0;


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
	
		return (poistumisaika-saapumisaika);
		
	}
	
	public double getKeskimaarainenLapimenoAika() {
		
		
		//sum += (poistumisaika-saapumisaika);
		//double keskiarvo = sum/id;
		
		return sum/id;
		
		
		
	}
	
	public void raportti(){
		
		//Trace.out(Trace.Level.INFO, "\nAsiakas "+id+ " valmis! " + ".................................................................................");
		//Trace.out(Trace.Level.INFO, "Asiakas "+id+ " saapui: " +saapumisaika);
		//Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui: " +poistumisaika);
		//Trace.out(Trace.Level.INFO,"Asiakas "+id+ " viipyi: " +(poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
		kikkeli++;
		
	}	
}
