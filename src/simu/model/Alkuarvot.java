package simu.model;


public class Alkuarvot {

	
	private int ruokalinja, kassat, asiakkaat, ryhmienMaara, muuttuvaRyhma;
	private double porrastusMaara;

	public Alkuarvot(int ruokalinja, int kassat, int asiakkaat,int ryhmienMaara, double porrastusMaara) {
		
		this.ruokalinja = ruokalinja;
		this.kassat = kassat;
		this.asiakkaat = asiakkaat;
		this.ryhmienMaara = ryhmienMaara;
		this.porrastusMaara = porrastusMaara;
		
	}
	
	public int getRuokalinja() {
		return ruokalinja;
	}



	public int getKassat() {
		return kassat;
	}



	public int getAsiakkaat() {
		return asiakkaat;
	}



	public int getRyhmienMaara() {
		return ryhmienMaara;
	}



	public double getPorrastusMaara() {
		return porrastusMaara;
	}
	
	
	
	public void setPorrastusMaara() {
		
		
		
	}
}
