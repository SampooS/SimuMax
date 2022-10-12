package simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Generator;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import simu.model.Tulokset.Palvelupisteet;
import testi.Simulaattori;

import java.util.ArrayList;
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
	private ArrayList<Tapahtuma> tapahtuneet;
	private int linjastodirection;
	private int kassadirection;
	private int saapuminen = 0;
	private int poistuminen = 0;

	
	public OmaMoottori(IKontrolleriMtoV kontrolleri){
		
		super(kontrolleri);

	}
	
	public void setSimulointiLahtoArvot(int ruokalinja, int rekisteri, int asiakkaat, int ryhmienmaara, double porrastusMaara) {
			
		this.linjastot = ruokalinja * 10; //Testi........
		this.kassat = rekisteri;
		this.ryhmienMaara = ryhmienmaara;
		final int ryhmaKoko = asiakkaat/ryhmienMaara;
		this.porrastusAika = (long)(porrastusMaara * 60000);
		this.tapahtuneet = new ArrayList<Tapahtuma>();
		this.linjastodirection = 0;
		this.kassadirection = linjastot;
		
		palvelupisteet = new Palvelupiste[linjastot + kassat + 1];
		
		tulokset = Tulokset.getInstance();
		tulokset.alustaTulokset();
		tulokset.setAlkuarvot(ryhmienMaara, porrastusMaara, asiakkaat,linjastot,kassat);
		tulokset.setPalvelupiste(palvelupisteet, linjastot, kassat);

		
		for (int i = 0; i < linjastot; i++) {
			
			palvelupisteet[i]=new Palvelupiste(new Normal(120000,60000, (1 + i + (long)Math.random() * 8765)), tapahtumalista, TapahtumanTyyppi.DEP1);
			
		}
		
		for (int i = linjastot; i < linjastot + kassat; i++) {
			
			palvelupisteet[i]=new Palvelupiste(new Normal(18000, 5000, (1 + i +(long)Math.random() * 5678)), tapahtumalista, TapahtumanTyyppi.DEP2);
			
		}
	
		
		
		palvelupisteet[linjastot+kassat]=new Palvelupiste(new Normal(1, 1), tapahtumalista, TapahtumanTyyppi.DEP3);	
		ryhmaSaapumisProsessi = new RyhmaSaapumisProsessi(tapahtumalista,TapahtumanTyyppi.RYHMASAAPUMINEN,porrastusAika);
		saapumisprosessi = new Saapumisprosessi(new Negexp(6000,3000), tapahtumalista, TapahtumanTyyppi.ARR1,ryhmaKoko);
		
		
		
		
	}
	
	
	@Override
	protected void alustukset() {
		
		Kello.getInstance().setAika(0);
		saapumisprosessi.generoiSeuraava(); 
		
		if(ryhmienMaara > 1) {
			
			ryhmaSaapumisProsessi.generoiSeuraava();
			
		}
		
		
	}
	
	private boolean onkoVielaRyhmia() {
		
		ryhmaNumero++;	
		return ryhmaNumero < ryhmienMaara;
		
	}
	
	public ArrayList<Tapahtuma> getTapahtumat() {
		return this.tapahtuneet;
	}
	
	private double getRuokasaliAika() {
		
		ContinuousGenerator generator = new Normal(900000, 300000);
		
		double saliaika = generator.sample();
		tulokset.setRuokasaliAika(saliaika);
		return saliaika;
		
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
						saapuminen++;
						kontrolleri.visualisoiAsiakas(saapuminen);
						tulokset.setSaapuneetasiakkaat(saapuminen);
						
						if(linjastodirection <= linjastot) {
							
							linjastodirection++;
							
						}else {
							
							
							linjastodirection = 0;
							
						}
								
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
				   	   
				   	   
				   	   if(kassadirection <= linjastot + kassat ) {
				   		   
				   		   kassadirection++;
				   		   
				   	   }else {
				   		   
				   		   kassadirection = linjastot;			   		   
				   		   
				   	   }
						   	   
				break;
				
				// DEP2 = Asiakas siirtyy kassalta ruokalaan
				
			case DEP2: 
				
					   source = t.getDirection();
					   a = palvelupisteet[source].otaJonosta();
				   	   palvelupisteet[linjastot + kassat].lisaaJonoon(a); 
				   	   tapahtuneet.add(t);// ..........................................................................................onko oikeassa kohdassa???
		   	     break;  
				
				// Asiakas heivataan helvettiin simulaatiosta ja loppuaika kirjataan.
				
			case DEP3: 
				
				       a = palvelupisteet[linjastot + kassat].otaJonosta();
					   a.setPoistumisaika(Kello.getInstance().getAika() + getRuokasaliAika());
			           a.raportti();
			           poistuminen++;
			           tulokset.setPoistuneetasiakkaat(poistuminen);
			           kontrolleri.naytaLapiPaasseetAsiakkaat(poistuminen);
			           
		           
	             break;
		}
			
		kontrolleri.naytaKellonAika(Kello.getInstance().getAika());
		kontrolleri.naytaRuokaJononPituus(tulokset.getJononpituus(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaPisinJonoRuokalinjastolle(tulokset.getRuokalinjastoMaxJono());
		kontrolleri.naytaKassaJononPituus(tulokset.getJononpituus(Palvelupisteet.KASSA));
		kontrolleri.naytaPisinJonoKassoille(tulokset.getKassaMaxJono());
		
		
		kontrolleri.naytaAsiakkaidenKeskimaarainenPalveluaika(tulokset.getAsiakkaidenPalveluAika_S());
		kontrolleri.naytaKeskiOdotusAika(tulokset.getKeskimaarainenJonotusAika());
        kontrolleri.naytaAsiakkaanLapimenoAika(tulokset.getkeskimaarainenLapiMenoAika());
        kontrolleri.setAsiakasChart(tulokset.getSaapuneetasiakkaat(), tulokset.getPoistuneetasiakkaat(),
        tulokset.getkeskimaarainenLapiMenoAika(), tulokset.getKeskimaarainenJonotusAika(), tulokset.getAsiakkaidenPalveluAika_S());
		
		kontrolleri.naytaRuokalinjastoAsiakkaatPalveltu(tulokset.getPalvelupisteenPalvelematAsiakkaat(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaRuokalinjastonAktiiviaika(tulokset.getAktiiviAika_B(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaRuokalinjastonKayttoaste(tulokset.getKayttoaste_U(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaRuokalinjastonKeskikayttoaste(tulokset.getKeskiKayttoaste(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaRuokalinjastonSuoritusteho(tulokset.getSuoritusteho_X(Palvelupisteet.RUOKALINJASTO));
		
		
		kontrolleri.naytaKassaAsiakkaatPalveltu(tulokset.getPalvelupisteenPalvelematAsiakkaat(Palvelupisteet.KASSA));
		kontrolleri.naytaKassaAktiiviaika(tulokset.getAktiiviAika_B(Palvelupisteet.KASSA));
		kontrolleri.naytaKassaKayttoaste(tulokset.getKayttoaste_U(Palvelupisteet.KASSA));
		kontrolleri.naytaKassaKeskikayttoaste(tulokset.getKeskiKayttoaste(Palvelupisteet.KASSA));
		kontrolleri.naytaKassaSuoritusteho(tulokset.getSuoritusteho_X(Palvelupisteet.KASSA));
		

		

			
	}
	
	@Override
	public void setLatausTulokset() {
		
		
		kontrolleri.naytaKellonAika(Simulaattori.getEndTime());
		
		kontrolleri.visualisoiAsiakas(tulokset.getSaapuneetasiakkaat());
		kontrolleri.naytaLapiPaasseetAsiakkaat(tulokset.getPoistuneetasiakkaat());
		
		kontrolleri.naytaRuokaJononPituus(tulokset.getJononpituus(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaPisinJonoRuokalinjastolle(tulokset.getMaXJononpituus(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaKassaJononPituus(tulokset.getJononpituus(Palvelupisteet.KASSA));
		kontrolleri.naytaPisinJonoKassoille(tulokset.getMaXJononpituus(Palvelupisteet.KASSA));
		
		
		kontrolleri.naytaAsiakkaidenKeskimaarainenPalveluaika(tulokset.getAsiakkaidenPalveluAika_S());
		kontrolleri.naytaKeskiOdotusAika(tulokset.getKeskimaarainenJonotusAika());
        kontrolleri.naytaAsiakkaanLapimenoAika(tulokset.getkeskimaarainenLapiMenoAika());
        kontrolleri.setAsiakasChart(tulokset.getSaapuneetasiakkaat(), tulokset.getPoistuneetasiakkaat(),
        tulokset.getkeskimaarainenLapiMenoAika(), tulokset.getKeskimaarainenJonotusAika(), tulokset.getAsiakkaidenPalveluAika_S());
        
        
		
		//kontrolleri.naytaRuokalinjastoAsiakkaatPalveltu(tulokset.getPalvelupisteenPalvelematAsiakkaat(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaRuokalinjastonAktiiviaika(tulokset.getAktiiviAika_B(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaRuokalinjastonKayttoaste(tulokset.getKayttoaste_U(Palvelupisteet.RUOKALINJASTO));
		kontrolleri.naytaRuokalinjastonKeskikayttoaste(tulokset.getKeskiKayttoaste(Palvelupisteet.RUOKALINJASTO));
		//kontrolleri.naytaRuokalinjastonSuoritusteho(tulokset.getSuoritusteho_X(Palvelupisteet.RUOKALINJASTO));
		
		
		//kontrolleri.naytaKassaAsiakkaatPalveltu(tulokset.getPalvelupisteenPalvelematAsiakkaat(Palvelupisteet.KASSA));
		kontrolleri.naytaKassaAktiiviaika(tulokset.getAktiiviAika_B(Palvelupisteet.KASSA));
		kontrolleri.naytaKassaKayttoaste(tulokset.getKayttoaste_U(Palvelupisteet.KASSA));
		kontrolleri.naytaKassaKeskikayttoaste(tulokset.getKeskiKayttoaste(Palvelupisteet.KASSA));
		//kontrolleri.naytaKassaSuoritusteho(tulokset.getSuoritusteho_X(Palvelupisteet.KASSA));
		
		
		
	}

	
	@Override
	protected void tulokset() {	
		
		System.out.println("\n" + "Simulointi päättyi kello " + (Kello.getInstance().getAika()/1000) + " sekuntia." + "\n");
		
		System.out.println(tulokset);
		
		System.out.println(Asiakas.kikkeli + " kikkeliä");
		
		//System.out.println("W = " + tulokset.getOdotusAika_W());
		kontrolleri.naytaKeskiOdotusAika(tulokset.getKeskimaarainenJonotusAika()); // Jottain pittee tehä // se on tehty :D
		kontrolleri.tallenna();
		//kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());
				
		
	}
	
}
