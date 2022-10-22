package simu.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import simu.framework.Kello;
import testi.Simulaattori;

/**
 * The Class Tulokset.
 *
 * @author Mohammed Al-Jewari
 */
public class Tulokset implements Serializable{
	/**
	 * This is a class for calculating all the data and return the required values.
	 */
	private static final long serialVersionUID = 1L;
	
	/** The busy time B. */
	private double aktiiviAika_B; // B Busy time
	
	/** The served students count C. */
	public int palvellutAsiakkaat_C = 0; // C completed count
	
	/** The simulation time T. */
	public double kokonaisAika_T; // T simuloinnin aika 
	
	/** The students list. */
	public static ArrayList<Asiakas> asiakaslista = new ArrayList<>();
	
	/** The service points. */
	public Palvelupiste [] palvelupisteet;
	
	/** The dining room students list. */
	private ArrayList<Double> ruokasalilista = new ArrayList<>();
	
	/** The food lines. */
	public int ruokalinja;
	
	/** The cashiers. */
	public int kassat;
	
	/** The groups. */
	public int ryhmat;
	
	/** The gradation time. */
	public double porrastusaika;
	
	/** The students. */
	public int asiakkaat;
	
	/** The arriving students. */
	public int saapuneetasiakkaat;
	
	/** The leaving students. */
	public int poistuneetasiakkaat;
	
	/** The cashier's max queue size. */
	private int kassaMaxjono;
	
	/** The max food queue. */
	private int ruokaMaxJono;
	
	/** The students arriving to food queue. */
	private int ruokaAsiakas;
	
	/** The food queue. */
	private int linjasto;
	
	/** The cashier. */
	private int kassa;
	
	/** The starting simulating values. */
	private Alkuarvot alkuarvot;
	
	/** The data for sql. */
	public ResultSet dummy;
	
	/**
	 * The Enum Palvelupisteet (Service points).
	 */
	public enum Palvelupisteet{/** The food queues. */ RUOKALINJASTO, /** The cashiers. */  KASSA, /** The dining room. */  RUOKASALI, /** All service points. */  KAIKKI}
	
	/** The instance of singleton class (Tulokset class). */
	private static Tulokset instanssi;

	
	/**
	 * Gets the single instance of Tulokset.
	 *
	 * @return single instance of Tulokset
	 */
	public static Tulokset getInstance() {
		
		if (instanssi == null){
			instanssi = new Tulokset();	
		}
		return instanssi;
	}
	
	/**
	 * Sets the data from sql.
	 *
	 * @param i the new data
	 * @return the data from the database.
	 * @throws SQLException the SQL exception
	 */
	public void setDummy(int i) throws SQLException {
		this.dummy = DBAccessObject.lataaDummy(i);
	}
	
	
	/**
	 * this method reset the Tulokset class. 
	 */
	public void alustaTulokset() {	
		
		asiakaslista.clear();
		asiakaslista = new ArrayList<>();
		ruokasalilista.clear();
		ruokasalilista = new ArrayList<>();
		palvelupisteet = new Palvelupiste[0];
		kassaMaxjono = 0;
		ruokaMaxJono = 0;
		
	}
	
	/**
	 * This method sets all user inputs data to the system to calculate depending on them.
	 *
	 * @param ryhmat the groups of students
	 * @param porrastusaika the gradation time
	 * @param asiakkaat the students
	 * @param linjasto the food line
	 * @param kassa the cashiers
	 */
	public void setAlkuarvot(int ryhmat, double porrastusaika, int asiakkaat, int linjasto, int kassa) {
		
		this.ryhmat = ryhmat;
		this.porrastusaika = porrastusaika;
		this.asiakkaat = asiakkaat;
		this.linjasto = linjasto;
		this.kassa = kassa;
		
	}
	
	/**
	 * This method sets the service points.
	 *
	 * @param pisteet the new palvelupisteet (service point)
	 */
	public void setPalvelupisteet(ArrayList<Palvelupiste> pisteet) {
		
		this.palvelupisteet = new Palvelupiste[pisteet.size()];
		for (int i = 0; i < pisteet.size()-1; i++) {
			palvelupisteet[i] = pisteet.get(i);
		}
		
	}
	
