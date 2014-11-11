package com.example.yourhealthyourrules;


public class Item {

	String name;
	String filename;
	
	public Item(String title, String file){
		name = title;
		filename = file;
		
	}
	
	public String getName(){
		return name;
	}
	
	public String getFile(){
		return filename;
	}
	
	

}
