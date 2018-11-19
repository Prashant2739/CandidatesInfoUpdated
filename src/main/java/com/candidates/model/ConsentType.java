package com.candidates.model;

public class ConsentType {

	private String country;
	private String launguage;
	private String brand;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLaunguage() {
		return launguage;
	}
	public void setLaunguage(String launguage) {
		this.launguage = launguage;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "ConsentType [country=" + country + ", launguage=" + launguage
				+ ", brand=" + brand + "]";
	}



	

}
