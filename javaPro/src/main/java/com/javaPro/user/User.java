package com.javaPro.user;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.javaPro.address.Address;
import com.javaPro.extraField.ExtraField;

@Document(collection = "User")
public class User extends ExtraField implements Serializable {

	private static final long serialVersionUID = -510550280949240768L;

	@Id
	private String id;

	@Field("User_Id")
	private String userId;

	@Field("Name")
	private String name;

	@Field("Roll_No")
	private String rollNo;

	@Field("Grade")
	private String grade;

	@Field("Salary")
	private double salary;

	@Field("Age")
	private int age;

	@Field("Address")
	private Address address;

	@Field("Contacts")
	private List<String> phoneNos;

	public User() {
		super();
	}

	public User(String id, String userId, String name, String rollNo, String grade, double salary, int age,
			Address address, List<String> phoneNos) {
		super();
		this.userId = userId;
		this.name = name;
		this.rollNo = rollNo;
		this.grade = grade;
		this.salary = salary;
		this.age = age;
		this.address = address;
		this.phoneNos = phoneNos;
	}

	public User(UserDTO dto) {
		super();
		this.userId = dto.getUserId();
		this.name = dto.getName();
		this.rollNo = dto.getRollNo();
		this.grade = dto.getGrade();
		this.salary = dto.getSalary();
		this.age = dto.getAge();
		this.address = new Address(dto.getAddress());
		this.phoneNos = dto.getPhoneNos();
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getPhoneNos() {
		return phoneNos;
	}

	public void setPhoneNos(List<String> phoneNos) {
		this.phoneNos = phoneNos;
	}

}
