package controller;

import java.util.ArrayList;

public interface IKontrolleriMtoV {
	
		//public void naytaLoppuaika(double aika);
		
		public void naytaKellonAika(double aika);
		
		
		public void naytaPisinJonoKassoille(int jonopituus);
		public void naytaKassaJononPituus(int maara);
		public void naytaPisinJonoRuokalinjastolle(int maara);
		public void naytaRuokaJononPituus(int maara);
		
		
		public void visualisoiAsiakas();
		public void naytaLapiPaasseetAsiakkaat();
		public void naytaKeskiOdotusAika(double aika);
		public void naytaAsiakkaanLapimenoAika(double aika);
		public void naytaAsiakkaidenKeskimaarainenPalveluaika(double aika);
		
		
		public void naytaRuokalinjastoAsiakkaatPalveltu(int maara);
		public void naytaRuokalinjastonKayttoaste(double maara);
		public void naytaRuokalinjastonKeskikayttoaste(double maara);
		public void naytaRuokalinjastonSuoritusteho(double maara);
		public void naytaRuokalinjastonAktiiviaika(double aika);
		
		
		public void naytaKassaAsiakkaatPalveltu(int maara);
		public void naytaKassaKayttoaste(double maara);
		public void naytaKassaKeskikayttoaste(double maara);
		public void naytaKassaSuoritusteho(double maara);
		public void naytaKassaAktiiviaika(double aika);
		
		public void tallenna();
		public void setLoadlist(ArrayList<String> lista);
		
}
