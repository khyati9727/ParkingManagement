package com.project.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import com.project.model.Car;
import com.project.service.Service;
import com.project.service.ServiceImplementation;

public class ParkingApplicationTest {

	@Parameter
	public static Service service;

	// Method Setting Initial Values for test
	@BeforeClass
	public static void start() throws Exception {
		service = ServiceImplementation.getInstance(6);
		service.park("KA-01-HH-1234", "White");
		service.park("KA-01-HH-9999", "White");
		service.park("KA-01-BB-0001", "Black");
		service.park("KA-01-HH-7777", "Red");
		service.park("KA-01-HH-2701", "Blue");
		service.park("KA-01-HH-3141", "Black");

	}

	// Leaving of car Check slot made empty or not
	@Test
	public void leaveTest() throws Exception {
		assertFalse(service.getEmptySlots().contains(4)); // It is expected to not be in empty slot list, this assert
															// checks whether the slot was in the emptyList attribute or
															// not
		assertTrue(service.leave(4)); // successful leave method returns true
		assertTrue(service.getEmptySlots().contains(4)); // It is expected to be in empty slot list as it is made empty
															// in previous call, this assert checks whether the slot is
															// in the emptyList attribute or not
	}

	// Parking Full Error Check By overloading parking cars
	@Test(expected = Exception.class)
	public void parkingFullTest() throws Exception {
		service.park("KA-01-P-333", "White"); // This will be parked as the slot 4 was empty
		service.park("DL-12-AA-9999", "White"); // this will throw error as the slot was not empty
	}

	@Test
	public void statusTest() throws Exception {

		service.leave(4);
		Map<Integer, Car> map = service.getStatus(); // this returns the parking slots that filled

		for (Integer x : service.getEmptySlots()) { // emptySlots is a list of slots that are empty
			assertFalse(map.containsKey(x)); // checking all emptySlots in map, should return false
		}

	}

	// checks the list of slots of cars with same colour as sent in parameter
	@SuppressWarnings("deprecation")
	@Test
	public void slotOfCarsOfColourTest() throws Exception {

		// Any of these can be used to call the method White,WHITE,WHitE,white

		List<Integer> expected = Arrays.asList(2, 1);
		Collections.sort(expected); // sorting the list because the out put received is always in increasing order
		List<Integer> actual = service.slotNoOfCarColour("white");

		Iterator<Integer> i1 = expected.iterator();
		Iterator<Integer> i2 = actual.iterator();

		// Case 1, checking the two lists are equal or not. the order is same as we get
		// the answer in increasing order. Reason for sorted response is, initially we
		// have made parking slot in increasing order which is maintained in arrayList
		// and when we add in this list it will be inserted increasing order only.
		while (i1.hasNext() && i2.hasNext()) {
			assertEquals(i1.next(), i2.next());
		}

		// Case 2, this also needs sorted expected
		assertThat(actual, is(expected));

		// Case 3, checking existence of actual elements in expected an vice versa using
		// two for each
		for (Integer x : expected) {
			assertTrue(actual.contains(x));
		}
		for (Integer x : actual) {
			assertTrue(expected.contains(x));
		}
	}

	// checks the list of registartion number of cars with same colour as sent in
	// parameter
	@SuppressWarnings("deprecation")
	@Test
	public void registrationOfCarOfColourTest() throws Exception {

		// Any of these can be used to call the method White,WHITE,WHitE,white

		List<String> expected = Arrays.asList("KA-01-HH-1234", "KA-01-HH-9999");
		List<String> actual = service.registrationNoOfCarColour("white");

		// Case 1, this will only work when expected is sorted in order of slot (The
		// data provided in expected is sorted on changing order will fail)
		assertThat(actual, is(expected));

		// Case 2, checking existence of actual elements in expected an vice versa using
		// two for each
		for (String x : expected) {
			assertTrue(actual.contains(x));
		}
		for (String x : actual) {
			assertTrue(expected.contains(x));
		}

	}

	// Trying to get slot of car in parking lot.
	@Test
	public void slotCarCheckTest1() throws Exception {
		assertEquals((Integer) 6, service.getParkingSlot("KA-01-HH-3141"));
	}

	// Trying to get slot of car not in parking lot, expected an exception
	@Test(expected = Exception.class)
	public void slotCarCheckTest2() throws Exception {
		service.getParkingSlot("MH-04-AY-1111");
	}

}
