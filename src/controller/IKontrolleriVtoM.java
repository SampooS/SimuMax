package controller;

import java.util.ArrayList;

import simu.model.Alkuarvot;

public interface IKontrolleriVtoM {
	
		public void kaynnistaSimulointi();
		public void nopeuta();
		public void hidasta();
		public void tallenna();
		public void pause();
		public void getLoadlist();
		public void setAjolist();
		public void setLoadToTulokset(int index);
		public void getLoadTulokset();
		
}
