package testi;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import simu.model.Asiakas;
import simu.model.Palvelupiste;
import simu.model.Tulokset;
import simu.model.Tulokset.Palvelupisteet;

class TuloksetTest {
	
	private static Tulokset tulokset;
	private static Palvelupiste [] myllypuro;
	private static ArrayList<Asiakas> asiakaslista = new ArrayList<>();
	private static int ruokalinja  = 5;
	private static int kassat = 6;
	//private enum Palvelupisteet{RUOKALINJASTO, KASSA, RUOKASALI, KAIKKI}
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tulokset = new Tulokset();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		this.myllypuro = new Palvelupiste[ruokalinja + kassat];
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@DisplayName("Onko kaksi luottu instansseja ovat samaa?")
	@Test
	void testGetInstance() {
		//fail("Not yet implemented");
		Tulokset tulokset1 = Tulokset.getInstance();
		Tulokset tulokset2 = Tulokset.getInstance();
		Assert.assertSame("2 objektia on samaa.", tulokset1, tulokset2);;
	}
	@DisplayName("Onko palvelupisteen ruokalinja = 5 ja kassat = 6 asetettu oikein?")
	@Test
	void testSetPalvelupiste() {
		//fail("Not yet implemented");
		tulokset.setPalvelupiste(myllypuro, ruokalinja, kassat);
		assertEquals(5 ,tulokset.ruokalinja,"palvelupiste ei asetettu");
		assertEquals(6 ,tulokset.kassat,"palvelupiste ei asetettu");
		assertEquals(myllypuro ,tulokset.palvelupisteet,"palvelupiste ei asetettu");
		
	}
	@DisplayName("Onko asiakas luottu oikein?")
	@Test
	void testLisaaAsiakasTulosListalle() {
		//fail("Not yet implemented");
		Asiakas asiakas = new Asiakas();
		tulokset.lisaaAsiakasTulosListalle(asiakas);
		assertEquals(true, !Tulokset.asiakaslista.isEmpty(),"lisääminen ei onnistu");
	}
	@DisplayName("Onko service time laskettu oikein?")
	@Test
	void testGetAsiakkaidenPalveluAika_S() {
		tulokset.setPalvelupiste(myllypuro, ruokalinja, kassat);
		
		for(int i = 0; i < ruokalinja;i++) {
			myllypuro[i] = new Palvelupiste(500);
		}
		for(int i = ruokalinja; i<ruokalinja + kassat;i++) {
			myllypuro[i] = new Palvelupiste(300);
		}
		
		tulokset.setPalvellutAsiakkaat_C(1500);
		assertEquals(2.866,tulokset.getAsiakkaidenPalveluAika_S(),0.001,"Service time on laskettu väärin");
	}

	@Test
	void testGetAsiakkaat_A() {
		tulokset.asiakasMaara_A = 100;
		tulokset.getAsiakkaat_A();
		assertEquals(100,tulokset.asiakasMaara_A,"Getterin metodi ei toimi oikein");
	}

	@Test
	void testSetAsiakkaat_A() {
		
		tulokset.setAsiakkaat_A(200);
		assertEquals(200,tulokset.asiakasMaara_A,"Setterin metodi ei toimi oikein");
	}

	@Test
	void testGetAktiiviAika_B() {
		
		
		tulokset.setPalvelupiste(myllypuro, ruokalinja, kassat);
		
		for(int i = 0; i < ruokalinja;i++) {
			myllypuro[i] = new Palvelupiste(500);
		}
		for(int i = ruokalinja; i<ruokalinja + kassat;i++) {
			myllypuro[i] = new Palvelupiste(300);
		}
		
		assertEquals(4300,tulokset.getAktiiviAika_B(Palvelupisteet.KAIKKI),"Metodi ei toimi oikein");
		
	}

	@DisplayName("Onko palvellut asikkaat = 90 haettu oikein?")
	@Test
	void testGetPalvellutAsiakkaat_C() {
		tulokset.palvellutAsiakkaat_C =90;
		tulokset.getPalvellutAsiakkaat_C();
		assertEquals(90,tulokset.palvellutAsiakkaat_C,"getPalvellutAsiakkaat_C metodi ei toimi");
	}
	@DisplayName("Onko palvellut asiakkaat = 200 asetettu oikein?")
	@Test
	void testSetPalvellutAsiakkaat_C() {
		tulokset.setPalvellutAsiakkaat_C(200);
		assertEquals(200,tulokset.getPalvellutAsiakkaat_C(),"setPalvellutAsiakkaat_C Metodi ei toimii");
	}
	@DisplayName("Onko kokonaisaika = 3000 haettu oikein?")
	@Test
	void testGetKokonaisAika_T() {
		tulokset.kokonaisAika_T = 3000.0;
		tulokset.getKokonaisAika_T();
		assertEquals(3000.0,tulokset.kokonaisAika_T,"getKokonaisAika_T metodi ei toimi");
	}
	@DisplayName("Onko kokonaisaika = 3000 asetettu oikein?")
	@Test
	void testSetKokonaisAika_T() {
		tulokset.setKokonaisAika_T(3000.0);
		assertEquals(3000.0,tulokset.getKokonaisAika_T(),"setKokonaisAika_T metodi ei toimi");
	}
	@DisplayName("Onko kayttoaste haettu oikein?")
	@Test
	void testGetKayttoaste_U() {
		
		tulokset.setPalvelupiste(myllypuro, ruokalinja, kassat);
		
		for(int i = 0; i < ruokalinja;i++) {
			myllypuro[i] = new Palvelupiste(500);
		}
		for(int i = ruokalinja; i<ruokalinja + kassat;i++) {
			myllypuro[i] = new Palvelupiste(300);
		}
		
		tulokset.setKokonaisAika_T(6000);
		assertEquals(0.716,tulokset.getKayttoaste_U(),0.001,"Väärä käyttöaste arvo.");
		
	}
	@DisplayName("Onko suoritusteho laskettu oikein kun T = 300 ja C = 1000")
	@Test
	void testGetSuoritusteho_X() {
		tulokset.setPalvellutAsiakkaat_C(1000);
		tulokset .setKokonaisAika_T(300);
		assertEquals(3.33, tulokset.getSuoritusteho_X(),0.01,"Suoritusteho ei laskettu oikein");
	}

}
