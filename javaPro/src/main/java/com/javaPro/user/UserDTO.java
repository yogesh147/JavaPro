package com.javaPro.user;

import java.util.List;

import com.javaPro.address.AddressDTO;

public class UserDTO {

	private String id;

	private String userId;

	private String name;

	private String rollNo;

	private String grade;

	private double salary;

	private int age;

	private AddressDTO address;

	private List<String> phoneNos;

	public UserDTO() {
		super();
	}

	public UserDTO(String id, String userId, String name, String rollNo, String grade, double salary, int age,
			AddressDTO address, List<String> phoneNos) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.rollNo = rollNo;
		this.grade = grade;
		this.salary = salary;
		this.age = age;
		this.address = address;
		this.phoneNos = phoneNos;
	}

	public UserDTO(User domain) {
		super();
		this.id = domain.getId();
		this.userId = domain.getUserId();
		this.name = domain.getName();
		this.rollNo = domain.getRollNo();
		this.grade = domain.getGrade();
		this.salary = domain.getSalary();
		this.age = domain.getAge();
		this.address = new AddressDTO(domain.getAddress());
		this.phoneNos = domain.getPhoneNos();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", name=" + name + ", rollNo=" + rollNo + ", grade=" + grade
				+ ", salary=" + salary + ", age=" + age + ", address=" + address + ", phoneNos=" + phoneNos + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public List<String> getPhoneNos() {
		return phoneNos;
	}

	public void setPhoneNos(List<String> phoneNos) {
		this.phoneNos = phoneNos;
	}

}
