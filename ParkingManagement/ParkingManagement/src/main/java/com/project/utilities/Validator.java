package com.project.utilities;

public class Validator {

	// Validates input for the Car Registration Number
	public static boolean registrationNumberValidator(String regNo) {
		String regex = "[A-Z]{2}[- ][0-9]{2}[- ][A-Z]{2}[- ][0-9]{4}";
		if (regNo.toUpperCase().matches(regex)) {
			return true;
		}
		return false;
	}

	// Validates the input for color
	public static boolean colourValidator(String colour) {
		String regex = "[A-Za-z]{3,}";
		if (colour.matches(regex)) {
			return true;
		}
		return false;
	}
}
