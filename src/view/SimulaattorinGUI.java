package view;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import controller.IKontrolleriVtoM;
import controller.Kontrolleri;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class SimulaattorinGUI.
 * @author Otto Oksanen
 */
public class SimulaattorinGUI extends Application implements ISimulaattorinUI, Initializable{


	private IKontrolleriVtoM kontrolleri = new Kontrolleri(this);
	private IVisualisointi naytto;
	private Visualeffects visualeffects = new Visualeffects();
	private Visibility visibility;
	
	private final String ALKURUUTU = "SimulaattoriNakyma.fxml";

	@FXML private TextField asiakasMaara,ryhmaMaara,ruokalinjastoMaara,kassaMaara,porrastusMaara;

	@FXML private Pane simulaatioPane;
	@FXML private Pane asiakasPane;
	@FXML private Pane ruokaPane;
	@FXML private Pane kassaPane;
	@FXML private Pane tallennusPane;
	@FXML private Pane simuAnimaatioPane;
	@FXML private Pane infoPane;
	@FXML private Pane startButtons;
	@FXML private Pane simuButtons;
	@FXML private Pane tulosButtons;
	@FXML private Pane esitiedotpane;
	
	
	@FXML private Label asiakas,pisinruokajono,ruokajono,kassajono,pisinkassajono,kello,poistuneet;
	
	@FXML private Label asiakasFinal;
	@FXML private Label lapiAsiakkaat;
	@FXML private Label keskiAsiakkaat;
	@FXML private Label keskiJonoAsiakkaat;
	@FXML private Label asiakasPalveluAika;
	
	@FXML private Label ruokaLinjastoPalveltuAsiakas;
	@FXML private Label aktiiviaika;
	@FXML private Label kayttoaste;
	@FXML private Label keskiKayttoaste;
	@FXML private Label suoritusteho;
	@FXML private ImageView ruokaaktiivi;
	@FXML private ImageView ruokakaytto;
	@FXML private ImageView ruokakeskikaytto;
	
	
	@FXML private Label kassaPalveltuAsiakas;
	@FXML private Label kassaAktiiviaika;
	@FXML private Label kassaKayttoaste;
	@FXML private Label kassaKeskiKayttoaste;
	@FXML private Label kassaSuoritusteho;
	@FXML private ImageView kassaaktiivi;
	@FXML private ImageView kassakaytto;
	@FXML private ImageView kassakeskikaytto;
	@FXML private ImageView kassasuoritusteho;
	
	@FXML private Label esiasiakas;
	@FXML private Label esiruokalinja;
	@FXML private Label esikassa;
	@FXML private Label esiryhmia;
	@FXML private Label esiporrastusaika;
	
	@FXML private ImageView lopeta;
	@FXML private ImageView ready;
	@FXML private ImageView write;
	@FXML private ImageView read;
	
	@FXML private ImageView kaynnista;
	@FXML private ImageView hidasta;
	@FXML private ImageView nopeuta;
	@FXML private ImageView load;
	
	@FXML private ImageView ruokalinjatButton;
	@FXML private ImageView kassatButton;
	@FXML private ImageView tallennuksetButton;
	@FXML private ImageView asiakasButton;
	
	@FXML private ImageView vihreapikkuvalo;
	@FXML private ImageView punainenpikkuvalo;
	
	@FXML private Pane alkupane;
	
	@FXML ImageView ruokajonopalkki;
	@FXML ImageView maxruokajonopalkki;
	@FXML ImageView kassajonopalkki;
	@FXML ImageView maxkassajonopalkki;
	@FXML ImageView saapuneetPalkki;
	@FXML ImageView poistuneetPalkki;
	@FXML ImageView kelloPalkki;
	@FXML AnchorPane taustakuvapane;
	@FXML VBox simu1;
	@FXML VBox simu2;
	@FXML VBox simu3;
	@FXML ImageView nappi1;
	@FXML ImageView nappi2;
	
	
	@FXML private ImageView saapunut;
	@FXML private ImageView poistunut;
	@FXML private ImageView lapimeno;
	@FXML private ImageView jonotusaika;
	@FXML private ImageView palveluaika;
	
	@FXML Pane tulospane;
	@FXML Pane ekasivunButtonit;
	@FXML Pane tokasivunButtonit;
	
	ArrayList<Pane> panelista;
	@FXML private ListView<String> loadlist;
	
	


