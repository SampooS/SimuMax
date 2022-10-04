package view;


import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class Visualisointi implements IVisualisointi{
	
	private Pane animaatioPane;
	private Pane asiakasPane;
	private Label pisinruokajono,ruokajono,pisinkassajono,kassajono,asiakas,kello, poistuneet,
	asiakasFinal,lapiAsiakkaat,keskiAsiakkaat,keskiJonoAsiakkaat;
	private int asiakasmaara = 0;
	private int poistuneetMaara = 0;
	int asiakasLkm = 0;

	public Visualisointi(Pane pane, Label pisinruokajono, Label pisinkassajono, Label asiakas, 
			Label kello, Label poistuneet, Label ruokajono, Label kassajono, Pane asiakaspane,
			Label asiakasFinal,Label lapiAsiakkaat,Label keskiAsiakkaat,Label keskiJonoAsiakkaat ) {

		this.animaatioPane = pane;
		animaatioPane.setVisible(true);
		this.pisinruokajono = pisinruokajono;
		this.ruokajono = ruokajono;
		this.pisinkassajono = pisinkassajono;
		this.kassajono = kassajono;
		this.asiakas = asiakas;
		this.kello = kello;
		this.poistuneet = poistuneet;
		this.asiakasPane = asiakaspane;
		this.asiakasFinal = asiakasFinal;
		this.lapiAsiakkaat = lapiAsiakkaat;
		this.keskiAsiakkaat = keskiAsiakkaat;
		this.keskiJonoAsiakkaat = keskiJonoAsiakkaat;

	}
	
	
	public void setAsiakasPaneVisibility(boolean visible) {
				
			asiakasPane.setVisible(visible);	
		
	}
	


	public void tyhjennaNaytto() {

	}
	
	public void uusiAsiakas() {
		
		asiakasmaara++;
		asiakas.setText(Integer.toString(asiakasmaara));
		asiakasFinal.setText(": " + Integer.toString(asiakasmaara));
		
		
	}
	
	@Override
	public void setJonoRuokalinjastolle(int maara) {
		
		ruokajono.setText(Integer.toString(maara));
		
	}
	
	@Override
	public void setPisinJonoKassoille(int jononpituus) {
		
		pisinkassajono.setText(Integer.toString(jononpituus));
		

	}
	
	
	@Override
	public void setPisinJonoRuokalinjastolle(int maara) {
		
		
		pisinruokajono.setText(Integer.toString(maara));
		
		
	}


	@Override
	public void setRuokalinjastonAktiiviaika(double aika) {

		
		
	}


	@Override
	public void setKassojenAktiiviaika(double aika) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setAsiakkaidenKeskimaarainenPalveluaika(double aika) {
		
		
		
	}


	@Override
	public void setLapiPaasseetAsiakkaat() {
		
		
		poistuneetMaara++;
		
		poistuneet.setText(Integer.toString(poistuneetMaara));
		
		lapiAsiakkaat.setText(": " + Integer.toString(poistuneetMaara));
		
		
	}





	@Override
	public void setKellonAika(double aika) {
		
		kello.setText(String.format("%.2f", aika/1000) + " sekuntia");
		
	}





	@Override
	public void setJonoKassoille(int maara) {
		
		kassajono.setText(Integer.toString(maara));
		
	}


	@Override
	public void setOdotusAika(double aika) {
		
		keskiJonoAsiakkaat.setText(": " + String.format("%.2f", aika/1000) + " sekuntia");
		
	}


	@Override
	public void setAsiakkaanLapimenoAika(double aika) {
		
		keskiAsiakkaat.setText(": " + String.format("%.2f", aika/1000) + " sekuntia");
		
	}






	

}
