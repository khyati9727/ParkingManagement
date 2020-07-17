package com.project.model;

//Not public for smooth running of the application. If made public anyone can create an object and give random slot of parking.
public class Car {

	// Constructor for car class, initializing of variable can only done through
	// constructor. Made protected so that user can not directly access it. As it
	// would lead to crashing of application.
	public Car(String registrationNumber, String colour, Integer parkingSlot) {

		this.colour = colour;
		this.registrationNumber = registrationNumber;
		this.parkingSlot = parkingSlot;

	}

	// Refers to car registration number
	private String registrationNumber;

	// Refers to car color
	private String colour;

	// refer to the slot in which car in parked
	private Integer parkingSlot;

	// getter for getRegistrationNumber
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	// getter for getColour
	public String getColour() {
		return colour;
	}

	// getter for getParkingSlot
	public Integer getParkingSlot() {
		return parkingSlot;
	}

}