	@Override
	public void start(Stage primaryStage) throws Exception {
				
        Parent root = FXMLLoader.load (getClass ().getResource (ALKURUUTU));
        Scene scene = new Scene (root, 1280, 720);
        primaryStage.setScene (scene);
        primaryStage.setFullScreenExitHint ("");
        primaryStage.setResizable (false);
        primaryStage.centerOnScreen ();
        primaryStage.setFullScreen (false);
        primaryStage.show ();
                               	
	}
	 
	
	/**
	 * Vaihtaa loadnäkymään. 
	 */
	@FXML
	public void loadRuutu() {
		
		startButtons.setMouseTransparent(true);
		tuoLoadRuutu();
		visibility.siirryTulosNakymaan(simuAnimaatioPane, tulospane, ekasivunButtonit, tokasivunButtonit);
		visibility.setPaneVisibility(3);
		
	}
	
	
	/**
	 * Hidastaa simulaation kulkua. 
	 */
	@FXML
	private void hidasta() {
		
		kontrolleri.hidasta();
		
	}
	
	
	/**
	 * Nopeuttaa simulaation kulkua. 
	 */
	@FXML
	private void nopeuta() {
			
		kontrolleri.nopeuta();
		
	}
	

	/**
	 * Sulkee sovelluksen. 
	 */
	@FXML
	private void lopeta() {
		
		System.exit(0);	
		
	}
	
	
	/**
	 * Hakee tietokannasta vanhojen ajojen aloitusparametrit. 
	 */
	@FXML
	private void tallennusEsitiedot() {
		
		kontrolleri.setAjolist();
			
	}
	
	
	/**
	 * Poistaa porrastustekstin. 
	 */
	@FXML
	public void pyyhiPorrastusTeksti() {
		
		porrastusMaara.setText("");
		
	}
	
	/**
	 * Palauttaa simulaation asiakasmäärän. 
	 */
	@Override
	public int getAsiakasMaara() {
		
		return Integer.parseInt(asiakasMaara.getText());
		
	}
	
	/**
	 * Palauttaa simulaation ruokalinjastojen määrän. 
	 */
	@Override
	public int getRuokalinjojenMaara() {
		
		return Integer.parseInt(ruokalinjastoMaara.getText());
		
	}
	
	/**
	 * Palauttaa simulaation kassojen määrän. 
	 */
	@Override
	public int getKassojenMaara() {
		
		return Integer.parseInt(kassaMaara.getText());
			
	}
		
	
	/**
	 * Palauttaa simulaation ryhmämäärän. 
	 */
	@Override
	public int getRyhmienMaara() {
		
		if(!ryhmaMaara.getText().isEmpty()) {
			
			return Integer.parseInt(ryhmaMaara.getText());
			
		}else {
			
			return 1;
			
		}		
	}
	
	
	/**
	 * Palauttaa simulaation porrastusajan. 
	 */
	@Override
	public double getPorrastusAika() {
		
		if(!porrastusMaara.getText().isEmpty()) {
			
			return Double.parseDouble(porrastusMaara.getText());
			
		}else {
			
			return 0;
			
		}	
	}
	
	/**
	 * Palauttaa simulaation maksimiajan. 
	 */
	@Override
	public double getAika(){
		
		return 18600000;
		
	}

	/**
	 * Palauttaa simulaation aloitus viiveen. 
	 */
	@Override
	public long getViive(){
		
		return 30;
		
	}

	@Override
	public IVisualisointi getVisualisointi() {
		
		 return naytto;
		 
	}
	
	public static void main(String[] args) {
		launch(args);
		 
	}
	
	
	/**
	 * Luo hover-efektin kutsumalla Visualeffects-luokan metodia. 
	 */
	@FXML
	private void hoverOn(Event e) {

		ImageView image = (ImageView) e.getSource();
		image.setEffect(visualeffects.setHoverOn(1));	
		
	}
	
	
	/**
	 * Luo hover-efektin kutsumalla Visualeffects-luokan metodia. 
	 */
	@FXML
	private void hoverOff(Event e) {
		
		ImageView image = (ImageView) e.getSource();
		image.setEffect(visualeffects.setHoverOff(0));
			
	}
	
	
	/**
	 * Luo hover-efektin kutsumalla Visualeffects-luokan metodia. 
	 */
	@FXML
	private void controlHoverOn(Event e) {

		ImageView image = (ImageView) e.getSource();
		image.setEffect(visualeffects.controlsetHoverOn(0.5));	
		
	}
	
	/**
	 * Luo hover-efektin kutsumalla Visualeffects-luokan metodia. 
	 */
	@FXML
	private void controlHoverOff(Event e) {
		
		ImageView image = (ImageView) e.getSource();
		image.setEffect(visualeffects.controlsetHoverOff(0));
			
	}
	
