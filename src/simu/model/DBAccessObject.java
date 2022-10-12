package simu.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import simu.framework.*;

/**
 * @author Sampo Savolainen
 */

public class DBAccessObject implements IDao {

	private static Connection connection;
	
	public DBAccessObject() {
		
		try {
			DBAccessObject.connection = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/ruokaladb",
					"API", "API"
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static ResultSet query(String query) throws SQLException {
		if  (connection.isValid(5)) {
			Statement statement = connection.createStatement();
			return statement.executeQuery(query);
		}
		
		return null;
	}
	
	/**
	 * Tallentaa tämänhetkisen ajon SQL:ään
	 * 
	 * @author Sampo Savolainen
	 * @param ArrayList<Tapahtuma> lista ajon tapahtumista
	 * 
	 */
public void tallennaAjo(ArrayList<Tapahtuma> tapahtumat) {
		
		// ajotauluun
		int ruokalinjat = Tulokset.getInstance().ruokalinja;
		int kassat = Tulokset.getInstance().kassat;
		int asiakkaita = Tulokset.getInstance().getAsiakkaat();
		int ryhmia = Tulokset.getInstance().getRyhmat();
		double porrastusAika = Tulokset.getInstance().getPorrastusAika();
		
		
		int ajoId = DBAccessObject.addRun(ruokalinjat, kassat, asiakkaita,ryhmia,porrastusAika);
		
		
		
		// asiakastauluun
		
		ArrayList<Asiakas> asiakkaat = Tulokset.asiakaslista;
		
		for (Asiakas a : asiakkaat) {
			double asiakasSaapuminen = a.getSaapumisaika();
			double asiakasPoistuminen = a.getPoistumisaika();
			
			Asiakas vietävä = new Asiakas();
			
			vietävä.setPoistumisaika(asiakasPoistuminen);
			vietävä.setSaapumisaika(asiakasSaapuminen);
			
			DBAccessObject.addAsiakas(vietävä, ajoId);
		}		
		
		// tapahtumatauluun
		
		for (Tapahtuma tapahtuma : tapahtumat) {
			DBAccessObject.addTapahtuma(tapahtuma, ajoId);
		}
		
		// palvelupistetauluun
		Palvelupiste[] palvelupisteet = Tulokset.getInstance().palvelupisteet;
		
		for (Palvelupiste piste : palvelupisteet) {
			addPP(piste, ajoId);
		}
		
	}

	/**
	 * @author Sampo Savolainen
	 * Hakee SQL:ästä annetun ajon ID:llä löytyvät asiakkaat.
	 * 
	 * @param ajoId halutun ajon id tietokannassa
	 * @return ArrayList<Asiakas> lista asiakkaista
	 */

	public ArrayList<Asiakas> getAsiakkaat(int ajoId) { // WORK IN PROGRESS
		
		 ArrayList<Asiakas> palautettava = new ArrayList<Asiakas>();
		 
		try {
			ResultSet results = query("select (saapumisAika, poistumisAika) from asiakkaat where ajoId=" + ajoId);
			
			ResultSet maxResults = query("Select count(asiakasId) from asiakkaat where ajoId = " + ajoId);
			maxResults.next();
			
			int max = maxResults.getInt(1);
			
			int i = 1;
			while (i <= max) {
				Asiakas uusiAsiakas = new Asiakas();
				uusiAsiakas.setSaapumisaika(results.getDouble(1));
				uusiAsiakas.setPoistumisaika(results.getDouble(2));
				
				palautettava.add(uusiAsiakas);
				results.next();
				i++;
			}
			
			return palautettava;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	/**
	 * Vie SQL:ään ajotauulun tämän ajon tiedot tiedot
	 * 
	 * @author Sampo Savolainen
	 * 
	 * @param int ruokalinjojen määrä
	 * @param int kassat
	 * @param int asiakkaita
	 * @return palauttaa ajon ID:n
	 */
	private static int addRun(int ruokalinjat, int kassat, int asiakkaita, int ryhmia, double porrastusAika) {
		
		int ajoId = 1;
		ResultSet results;
		
		try {

			results = query("Select max(ajoId) from ajot");
			results.next();
			
			ajoId = results.getInt(1)+1;
			
			System.out.println("AjoId asetettu");
					
		} catch (SQLException e) {
			System.out.println("Ajon lisäys ei toiminut");
			e.printStackTrace();
		}
		
		try {
			query("insert into ajot (ruokalinjat, kassat, asiakkaita, ryhmia, porrastusaika) values ("+ 
			ruokalinjat + ", " +
			kassat + ", " + 
			asiakkaita + ", " + 
			ryhmia + ", " + 
			porrastusAika + ");"
			);
			System.out.println("");
		} catch (SQLException e) {
			System.out.println("Ajon lisäys ei toiminut");
			e.printStackTrace();
		}
		return ajoId;
	}
	
	/**
	 * Vie asiakastauluun yhden asiakkaan tiedot
	 * @author Sampo Savolainen
	 * 
	 * @param asiakas syötettävä asiakas
	 * @param ajoId Tämänhetkisen ajon id
	 */
	
	private static void addAsiakas(Asiakas asiakas, int ajoId) {
		
		try {
			/*
			System.out.println("insert into asiakkaat (saapumisaika, poistumisaika, ajoId) values (" + 
					asiakas.getSaapumisaika() + ", " +
					asiakas.getPoistumisaika() + ", " +
					ajoId
					);
					*/
			query("insert into asiakkaat (saapumisaika, poistumisaika, ajoId) values (" + 
			asiakas.getSaapumisaika() + ", " +
			asiakas.getPoistumisaika() + ", " +
			ajoId + ");"
			);
			
		} catch (SQLException e) {
			System.out.println("asiakkaan lisäys ei toiminut");
			e.printStackTrace();			
		}
	}
	
	/**
	 * Vie tapahtumatauluun yhden tapahtuman tiedot
	 * 
	 * @author Sampo Savolainen
	 * @param tapahtuma vietävä tapahtuma
	 * @param ajoId tämän ajon id
	 */
	
	private static void addTapahtuma(Tapahtuma tapahtuma, int ajoId) {
		try {
			/*
			System.out.println("insert into tapahtumat (tapahtumaAika, tapahtumaTyyppi, suunta, ajoId) values (" +
					tapahtuma.getAika() + ", " +
					tapahtuma.getTyyppi() + ", " +
					tapahtuma.getDirection() + ", " +
					ajoId + ");"
			);*/
			
			query("insert into tapahtumat (tapahtumaAika, tapahtumaTyyppi, suunta, ajoId) values (" +
					tapahtuma.getAika() + ", " +
					"\"" + tapahtuma.getTyyppi() + "\"" + ", " +
					tapahtuma.getDirection() + ", " +
					ajoId + ");"
			);
			
		} catch (SQLException e) {
			System.out.println("tapahtuman lisäys ei toiminut");
			e.printStackTrace();			
		}
	}
	
private static void addPP(Palvelupiste piste, int ajoId) {
		
			int maxJono = piste.getMaksimiJononKoko();
			double aktiiviaika = piste.getActiveTime();
			TapahtumanTyyppi tyyppi = piste.getTyyppi();
			
			try {
				query("insert into palvelupisteet (aktiiviaika, maxJono, ajoId, tyyppi) values(" + aktiiviaika + ", " +
						maxJono + ", " +
						ajoId + ", '" + 
						tyyppi + "');"
			);
			} catch (SQLException e) {
				System.out.println("Palvelupisteiden lisäys ei onnistunut");
				e.printStackTrace();
			}
		
	}

	@Override
	public int[] getAjo(int ajoId) {
		int[] palautettava = new int[3];
		
		try {
			ResultSet rs = query("select (ruokalinjat, kassat, asiakkaita) from ajot where ajoid=" + ajoId);
			
			rs.next();
			
			palautettava[0] = rs.getInt(1);
			palautettava[1] = rs.getInt(2);
			palautettava[2] = rs.getInt(3);
			palautettava[3] = rs.getInt(4);
			
			return palautettava;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ArrayList<Alkuarvot> getKaikkiAjot() {
		
		ArrayList<Alkuarvot> alkuarvot = new ArrayList<>();

		try {
			
			ResultSet results;
			results = query("Select count(ajoId) from ajot");
			results.next();
			
			
			int count = results.getInt(1);
			
			ResultSet rs = query("select ruokalinjat, kassat, asiakkaita, ryhmia, porrastusaika from ajot");
			rs.next();

			for(int i = 0; i < count; i++) {
				
				alkuarvot.add(new Alkuarvot(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getDouble(5)));
				rs.next(); 
				
			}
							
			return alkuarvot;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String[] getAjoTimeStamp() {
		
		try {
			
			ResultSet results;
			results = query("Select count(ajoId) from ajot");
			results.next();
			
			int count = results.getInt(1);
			
			String[] palautettava = new String[count];
			
			ResultSet rs = query("select ajoAika from ajot");
			
			rs.next();
			
			for(int i = 0; i < palautettava.length; i++) {
				
				palautettava[i] = rs.getString(1);
				rs.next();
				
			}
			
			return palautettava;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Tapahtuma> getTapahtumat(int ajoId) {
		
		
		
		ArrayList<Tapahtuma> tapahtumat = new ArrayList<Tapahtuma>();
		
		try {
			ResultSet rs = query("count (tapahtumaId) from tapahtuma where ajoId=" + ajoId);
			rs.next();
			int max = rs.getInt(1);
			
			rs = query("select (tapahtumaAika, tapahtumatyyppi, suunta) from tapahtumat where ajoId=" + ajoId);
			int i = 1;
			
			while (i <= max) {
				
				Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.DEP3, rs.getDouble(1));
				
				tapahtumat.add(tapahtuma);
			}
			
			return tapahtumat;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*
	 * Lataa ID:tä vastaavan ajon tiedot Tulos singletoniin sql:ästä
	 */
	
	public void lataaTuloksiin(int ajoId) {
		
		try {
		Alkuarvot ladattava = getAlkuarvot(ajoId);
		
		Tulokset.getInstance().setAlkuarvot(ladattava.getRyhmienMaara(), ladattava.getPorrastusMaara(), ladattava.getAsiakkaat(),ladattava.getRuokalinja(),ladattava.getKassat());
		Tulokset.getInstance().setSaapuneetasiakkaat(ladattava.getAsiakkaat());
		Tulokset.getInstance().setPoistuneetasiakkaat(ladattava.getAsiakkaat());
		
		} catch (SQLException e) {
			System.out.println("Alkuarvojen asettaminen ei onnistunut");
			e.printStackTrace();
		}
		// Ladataan asiakaslista
		
		try {
			
			ArrayList<Asiakas> asiakaslista = lataaAsiakkaat(ajoId);
			
			Tulokset.getInstance().setAsiakasLista(asiakaslista);
			Tulokset.getInstance().setAsiakkaat(asiakaslista.size());
			
		} catch (SQLException e) {
			System.out.println("Asiakkaiden lataus ei onnistunut");
			e.printStackTrace();
		}
		
		// ladataan palvelupisteet
		
		try {
			
			int linjastot = 0;
			int kassat = 0;
			
			for (Palvelupiste piste : lataaPalvelupisteet(ajoId)) {
				switch (piste.getTyyppi()) {
					case DEP1:
						linjastot++;
						break;
					case DEP2:
						kassat++;
						break;
					default:
						// muut ei kiinnosta tässä
						break;
				}
 			}
			
			Tulokset.getInstance().setPalvelupisteet(lataaPalvelupisteet(ajoId));
			
			Tulokset.getInstance().ruokalinja = linjastot;
			Tulokset.getInstance().kassat = kassat;
			
			
		} catch (SQLException e) {
			System.out.println("Palvelupisteiden lataus ei onnistunut");
			e.printStackTrace();
		}
		
		// ladataan tapahtumat
		
		
		
	}
	
	private Alkuarvot getAlkuarvot(int ajoId) throws SQLException {
		// ruokalinjat, kassat, asiakkaat, ryhmien määrä, porrastus
		ResultSet rs = query("select ruokalinjat, kassat, asiakkaita, asiakkaita, ryhmia, porrastusaika from ajot where ajoId=" + ajoId);
		rs.next();
		Alkuarvot ladattu = new Alkuarvot(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5));
		
		return ladattu;
	}
	
	/*
	 * Lataa ID:tä vastaavan ajon asiakkaat sql:ästä
	 */
	
	private ArrayList<Asiakas> lataaAsiakkaat(int ajoId) throws SQLException {
		
		ResultSet count = query("select count (asiakasId) from asiakkaat where ajoId=" + ajoId);
		count.next();
		int max = count.getInt(1);
		
		System.out.println(max + "...............................................................................................");
		ResultSet rs = query("select saapumisaika, poistumisaika from asiakkaat where ajoId=" + ajoId);
		
		ArrayList<Asiakas> asiakaslista = new ArrayList<Asiakas>();
		
		int i = 1;
		while (i <= max) {
			
			rs.next();
			
			Asiakas uusi = new Asiakas();
			uusi.setSaapumisaika(rs.getDouble(1));
			System.out.println("Saapumisaika: " + rs.getDouble(1));
			uusi.setPoistumisaika(rs.getDouble(2));
			System.out.println("Saapumisaika: " + rs.getDouble(2));
			
			asiakaslista.add(uusi);
			System.out.println("Asiakas ladattu: " + uusi.getId());
			i++;
		}
		
		for(Asiakas asiakas:asiakaslista) {
			System.out.println(asiakas.getId());
			
		}
		return asiakaslista;
	}
	
	
	/*
	 * Lataa ID:tä vastaavan ajon palvelupisteet sql:ästä
	 */
	private ArrayList<Palvelupiste> lataaPalvelupisteet(int ajoId) throws SQLException {
		
		ResultSet count = query("select count(ppId) from palvelupisteet where ajoId=" + ajoId);
		count.next();
		
		int max = count.getInt(1);
		
		ResultSet rs = query ("select aktiiviaika, maxJono, tyyppi from palvelupisteet where ajoId=" + ajoId);
		rs.next();
		
		ArrayList<Palvelupiste> pisteet = new ArrayList<Palvelupiste>();
		
		for (int i = 0; i < max; i++) {
			
			double active = rs.getDouble(1);
			int jono = rs.getInt(2);
			String tyyppi = rs.getString(3);
			
			Palvelupiste uusi = new Palvelupiste(active);
			uusi.setEnum(tyyppi);
			uusi.setMaxJononKoko(jono);
			
			pisteet.add(uusi);
		}
		
		return pisteet;
		
		
	}

}
