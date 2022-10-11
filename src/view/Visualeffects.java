package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Visualeffects {
	
	
	public Visualeffects() {
		
		
		
		
		
	}
	
	
	
	public Glow setHoverOn(double maara) {
		
		Glow glow = new Glow();
		glow.setLevel(maara);
		
		return glow;
		
	}
	
	public Glow setHoverOff(double maara) {
		
		Glow glow = new Glow();
		glow.setLevel(maara);
		
		return glow;
			
	}
	
	public SepiaTone controlsetHoverOn(double maara) {
		
		SepiaTone sepia = new SepiaTone();
		sepia.setLevel(maara);
		
		return sepia;
		
	}
	
	public SepiaTone controlsetHoverOff(double maara) {
		
		SepiaTone sepia = new SepiaTone();
		sepia.setLevel(maara);
		
		return sepia;
			
	}
	
	
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

}
