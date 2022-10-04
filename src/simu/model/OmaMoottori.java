package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import simu.model.Tulokset.Palvelupisteet;
import java.util.Random;


import controller.IKontrolleriMtoV;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	private RyhmaSaapumisProsessi ryhmaSaapumisProsessi;
	private Tulokset tulokset = Tulokset.getInstance();
	private int linjastot;
	private int kassat;
	private double porrastusAika;
	private int ryhmienMaara;
	private int ryhmaNumero = 1;

	
	public OmaMoottori(IKontrolleriMtoV kontrolleri){
		
		super(kontrolleri);

	}
	
	public void setSimulointiLahtoArvot(int ruokalinja, int rekisteri, int asiakkaat, int ryhmienMaara, double porrastusMaara) {
			
		this.linjastot = ruokalinja;
		this.kassat = rekisteri;
		this.ryhmienMaara = ryhmienMaara;
		final int ryhmaKoko = asiakkaat/ryhmienMaara;
		this.porrastusAika = (long)(porrastusMaara * 60000);
		

		palvelupisteet = new Palvelupiste[ruokalinja + rekisteri + 1];
	
		for (int i = 0; i < ruokalinja; i++) {
			
			palvelupisteet[i]=new Palvelupiste(new Normal(30000,5000, (1 + i + (long)Math.random() * 8765)), tapahtumalista, TapahtumanTyyppi.DEP1);
			
		}
		
		for (int i = ruokalinja; i < ruokalinja + rekisteri; i++) {
			
			palvelupisteet[i]=new Palvelupiste(new Normal(10000, 3000, (1 + i +(long)Math.random() * 5678)), tapahtumalista, TapahtumanTyyppi.DEP2);
			
		}
		
		palvelupisteet[ruokalinja+rekisteri]=new Palvelupiste(new Normal(1, 1), tapahtumalista, TapahtumanTyyppi.DEP3);	
		ryhmaSaapumisProsessi = new RyhmaSaapumisProsessi(tapahtumalista,TapahtumanTyyppi.RYHMASAAPUMINEN,porrastusAika);
		saapumisprosessi = new Saapumisprosessi(new Negexp(1000,1000), tapahtumalista, TapahtumanTyyppi.ARR1,ryhmaKoko);
		tulokset.setPalvelupiste(palvelupisteet, ruokalinja, rekisteri);
		
		
	}
	
	
	@Override
	protected void alustukset() {
		
		saapumisprosessi.generoiSeuraava(); 
		ryhmaSaapumisProsessi.generoiSeuraava();
		
	}
	
	private boolean onkoVielaRyhmia() {
		
		ryhmaNumero++;	
		return ryhmaNumero < ryhmienMaara;
		
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		int direction = 0;
		int source;

		
		// switch tapahtuman tyypin mukaan, aka miss� vaiheessa kuljetaan, oikea jono l�ytyy asiakkaaseen tallennetulla "direction" muuttujalla (palvelupiste-taulun indeksi)
		
		switch (t.getTyyppi()){
		
				// ARR1 = uusi asiakas. Luodaan asiakas ja laitetaan se random linjaston jonoon
		
			case ARR1:
				
						Asiakas asiakas = new Asiakas();
						direction = new Random().nextInt(linjastot);
						asiakas.setDirection(direction);
						palvelupisteet[direction].lisaaJonoon(asiakas);	
						tulokset.lisaaAsiakasTulosListalle(asiakas);
						saapumisprosessi.generoiSeuraava();	
						kontrolleri.visualisoiAsiakas();	
								
				break;
				
			case RYHMASAAPUMINEN:	
				
						saapumisprosessi.setUusiRyhma();
						saapumisprosessi.generoiSeuraava();	
	
						if(onkoVielaRyhmia()) {
						
							ryhmaSaapumisProsessi.generoiSeuraava();
						
						}
				break;
				
				// DEP1 = asiakas siirtyy ruokalinjastosta random kassalle.
				
			case DEP1: 			
						
					   source = t.getDirection();
					   a = palvelupisteet[source].otaJonosta();
					   direction = new Random().nextInt() % kassat + linjastot;
					   a.setDirection(direction);
				   	   palvelupisteet[direction].lisaaJonoon(a);
						   	   
				break;
				
				// DEP2 = Asiakas siirtyy kassalta ruokalaan
				
			case DEP2: 
				
					   source = t.getDirection();
					   a = palvelupisteet[source].otaJonosta();
				   	   palvelupisteet[linjastot + kassat].lisaaJonoon(a); 
				   	   
				break;  
				
				// Asiakas heivataan helvettiin simulaatiosta ja loppuaika kirjataan.
				
			case DEP3: 
				
				       a = palvelupisteet[linjastot + kassat].otaJonosta();
					   a.setPoistumisaika(Kello.getInstance().getAika());
			           a.raportti();
			           kontrolleri.naytaLapiPaasseetAsiakkaat();
			           kontrolleri.naytaAsiakkaanLapimenoAika(tulokset.getLapiMenoAika());
		}
			
		kontrolleri.naytaPisinJonoKassoille(tulokset.getMaXJononpituus(Palvelupisteet.KASSA));	
		kontrolleri.naytaRuokaJononPituus(tulokset.getJononpituus(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaKassojenActive(tulokset.getAktiiviAika_B(Palvelupisteet.KASSA));
		kontrolleri.naytaAsiakkaidenKeskimaarainenPalveluaika(tulokset.getAsiakkaidenPalveluAika_S());
		kontrolleri.naytaPisinJonoRuokalinjastolle(tulokset.getMaXJononpituus(Palvelupisteet.RUOKALINJASTO));
	   	kontrolleri.naytaKassaJononPituus(tulokset.getJononpituus(Palvelupisteet.KASSA));	
		kontrolleri.naytaRuokalinjastonAktiiviaika(tulokset.getAktiiviAika_B(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaKellonAika(Kello.getInstance().getAika());
			
	}

	
	@Override
	protected void tulokset() {	
		
		System.out.println("\n" + "Simulointi päättyi kello " + (Kello.getInstance().getAika()/1000) + " sekuntia." + "\n");
		
		System.out.println(tulokset);
		
		System.out.println(Asiakas.kikkeli + " kikkeliä");
		
		//System.out.println("W = " + tulokset.getOdotusAika_W());
		kontrolleri.naytaKeskiOdotusAika(tulokset.getOdotusAika_W()); // Jottain pittee tehä // se on tehty :D
		
		//kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());
			
		
		
	}
	
}
