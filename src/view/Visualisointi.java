package view;


import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import simu.model.Alkuarvot;


public class Visualisointi implements IVisualisointi{
	
	
	private Visualeffects visualeffects;
	private Label 
	pisinruokajono,ruokajono,pisinkassajono,
	kassajono,asiakas,kello, poistuneet;
	
	private Label asiakasFinal,lapiAsiakkaat,keskiAsiakkaat,keskiJonoAsiakkaat, asiakasPalveluAika;

	
	private Label aktiiviaika,kayttoaste, suoritusteho, keskiKayttoaste;
	
	private Label kassaAktiiviaika,kassaKayttoaste,kassaSuoritusteho,kassaKeskikayttoaste;
	
	ImageView ruokajonopalkki,maxruokajonopalkki,kassajonopalkki;

	ImageView maxkassajonopalkki;
	ImageView saapuneetpalkki;
	ImageView poistuneetpalkki;
	ImageView kellopalkki;
	ImageView saapunut;
	ImageView poistunut;
	ImageView lapimeno;
	ImageView jonotusaika;
	ImageView palveluaika;
	ImageView ruokaaktiivi;
	ImageView ruokakaytto;
	ImageView ruokakeskikaytto;
	ImageView kassaaktiivi;
	ImageView kassakaytto;
	ImageView kassakeskikaytto;
	ImageView kassasuoritusteho;
	
	int asiakasLkm = 0;
	
	private ListView<String> loadlist;
	private Pane esitiedotpane;
	private Label esiasiakas,esiruokalinja,esikassa,esiryhmia,esiporrastusaika;

	public Visualisointi(	
			
		Pane pane, Label pisinruokajono, Label pisinkassajono, 
		Label asiakas, Label kello, Label poistuneet, 
		Label ruokajono, Label kassajono,
		ImageView ruokajonopalkki, ImageView maxruokajonopalkki,ImageView kassajonopalkki,
		ImageView maxkassajonopalkki,ImageView saapuneetpalkki,ImageView poistuneetpalkki,ImageView kellopalkki) 
	
	{
		
		this.saapuneetpalkki = saapuneetpalkki;
		this.poistuneetpalkki = poistuneetpalkki;
		this.kellopalkki = kellopalkki;
		this.kassajonopalkki = kassajonopalkki;
		this.maxkassajonopalkki = maxkassajonopalkki;
		this.maxruokajonopalkki = maxruokajonopalkki;
		this.ruokajonopalkki = ruokajonopalkki;
		this.pisinruokajono = pisinruokajono;
		this.ruokajono = ruokajono;
		this.pisinkassajono = pisinkassajono;
		this.kassajono = kassajono;
		this.asiakas = asiakas;
		this.kello = kello;
		this.poistuneet = poistuneet;
		this.visualeffects = new Visualeffects();

	}
	

	@Override
	public void setKellonAika(double aika) {
		
		kello.setText(String.format("%.0f", aika/1000) + " s");
		GaussianBlur blur = new GaussianBlur();
		blur.setRadius(10);
		kellopalkki.setEffect(blur);
		kellopalkki.setFitWidth(aika/39000);
		
	}



	// -> Asiakkaan visualisoinnit ---------------------------------------------------------------------------------------------------------------
	
	
	public void setAsiakasPane(
			
			Label asiakasFinal,Label lapiAsiakkaat,Label keskiAsiakkaat,
			Label keskiJonoAsiakkaat,Label asiakasPalveluAika, ImageView saapunut,
			ImageView poistunut,ImageView lapimeno,ImageView jonotusaika,ImageView palveluaika) {
			
			this.asiakasFinal = asiakasFinal;
			this.lapiAsiakkaat = lapiAsiakkaat;
			this.keskiAsiakkaat = keskiAsiakkaat;
			this.keskiJonoAsiakkaat = keskiJonoAsiakkaat;	
			this.asiakasPalveluAika = asiakasPalveluAika;
			this.saapunut = saapunut;
			this.poistunut = poistunut;
			this.lapimeno = lapimeno;
			this.jonotusaika = jonotusaika;
			this.palveluaika = palveluaika;
			
				
	}

	@Override
	public void setLapiPaasseetAsiakkaat(int poistumismaara) {
			
		poistuneet.setText(Integer.toString(poistumismaara));
		lapiAsiakkaat.setText(": " + Integer.toString(poistumismaara));
		
		if(poistumismaara > 1) {
			
			poistuneetpalkki.setFitWidth(poistumismaara/2.1);
			poistunut.setFitHeight(poistumismaara/3.2);
			
		}else {
			
			poistuneetpalkki.setFitWidth(poistumismaara);
			poistunut.setFitHeight(poistumismaara);
			
		}	
	}