	/**
	 * this method sets the groups if user decide to simulate using groups.
	 *
	 * @param ryhmat the new groups
	 */
	public void setRyhmat(int ryhmat) {
		this.ryhmat = ryhmat;
	}
	
	/**
	 * Gets the groups.
	 *
	 * @return how many groups the program simulating.
	 */
	public int getRyhmat() {
		
		return ryhmat;
	}
	
	/**
	 * This method sets how long time between the groups.
	 *
	 * @param porrastusaika the new gradation time
	 */
	public void setPorrastusAika(int porrastusaika) {
		this.porrastusaika = porrastusaika;
	}
	
	/**
	 * Gets the gradation time.
	 *
	 * @return how long time should the group wait to come to the line.
	 */
	public double getPorrastusAika() {
		
		return porrastusaika;
	}
	
	/**
	 * This method sets how many students the program simulates. 
	 *
	 * @param asiakkaat the new students count
	 */
	public void setAsiakkaat(int asiakkaat) {
		
		this.asiakkaat = asiakkaat;
	}
	
	/**
	 * Gets the students count.
	 *
	 * @return how many students the user decides to simulates.
	 */
	public int getAsiakkaat() {
		
		return asiakkaat;
	}
	
	/**
	 * This method sets how many students arrive to the system.
	 *
	 * @param maara the new arriving students
	 */
	public void setSaapuneetasiakkaat(int maara) {
		
		this.saapuneetasiakkaat = 0;
		saapuneetasiakkaat = maara;
		
	}
	
	/**
	 * This method sets how many students leave the system.
	 *
	 * @param maara the new leaving students
	 */
	public void setPoistuneetasiakkaat(int maara) {
		
		this.poistuneetasiakkaat = 0;
		poistuneetasiakkaat = maara;
		
	}
	
	/**
	 * Gets the arriving students count.
	 *
	 * @return how many students arrive the system.
	 */
	public int getSaapuneetasiakkaat() {
		
		return saapuneetasiakkaat;
		
	}
	
	/**
	 * Gets the leaving students count.
	 *
	 * @return how many students leave the system.
	 */
	public int getPoistuneetasiakkaat() {
		
		return poistuneetasiakkaat;
		
	}
	
	
	/**
	 * This method sets the service points the system simulates.  
	 *
	 * @param palvelupisteet the service points
	 * @param ruokalinja the food queues
	 * @param kassat the cashiers
	 */
	public void setPalvelupiste(Palvelupiste [] palvelupisteet, int ruokalinja, int kassat) {
		
		this.palvelupisteet = palvelupisteet;
		this.ruokalinja = ruokalinja;
		this.kassat = kassat;
		this.ruokaAsiakas = 0;
		
	}
	
	/**
	 * This method adds student to the list of the students.
	 *
	 * @param asiakas the student
	 */
	public void lisaaAsiakasTulosListalle(Asiakas asiakas) {
		
		asiakaslista.add(asiakas);
		
	}
	



	/*
	public int getAsiakkaat_A() {
		
		return asiakaslista.size();
	}


	public void setAsiakkaat_A(int asiakasMaara_A) {
		this.asiakasMaara_A = asiakasMaara_A;
	}
	*/
	
	/**
	 * This method sets how many students arrive to the food line.
	 */
	public void setRuokaAsiakas() {
		
		ruokaAsiakas++;
		
	}
	
	/**
	 * Gets the food client (student).
	 *
	 * @return how many students are lining for the food.
	 */
	public int  getRuokaAsiakas() {
		
		return ruokaAsiakas;
		
	}
	
	/**
	 * This method adds student to the list of dining room.
	 *
	 * @param maara the new dining room time
	 */
	public void setRuokasaliAika(double maara) {
		
		ruokasalilista.add(maara);
		
	}

	/**
	 * Gets the average time spent in the dining room.
	 *
	 * @return the average time of the student spends in the dining room.
	 */
	public double getKeskimaarainenRuokasaliAika() {
		
		double aika = 0;
		
		for(Double saliaika: ruokasalilista) {
			
			aika += saliaika;
			
		}
		
		return aika;
		
		
	}
	
