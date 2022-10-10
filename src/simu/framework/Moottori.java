package simu.framework;

import controller.IKontrolleriMtoV;
import simu.model.Palvelupiste;

public abstract class Moottori extends Thread implements IMoottori{
	
	private double simulointiaika = 0;
	private long viive = 0;
	
	private Kello kello;
	
	protected Tapahtumalista tapahtumalista;
	protected Palvelupiste[] palvelupisteet;
	protected IKontrolleriMtoV kontrolleri;
	
	public Moottori(IKontrolleriMtoV kontrolleri){

		kello = Kello.getInstance(); 	
		tapahtumalista = new Tapahtumalista();
		this.kontrolleri = kontrolleri; 	
		
	}

	public void setSimulointiaika(double aika) {
		
		simulointiaika = aika;

	}
	
	@Override 
	public void setViive(long viive) {
		
		this.viive = viive;
		
	}
	
	@Override 
	public long getViive() {
		
		return viive;
		
	}
	
	
	public void run(){
		
		alustukset(); 
		
		while (simuloidaan()){
					
			viive(); 
			
			kello.setAika(nykyaika());
		
			suoritaBTapahtumat();

			yritaCTapahtumat();	
				
		}
		
		tulokset();
		
	}
	
	private void suoritaBTapahtumat(){
		
		try {
			
				while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
					
					suoritaTapahtuma(tapahtumalista.poista());
					
				}	
			
		}catch(Exception e) {
			
			System.out.println("Asiakkaat loppuivat");
		}

	}

	private void yritaCTapahtumat(){
					
			for (Palvelupiste p: palvelupisteet){
				
				try {
					
					if (!p.onVarattu() && p.onJonossa()){
						
						p.aloitaPalvelu();
						
				}
					
					
				}catch(Exception e) {
					
					e.getStackTrace();
					
				}
				

		}
	}
	
	private void viive() { 

		try {
			
			sleep(viive);
			
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	

	private double nykyaika(){
		
		return tapahtumalista.getSeuraavanAika();
			
	}
	
	private boolean simuloidaan(){
		
		return kello.getAika() < simulointiaika;
		
	}
	
	
			

	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa
	
}