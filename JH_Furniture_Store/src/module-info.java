module JH_Furniture_Store {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.sql;
	
	opens main to javafx.graphics, javafx.fxml;
}
