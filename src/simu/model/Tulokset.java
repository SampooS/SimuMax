package simu.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import simu.framework.Kello;
import testi.Simulaattori;

public class Tulokset implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//public int asiakasMaara_A; // A arrival count
	private double aktiiviAika_B; // B Busy time
	public int palvellutAsiakkaat_C = 0; // C completed count
	public double kokonaisAika_T; // T simuloinnin aika 
	//Asiakas [] asiakasTaulukko;
	public static ArrayList<Asiakas> asiakaslista = new ArrayList<>();
	public Palvelupiste [] palvelupisteet;
	private ArrayList<Double> ruokasalilista = new ArrayList<>();
	public int ruokalinja;
	public int kassat;
	public int ryhmat;
	public double porrastusaika;
	public int asiakkaat;
	public int saapuneetasiakkaat;
	public int poistuneetasiakkaat;
	
	private int kassaMaxjono;
	private int ruokaMaxJono;
	
	private int ruokaAsiakas;
	
	private int linjasto;
	private int kassa;
	
	private Alkuarvot alkuarvot;
	
	public ResultSet dummy;
	
	public enum Palvelupisteet{RUOKALINJASTO, KASSA, RUOKASALI, KAIKKI}
	
	private static Tulokset instanssi;
	
	public static Tulokset getInstance() {
		
		if (instanssi == null){
			instanssi = new Tulokset();	
		}
		return instanssi;
	}
	
	public void setDummy(int i) throws SQLException {
		this.dummy = DBAccessObject.lataaDummy(i);
	}
	
	
	
	public void alustaTulokset() {	
		
		asiakaslista.clear();
		asiakaslista = new ArrayList<>();
		ruokasalilista.clear();
		ruokasalilista = new ArrayList<>();
		palvelupisteet = new Palvelupiste[0];
		kassaMaxjono = 0;
		ruokaMaxJono = 0;
		
	}
	
	
	public void setAlkuarvot(int ryhmat, double porrastusaika, int asiakkaat, int linjasto, int kassa) {
		
		this.ryhmat = ryhmat;
		this.porrastusaika = porrastusaika;
		this.asiakkaat = asiakkaat;
		this.linjasto = linjasto;
		this.kassa = kassa;
		
	}
	
	public void setPalvelupisteet(ArrayList<Palvelupiste> pisteet) {
		
		this.palvelupisteet = new Palvelupiste[pisteet.size()];
		for (int i = 0; i < pisteet.size()-1; i++) {
			palvelupisteet[i] = pisteet.get(i);
		}
		
	}
	
	public void setRyhmat(int ryhmat) {
		this.ryhmat = ryhmat;
	}
	
	
	public int getRyhmat() {
		
		return ryhmat;
	}
	
	public void setPorrastusAika(int porrastusaika) {
		this.porrastusaika = porrastusaika;
	}
	
	public double getPorrastusAika() {
		
		return porrastusaika;
	}
	
	public void setAsiakkaat(int asiakkaat) {
		
		this.asiakkaat = asiakkaat;
	}
	
	public int getAsiakkaat() {
		
		return asiakkaat;
	}
	
	public void setSaapuneetasiakkaat(int maara) {
		
		this.saapuneetasiakkaat = 0;
		saapuneetasiakkaat = maara;
		
	}
	
	public void setPoistuneetasiakkaat(int maara) {
		
		this.poistuneetasiakkaat = 0;
		poistuneetasiakkaat = maara;
		
	}
	
	public int getSaapuneetasiakkaat() {
		
		return saapuneetasiakkaat;
		
	}
	
	public int getPoistuneetasiakkaat() {
		
		return poistuneetasiakkaat;
		
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
	



	/*
	public int getAsiakkaat_A() {
		
		return asiakaslista.size();
	}


	public void setAsiakkaat_A(int asiakasMaara_A) {
		this.asiakasMaara_A = asiakasMaara_A;
	}
	*/
	
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


	public double getKokonaisAika_T() {
		
		return Kello.getInstance().getAika();
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
		return kaikki/asiakaslista.size()-1;
	}
	
	public double getResponseTime_R() {
		
		return (double) (getKeskimaarainenJonotusAika() / getPalvellutAsiakkaat_C(Palvelupisteet.KAIKKI));
		
	}
	
	
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
	
	
	public int getKassaMaxJono() {
		
		return kassaMaxjono;
		
	}
	
	public int getRuokalinjastoMaxJono() {
		
		return ruokaMaxJono;
		
	}
	
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
	

	
	
	public double getkeskimaarainenLapiMenoAika() { //keskimääräinen arvo....toimii näin Mohammed, johtuen "raportti metodista, ei tarvitse koskea.
		
		double lapimeno = 0;
		
		for(Asiakas asiakas: asiakaslista) {
			
			lapimeno += asiakas.getLapimenoAika_Ri();
			
		}
		//asiakaslista.get(asiakaslista.size() - 1).getKeskimaarainenLapimenoAika()
		
		return (lapimeno / asiakaslista.size()-1);
		
	}
	
	public ArrayList<Asiakas> getAsiakasLista() {
		
		return asiakaslista;
		
		
	}
	
	public static void setAsiakasLista(ArrayList<Asiakas> list) {
		asiakaslista = list;
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
