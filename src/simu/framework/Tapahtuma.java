package simu.framework;

import simu.model.TapahtumanTyyppi;

public class Tapahtuma implements Comparable<Tapahtuma> {
	
	private TapahtumanTyyppi tyyppi;
	private double aika;
	private int direction;
	
	public Tapahtuma(TapahtumanTyyppi tyyppi, double aika){
		
		this.tyyppi = tyyppi;
		this.aika = aika;
		
	}
	
	public Tapahtuma(TapahtumanTyyppi tyyppi, double aika, int dir){
		
		this.tyyppi = tyyppi;
		this.aika = aika;
		this.direction = dir;
		
	}
	
	public void setTyyppi(TapahtumanTyyppi tyyppi) {
		
		this.tyyppi = tyyppi;
		
	}
	public TapahtumanTyyppi getTyyppi() {
		
		return tyyppi;
		
	}
	public void setAika(double aika) {
		
		this.aika = aika;
		
	}
	public double getAika() {
		
		return aika;
		
	}
	public void setDirection(int i) {
		
		this.direction = i;
		
	}
	public int getDirection() {
		
		return this.direction;
		
	}
	
	@Override
	public int compareTo(Tapahtuma arg) {
		
		if (this.aika < arg.aika) return -1;
		else if (this.aika > arg.aika) return 1;
		return 0;
		
	}
}
