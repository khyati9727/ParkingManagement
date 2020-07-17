package com.project.service;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.project.model.Car;

public interface Service {

	public Integer park(String registerationNo, String colour) throws Exception;

	public boolean leave(Integer slot) throws Exception;

	public List<Integer> slotNoOfCarColour(String colour) throws Exception;

	public List<String> registrationNoOfCarColour(String colour) throws Exception;

	public Integer getParkingSlot(String registrationNo) throws Exception;

	public Map<Integer, Car> getStatus() throws Exception;

	public TreeSet<Integer> getEmptySlots();

}
