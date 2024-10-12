module JH_Furniture_Store {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	
	opens main to javafx.graphics, javafx.fxml;
	exports model to javafx.base;
	opens model to javafx.base;
}
