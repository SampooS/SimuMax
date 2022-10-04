package simu.model;

import java.io.Serializable;
import java.util.ArrayList;

import simu.framework.Kello;

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
	public int ruokalinja;
	public int kassat;
	
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
		
	}
	
	
	public void lisaaAsiakasTulosListalle(Asiakas asiakas) {
		
		asiakaslista.add(asiakas);
		
	}
	
	public double getAsiakkaidenPalveluAika_S() {		// palauttaa palveluaika tai Service time B/C
		
		double ruoka = getAktiiviAika_B(Palvelupisteet.RUOKALINJASTO);
		double kassa = getAktiiviAika_B(Palvelupisteet.KASSA);
		double kaikki = ruoka + kassa;
		
		//return kaikki/asiakaslista.get(asiakaslista.size() - 1).getId();
		//vaihdetaan tarvitaessa kun halutaan selvitää yhden palvelupisteen service time
		return getAktiiviAika_B(Palvelupisteet.KAIKKI)/getPalvellutAsiakkaat_C();
	}


	public int getAsiakkaat_A() {
		
		return asiakasMaara_A;
	}


	public void setAsiakkaat_A(int asiakasMaara_A) {
		this.asiakasMaara_A = asiakasMaara_A;
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
			
		case RUOKASALI: 	return palvelupisteet[this.ruokalinja + this.kassat].getActiveTime();
			
		case KAIKKI: 		return kassaAktiiviAika+ruokalinjastoAktiiviAika;	
			
	}
		return 0;
	
		
	}


	public int getPalvellutAsiakkaat_C() {
		
		if( palvellutAsiakkaat_C == 0 ) {
			
			palvellutAsiakkaat_C = asiakaslista.size();
			
		}
		
		return palvellutAsiakkaat_C;
	}


	public void setPalvellutAsiakkaat_C(int palvellutAsiakkaat_C) {
		
		this.palvellutAsiakkaat_C = palvellutAsiakkaat_C;
		
	}


	public double getKokonaisAika_T() {
		
		return Kello.getInstance().getAika();
		//return kokonaisAika_T; //vaihdan sen testia varten
	}


	public void setKokonaisAika_T(double kokonaisAika_T) {
		
		this.kokonaisAika_T = kokonaisAika_T;
		
	}


	public double getKayttoaste_U() {		//Palauttaa palvelupisteen käyttöaste B/T
	
		return (getAktiiviAika_B(Palvelupisteet.KAIKKI) / getKokonaisAika_T());
		
	}
	
	public double getSuoritusteho_X() {		// palauttaa Throughput tai suoritusteho. C/T
		
		return (double) (getPalvellutAsiakkaat_C() / getKokonaisAika_T());
		
	}
	
	
	public double getOdotusAika_W() { 		// Waiting time W läpimenoaikojen summa. ????????????????????????????????
		
		
		double summa = 0.0;
		
		
		
		for (int i = 0; i < asiakaslista.size(); i++) {
			
			summa += (asiakaslista.get(i).getLapimenoAika_Ri());
			
		}
		
		
		return (summa/1000);
	}
	
	public double getResponseTime_R() {
		
		return (double) (getOdotusAika_W() / getPalvellutAsiakkaat_C())/1000;
		
	}
	
	public int getMaXJononpituus(Palvelupisteet type) {
		
		int jono = 0;
		
		int ruokajono = 0;
		int kassajono = 0;
		
		for(int i = 0; i < this.ruokalinja; i++) {
			
			ruokajono += palvelupisteet[i].getMaksimiJononKoko();
			
		}
		
		for(int i = this.ruokalinja; i < (this.ruokalinja + this.kassat); i++) {
			
			kassajono += palvelupisteet[i].getMaksimiJononKoko();
			
		}

		
		switch (type){
		
		case RUOKALINJASTO: jono = ruokajono;
			break;
		case KASSA: 		jono = kassajono;
			break;
		case RUOKASALI: 	jono = 0;
			break;
		case KAIKKI: 		jono = ruokajono + kassajono;
		    break;
		}
		
		return jono;
		
		
	}
	
	public int getJononpituus(Palvelupisteet type) {
		
		int jono = 0;
		
		int ruokajono = 0;
		int kassajono = 0;
		
		for(int i = 0; i < this.ruokalinja; i++) {
			
			ruokajono += palvelupisteet[i].getJononKoko();
			
		}
		
		for(int i = this.ruokalinja; i < (this.ruokalinja + this.kassat); i++) {
			
			kassajono += palvelupisteet[i].getJononKoko();
			
		}


		
		switch (type){
		
		case RUOKALINJASTO: jono = ruokajono;
			break;
		case KASSA: 		jono = kassajono;
			break;
		case RUOKASALI: 	jono = 0;
			break;
		case KAIKKI: 		jono = ruokajono + kassajono;
		    break;
		}
		
		return jono;
		
		
	}
	
	
	public double getLapiMenoAika() { //keskimääräinen arvo
		
		//return asiakaslista.get(asiakaslista.size() - 1).getKeskimaarainenLapimenoAika();
		
		return asiakaslista.get(asiakaslista.size() - 1).getKeskimaarainenLapimenoAika();
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