	/**
	 * This method return the busy time of the service point/s.
	 * @param type (Service points)
	 * @return	the busy time of the service point/s.
	 */
	public double getAktiiviAika_B(Palvelupisteet type) {
		
		double ruokalinjastoAktiiviAika = 0;
		double kassaAktiiviAika = 0;
		
		for(int i = 0; i < linjasto; i++) {
			
			ruokalinjastoAktiiviAika += palvelupisteet[i].getActiveTime();	
			
		}
		
		for(int i = linjasto; i < (linjasto + kassa); i++) {
			
			kassaAktiiviAika += palvelupisteet[i].getActiveTime();	
			
		}
		
		switch (type){
		
		case RUOKALINJASTO: return ruokalinjastoAktiiviAika/linjasto;  //Ruokalinjaston KESKIMÄÄRÄINEN aika kokonaisuudessaan
			
		case KASSA: 		return kassaAktiiviAika/kassa; //Kassojen KESKIMÄÄRÄINEN aika kokonaisuudessaan
			
		case RUOKASALI: 	return getKeskimaarainenRuokasaliAika();
			
		case KAIKKI: 		return (kassaAktiiviAika+ruokalinjastoAktiiviAika)/(kassa + linjasto + 1);	
			
	}
		return 0;
	
		
	}


	/**
	 * This method return the served students of the service point/s.
	 * @param type (Service points)
	 * @return the served students of the service point/s.
	 */
	public int getPalvellutAsiakkaat_C(Palvelupisteet type) {
		
		int ruokalinjastoAsiakkaat = 0;
		int kassaAsiakkaat = 0;
		
		for(int i = 0; i < linjasto; i++) {
			
			ruokalinjastoAsiakkaat += palvelupisteet[i].getPalvellutAsiakkaat();	
			
		}
		
		for(int i = linjasto; i < (linjasto + kassa); i++) {
			
			kassaAsiakkaat += palvelupisteet[i].getPalvellutAsiakkaat();	
			
		}
		
		
		switch (type){
		
		case RUOKALINJASTO: return ruokalinjastoAsiakkaat;  //Ruokalinjaston KESKIMÄÄRÄINEN aika kokonaisuudessaan
			
		case KASSA: 		return kassaAsiakkaat; //Kassojen KESKIMÄÄRÄINEN aika kokonaisuudessaan
			
		case RUOKASALI: 	return 0;
			
		case KAIKKI: 		return asiakaslista.size()-1;	
			
	}
		return 0;
	}


	
	
	public void setPalvellutAsiakkaat_C(int palvellutAsiakkaat_C) {
		
		this.palvellutAsiakkaat_C = palvellutAsiakkaat_C;
		
	}


	/**
	 * This method returns the simulation time.
	 * @return the simulation time.
	 */
	public double getKokonaisAika_T() {
		
		return Kello.getInstance().getAika();
	}
	
	/**
	 * This method returns simulation's end time.
	 * @return simulation's end time.
	 */
	public double getEndTime() {
		
		return Simulaattori.getEndTime();
		
	}


	public void setKokonaisAika_T(double kokonaisAika_T) {
		
		this.kokonaisAika_T = kokonaisAika_T;
		
	}


	/**
	 * This method calculate the utilization rate of the service point/s.
	 * @param type (Service points)
	 * @return utilization rate of the service point/s.
	 */
	public double getKayttoaste_U(Palvelupisteet type) {		//Palauttaa palvelupisteen käyttöaste B/T
	
		return (getAktiiviAika_B(type) / getKokonaisAika_T());
		
	}
	
	/**
	 * This method calculate the average of the utilization rate of the service point/s.
	 * @param type (Service points)
	 * @return the average of utilization rate of the service point/s.
	 */
	public double getKeskiKayttoaste(Palvelupisteet type) {
				
		return (getAktiiviAika_B(type) / Simulaattori.getEndTime());
	}
	
	/**
	 * This method calculate the throughput of the service point/s.
	 * @param type (Service points)
	 * @return the throughput value of the service point/s.
	 */
	public double getSuoritusteho_X(Palvelupisteet type) {		// palauttaa Throughput tai suoritusteho. C/T
		
		return (double) (getPalvelupisteenPalvelematAsiakkaat(type) / getKokonaisAika_T());
		
	}
	
