package view;


import java.time.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class Visualisointi implements IVisualisointi{
	
	private Pane animaatioPane;
	private Pane asiakasPane;
	
	private Label 
	pisinruokajono,ruokajono,pisinkassajono,
	kassajono,asiakas,kello, poistuneet;
	
	private Label asiakasFinal,lapiAsiakkaat,keskiAsiakkaat,keskiJonoAsiakkaat, asiakasPalveluAika;
	private BarChart<String,Number> asiakasChart;
	private CategoryAxis asiakascategoryaxis;
	private NumberAxis asiakasnumberaxis;
	
	private Label aktiiviaika, palvellutAsiakkaat, kayttoaste, suoritusteho, keskiKayttoaste;
	
	private Label kassaAktiiviaika, kassaPalvellutAsiakkaat,kassaKayttoaste,kassaSuoritusteho,kassaKeskikayttoaste;
	
	ImageView ruokajonopalkki;
	ImageView maxruokajonopalkki;
	ImageView kassajonopalkki;
	ImageView maxkassajonopalkki;
	ImageView saapuneetpalkki;
	ImageView poistuneetpalkki;
	ImageView kellopalkki;
	
	
	private int asiakasmaara = 0;
	private int poistuneetMaara = 0;
	private int poistuneetCharttiin = 0;
	int asiakasLkm = 0;
	

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
		this.animaatioPane = pane;
		//animaatioPane.setVisible(true);
		this.pisinruokajono = pisinruokajono;
		this.ruokajono = ruokajono;
		this.pisinkassajono = pisinkassajono;
		this.kassajono = kassajono;
		this.asiakas = asiakas;
		this.kello = kello;
		this.poistuneet = poistuneet;	

		

	}
	

	
	
	public void setAsiakasPane(
	Label asiakasFinal,Label lapiAsiakkaat,Label keskiAsiakkaat,
	Label keskiJonoAsiakkaat,Label asiakasPalveluAika, BarChart<String,Number> asiakasChart,
	CategoryAxis asiakascategoryaxis,NumberAxis asiakasnumberaxis) {
		
		this.asiakasFinal = asiakasFinal;
		this.lapiAsiakkaat = lapiAsiakkaat;
		this.keskiAsiakkaat = keskiAsiakkaat;
		this.keskiJonoAsiakkaat = keskiJonoAsiakkaat;	
		this.asiakasPalveluAika = asiakasPalveluAika;
		this.asiakasChart = asiakasChart;
		this.asiakascategoryaxis = asiakascategoryaxis;
		this.asiakasnumberaxis = asiakasnumberaxis;
		
			
	}
	
	public void setRuokalinjastoPane(Label aktiiviaika, Label palvellutAsiakkaat, Label kayttoaste, Label suoritusteho, Label keskiKayttoaste) {
		
		this.aktiiviaika = aktiiviaika;
		this.palvellutAsiakkaat = palvellutAsiakkaat;
		this.kayttoaste = kayttoaste;
		this.suoritusteho = suoritusteho;
		this.keskiKayttoaste = keskiKayttoaste;
		
	}
	
	public void setKassaPane(Label aktiiviaika, Label palvellutAsiakkaat, Label kayttoaste, Label suoritusteho, Label keskiKayttoaste) {
		
		this.kassaAktiiviaika = aktiiviaika;
		this.kassaPalvellutAsiakkaat = palvellutAsiakkaat;
		this.kassaKayttoaste = kayttoaste;
		this.kassaSuoritusteho = suoritusteho;
		this.kassaKeskikayttoaste = keskiKayttoaste;
		
	}
	
	
	public void uusiAsiakas() {
		
		asiakasmaara++;
		asiakas.setText(Integer.toString(asiakasmaara));
		asiakasFinal.setText(": " + Integer.toString(asiakasmaara));
		
		if(asiakasmaara > 1) {
			
			saapuneetpalkki.setFitWidth(asiakasmaara/2.1);
			
		}else {
			
			saapuneetpalkki.setFitWidth(asiakasmaara);
			
		}
		
		
		
	}
	

	public void setJonoRuokalinjastolle(int maara) {
		
		ColorAdjust vari = setRuokaJononVari(maara);
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
		
		ColorAdjust vari = setRuokaJononVari(maara);
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
	
	@Override
	public void setJonoKassoille(int maara) {
		
		int kassakerroin = maara * 10;
		kassajono.setText(Integer.toString(maara));
		ColorAdjust vari = setKassaJononVari(kassakerroin);
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
		ColorAdjust vari = setKassaJononVari(kassakerroin);
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
	
	private ColorAdjust setRuokaJononVari(int maara) {
		
		ColorAdjust vari = new ColorAdjust();
		
		if(maara < 100) {
			
			vari.setHue(0);
			vari.setSaturation(0);
			
		}else if(maara > 100 && maara < 150) {
			
			vari.setSaturation(0.1);
			vari.setHue(-0.45);
			
		}else if(maara > 150 && maara < 200) {
			
			vari.setSaturation(0.25);
			vari.setHue(-0.65);
			
		}else if(maara > 200) {
			
			vari.setSaturation(0.75);
			vari.setHue(-0.65);
			
		}
		
		return vari;
	}
	
	private ColorAdjust setKassaJononVari(int maara) {
		
		ColorAdjust vari = new ColorAdjust();
		
		if(maara < 100) {
			
			vari.setHue(0);
			vari.setSaturation(0);
			
		}else if(maara > 100 && maara < 150) {
			
			vari.setSaturation(0.1);
			vari.setHue(-0.45);
			
		}else if(maara > 150 && maara < 200) {
			
			vari.setSaturation(0.25);
			vari.setHue(-0.65);
			
		}else if(maara > 200) {
			
			vari.setSaturation(0.75);
			vari.setHue(-0.65);
			
		}
		
		return vari;
	}
	
	




	
	








	@Override
	public void setLapiPaasseetAsiakkaat() {
		
		
		poistuneetMaara++;
		
		poistuneet.setText(Integer.toString(poistuneetMaara));
		
		lapiAsiakkaat.setText(": " + Integer.toString(poistuneetMaara));
		
		if(poistuneetMaara > 1) {
			
			poistuneetpalkki.setFitWidth(poistuneetMaara/2.1);
			
		}else {
			
			poistuneetpalkki.setFitWidth(poistuneetMaara);
			
		}
		
		
		
		
	}

	@Override
	public void setKellonAika(double aika) {
		
		kello.setText(String.format("%.0f", aika/1000) + " s");
		GaussianBlur blur = new GaussianBlur();
		blur.setRadius(10);
		kellopalkki.setEffect(blur);
		kellopalkki.setFitWidth(aika/26500);
		
	}



	// -> Asiakkaan visualisoinnit ---------------------------------------------------------------------------------------------------------------



	@Override
	public void setAsiakasOdotusAika(double aika) {
		
		keskiJonoAsiakkaat.setText(": " + String.format("%.0f", aika/1000) + " s");
		
	}


	@Override
	public void setAsiakkaanLapimenoAika(double aika) {
		
		keskiAsiakkaat.setText(": " + String.format("%.0f", aika/1000) + " s");
		
	}
	
	@Override
	public void setAsiakkaidenKeskimaarainenPalveluaika(double aika) {
		
		asiakasPalveluAika.setText(": " + String.format("%.0f", aika/1000) + " s");
		
	}


	// -> Ruokalinjasto visualisoinnit ---------------------------------------------------------------------------------------------------------------

	@Override
	public void setRuokalinjastonAsiakkatPalveltu(int maara) {
		
		palvellutAsiakkaat.setText(": " + maara);
		
	}




	@Override
	public void setRuokalinjastoKayttoaste(double maara) {
		
		
		kayttoaste.setText(": " + String.format("%.2f", maara * 100) + " %");
		
	}




	@Override
	public void setRuokalinjastonSuoritusteho(double maara) {
		
		suoritusteho.setText(": " + maara + " %");
		
	}
	
	@Override
	public void setRuokalinjastonAktiiviaika(double aika) {

		aktiiviaika.setText(": " + String.format("%.0f", aika/1000) + " s");
		
	}
	
	
	
	// -> Kassavisualisoinnit --------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public void setKassojenAktiiviaika(double aika) {
		
		kassaAktiiviaika.setText(": " + String.format("%.0f", aika/1000) + " s");
		
	}




	@Override
	public void setKassaPalvellutAsiakkaat(int maara) {
		
		kassaPalvellutAsiakkaat.setText(": " + maara);
		
	}




	@Override
	public void setKassaKayttoaste(double maara) {
		
		kassaKayttoaste.setText(": " + String.format("%.2f", maara * 100) + " %");
		
	}




	@Override
	public void setKassaSuoritusteho(double maara) {
		
		kassaSuoritusteho.setText(": " + maara + " %");
		
	}




	@Override
	public void setRuokalinjastoKeskiKayttoaste(double maara) {
		
		keskiKayttoaste.setText(": " + String.format("%.2f", maara * 100) + " %");
		
	}




	@Override
	public void setKassaKeskiKayttoaste(double maara) {
		
		kassaKeskikayttoaste.setText(": " + String.format("%.2f", maara * 100) + " %");
		
	}




	@Override
	public void setAsiakasKeskiPalveluAika(double aika) {
		// TODO Auto-generated method stub
		
	}






	

}