	@FXML
	private void siirryTulosNakymaan() {
		
		visibility.siirryTulosNakymaan(simuAnimaatioPane, tulospane, ekasivunButtonit, tokasivunButtonit);
		visibility.setPaneVisibility(4);
		punainenpikkuvalo.setVisible(true);
		ekasivunButtonit.setVisible(false);
		tokasivunButtonit.setVisible(true);
		tokasivunButtonit.setOpacity(0.87);
		
	}
	
	/**
	 * Avaa tulosnäkymän. 
	 */
	@FXML
	private void siirryEkalleSivulle() {
		
		visibility.siirrySimulaatioSivulle(simuAnimaatioPane, tulospane, ekasivunButtonit, tokasivunButtonit);
		visibility.setPaneVisibility(666);
		punainenpikkuvalo.setVisible(false);
		ekasivunButtonit.setVisible(true);
		ekasivunButtonit.setOpacity(0.87);
		tokasivunButtonit.setVisible(false);
		simu1.setOpacity(1);
		simu2.setOpacity(1);
		simu3.setOpacity(1);
			
	}
	
	/**
	 * Avaa asiakasnäkymän. 
	 */
	@FXML
	private void asiakasPaneeli() {
			
		visibility.setPaneVisibility(0);
		
	}
	
	
	/**
	 * Avaa linjastonäkymän. 
	 */
	@FXML
	private void ruokaPaneeli() {
		
		visibility.setPaneVisibility(1);	
		
	}
	
	/**
	 * Avaa kassatnäkymän. 
	 */
	@FXML
	private void kassaPaneeli() {
		
		visibility.setPaneVisibility(2);
		
	}
	
	
	/**
	 * Avaa loadnäkymän ja lataa tallennusten esitiedot visualisointiluokan käsiteltäväksi. 
	 */
	@FXML
	private void tallennusPaneeli() {
		
		kontrolleri.getLoadlist();
		visibility.setPaneVisibility(3);
		
	}
	
