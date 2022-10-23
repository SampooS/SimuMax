package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.DBAccessObject;
import simu.model.OmaMoottori;
import simu.model.Tulokset;
import view.ISimulaattorinUI;

/**
 * Class Kontrolleri.
 * @author Otto Oksanen
 */
public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV{
	
	private IMoottori moottori; 
	private ISimulaattorinUI ui;
	private DBAccessObject db;
	
	
	public Kontrolleri(ISimulaattorinUI ui) {
		
		this.ui = ui;
		this.db = new DBAccessObject();
		
	}

	/**
	 * Lähettää asiakkaan antamat simulaatioparametrit moottorin käytettäväksi ja käynnistää simulaation. 
	 */
	@Override
	public void kaynnistaSimulointi() {
		
		moottori = new OmaMoottori(this);
		moottori.setSimulointiaika(ui.getAika());
		moottori.setSimulointiLahtoArvot(ui.getRuokalinjojenMaara(), ui.getKassojenMaara(), ui.getAsiakasMaara(), ui.getRyhmienMaara(), ui.getPorrastusAika());
		moottori.setViive(ui.getViive());
		((Thread)moottori).start();

	}
	
	/**
	 * Hidastaa simulaatiota. 
	 */
	@Override
	public void hidasta() { 
		
		moottori.setViive((long)(moottori.getViive()*1.10));
		
	}

	/**
	 * Nopeuttaa simulaatiota. 
	 */
	@Override
	public void nopeuta() {
		
		moottori.setViive((long)(moottori.getViive()*0.9));
		
	}
	
	
	/**
	 * Tallentaa simulaation tietokantaan. 
	 */
	@Override
	public void tallenna() {
		
		Platform.runLater(() -> db.tallennaAjo(moottori.getTapahtumat()));
		
	}
	

	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void visualisoiAsiakas(int asiakasmaara) {
	
		Platform.runLater(() -> ui.getVisualisointi().uusiAsiakas(asiakasmaara) );
		
	}
	

	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaPisinJonoKassoille(int jononpituus) {
		
		Platform.runLater(() -> ui.getVisualisointi().setPisinJonoKassoille(jononpituus));

	}
	
	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaAsiakkaidenKeskimaarainenPalveluaika(double aika) { 
		
		Platform.runLater(() -> ui.getVisualisointi().setAsiakkaidenKeskimaarainenPalveluaika(aika));
		
	}
	
	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaPisinJonoRuokalinjastolle(int maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setPisinJonoRuokalinjastolle(maara));
	
	}
	
	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaLapiPaasseetAsiakkaat(int poistumismaara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setLapiPaasseetAsiakkaat(poistumismaara));
		
	}
	
	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaRuokalinjastonAktiiviaika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setRuokalinjastonAktiiviaika(aika));
		
	}


	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaKellonAika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKellonAika(aika));
		
	}


	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaRuokaJononPituus(int maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setJonoRuokalinjastolle(maara));
		
	}


	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaKassaJononPituus(int maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setJonoKassoille(maara));
		
	}


	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaKeskiOdotusAika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setAsiakasOdotusAika(aika));
		
	}


	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaAsiakkaanLapimenoAika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setAsiakkaanLapimenoAika(aika));
		
	}


	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaRuokalinjastonKayttoaste(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setRuokalinjastoKayttoaste(maara));
		
	}
	

	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaRuokalinjastonSuoritusteho(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setRuokalinjastonSuoritusteho(maara));
		
	}


	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaKassaKayttoaste(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKassaKayttoaste(maara));
		
	}

	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaKassaSuoritusteho(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKassaSuoritusteho(maara));
		
	}

	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaKassaAktiiviaika(double aika) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKassojenAktiiviaika(aika));
		
	}

	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaRuokalinjastonKeskikayttoaste(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setRuokalinjastoKeskiKayttoaste(maara));
		
	}

	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void naytaKassaKeskikayttoaste(double maara) {
		
		Platform.runLater(() -> ui.getVisualisointi().setKassaKeskiKayttoaste(maara));
		
	}

	/**
	 * Lähettää esitieto datan tietokannasta Visualisointi luokan käsiteltäväksi. 
	 */
	@Override
	public void setLoadlist(ArrayList<String> lista) {
		
		Platform.runLater(() -> ui.getVisualisointi().setLoadlist(lista));
		
	}

	/**
	 * Hakee esitietodatan tietokannasta. 
	 */
	@Override
	public void getLoadlist() {
		
		String[] ajot = db.getAjoTimeStamp();
		ArrayList<String> sendajot = new ArrayList<>();
		
		for(int i = 0; i < ajot.length; i++) {
			
			sendajot.add(ajot[i]);
			
		}
		
		setLoadlist(sendajot);
	}

	
	/**
	 * Lähettää tietokannasta dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void setAjolist() {
		
		Platform.runLater(() -> ui.getVisualisointi().setEsitiedotRuudulle(db.getKaikkiAjot()));
		
	}

	/**
	 * Lähettää tietokannasta dataa Tulokset luokan käsiteltäväksi. 
	 */
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
	
	
	/**
	 * Lähettää moottorista dataa Visualisointiluokan käsiteltäväksi. 
	 */
	@Override
	public void getLoadTulokset() {
		
		moottori.setLataukset();
		
	}
	
}