	@Override
	public void setAsiakasOdotusAika(double aika) {
		
		keskiJonoAsiakkaat.setText(": " + String.format("%.0f", aika/1000) + " s");
		jonotusaika.setFitHeight(aika/17000);
	}


	@Override
	public void setAsiakkaanLapimenoAika(double aika) {
		
		keskiAsiakkaat.setText(": " + String.format("%.0f", aika/1000) + " s");
		lapimeno.setFitHeight(aika/17000);
	}
	
	@Override
	public void setAsiakkaidenKeskimaarainenPalveluaika(double aika) {
		
		asiakasPalveluAika.setText(": " + String.format("%.0f", aika/1000) + " s");
		palveluaika.setFitHeight(aika/17000);
	}
	
	
	@Override
	public void uusiAsiakas(int asiakasmaara) {
		
		asiakas.setText(Integer.toString(asiakasmaara));
		asiakasFinal.setText(": " + Integer.toString(asiakasmaara));
		
		if(asiakasmaara > 1) {
			
			saapuneetpalkki.setFitWidth(asiakasmaara/2.1);
			saapunut.setFitHeight(asiakasmaara/3.2);
			
		}else {
			
			saapuneetpalkki.setFitWidth(asiakasmaara);
			saapunut.setFitHeight(asiakasmaara);
			
		}
	}


	// -> Ruokalinjasto visualisoinnit ---------------------------------------------------------------------------------------------------------------
	
	
	public void setRuokalinjastoPane(
			
			Label aktiiviaika, Label palvellutAsiakkaat, Label kayttoaste, 
		    Label suoritusteho, Label keskiKayttoaste,ImageView ruokaaktiivi,
		    ImageView ruokakaytto,ImageView ruokakeskikaytto) {
			
			this.aktiiviaika = aktiiviaika;
			this.kayttoaste = kayttoaste;
			this.suoritusteho = suoritusteho;
			this.keskiKayttoaste = keskiKayttoaste;
			this.ruokaaktiivi = ruokaaktiivi;
			this.ruokakaytto = ruokakaytto;
			this.ruokakeskikaytto = ruokakeskikaytto;
				
	}

	@Override
	public void setRuokalinjastoKayttoaste(double maara) {
		
		if(maara < 0.1) {
			
			maara = 0;
			kayttoaste.setText(": " + String.format("%.2f", maara * 100) + " %");
			ruokakaytto.setFitHeight(0.1);
			
		}else {
			
			kayttoaste.setText(": " + String.format("%.2f", maara * 100) + " %");
			ruokakaytto.setFitHeight(maara * 230);
			
		}	
	}

	@Override
	public void setRuokalinjastonSuoritusteho(double maara) {
		
		
		
	}
	
	@Override
	public void setRuokalinjastonAktiiviaika(double aika) {

		aktiiviaika.setText(": " + String.format("%.0f", aika/1000) + " s");
		ruokaaktiivi.setFitHeight((aika/1000)/20);
		
	}
	
	@Override
	public void setRuokalinjastoKeskiKayttoaste(double maara) {
		
		keskiKayttoaste.setText(": " + String.format("%.2f", maara * 100) + " %");
		ruokakeskikaytto.setFitHeight(maara * 230);
		
	}
	
	
	public void setJonoRuokalinjastolle(int maara) {
		
		ColorAdjust vari = visualeffects.setVariJonoPalkeille(maara);
		GaussianBlur blur = new GaussianBlur();
		blur.setRadius(14);
		vari.setInput(blur);
		ruokajono.setText(Integer.toString(maara));
		ruokajonopalkki.setEffect(vari);
			
		if(maara > 1) {
			
			ruokajonopalkki.setFitWidth(maara/2.1);	
			
		}else {
			
			ruokajonopalkki.setFitWidth(0.1);	
			
		}
	}
	
	@Override
	public void setPisinJonoRuokalinjastolle(int maara) {
		
		ColorAdjust vari = visualeffects.setVariJonoPalkeille(maara);
		GaussianBlur blur = new GaussianBlur();
		blur.setRadius(14);
		vari.setInput(blur);
		pisinruokajono.setText(Integer.toString(maara));
		maxruokajonopalkki.setEffect(vari);	
		
		if(maara > 1) {
			
			maxruokajonopalkki.setFitWidth(maara/2.1);	
			
		}else {
			
			maxruokajonopalkki.setFitWidth(0.1);	
			
		}
		
	}
	
	
	
