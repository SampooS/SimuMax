package controller;

public interface IKontrolleriMtoV {
	
		//public void naytaLoppuaika(double aika);
		public void visualisoiAsiakas();
		public void naytaPisinJonoKassoille(int jonopituus);
		public void naytaKassaJononPituus(int maara);
		public void naytaKassojenActive(double aika);
		public void naytaAsiakkaidenKeskimaarainenPalveluaika(double aika);
		public void naytaPisinJonoRuokalinjastolle(int maara);
		public void naytaRuokaJononPituus(int maara);
		public void naytaLapiPaasseetAsiakkaat();
		public void naytaRuokalinjastonAktiiviaika(double aika);
		public void naytaKellonAika(double aika);
		public void naytaKeskiOdotusAika(double aika);
		public void naytaAsiakkaanLapimenoAika(double aika);
}
