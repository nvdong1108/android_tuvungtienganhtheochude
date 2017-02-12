package com.nvd.item;

public class vocabulary {
	private int id;
	private String eng;
	private String vn;
	private String phatam;
	private int yeuthich;
	private int hinhanh;
	private String nametable;

	public vocabulary(int id, String eng, String vn, String phatam,
			int yeuthich, int hinhanh, String NameTable) {
		super();
		this.id = id;
		this.eng = eng;
		this.vn = vn;
		this.phatam = phatam;
		this.yeuthich = yeuthich;
		this.hinhanh = hinhanh;
		this.nametable = NameTable;
	}

	public String getNametable() {
		return nametable;
	}

	public void setNametable(String nametable) {
		this.nametable = nametable;
	}

	public vocabulary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEng() {
		return eng;
	}

	public void setEng(String eng) {
		this.eng = eng;
	}

	public String getVn() {
		return vn;
	}

	public void setVn(String vn) {
		this.vn = vn;
	}

	public String getPhatam() {
		return phatam;
	}

	public void setPhatam(String phatam) {
		this.phatam = phatam;
	}

	public int getYeuthich() {
		return yeuthich;
	}

	public void setYeuthich(int yeuthich) {
		this.yeuthich = yeuthich;
	}

	public int getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(int hinhanh) {
		this.hinhanh = hinhanh;
	}

}
