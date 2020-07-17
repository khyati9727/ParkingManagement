package com.project.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Parking {

	// Maintains the status of slots status, By keeping track of Cars Parked In them
	private Map<Integer, Car> parkingStatus = new HashMap<Integer, Car>();;

	// Holds the Status of empty Slots TreeSet as slots have unique numbers and must
	// be alloted in increasing order
	private TreeSet<Integer> emptySlots = new TreeSet<Integer>();

	// Constructor
	public Parking(int n) {
		for (int i = 1; i <= n; i++) {
			parkingStatus.put(i, null);
			emptySlots.add(i);
		}
	}

	// add method for ParkingStatus
	public void addParkingStatus(Integer slot, Car car) {
		parkingStatus.put(slot, car);
	}

	// remove method for ParkingStatus
	public void removeParkingStatus(Integer slot) {
		parkingStatus.put(slot, null);
	}

	// add method for EmptySlots
	public void addEmptySlots(Integer slot) {
		emptySlots.add(slot);
	}

	// remove method for EmptySlots
	public void removeEmptySlots(Integer slot) {
		emptySlots.remove(slot);
	}

	// Getter method for ParkingStatus
	public Map<Integer, Car> getParkingStatus() {
		return parkingStatus;
	}

	// Getter method for EmptySlots
	public TreeSet<Integer> getEmptySlots() {
		return emptySlots;
	}

}
