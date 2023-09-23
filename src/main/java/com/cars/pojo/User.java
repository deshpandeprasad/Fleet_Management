package com.cars.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

import com.cars.pojo.Car;

@Component
@Entity
@Table(name="USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userid;
	
	@Column(unique = true)
	private String username;
	private String password;
	private String name;
	private String address;
	private String contact;
	private String role;
	
	@OneToMany(mappedBy="reservedByUser")
	private List<Car> reservedCars;
	
	@OneToMany(mappedBy="theUser")
	private List<Car> cars;

	public User() {

	}

	public User(String username, String password, String name,String address,String contact,String role) {
//		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.role = role;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Car> getReservedCars() {
		return reservedCars;
	}

	public void setReservedCars(List<Car> reservedCars) {
		this.reservedCars = reservedCars;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}