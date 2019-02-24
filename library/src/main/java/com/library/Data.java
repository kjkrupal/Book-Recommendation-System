package com.library;

public class Data {
	private String pubYear;
	private String author;
	
	public Data() {
		
	}
	
	public Data(String pubYear, String author) {
		super();
		this.pubYear = pubYear;
		this.author = author;
	}
	public String getPubYear() {
		return pubYear;
	}
	public void setPubYear(String pubYear) {
		this.pubYear = pubYear;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}	
}
