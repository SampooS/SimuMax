module simulaattorinrunko5 {
	exports controller;
	exports simu.model;
	exports view;
	exports testi;
	exports simu.framework;
	exports eduni.distributions;

	requires java.desktop;
	requires javafx.base;
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires junit;
	requires org.junit.jupiter.api;
	requires java.sql;
	requires transitive javafx.graphics;
	
	opens view to javafx.fxml, javafx.controls;
}