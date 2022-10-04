package view;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigaattori extends Application{
	
	private final String ALKURUUTU = "SimulaattoriNakyma.fxml";
	private final Stage MAINSTAGE = new Stage ();

	@Override
	public void 
	start(Stage arg0) throws Exception {	
		
        Parent root = FXMLLoader.load (getClass ().getResource (ALKURUUTU));
        Scene scene = new Scene (root, 1280, 720);
            MAINSTAGE.setScene (scene);
                MAINSTAGE.setFullScreenExitHint ("");
                    MAINSTAGE.setResizable (false);
                        MAINSTAGE.centerOnScreen ();
                            MAINSTAGE.setFullScreen (false);
                                MAINSTAGE.show ();
		
	}
	
    public void
    changeScene (String fxml) throws IOException {

        Parent pane = FXMLLoader.load (getClass ().getResource (fxml));
        MAINSTAGE.getScene ().setRoot (pane);
        
    }
}
