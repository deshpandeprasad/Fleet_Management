package com.cars.pojo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Entity
@Table(name="CARS")
public class Car {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long carId;
	
	@Column(unique=true)
	private String plate;
	
	private String carname;
	private String carbrand;
	
	private LocalDate returnDate = null;
	private LocalDate bookingStartDate = null; //startReservationDate
	private LocalDate bookingEndDate = null;
	private boolean readyForPickUp = false;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	private User reservedByUser;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	private User theUser;

	
//public Car() {
//		
//	}
//
//
//	public Car(String plate,String carname, String carbrand) {
//		super();
//		this.plate=plate;
//		this.carname = carname;
//		this.carbrand = carbrand;
//
//	}
	
	@Column(name = "picture")
	private String imagePath;
	
	@Transient
	private MultipartFile imageFile;

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}
	
	
	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public String getCarbrand() {
		return carbrand;
	}

	public void setCarbrand(String carbrand) {
		this.carbrand = carbrand;
	}

	public User getTheUser() {
		return theUser;
	}

	public void setTheUser(User theUser) {
		this.theUser = theUser;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	
	public LocalDate getBookingEndDate() {
		return bookingEndDate;
	}
	
	public void setBookingEndDate(LocalDate bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}
	
	public LocalDate getBookingStartDate() {
		return bookingStartDate;
	}
	
	public void setBookingStartDate(LocalDate bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}
	
	public User getReservedByUser() {
		return reservedByUser;
	}
	
	public void setReservedByUser(User reservedByUser) {
		this.reservedByUser = reservedByUser;
	}
	
	public void setReadyForPickup(boolean readyForPickUp) {
		this.readyForPickUp = readyForPickUp;
	}
	
	public boolean getReadyForPickUp() {
		return readyForPickUp;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
}
