package view;


import controller.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class SimulaattorinGUI extends Application implements ISimulaattorinUI{


	private IKontrolleriVtoM kontrolleri = new Kontrolleri(this);
	private IVisualisointi naytto;
	
	private final String ALKURUUTU = "SimulaattoriNakyma.fxml";

	@FXML private TextField asiakasMaara,ryhmaMaara,ruokalinjastoMaara,kassaMaara,porrastusMaara;
	@FXML private ImageView lopeta;
	@FXML private Pane simulaatioPane, asiakasPane;
	
	@FXML private Label asiakas,pisinruokajono,ruokajono,kassajono,pisinkassajono,kello,poistuneet,
						asiakasFinal,lapiAsiakkaat,keskiAsiakkaat,keskiJonoAsiakkaat;
	
	@FXML private Button kaynnista,hidasta,nopeuta,tallennuksetButton,pauseButton,
						 kassatButton,ruokalinjatButton,asiakasButton;
	
	@FXML private ImageView tuloksiin, ekalleSivulle;
	@FXML private AnchorPane toinentausta, mustaEkaruutu, mustaToinenruutu;

	
	@Override
	public void init(){
		
		Trace.setTraceLevel(Level.INFO);
        simulaatioPane = new Pane();
        asiakas = new Label();
        pisinruokajono = new Label();
        ruokajono = new Label();
        kassajono = new Label();
        pisinkassajono = new Label();
        kello = new Label();
        poistuneet = new Label();
        tuloksiin = new ImageView();
        ekalleSivulle = new ImageView();
        toinentausta = new AnchorPane();
        mustaEkaruutu = new AnchorPane();
        mustaToinenruutu = new AnchorPane();
        asiakasPane = new Pane();
        asiakasFinal = new Label();
        lapiAsiakkaat = new Label();
        keskiAsiakkaat = new Label();
        keskiJonoAsiakkaat = new Label();
      		
	}


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
	 
	
	@FXML
	public void kaynnistaSimulaatio() {
		
		naytto = 
		new Visualisointi(
		simulaatioPane, pisinruokajono,pisinkassajono,
		asiakas,kello, poistuneet, ruokajono, kassajono,asiakasPane,
		asiakasFinal,lapiAsiakkaat,keskiAsiakkaat,keskiJonoAsiakkaat);
		
		kontrolleri.kaynnistaSimulointi();
		
	}
	
	@FXML
	public void hidasta() {
		
		kontrolleri.hidasta();
		
	}
	
	@FXML
	public void nopeuta() {
			
		kontrolleri.nopeuta();
		
	}
	
	@FXML
	public void pause() {
		
		kontrolleri.pause();
		
	}
	
	
	@FXML
	public void lopeta() {
		
		System.exit(0);	
		
	}
	
	@FXML
	public void pyyhiPorrastusTeksti() {
		
		porrastusMaara.setText("");
		
	}
	
	
	@FXML
	public void setAsiakasPaneVisibility() {
		
		naytto.setAsiakasPaneVisibility(true);		
		
	}
	
	public int getAsiakasMaara() {
		
		return Integer.parseInt(asiakasMaara.getText());
		
	}
	
	public int getRuokalinjojenMaara() {
		
		return Integer.parseInt(ruokalinjastoMaara.getText());
		
	}
	
	public int getKassojenMaara() {
		
		return Integer.parseInt(kassaMaara.getText());
			
	}
		
	@Override
	public int getRyhmienMaara() {
		
		return Integer.parseInt(ryhmaMaara.getText());
		
	}

	@Override
	public double getPorrastusAika() {
		
		return Double.parseDouble(porrastusMaara.getText());
		
	}
	
	@Override
	public double getAika(){
		
		// 3.5 Tuntia, tämä on ravintolan ruokailun oikean maailman aika. 
		return 12600000;
		
	}

	@Override
	public long getViive(){
		
		return 50;
		
	}

	/*
	@Override
	public void setLoppuaika(double aika){
		
		 this.kello.setText(String.format("%.2f", aika/1000));
	}

*/

	@Override
	public IVisualisointi getVisualisointi() {
		
		 return naytto;
		 
	}
	

	public static void main(String[] args) {
		launch(args);
		 
	}
	
	
	public void hoverOn() {
			
		Glow glow = new Glow();
		glow.setLevel(1);
		tuloksiin.setEffect(glow);
		ekalleSivulle.setEffect(glow);
		
	}
	
	public void hoverOff() {
		
		Glow glow = new Glow();
		glow.setLevel(0);
		tuloksiin.setEffect(glow);
		ekalleSivulle.setEffect(glow);
		
	}
	
	
	public void siirryTulosNakymaan() {
		
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(toinentausta.layoutXProperty(),toinentausta.getLayoutX()),
						new KeyValue(mustaEkaruutu.opacityProperty(),mustaEkaruutu.getOpacity()),
						new KeyValue(mustaToinenruutu.opacityProperty(),mustaToinenruutu.getOpacity())),
				new KeyFrame(Duration.seconds(0.75),
						new KeyValue(toinentausta.layoutXProperty(),toinentausta.getLayoutX() - 1340),
						new KeyValue(mustaEkaruutu.opacityProperty(),mustaEkaruutu.getOpacity() + 1),
						new KeyValue(mustaToinenruutu.opacityProperty(),mustaToinenruutu.getOpacity() - 1)));
		
		timeline.play();
		
		
		
	}
	
	public void siirryEkalleSivulle() {
		
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO,
						new KeyValue(toinentausta.layoutXProperty(),toinentausta.getLayoutX()),
						new KeyValue(mustaEkaruutu.opacityProperty(),mustaEkaruutu.getOpacity()),
						new KeyValue(mustaToinenruutu.opacityProperty(),mustaToinenruutu.getOpacity())),
				new KeyFrame(Duration.seconds(0.75),
						new KeyValue(toinentausta.layoutXProperty(),toinentausta.getLayoutX() + 1340),
						new KeyValue(mustaEkaruutu.opacityProperty(),mustaEkaruutu.getOpacity() - 1),
						new KeyValue(mustaToinenruutu.opacityProperty(),mustaToinenruutu.getOpacity() + 1)));
		
		timeline.play();
			
		naytto.setAsiakasPaneVisibility(false);
		
		
	}	
}
