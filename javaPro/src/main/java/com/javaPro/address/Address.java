package com.javaPro.address;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Address implements Serializable {

	private static final long serialVersionUID = 191342392236355473L;

	@Id
	private String id;

	@Field("Street")
	private String street;

	@Field("House_No")
	private String houseNo;

	@Field("City")
	private String city;

	@Field("State")
	private String state;

	@Field("Country")
	private String country;

	@Field("Lat_Long")
	private String latLong;

	public Address() {
		super();
	}

	public Address(String id, String street, String houseNo, String city, String state, String country, String latLong) {
		super();
		this.street = street;
		this.houseNo = houseNo;
		this.city = city;
		this.state = state;
		this.country = country;
		this.latLong = latLong;
	}

	public Address(AddressDTO dto) {
		super();
		this.street = dto.getStreet();
		this.houseNo = dto.getHouseNo();
		this.city = dto.getCity();
		this.state = dto.getState();
		this.country = dto.getCountry();
		this.latLong = dto.getLatLong();
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
