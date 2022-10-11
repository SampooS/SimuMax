package testi;
import javafx.application.Application;
import javafx.stage.Stage;
import simu.framework.*;
import simu.framework.Trace.Level;
import view.Navigaattori;
import view.SimulaattorinGUI;


public class Simulaattori extends Application { 
	
	
	private SimulaattorinGUI simulaattorigui = new SimulaattorinGUI();

	public static void main(String[] args) {
		
		Trace.setTraceLevel(Level.INFO);
		
		
		//Application.launch(Navigaattori.class,args);
		
		
		view.SimulaattorinGUI.main(args);

	}
	
	public static double getEndTime() {return 18600000;}

	@Override
	public void start(Stage arg0) throws Exception {
	
		simulaattorigui.start(arg0);
	}
}
