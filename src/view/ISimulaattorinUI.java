package view;

public interface ISimulaattorinUI {
	
	public double getAika();
	public long getViive();
	public int getAsiakasMaara();
	public int getRuokalinjojenMaara();
	public int getKassojenMaara();
	public int getRyhmienMaara();
	public double getPorrastusAika();
	public void setSimulaatioLoppui();
	//public void setLoppuaika(double aika);
	public IVisualisointi getVisualisointi();

}
