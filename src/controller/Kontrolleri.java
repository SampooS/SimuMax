package controller;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV{
	
	private IMoottori moottori; 
	private ISimulaattorinUI ui;
	
	public Kontrolleri(ISimulaattorinUI ui) {
		
		this.ui = ui;
		
	}

	@Override
	public void kaynnistaSimulointi() {
		
		moottori = new OmaMoottori(this);
		moottori.setSimulointiaika(ui.getAika());
		moottori.setSimulointiLahtoArvot(ui.getRuokalinjojenMaara(), ui.getKassojenMaara(), ui.getAsiakasMaara(), ui.getRyhmienMaara(), ui.getPorrastusAika());
		moottori.setViive(ui.getViive());
		((Thread)moottori).start();

	}
	
	@Override
	public void hidasta() { 
		
		moottori.setViive((long)(moottori.getViive()*1.10));
		
	}

	@Override
	public void nopeuta() {
		
		moottori.setViive((long)(moottori.getViive()*0.9));
		
	}
	
	@Override
	public void pause() {
				
		//Tehhää jottai
		
	}
	
	@Override
	public void tallenna() {
		
		// Tehhään jottai koodin naputusta
		
	}
	

		
	/*
	@Override
	public void naytaLoppuaika(double aika) {
		
		Platform.runLater(()->ui.setLoppuaika(aika));
		
	}
	
	*/

	@Override
	public void visualisoiAsiakas() {
	
		Platform.runLater(() -> ui.getVisualisointi().uusiAsiakas() );
		
	}
	

	@Override
	public void naytaPisinJonoKassoille(int jononpituus) {
		
		Platform.runLater(() -> ui.getVisualisointi().setPisinJonoKassoille(jononpituus));

	}
	
	public void naytaKassojenActive(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKassojenAktiiviaika(aika));
		
	}
	
	public void naytaAsiakkaidenKeskimaarainenPalveluaika(double aika) { 
		
		Platform.runLater(() -> ui.getVisualisointi().setAsiakkaidenKeskimaarainenPalveluaika(aika));
		
	}
	
	public void naytaPisinJonoRuokalinjastolle(int maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setPisinJonoRuokalinjastolle(maara));
	
	}
	
	public void naytaLapiPaasseetAsiakkaat() {
		
		Platform.runLater(() -> ui.getVisualisointi().setLapiPaasseetAsiakkaat());
		
	}
	
	public void naytaRuokalinjastonAktiiviaika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setRuokalinjastonAktiiviaika(aika));
		
	}


	@Override
	public void naytaKellonAika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKellonAika(aika));
		
	}


	@Override
	public void naytaRuokaJononPituus(int maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setJonoRuokalinjastolle(maara));
		
	}


	@Override
	public void naytaKassaJononPituus(int maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setJonoKassoille(maara));
		
	}


	@Override
	public void naytaKeskiOdotusAika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setOdotusAika(aika));
		
	}


	@Override
	public void naytaAsiakkaanLapimenoAika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setAsiakkaanLapimenoAika(aika));
		
	}
}
