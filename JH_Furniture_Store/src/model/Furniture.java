package model;

public class Furniture {
	private String ID;
	private String name;
	private String type;
	private Integer price;
	
	public Furniture(String ID, String name, String type, Integer price) {
		this.ID = ID;
		this.name = name;
		this.type = type;
		this.price = price;
	}
	
	
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
