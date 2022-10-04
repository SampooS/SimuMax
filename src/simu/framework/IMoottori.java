package simu.framework;

public interface IMoottori {
		
	public void setSimulointiaika(double aika);
	public void setViive(long aika);
	public void setSimulointiLahtoArvot(int ruokalinja, int rekisteri, int asiakkaat, int ryhmaMaara, double porrastusMaara);
	public long getViive();

}