	/**
	 * Kutsuu Visualeffects luokan metodia boxBlurToBackground(), joka asettaa visuaalisen efektin taustakuvalle. 
	 */
	private void boxBlurToBackground() {
		
		visualeffects.boxBlurToBackground(taustakuvapane);
		
	}
	
	
	/**
	 * Kutsuu tuoSimulaatioRuutu()-metodia, joka käynnistää simulaation. 
	 */
	@FXML
	public void kaynnistaSimulaatio() {
	
		tuoSimulaatioRuutu();
		
	}
	
	
	/**
	 * Tuo simulaationäkymän ja käynnistää simulaation. 
	 */
	private void tuoSimulaatioRuutu() {
		
		simulaatioPane.setOpacity(0);
		simu1.setOpacity(0);
		simu2.setOpacity(0);
		simu3.setOpacity(0);
		tulospane.setVisible(false);
		simulaatioPane.setVisible(true);
		ekasivunButtonit.setOpacity(0);
   		ekasivunButtonit.setVisible(true);
   		
		
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(alkupane.opacityProperty(),1),
						new KeyValue(startButtons.opacityProperty(),0.87)),
				new KeyFrame(Duration.seconds(1),
						new KeyValue(alkupane.opacityProperty(),0),
						new KeyValue(startButtons.opacityProperty(),0)),
				new KeyFrame(Duration.seconds(1),
						new KeyValue(simulaatioPane.opacityProperty(),0),
						new KeyValue(ekasivunButtonit.opacityProperty(),0)),
				new KeyFrame(Duration.seconds(2),
						new KeyValue(simulaatioPane.opacityProperty(),1),
						new KeyValue(ekasivunButtonit.opacityProperty(),0.87)),
				new KeyFrame(Duration.seconds(2.5)));
		
		
		timeline.play();
		
        timeline.setOnFinished(actionEvent -> {
  
    		simu1.setOpacity(1);
    		simu2.setOpacity(1);
    		simu3.setOpacity(1);
       		kontrolleri.kaynnistaSimulointi();
    		simulaatioPane.setVisible(true);
       	
        });	
	}
	
	/**
	 * Vaihtaa näkymän aloitus näkymään. 
	 */
	@FXML
	private void startRuutuun() {
		
		startButtons.setVisible(true);
		startButtons.setMouseTransparent(false);
		
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(simulaatioPane.opacityProperty(),1),
						new KeyValue(ekasivunButtonit.opacityProperty(),1),
						new KeyValue(tokasivunButtonit.opacityProperty(),0.87)),
				new KeyFrame(Duration.seconds(1),
						new KeyValue(simulaatioPane.opacityProperty(),0),
						new KeyValue(ekasivunButtonit.opacityProperty(),0),
						new KeyValue(tokasivunButtonit.opacityProperty(),0)),
				new KeyFrame(Duration.seconds(1),
						new KeyValue(alkupane.opacityProperty(),0),
						new KeyValue(startButtons.opacityProperty(),0)),
				new KeyFrame(Duration.seconds(2),
						new KeyValue(alkupane.opacityProperty(),1),
						new KeyValue(startButtons.opacityProperty(),0.87)),
				new KeyFrame(Duration.seconds(2.5)));
		
		timeline.play();
		timeline.setOnFinished(actionEvent -> {
			
			simulaatioPane.setVisible(false);
			ekasivunButtonit.setVisible(false);
			tokasivunButtonit.setVisible(false);
				
		});	
	}
	
	
	/**
	 * Vaihtaa aloitusnäkymän load näkymään. 
	 */
	private void tuoLoadRuutu() {
		
		simulaatioPane.setOpacity(0);
		simulaatioPane.setVisible(true);
		simu1.setOpacity(0);
		simu2.setOpacity(0);
		simu3.setOpacity(0);
		tokasivunButtonit.setOpacity(0);
		
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(alkupane.opacityProperty(),1),
						new KeyValue(startButtons.opacityProperty(),0.87)),
				new KeyFrame(Duration.seconds(1),
						new KeyValue(alkupane.opacityProperty(),0),
						new KeyValue(startButtons.opacityProperty(),0)),
				new KeyFrame(Duration.seconds(1),
						new KeyValue(simulaatioPane.opacityProperty(),0),
						new KeyValue(tokasivunButtonit.opacityProperty(),0),
						new KeyValue(tokasivunButtonit.visibleProperty(),true)),
				new KeyFrame(Duration.seconds(2),
						new KeyValue(simulaatioPane.opacityProperty(),1),
						new KeyValue(tokasivunButtonit.opacityProperty(),0.87)),
				new KeyFrame(Duration.seconds(2.5)));
		
		
		timeline.play();
        timeline.setOnFinished(actionEvent -> {
  
    		simu1.setOpacity(1);
    		simu2.setOpacity(1);
    		simu3.setOpacity(1);
    		ekasivunButtonit.setMouseTransparent(false);
    		tokasivunButtonit.setMouseTransparent(false);
    		ekasivunButtonit.setVisible(false);
    		startButtons.setVisible(false);
    		kontrolleri.getLoadlist();
    		
        });
	}
	
	
	/**
	 * Lataa tulokset tietokannasta ja lähettää ne Visualisointiluokan käsiteltäväksi. 
	 */
	@FXML
	public void lataaVanhaAjoTuloksiin() {
		
		kontrolleri.setLoadToTulokset(loadlist.getSelectionModel().getSelectedIndex() + 1);  
		kontrolleri.getLoadTulokset();
		
	}

	
	/**
	 * Initialisoi visuaaliset komponentit. 
	 */
    @Override
    public final void initialize(URL url, ResourceBundle resourceBundle) {

		boxBlurToBackground();
		
    	panelista = new ArrayList<>();
    	panelista.add(asiakasPane);
    	panelista.add(ruokaPane);
    	panelista.add(kassaPane);
    	panelista.add(tallennusPane);
    	panelista.add(infoPane);
   
    	visibility = new Visibility(panelista);
    	
		naytto = 
		new Visualisointi(
		simulaatioPane, pisinruokajono,pisinkassajono,
		asiakas,kello, poistuneet, ruokajono, kassajono,
		ruokajonopalkki, maxruokajonopalkki,kassajonopalkki,
		maxkassajonopalkki,saapuneetPalkki,poistuneetPalkki,
		kelloPalkki);
			
		naytto.setAsiakasPane(
		asiakasFinal, lapiAsiakkaat, keskiAsiakkaat, 
		keskiJonoAsiakkaat, asiakasPalveluAika,saapunut,
		poistunut,lapimeno,jonotusaika,palveluaika); 
		
		naytto.setRuokalinjastoPane(aktiiviaika, ruokaLinjastoPalveltuAsiakas, 
		kayttoaste, suoritusteho, keskiKayttoaste,ruokaaktiivi,ruokakaytto,ruokakeskikaytto);
		
		naytto.setKassaPane(kassaAktiiviaika, kassaPalveltuAsiakas, kassaKayttoaste, kassaSuoritusteho, kassaKeskiKayttoaste,
		kassaaktiivi,kassakaytto,kassakeskikaytto, kassasuoritusteho);
		
		naytto.setLoadPane(loadlist,esitiedotpane,esiasiakas,esiruokalinja,esikassa,esiryhmia,esiporrastusaika);
		
    }
}
