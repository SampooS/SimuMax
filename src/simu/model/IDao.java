package simu.model;

import java.util.ArrayList;

import simu.framework.*;

public interface IDao {	
	abstract void tallennaAjo(ArrayList<Tapahtuma> tapahtumat);
	abstract ArrayList<Asiakas> getAsiakkaat(int ajoId);
	abstract int[] getAjo(int ajoId);
	abstract ArrayList<Tapahtuma> getTapahtumat(int ajoId);
}
