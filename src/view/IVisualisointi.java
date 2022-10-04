package view;


public interface IVisualisointi {

	public void tyhjennaNaytto();	
	public void uusiAsiakas();
	public void setPisinJonoKassoille(int jononpituus);
	public void setJonoKassoille(int maara);		
	public void setKassojenAktiiviaika(double aika);
	public void setAsiakkaidenKeskimaarainenPalveluaika(double ka);
	public void setPisinJonoRuokalinjastolle(int maara);
	public void setJonoRuokalinjastolle(int maara);
	public void setLapiPaasseetAsiakkaat();
	public void setRuokalinjastonAktiiviaika(double aika);
	public void setKellonAika(double aika);
	public void setOdotusAika(double aika);
	public void setAsiakasPaneVisibility(boolean visible);
	public void setAsiakkaanLapimenoAika(double aika);
		
}

