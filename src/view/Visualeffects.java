package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Class Visualeffects.
 * @author Otto Oksanen
 */
public class Visualeffects {
	
	
	/**
	 * Luo hoverefektin. 
	 */
	public Glow setHoverOn(double maara) {
		
		Glow glow = new Glow();
		glow.setLevel(maara);
	
		return glow;
		
	}
	
	/**
	 * Luo hoverefektin. 
	 */
	public Glow setHoverOff(double maara) {
		
		Glow glow = new Glow();
		glow.setLevel(maara);
		
		return glow;
			
	}
	
	/**
	 * Luo hoverefektin. 
	 */
	public SepiaTone controlsetHoverOn(double maara) {
		
		SepiaTone sepia = new SepiaTone();
		sepia.setLevel(maara);
		
		return sepia;
		
	}
	
	/**
	 * Luo hoverefektin. 
	 */
	public SepiaTone controlsetHoverOff(double maara) {
		
		SepiaTone sepia = new SepiaTone();
		sepia.setLevel(maara);
		
		return sepia;
			
	}
	
	/**
	 * Luo boxblurefektin. 
	 */
	public void boxBlurToBackground(Pane pane) {
		
		BoxBlur blur = new BoxBlur();
		
		blur.setHeight(0);
		blur.setWidth(0);
		
		pane.setEffect(blur);
		
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(blur.widthProperty(), 0)),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(blur.widthProperty(), 7)),
                new KeyFrame(Duration.seconds(2.5),
                        new KeyValue(blur.widthProperty(), 0)),
                new KeyFrame(Duration.seconds(7),
                        new KeyValue(blur.widthProperty(), 7)),
                new KeyFrame(Duration.seconds(7.5),
                        new KeyValue(blur.widthProperty(), 0)),
                new KeyFrame(Duration.seconds(10),
                        new KeyValue(blur.widthProperty(), 0)
               )
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();	
		
	}
	
	
	/**
	 * Vaihtaa jonoanimaatio palkkien väriä suhteessa siihen kuinka hyväksyttävällä tasolla jonon koko on. 
	 */
	public ColorAdjust setVariJonoPalkeille(int maara) {
		
		ColorAdjust vari = new ColorAdjust();
		
		if(maara < 100) {
			
			vari.setHue(0);
			vari.setSaturation(0);
			
		}else if(maara > 100 && maara < 150) {
			
			vari.setSaturation(0.1);
			vari.setHue(-0.45);
			
		}else if(maara > 150 && maara < 200) {
			
			vari.setSaturation(0.25);
			vari.setHue(-0.65);
			
		}else if(maara > 200) {
			
			vari.setSaturation(0.75);
			vari.setHue(-0.65);
			
		}
		
		return vari;
			
	}
}
