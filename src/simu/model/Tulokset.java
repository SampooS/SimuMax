package simu.model;

import java.io.Serializable;
import java.util.ArrayList;

import simu.framework.Kello;
import testi.Simulaattori;
import view.Alkuarvot;

public class Tulokset implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int asiakasMaara_A; // A arrival count
	private double aktiiviAika_B; // B Busy time
	public int palvellutAsiakkaat_C = 0; // C completed count
	public double kokonaisAika_T; // T simuloinnin aika 
	//Asiakas [] asiakasTaulukko;
	public static ArrayList<Asiakas> asiakaslista = new ArrayList<>();
	public Palvelupiste [] palvelupisteet;
	private ArrayList<Double> ruokasalilista = new ArrayList<>();
	public int ruokalinja;
	public int kassat;
	
	private int kassaMaxjono;
	private int ruokaMaxJono;
	
	private int ruokaAsiakas;
	
	public enum Palvelupisteet{RUOKALINJASTO, KASSA, RUOKASALI, KAIKKI}
	
	private static Tulokset instanssi;
	
	public static Tulokset getInstance() {
		
		
		if (instanssi == null){
			instanssi = new Tulokset();	
		}
		return instanssi;
	}
	
	
	
	public void setPalvelupiste(Palvelupiste [] palvelupisteet, int ruokalinja, int kassat) {
		
		this.palvelupisteet = palvelupisteet;
		this.ruokalinja = ruokalinja;
		this.kassat = kassat;
		this.ruokaAsiakas = 0;
		
	}
	
	
	public void lisaaAsiakasTulosListalle(Asiakas asiakas) {
		
		asiakaslista.add(asiakas);
		
	}
	



	public int getAsiakkaat_A() {
		
		return asiakasMaara_A;
	}


	public void setAsiakkaat_A(int asiakasMaara_A) {
		this.asiakasMaara_A = asiakasMaara_A;
	}
	
	public void setRuokaAsiakas() {
		
		
		ruokaAsiakas++;
		
	}
	
	
	public int  getRuokaAsiakas() {
		
		return ruokaAsiakas;
		
	}
	
	public void setRuokasaliAika(double maara) {
		
		ruokasalilista.add(maara);
		
	}
	
	public double getKeskimaarainenRuokasaliAika() {
		
		double aika = 0;
		
		for(Double saliaika: ruokasalilista) {
			
			aika += saliaika;
			
		}
		
		return aika;
		
		
	}


	public double getAktiiviAika_B(Palvelupisteet type) {
		
		double ruokalinjastoAktiiviAika = 0;
		double kassaAktiiviAika = 0;
		
		for(int i = 0; i < this.ruokalinja; i++) {
			
			ruokalinjastoAktiiviAika += palvelupisteet[i].getActiveTime();	
			
		}
		
		for(int i = this.ruokalinja; i < (this.ruokalinja + this.kassat); i++) {
			
			kassaAktiiviAika += palvelupisteet[i].getActiveTime();	
			
		}
		
		switch (type){
		
		case RUOKALINJASTO: return ruokalinjastoAktiiviAika/this.ruokalinja;  //Ruokalinjaston KESKIMÄÄRÄINEN aika kokonaisuudessaan
			
		case KASSA: 		return kassaAktiiviAika/this.kassat; //Kassojen KESKIMÄÄRÄINEN aika kokonaisuudessaan
			
		case RUOKASALI: 	return getKeskimaarainenRuokasaliAika();
			
		case KAIKKI: 		return (kassaAktiiviAika+ruokalinjastoAktiiviAika)/(kassat + ruokalinja + 1);	
			
	}
		return 0;
	
		
	}


	public int getPalvellutAsiakkaat_C(Palvelupisteet type) {
		
		int ruokalinjastoAsiakkaat = 0;
		int kassaAsiakkaat = 0;
		
		for(int i = 0; i < this.ruokalinja; i++) {
			
			ruokalinjastoAsiakkaat += palvelupisteet[i].getPalvellutAsiakkaat();	
			
		}
		
		for(int i = this.ruokalinja; i < (this.ruokalinja + this.kassat); i++) {
			
			kassaAsiakkaat += palvelupisteet[i].getPalvellutAsiakkaat();	
			
		}
		
		
		switch (type){
		
		case RUOKALINJASTO: return ruokalinjastoAsiakkaat;  //Ruokalinjaston KESKIMÄÄRÄINEN aika kokonaisuudessaan
			
		case KASSA: 		return kassaAsiakkaat; //Kassojen KESKIMÄÄRÄINEN aika kokonaisuudessaan
			
		case RUOKASALI: 	return 0;
			
		case KAIKKI: 		return asiakaslista.size();	
			
	}
		return 0;
	}


	public void setPalvellutAsiakkaat_C(int palvellutAsiakkaat_C) {
		
		this.palvellutAsiakkaat_C = palvellutAsiakkaat_C;
		
	}


	public double getKokonaisAika_T() {
		
		return Kello.getInstance().getAika();
		//return kokonaisAika_T; //vaihdan sen testia varten
	}
	
	public double getEndTime() {
		
		return Simulaattori.getEndTime();
		
	}


	public void setKokonaisAika_T(double kokonaisAika_T) {
		
		this.kokonaisAika_T = kokonaisAika_T;
		
	}


	public double getKayttoaste_U(Palvelupisteet type) {		//Palauttaa palvelupisteen käyttöaste B/T
	
		return (getAktiiviAika_B(type) / getKokonaisAika_T());
		
	}
	
	public double getKeskiKayttoaste(Palvelupisteet type) {
				
		return (getAktiiviAika_B(type) / Simulaattori.getEndTime());
	}
	
	public double getSuoritusteho_X(Palvelupisteet type) {		// palauttaa Throughput tai suoritusteho. C/T
		
		return (double) (getPalvelupisteenPalvelematAsiakkaat(type) / getKokonaisAika_T());
		
	}
	
	
	public double getKeskimaarainenJonotusAika() { 
		
		double jonotusAika = 0;
		
		jonotusAika = getkeskimaarainenLapiMenoAika() - getAsiakkaidenPalveluAika_S();
		
		return jonotusAika;
	}
	
	public double getAsiakkaidenPalveluAika_S() {		// palauttaa palveluaika tai Service time B/C
		
		double ruoka = getAktiiviAika_B(Palvelupisteet.RUOKALINJASTO);
		double kassa = getAktiiviAika_B(Palvelupisteet.KASSA);
		double ruokasali = getAktiiviAika_B(Palvelupisteet.RUOKASALI);
		
		double kaikki = ruoka + kassa + ruokasali;
		
		//return kaikki/asiakaslista.get(asiakaslista.size() - 1).getId();
		//vaihdetaan tarvitaessa kun halutaan selvitää yhden palvelupisteen service time
		return kaikki/asiakaslista.size();
	}
	
	public double getResponseTime_R() {
		
		return (double) (getKeskimaarainenJonotusAika() / getPalvellutAsiakkaat_C(Palvelupisteet.KAIKKI));
		
	}
	
	
	public int getPalvelupisteenPalvelematAsiakkaat(Palvelupisteet type) {
		
		int asiakkaat = 0;
		
		int ruokaAsiakkaat = 0;
		int kassaAsiakkaat = 0;
		int ruokalaAsiakkaat = 0;
		
		for(int i = 0; i < this.ruokalinja; i++) {
			
			ruokaAsiakkaat += palvelupisteet[i].getPalvellutAsiakkaat();
			
		}
		
		for(int i = this.ruokalinja; i < (this.ruokalinja + this.kassat); i++) {
			
			kassaAsiakkaat += palvelupisteet[i].getPalvellutAsiakkaat();
			
		}
		
		for (int i = (ruokalinja + kassat); i < (ruokalinja + kassat + 1); i++) {
			
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
	
	public int getJononpituus(Palvelupisteet type) {
		
		
		int ruokajono = 0;
		int kassajono = 0;
		
		
		for(int i = 0; i < ruokalinja; i++) {
			
			ruokajono += palvelupisteet[i].getJononKoko();
			
		}
		
		for(int i = ruokalinja; i < (ruokalinja + kassat); i++) {
			
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
	
	
	public int getKassaMaxJono() {
		
		return kassaMaxjono;
		
	}
	
	public int getRuokalinjastoMaxJono() {
		
		return ruokaMaxJono;
		
	}
	
	public int getMaXJononpituus(Palvelupisteet type) {
		
		int ruokajono = 0;
		int kassajono = 0;
		
		for(int i = 0; i < ruokalinja; i++) {
			
			ruokajono += palvelupisteet[i].getMaksimiJononKoko();
			
		}
		
		for(int i = ruokalinja; i < (ruokalinja + kassat); i++) {
			
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
	
	public double getPorrastusMaara() {
		
		
		return Alkuarvot.getInstance().getRyhmienMaara() * Alkuarvot.getInstance().getPorrastusMaara();
		
	}
	
	
	public double getkeskimaarainenLapiMenoAika() { //keskimääräinen arvo....toimii näin Mohammed, johtuen "raportti metodista, ei tarvitse koskea.
		
		double lapimeno = 0;
		
		for(Asiakas asiakas: asiakaslista) {
			
			lapimeno += asiakas.getLapimenoAika_Ri();
			
		}
		//asiakaslista.get(asiakaslista.size() - 1).getKeskimaarainenLapimenoAika()
		
		return (lapimeno / asiakaslista.size());
		
	}
	
	public ArrayList<Asiakas> getAsiakasLista() {
		
		return asiakaslista;
		
		
	}
	
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
