package com.nvd.item;

public class Topic {
	private int icon ; 
	private String nameTopic;
	public Topic(int icon, String nameTopic) {
		super();
		this.icon = icon;
		this.nameTopic = nameTopic;
	}
	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getNameTopic() {
		return nameTopic;
	}
	public void setNameTopic(String nameTopic) {
		this.nameTopic = nameTopic;
	} 
	

}
