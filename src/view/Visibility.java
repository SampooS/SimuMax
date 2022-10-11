package view;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class Visibility {
	
	private ArrayList<Pane> panelista;
	
	public Visibility(ArrayList<Pane> panelista) {
		
		this.panelista = panelista;
		
	}
	
	public void siirrySimulaatioSivulle(Pane simu, Pane tulos, Pane simubuttons, Pane tulosbuttons) {
		
		simu.setVisible(true);
		simubuttons.setVisible(true);
		simubuttons.setMouseTransparent(false);
		
		tulos.setVisible(false);
		tulos.setMouseTransparent(true);
		tulosbuttons.setVisible(false);
		tulosbuttons.setMouseTransparent(true);
		
	}
	
	public void siirryTulosNakymaan(Pane simu, Pane tulos, Pane simubuttons, Pane tulosbuttons) {
		
		simu.setVisible(false);
		simubuttons.setVisible(false);
		simubuttons.setMouseTransparent(true);
		
		tulos.setVisible(true);
		tulos.setMouseTransparent(false);
		tulosbuttons.setVisible(true);
		tulosbuttons.setMouseTransparent(false);
		
	}
	
	public void setPaneVisibility(int index) {
		
		for(int i = 0; i < panelista.size(); i++) {
			
			if(index == 666) {
				
				panelista.get(i).setVisible(false);
				
			}else if(i == index){
				
				panelista.get(i).setVisible(true);
				
			}else {
				
				panelista.get(i).setVisible(false);
				
			}
		}
	}

}
