package com.test.struts2.form.actions;

public class City {
	private Integer cityId;
	private String cityName;
	
	public City() {
		super();
	}
	public City(Integer cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
}
