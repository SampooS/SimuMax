package view;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;

public interface IVisualisointi {

	public void setAsiakasPane(
	Label asiakasFinal,Label lapiAsiakkaat,Label keskiAsiakkaat,
	Label keskiJonoAsiakkaat,Label asiakasPalveluAika,
	BarChart<String,Number> asiakasChart,CategoryAxis asiakascategoryaxis,NumberAxis asiakasnumberaxis);
	public void setRuokalinjastoPane(Label aktiiviaika,Label palvellutAsiakkaat,Label kayttoaste,Label suoritusteho,Label keskiKayttoaste);
	public void setKassaPane(Label aktiiviaika,Label palvellutAsiakkaat,Label kayttoaste,Label suoritusteho,Label keskiKayttoaste);
	
	
	public void setPisinJonoKassoille(int jononpituus);
	public void setJonoKassoille(int maara);		
	public void setPisinJonoRuokalinjastolle(int maara);
	public void setJonoRuokalinjastolle(int maara);
	public void setKellonAika(double aika);
	
	
	public void uusiAsiakas();
	public void setAsiakkaanLapimenoAika(double aika);
	public void setAsiakkaidenKeskimaarainenPalveluaika(double ka);
	public void setLapiPaasseetAsiakkaat();
	public void setAsiakasOdotusAika(double aika);
	public void setAsiakasKeskiPalveluAika(double aika);
	
	
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
	
		
}

