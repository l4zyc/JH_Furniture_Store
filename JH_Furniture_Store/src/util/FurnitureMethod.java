package util;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Furniture;

public class FurnitureMethod {
	
	private Connect connect = Connect.getInstance();
	
	public void showAlert(AlertType type, String content) {
		Alert alert = new Alert(type);
		alert.setContentText(content);
		alert.setTitle("Error");
		alert.showAndWait();
	}
	
	public void insertData(String ID, String fName, String fType, Integer fPrice) {
		String Query = String.format(
				"INSERT INTO MsFurniture (FurnitureID, FurnitureName, FurnitureType, FurniturePrice) VALUES "
				+ "('%s', '%s', '%s', '%d')"
				, ID, fName, fType, fPrice);
		
		connect.execUpdate(Query);
		
		showAlert(AlertType.INFORMATION, "Data Added!");
	}
	
	public void addFurniture(TableView<Furniture> table, ObservableList<Furniture> f_list, TextField fiD, TextField fName, ComboBox<String> fType, TextField fPrice) {
		String ID, name, type;
		Integer price = 0;
		
		ID = fiD.getText(); 
		name = fName.getText(); 
		type = fType.getSelectionModel().getSelectedItem();
		
		try {
			price = Integer.parseInt(fPrice.getText());
		} catch(Exception e) {
			System.out.println("A Problem Occured");
		}
		
		if(!ID.startsWith("FR") || ID.equals(null)) {
			showAlert(AlertType.ERROR, "ID doesnt start with FR");
			return;
		}
		
		if(fType.getSelectionModel().isEmpty()) {
			showAlert(AlertType.ERROR, "Type was not chosen");
			return;
		}
		
		if(price < 0) {
			showAlert(AlertType.ERROR, "Price is less than 0");
			return;
		}
		
		fName.setText("");
		fPrice.setText("");
		fType.getSelectionModel().select(0);
		
		insertData(ID, name, type, price);
		
		fiD.setText(getLastId());
		refreshTable(f_list, table);
	}
	
	public String getLastId(){
		String query = "SELECT FurnitureID FROM msfurniture "
				+ "ORDER BY FurnitureID "
				+ "DESC LIMIT 1";
		
		String lastID = "";

		try {
			connect.rs = connect.execQuery(query);
			
			if((!connect.rs.next())) {
				return "FR001";
			}
			
			lastID = connect.rs.getString("FurnitureID");
			String num = lastID.substring(2);
			Integer incr = Integer.parseInt(num) + 1;
			
			lastID = String.format("FR%03d", incr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lastID;
	}
	
	public ArrayList<Furniture> getData() {
		String query = "SELECT * FROM msfurniture";
		
		connect.rs = connect.execQuery(query);
		
		ArrayList<Furniture> list_of_furniture = new ArrayList<Furniture>();
		
		try {
			while(connect.rs.next()) {
				
				String ID = connect.rs.getString("FurnitureID");
				String fName = connect.rs.getString("FurnitureName");
				String fType = connect.rs.getString("FurnitureType");
				Integer fPrice = connect.rs.getInt("FurniturePrice");
				
				list_of_furniture.add(new Furniture(ID, fName, fType, fPrice));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list_of_furniture;
	}
	
	public void refreshTable(ObservableList<Furniture> furniture_list, TableView<Furniture> table) {
	    furniture_list.clear(); 
	    furniture_list.addAll(getData());
	    table.setItems(furniture_list);
	}
	
	
}
