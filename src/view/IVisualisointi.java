package view;

import java.util.ArrayList;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import simu.model.Alkuarvot;
import simu.model.Asiakas;

public interface IVisualisointi {

	public void setAsiakasPane(
	Label asiakasFinal,Label lapiAsiakkaat,Label keskiAsiakkaat,
	Label keskiJonoAsiakkaat,Label asiakasPalveluAika, ImageView saapunut,
	ImageView poistunut,ImageView lapimeno,ImageView jonotusaika,ImageView palveluaika
	);
	public void setRuokalinjastoPane(Label aktiiviaika,Label palvellutAsiakkaat,Label kayttoaste,Label suoritusteho,Label keskiKayttoaste,
	ImageView ruokaaktiivi,ImageView ruokakaytto,ImageView ruokakeskikaytto);
	public void setKassaPane(Label aktiiviaika,Label palvellutAsiakkaat,Label kayttoaste,Label suoritusteho,Label keskiKayttoaste,
	ImageView kaasaktiivi,ImageView kassakaytto,ImageView kassakeskikaytto, ImageView kassasuoritusteho);
	public void setLoadPane(ListView<String> loadlist,Pane esitiedotpane,Label esiasiakas,Label esiruokalinja,Label esikassa,Label esiryhmia,Label esiporrastusaika);
	
	
	public void setPisinJonoKassoille(int jononpituus);
	public void setJonoKassoille(int maara);		
	public void setPisinJonoRuokalinjastolle(int maara);
	public void setJonoRuokalinjastolle(int maara);
	public void setKellonAika(double aika);
	
	
	public void uusiAsiakas(int asiakasmaara);
	public void setAsiakkaanLapimenoAika(double aika);
	public void setAsiakkaidenKeskimaarainenPalveluaika(double ka);
	public void setLapiPaasseetAsiakkaat(int poistumismaara);
	public void setAsiakasOdotusAika(double aika);
	public void setAsiakasKeskiPalveluAika(double aika);
	public void setAsiakasChart(int saapunut, int poistunut, double lapimeno, double jonotusaika, double palveluaika);
	
	
	public void setRuokalinjastonAsiakkatPalveltu(int maara);
	public void setRuokalinjastonAktiiviaika(double aika);
	public void setRuokalinjastoKayttoaste(double maara);
	public void setRuokalinjastoKeskiKayttoaste(double maara);
	public void setRuokalinjastonSuoritusteho(double maara);
	
	
	public void setKassaPalvellutAsiakkaat(int maara);
	public void setKassaKayttoaste(double maara);
	public void setKassaKeskiKayttoaste(double maara);
	public void setKassaSuoritusteho(double maara);
	public void setKassojenAktiiviaika(double aika);
	
	public void setLoadlist(ArrayList<String> tallennukset);
	
	public void tallennusEsitiedot();
	public ListView<String> getLoadList();
	public void setEsitiedotRuudulle(ArrayList<Alkuarvot> arvot);

		
}

