package com.javaPro.address;

public class AddressDTO {

	private String id;

	private String street;

	private String houseNo;

	private String city;

	private String state;

	private String country;

	private String latLong;

	public AddressDTO() {
		super();
	}

	public AddressDTO(String id, String street, String houseNo, String city, String state, String country,
			String latLong) {
		super();
		this.id = id;
		this.street = street;
		this.houseNo = houseNo;
		this.city = city;
		this.state = state;
		this.country = country;
		this.latLong = latLong;
	}

	public AddressDTO(Address domain) {
		super();
		this.id = domain.getId();
		this.street = domain.getStreet();
		this.houseNo = domain.getHouseNo();
		this.city = domain.getCity();
		this.state = domain.getState();
		this.country = domain.getCountry();
		this.latLong = domain.getLatLong();
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", houseNo=" + houseNo + ", city=" + city + ", state="
				+ state + ", country=" + country + ", latLong=" + latLong + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLatLong() {
		return latLong;
	}

	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}

}