	/**
	 * This method calculates how long is the waiting time of the students.
	 * @return the waiting time of the students during the simulation.
	 */
	public double getKeskimaarainenJonotusAika() { 
		
		double jonotusAika = 0;
		
		jonotusAika = getkeskimaarainenLapiMenoAika() - getAsiakkaidenPalveluAika_S();
		
		return jonotusAika;
	}
	
	/**
	 * This method calculates how long is the service time of each service point.
	 * @return  the service time of each service point.
	 */
	public double getAsiakkaidenPalveluAika_S() {		// palauttaa palveluaika tai Service time B/C
		
		double ruoka = getAktiiviAika_B(Palvelupisteet.RUOKALINJASTO);
		double kassa = getAktiiviAika_B(Palvelupisteet.KASSA);
		double ruokasali = getAktiiviAika_B(Palvelupisteet.RUOKASALI);
		
		double kaikki = ruoka + kassa + ruokasali;
		
		//return kaikki/asiakaslista.get(asiakaslista.size() - 1).getId();
		//vaihdetaan tarvitaessa kun halutaan selvitää yhden palvelupisteen service time
		return kaikki/asiakaslista.size()-1;
	}
	
	/**
	 *  This method calculates the response time.
	 * @return the response time.
	 */
	public double getResponseTime_R() {
		
		return (double) (getKeskimaarainenJonotusAika() / getPalvellutAsiakkaat_C(Palvelupisteet.KAIKKI));
		
	}
	
	/**
	 * This method calculates how many students have been served.
	 * @param type (Service points)
	 * @return how many students have been served.
	 */
	public int getPalvelupisteenPalvelematAsiakkaat(Palvelupisteet type) {
		
		int asiakkaat = 0;
		
		int ruokaAsiakkaat = 0;
		int kassaAsiakkaat = 0;
		int ruokalaAsiakkaat = 0;
		
		for(int i = 0; i < linjasto; i++) {
			
			ruokaAsiakkaat += palvelupisteet[i].getPalvellutAsiakkaat();
			
		}
		
		for(int i = linjasto; i < (linjasto + kassa); i++) {
			
			kassaAsiakkaat += palvelupisteet[i].getPalvellutAsiakkaat();
			
		}
		
		for (int i = (linjasto + kassa); i < (linjasto + kassa + 1); i++) {
			
			ruokalaAsiakkaat += palvelupisteet[i].getPalvellutAsiakkaat();
			
		}
		 
		switch (type){
		
		case RUOKALINJASTO: asiakkaat = ruokaAsiakkaat;
			break;
		case KASSA: 		asiakkaat = kassaAsiakkaat;
			break;
		case RUOKASALI: 	asiakkaat = 0;
			break;
		case KAIKKI: 		asiakkaat = ruokaAsiakkaat + kassaAsiakkaat + ruokalaAsiakkaat;
		    break;
		}
		
		return asiakkaat;
		
		
		
	}
	
	/**
	 * This method calculates how long the queue of the service point/s.
	 * @param type (Service points)
	 * @return the long of the queue of the service point/s.
	 */
	public int getJononpituus(Palvelupisteet type) {
		
		int ruokajono = 0;
		int kassajono = 0;
		
		
		for(int i = 0; i < linjasto; i++) {
			
			ruokajono += palvelupisteet[i].getJononKoko();
			
		}
		
		for(int i = linjasto; i < (linjasto + kassa); i++) {
			
			kassajono += palvelupisteet[i].getJononKoko();
			
		}
		
		if(ruokaMaxJono < ruokajono) {
			
			ruokaMaxJono = ruokajono;
			
		}
		
		if(kassaMaxjono < kassajono) {
			
			kassaMaxjono = kassajono;
			
		}
		
		switch (type){
		
		case RUOKALINJASTO: return ruokajono;

		case KASSA: 		return kassajono;

		case RUOKASALI: 	return 0;

		case KAIKKI: 		return ruokajono + kassajono;

		}
		
		return 0;
			
	}
	
	/**
	 * This method return how long is the longest cashier's queue is.
	 * @return how long is the longest cashier's queue is during the simulation.
	 */
	public int getKassaMaxJono() {
		
		return kassaMaxjono;
		
	}
	
