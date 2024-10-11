package controller;

import main.Main;
import util.FurnitureMethod;
import view.FurnitureView;

public class Controller {
	private FurnitureView view;
	private FurnitureMethod method;

	public Controller(FurnitureView view) {
		this.view = view;
		this.method = new FurnitureMethod();
		
		this.view.getAddFurniture().setOnAction(e -> {
			this.method.addFurniture(view.getIdField(), 
					view.getNameField(), view.getFurnitureType(), 
					view.getPriceField());
		});
	}
}
