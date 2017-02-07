package com.nvd.item;

public class vocabulary {
	private String WordEnglish;
	private String WordVietNam;
	private int Audio;
	private int icon_audio ;
	private int icon_yeuthich_true;
	private int icon_yeuthich_false;
	private int YEUTHICH ; 
	public vocabulary(String wordEnglish, String wordVietNam, int audio , int yeuthich) {
		super();
		WordEnglish = wordEnglish;
		WordVietNam = wordVietNam;
		Audio = audio;
		YEUTHICH = yeuthich;
	}
	public vocabulary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getYEUTHICH() {
		return YEUTHICH;
	}
	public void setYEUTHICH(int yEUTHICH) {
		YEUTHICH = yEUTHICH;
	}
	public String getWordEnglish() {
		return WordEnglish;
	}
	public void setWordEnglish(String wordEnglish) {
		WordEnglish = wordEnglish;
	}
	public String getWordVietNam() {
		return WordVietNam;
	}
	public void setWordVietNam(String wordVietNam) {
		WordVietNam = wordVietNam;
	}
	public int getAudio() {
		return Audio;
	}
	public void setAudio(int audio) {
		Audio = audio;
	}
	public int getIcon_audio() {
		return icon_audio;
	}
	public void setIcon_audio(int icon_audio) {
		this.icon_audio = icon_audio;
	}
	public int getIcon_yeuthich_true() {
		return icon_yeuthich_true;
	}
	public void setIcon_yeuthich_true(int icon_yeuthich_true) {
		this.icon_yeuthich_true = icon_yeuthich_true;
	}
	public int getIcon_yeuthich_false() {
		return icon_yeuthich_false;
	}
	public void setIcon_yeuthich_false(int icon_yeuthich_false) {
		this.icon_yeuthich_false = icon_yeuthich_false;
	}
	

}
