package com.nvd.item;

public class Topic {
	private int bestdiem;
	private String nameTopic;

	public Topic(int bestDiem, String nameTopic) {
		super();
		this.bestdiem = bestDiem;
		this.nameTopic = nameTopic;
	}

	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBestdiem() {
		return bestdiem;
	}

	public void setBestdiem(int bestdiem) {
		this.bestdiem = bestdiem;
	}

	public String getNameTopic() {
		return nameTopic;
	}

	public void setNameTopic(String nameTopic) {
		this.nameTopic = nameTopic;
	}

}