	/**
	 *  This method return how long is the longest food queue is.
	 * @return the longest food queue.
	 */
	public int getRuokalinjastoMaxJono() {
		
		return ruokaMaxJono;
		
	}
	
	/**
	 * This method returns the longest queue of the service point/s during the simulation. 
	 * @param type (Service points)
	 * @return the longest queue of the service point/s during the simulation.
	 */
	public int getMaXJononpituus(Palvelupisteet type) {
		
		int ruokajono = 0;
		int kassajono = 0;
		
		for(int i = 0; i < linjasto; i++) {
			
			ruokajono += palvelupisteet[i].getMaksimiJononKoko();
			
		}
		
		for(int i = linjasto; i < (linjasto + kassa); i++) {
			
			kassajono += palvelupisteet[i].getMaksimiJononKoko();
			
		}

		
		switch (type){
		
		case RUOKALINJASTO: return ruokajono;

		case KASSA: 		return kassajono;

		case RUOKASALI: 	return 0;

		case KAIKKI: 		return ruokajono + kassajono;

		}
		
		return 0;
			
	}
	
	/**
	 * The method return the average of the lead time.
	 * @return the average of the lead time.
	 */
	public double getkeskimaarainenLapiMenoAika() {
		
		double lapimeno = 0;
		
		for(Asiakas asiakas: asiakaslista) {
			
			lapimeno += asiakas.getLapimenoAika_Ri();
			
		}
		//asiakaslista.get(asiakaslista.size() - 1).getKeskimaarainenLapimenoAika()
		
		return (lapimeno / asiakaslista.size()-1);
		
	}
	
	/**
	 * This method return the list of the students arrive to the system.
	 * @return the list of the students arrive to the system.
	 */
	public ArrayList<Asiakas> getAsiakasLista() {
		
		return asiakaslista;
		
		
	}
	
	/**
	 * This method sets how many students arrive to the system as a list.
	 * @param list (Students)
	 */
	public static void setAsiakasLista(ArrayList<Asiakas> list) {
		asiakaslista = list;
	}
	
	/**
	 * To string.
	 *
	 * @return the calculated data
	 */
	public String toString() {
		
		return "Ruokalinjaston keskimääräinen aktiiviaika: " + (getAktiiviAika_B(Palvelupisteet.RUOKALINJASTO)/1000) + " sekuntia" + " Kokonaisajasta " + (getKokonaisAika_T()/1000)+ " sekuntia." +  "\n"+
			   "Kassan keskimääräinen aktiiviaika: " + (getAktiiviAika_B(Palvelupisteet.KASSA)/1000) + " sekuntia" + " Kokonaisajasta " + (getKokonaisAika_T()/1000) + " sekuntia." +  "\n"+
			   "Ruokasalin aktiiviaika: " + (getAktiiviAika_B(Palvelupisteet.RUOKASALI)/1000)+ " sekuntia" + " Kokonaisajasta " + (getKokonaisAika_T()/1000) + " sekuntia." +  "\n" +
			   "Asiakkaiden keskimääräinen palveluaika kaikki pisteet yhteensä: " + getAsiakkaidenPalveluAika_S()/1000 + " sekuntia." + "\n" +
			   "Pisin jono kassoille oli: " + getMaXJononpituus(Palvelupisteet.KASSA) + "\n" +
			   "Asiakkaiden keskimääräinen läpimenoaika: " + asiakaslista.get(asiakaslista.size() - 1).getKeskimaarainenLapimenoAika()/1000 + " sekuntia." + "\n" +
			   "Kaikkien pisteiden aktiiviaika: " + (getAktiiviAika_B(Palvelupisteet.KAIKKI)/1000) + " sekuntia." + "\n" + 
			   // HUOM poista -2, kun ruokala kunnolla toteutettu! Muuten aktiiviajan laskenta hirtt�� ittens�
			   "Kaikkien pisteiden keskimääräinen aktiiviaika: " + (getAktiiviAika_B(Palvelupisteet.KAIKKI)/(palvelupisteet.length-2)/1000) + " sekuntia.";
		
	}
	
}
