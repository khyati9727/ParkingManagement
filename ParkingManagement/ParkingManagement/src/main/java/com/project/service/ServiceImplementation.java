package com.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.project.model.Car;
import com.project.model.Parking;

//Implementing interface ParkingSlot helps in maintaing loose coupling 
public class ServiceImplementation implements Service {

	private Parking parking;
	static Service service;
	Integer n;

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	private ServiceImplementation(int n) {
		this.n = n;
		parking = new Parking(n);
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public static Service getInstance(int n) {
		service = new ServiceImplementation(n);
		return service;
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
//		Method used for allotting parking space
//		If parking is full throws Exception "Sorry, parking lot is full"	
//		else nearest empty parking will be alloted
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public Integer park(String registerationNo, String colour) throws Exception {
		if (parking.getEmptySlots().isEmpty()) {
			throw new Exception("Sorry, parking lot is full");
		}
		Integer slot = parking.getEmptySlots().pollFirst(); // poll method returns the smallest value available
		parking.addParkingStatus(slot, new Car(registerationNo, colour, slot));
		parking.removeEmptySlots(slot);
		return slot;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//		Method used for removing the allotted space for the car leaving
//		Error thrown If the slot was Previously empty and still u try to remove allotted space 
//		If alloted returns true		
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean leave(Integer slot) throws Exception {
		if (!parking.getEmptySlots().add(slot)) {
			throw new Exception("THE SPACE WAS PREVIOUSLY VACANT");
		}
		parking.removeParkingStatus(slot);
		parking.addEmptySlots(slot);
		return true;
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//		Method returns the list of cars that have same colour as the parameter
// 		This method is private because this is a helping method for methods slotNoOfCarColour(String colour) & registrationNoOfCarColour(String colour) 		
//		The comparison is not case sensitive 
//		If no such car found Exception thrown "NO MATCH FOUND"
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	private List<Car> numberOfCarOfColour(String colour) throws Exception {
		List<Car> response = new ArrayList<Car>();
		for (Map.Entry<Integer, Car> entry : parking.getParkingStatus().entrySet()) {
			if (entry.getValue() != null && entry.getValue().getColour().equalsIgnoreCase(colour)) {
				response.add(entry.getValue());
			}
		}
		if (response.isEmpty()) {
			throw new Exception("NO MATCH FOUND");
		}
		return response;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
//		Method calls numberOfCarOfColour of this class 	
//		Returns the List containing slots only
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		

	public List<Integer> slotNoOfCarColour(String colour) throws Exception {
		List<Car> list = numberOfCarOfColour(colour);
		List<Integer> result = new ArrayList<Integer>();
		for (Car car : list) {
			result.add(car.getParkingSlot());
		}
		return result;
	}

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//		Method calls numberOfCarOfColour of this class
//		Returns the List containing Registration Number only		
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		

	public List<String> registrationNoOfCarColour(String colour) throws Exception {
		List<Car> list = numberOfCarOfColour(colour);
		List<String> result = new ArrayList<String>();
		for (Car car : list) {
			result.add(car.getRegistrationNumber());
		}
		return result;
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
//		Method returns the slot allotted for the car with registration number
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public Integer getParkingSlot(String registrationNo) throws Exception {
		for (Map.Entry<Integer, Car> map : parking.getParkingStatus().entrySet()) {
			if (map.getValue() != null && map.getValue().getRegistrationNumber().equalsIgnoreCase(registrationNo)) {
				return map.getKey();
			}
		}
		throw new Exception("Not Found");
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//		Method returns the status 
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		

	public Map<Integer, Car> getStatus() throws Exception {
		Map<Integer, Car> result = new HashMap<Integer, Car>();
		for (Map.Entry<Integer, Car> entry : parking.getParkingStatus().entrySet()) {
			if (entry.getValue() != null) {
				result.put(entry.getKey(), entry.getValue());
			}
		}
		if (result.isEmpty()) {
			throw new Exception("Parking Lot Empty");
		}
		return result;
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//		Getter method for EmptySlots
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public TreeSet<Integer> getEmptySlots() {
		return parking.getEmptySlots();
	}

}
