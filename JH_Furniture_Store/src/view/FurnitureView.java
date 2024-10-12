package view;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Furniture;
import util.FurnitureMethod;

public class FurnitureView {
	
	private Scene scene;
	private BorderPane mainLayout;
	private FurnitureMethod fm = new FurnitureMethod();
	
	private FurnitureMethod method = new FurnitureMethod();
	
	private Label IdLbl, nameLbl, typeLbl, priceLbl, addNewFurniture;
	private TextField idField, nameField, priceField;
	private ComboBox<String> furnitureType;
	
	private Button addFurniture;
	private ObservableList<Furniture> furniture_list = FXCollections.observableArrayList();
	
	private TableView<Furniture> table;
	
	public void setTable() {
		table = new TableView<Furniture>();
		
		TableColumn<Furniture, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		
		TableColumn<Furniture, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Furniture, String> typeColumn = new TableColumn<>("Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		TableColumn<Furniture, Integer> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
	    idColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.15));  // 15% of table width
	    nameColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.35)); // 35% of table width
	    typeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25)); // 25% of table width
	    priceColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25)); // 25% of table width

	    table.setPrefWidth(600);
	    table.setPrefHeight(300);

	    table.setMinWidth(700);
	    table.setMinHeight(400);
		
		table.getColumns().add(idColumn);
		table.getColumns().add(nameColumn);
		table.getColumns().add(typeColumn);
		table.getColumns().add(priceColumn);
		
		furniture_list.addAll(fm.getData());
		table.getItems().addAll(furniture_list);
	}

	public void init() {
		mainLayout = new BorderPane();
		
		setTop();
		setCenter();
		setBottom();
		
		scene = new Scene(mainLayout, 1220, 700);
	}
	
	public void setTop() {
		GridPane gp = new GridPane();
		setTable();
		addNewFurniture = new Label("Add New Furniture");
		GridPane.setHalignment(addNewFurniture, HPos.CENTER);
		
		gp.add(table, 0, 0);
		gp.add(addNewFurniture, 0, 1);
		
		gp.setAlignment(Pos.CENTER);
		
		table.setMinWidth(800);
		
		gp.setPadding(new Insets(20,0,0,0));
		gp.setVgap(10);
		
		mainLayout.setTop(gp);
	}
	
	public void setCenter() {
		GridPane gp = new GridPane();
		IdLbl = new Label("Furniture ID");
		nameLbl = new Label("Furniture Name");
		typeLbl = new Label("Furniture Type");
		priceLbl = new Label("Furniture Price");
		
		idField = new TextField();
		nameField = new TextField();
		priceField = new TextField();
		
		idField.setText(method.getLastId());
		idField.setEditable(false);
		
		furnitureType = new ComboBox<String>();
		furnitureType.getItems().addAll("Chair", "Bed", "Table", "Wardrobe");
		
		gp.add(IdLbl, 0, 0);
		gp.add(idField, 1, 0);
		
		gp.add(nameLbl, 0, 1);
		gp.add(nameField, 1, 1);
		
		gp.add(typeLbl, 0, 2);
		gp.add(furnitureType, 1, 2);
		
		gp.add(priceLbl, 0, 3);
		gp.add(priceField, 1, 3);
		
		gp.setAlignment(Pos.CENTER);
		
		gp.setHgap(10);
		gp.setVgap(20);
		
		mainLayout.setCenter(gp);
	}
	
	public void setBottom() {
		addFurniture = new Button("Add Furniture");
		BorderPane.setAlignment(addFurniture, Pos.CENTER);
		mainLayout.setBottom(addFurniture);
		
		BorderPane.setMargin(addFurniture, new Insets(0,0,20,0));
	}
	
	public void start(Stage stage) {
		init();
		stage.setScene(scene);
		stage.setTitle("JH Furniture Store");
		stage.setResizable(false);
		
		stage.show();
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}	

	public BorderPane getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(BorderPane mainLayout) {
		this.mainLayout = mainLayout;
	}

	public Label getIdLbl() {
		return IdLbl;
	}

	public void setIdLbl(Label idLbl) {
		IdLbl = idLbl;
	}

	public Label getNameLbl() {
		return nameLbl;
	}

	public void setNameLbl(Label nameLbl) {
		this.nameLbl = nameLbl;
	}

	public Label getTypeLbl() {
		return typeLbl;
	}

	public void setTypeLbl(Label typeLbl) {
		this.typeLbl = typeLbl;
	}

	public Label getPriceLbl() {
		return priceLbl;
	}

	public void setPriceLbl(Label priceLbl) {
		this.priceLbl = priceLbl;
	}

	public Label getAddNewFurniture() {
		return addNewFurniture;
	}

	public void setAddNewFurniture(Label addNewFurniture) {
		this.addNewFurniture = addNewFurniture;
	}

	public TextField getIdField() {
		return idField;
	}

	public void setIdField(TextField idField) {
		this.idField = idField;
	}

	public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

	public TextField getPriceField() {
		return priceField;
	}

	public void setPriceField(TextField priceField) {
		this.priceField = priceField;
	}

	public ComboBox<String> getFurnitureType() {
		return furnitureType;
	}

	public void setFurnitureType(ComboBox<String> furnitureType) {
		this.furnitureType = furnitureType;
	}

	public Button getAddFurniture() {
		return addFurniture;
	}

	public void setAddFurniture(Button addFurniture) {
		this.addFurniture = addFurniture;
	}

	public ObservableList<Furniture> getFurniture_list() {
		return furniture_list;
	}

	public void setFurniture_list(ObservableList<Furniture> furniture_list) {
		this.furniture_list = furniture_list;
	}

	public TableView<Furniture> getTable() {
		return table;
	}

	public void setTable(TableView<Furniture> table) {
		this.table = table;
	}
	
	
}
