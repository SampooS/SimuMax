module simulaattorinrunko5 {
	exports controller;
	exports simu.model;
	exports view;
	exports testi;
	exports simu.framework;
	exports eduni.distributions;

	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires junit;
	requires org.junit.jupiter.api;
	
	opens view to javafx.fxml;
}