	// -> Kassavisualisoinnit --------------------------------------------------------------------------------------------------------------------------
	
	
	public void setKassaPane(
			
			Label aktiiviaika, Label palvellutAsiakkaat, Label kayttoaste, 
			Label suoritusteho, Label keskiKayttoaste,ImageView kassaktiivi,
			ImageView kassakaytto,ImageView kassakeskikaytto, ImageView kassasuoritusteho) {
			
			this.kassaAktiiviaika = aktiiviaika;
			this.kassaKayttoaste = kayttoaste;
			this.kassaSuoritusteho = suoritusteho;
			this.kassaKeskikayttoaste = keskiKayttoaste;
			this.kassaaktiivi = kassaktiivi;
			this.kassakaytto = kassakaytto;
			this.kassakeskikaytto = kassakeskikaytto;
			this.kassasuoritusteho = kassasuoritusteho;
			
		}
	
	
	@Override
	public void setKassojenAktiiviaika(double aika) {
		
		kassaAktiiviaika.setText(": " + String.format("%.0f", aika/1000) + " s");
		kassaaktiivi.setFitHeight((aika/1000)/20);
		
	}


	@Override
	public void setKassaKayttoaste(double maara) {
			
		if(maara < 0.1) {
			
			maara = 0;
			kassaKayttoaste.setText(": " + String.format("%.2f", maara * 100) + " %");
			kassakaytto.setFitHeight(0.1);
			
			
		}else {
			
			kassaKayttoaste.setText(": " + String.format("%.2f", maara * 100) + " %");
			kassakaytto.setFitHeight(maara * 230);
			
		}
	}

	@Override
	public void setKassaSuoritusteho(double maara) {
		
		kassaSuoritusteho.setText(": " + String.format("%.2f", maara * 4));
		kassasuoritusteho.setFitHeight(maara * 4 * 3);
		
	}

	@Override
	public void setKassaKeskiKayttoaste(double maara) {
		
		kassaKeskikayttoaste.setText(": " + String.format("%.2f", maara * 100) + " %");
		kassakeskikaytto.setFitHeight(maara * 230);
		
	}
	
	@Override
	public void setJonoKassoille(int maara) {
		
		int kassakerroin = maara * 10;
		kassajono.setText(Integer.toString(maara));
		ColorAdjust vari = visualeffects.setVariJonoPalkeille(kassakerroin);
		GaussianBlur blur = new GaussianBlur();
		blur.setRadius(14);
		vari.setInput(blur);
		
		kassajonopalkki.setEffect(vari);
		
		if(kassakerroin > 0) {
			
			kassajonopalkki.setFitWidth(kassakerroin);	
			
		}else {
				
			kassajonopalkki.setFitWidth(0.1);	
			
		}	
	}

	
	@Override
	public void setPisinJonoKassoille(int maara) {
		
		
		int kassakerroin = maara * 10;
		
		pisinkassajono.setText(Integer.toString(maara));
		ColorAdjust vari = visualeffects.setVariJonoPalkeille(kassakerroin);
		GaussianBlur blur = new GaussianBlur();
		blur.setRadius(14);
		vari.setInput(blur);
		maxkassajonopalkki.setEffect(vari);	

		
		if(kassakerroin > 0) {
			
			maxkassajonopalkki.setFitWidth(kassakerroin);	
			
		}else {
			
			maxkassajonopalkki.setFitWidth(0.1);	
			
		}
	}


	// Loadpane stup.....................................................................................................................................
	@Override
	public void setLoadPane(ListView<String> loadlist,Pane esitiedotPane,Label esiasiakas,Label esiruokalinja,Label esikassa, Label esiryhmia,Label esiporrastusaika) {
		
		this.loadlist = loadlist;
		this.esitiedotpane = esitiedotPane;
		this.esiasiakas = esiasiakas;
		this.esiruokalinja = esiruokalinja;
		this.esikassa = esikassa;
		this.esiryhmia = esiryhmia;
		this.esiporrastusaika = esiporrastusaika;
		
	}




	@Override
	public void setLoadlist(ArrayList<String> tallennukset) {
		
		ObservableList<String> loadings = FXCollections.observableArrayList();
		
		for(String tieto: tallennukset) {
			
			loadings.add(tieto);
		}
		
		loadlist.getItems().clear();
		loadlist.getItems().addAll(loadings);
		loadlist.setStyle("-fx-cell-background-color: #000000;");
		System.out.println(loadlist.getItems());
			
	}
	
	
	@Override
	public void setEsitiedotRuudulle(ArrayList<Alkuarvot> arvot) {
		
		Alkuarvot a = arvot.get(loadlist.getFocusModel().getFocusedIndex());
		esitiedotpane.setVisible(true);
		esiasiakas.setText(": " + Integer.toString(a.getAsiakkaat()));
		esiruokalinja.setText(": " + Integer.toString(a.getRuokalinja() / 10));
		esikassa.setText(": " + Integer.toString(a.getKassat()));
		esiryhmia.setText(": " + Integer.toString(a.getRyhmienMaara()));
		esiporrastusaika.setText(": " + Double.toString(a.getPorrastusMaara()) + " min");
		
		
	}
	
	
	public ListView<String> getLoadList() {
		
		return loadlist;
		
	}
}
