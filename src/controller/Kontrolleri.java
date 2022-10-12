package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.Alkuarvot;
import simu.model.DBAccessObject;
import simu.model.OmaMoottori;
import simu.model.Tulokset;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV{
	
	private IMoottori moottori; 
	private ISimulaattorinUI ui;
	private DBAccessObject db;
	
	
	public Kontrolleri(ISimulaattorinUI ui) {
		
		this.ui = ui;
		this.db = new DBAccessObject();
		
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
		
		Platform.runLater(() -> db.tallennaAjo(moottori.getTapahtumat()));
		
	}
	

		
	/*
	@Override
	public void naytaLoppuaika(double aika) {
		
		Platform.runLater(()->ui.setLoppuaika(aika));
		
	}
	
	*/

	@Override
	public void visualisoiAsiakas(int asiakasmaara) {
	
		Platform.runLater(() -> ui.getVisualisointi().uusiAsiakas(asiakasmaara) );
		
	}
	

	@Override
	public void naytaPisinJonoKassoille(int jononpituus) {
		
		Platform.runLater(() -> ui.getVisualisointi().setPisinJonoKassoille(jononpituus));

	}
	
	
	public void naytaAsiakkaidenKeskimaarainenPalveluaika(double aika) { 
		
		Platform.runLater(() -> ui.getVisualisointi().setAsiakkaidenKeskimaarainenPalveluaika(aika));
		
	}
	
	public void naytaPisinJonoRuokalinjastolle(int maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setPisinJonoRuokalinjastolle(maara));
	
	}
	
	public void naytaLapiPaasseetAsiakkaat(int poistumismaara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setLapiPaasseetAsiakkaat(poistumismaara));
		
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
		
		Platform.runLater(() -> ui.getVisualisointi().setAsiakasOdotusAika(aika));
		
	}


	@Override
	public void naytaAsiakkaanLapimenoAika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setAsiakkaanLapimenoAika(aika));
		
	}


	@Override
	public void naytaRuokalinjastoAsiakkaatPalveltu(int maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setRuokalinjastonAsiakkatPalveltu(maara));
		
	}

	@Override
	public void naytaRuokalinjastonKayttoaste(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setRuokalinjastoKayttoaste(maara));
		
	}

	@Override
	public void naytaRuokalinjastonSuoritusteho(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setRuokalinjastonSuoritusteho(maara));
		
	}

	@Override
	public void naytaKassaAsiakkaatPalveltu(int maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKassaPalvellutAsiakkaat(maara));
		
	}

	@Override
	public void naytaKassaKayttoaste(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKassaKayttoaste(maara));
		
	}

	@Override
	public void naytaKassaSuoritusteho(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKassaSuoritusteho(maara));
		
	}

	@Override
	public void naytaKassaAktiiviaika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKassojenAktiiviaika(aika));
		
	}

	@Override
	public void naytaRuokalinjastonKeskikayttoaste(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setRuokalinjastoKeskiKayttoaste(maara));
		
	}

	@Override
	public void naytaKassaKeskikayttoaste(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKassaKeskiKayttoaste(maara));
		
	}

	@Override
	public void setLoadlist(ArrayList<String> lista) {
		
		Platform.runLater(() -> ui.getVisualisointi().setLoadlist(lista));
		
	}

	@Override
	public void getLoadlist() {
		

		String[] ajot = db.getAjoTimeStamp();
		ArrayList<String> sendajot = new ArrayList<>();
		
		for(int i = 0; i < ajot.length; i++) {
			
			sendajot.add(ajot[i]);
			
		}
		
		//Collections.reverse(sendajot);
		setLoadlist(sendajot);
	}

	@Override
	public void setAjolist() {
		
		
		Platform.runLater(() -> ui.getVisualisointi().setEsitiedotRuudulle(db.getKaikkiAjot()));
		
		
	}

	@Override
	public void setLoadToTulokset(int index) {
		
		db.lataaTuloksiin(index);
		try {
			Tulokset.getInstance().setDummy(index);
		} catch (SQLException e) {
			System.out.println("Dummy tulosten asettaminen ei onnistunut");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void getLoadTulokset() {
		
		//moottori = new OmaMoottori(this);
		moottori.setLatausTulokset();
		
	}
	

	@Override
	public void setAsiakasChart(int saapunut, int poistunut, double lapimeno, double jonotusaika, double palveluaika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setAsiakasChart(saapunut, poistunut, lapimeno, jonotusaika, palveluaika));
		
	}


}
