package com.project.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import com.project.model.Car;
import com.project.service.Service;
import com.project.service.ServiceImplementation;

public class User {
	// STATIC VARIABLES FOR UNIFORM FUNCTIONING OF APPLICATION
	static Service service;
	static Integer n;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Integer slot;

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//			FORMAT ASKED FOR USER COMMAND LINE INTERACTION (COMMENT THIS AND UNCOMMENT THE CODE PART COMMENTED BELOW FOR A DIFFERENT IMPLEMENTATION OF APPLICATION)
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		

//		String line;
//		// WHILE LOOP FOR READING INPUT, TILL AVAILABLE
//		while ((line = br.readLine()) != null) {
//			String[] str = line.split(" ");
//
//			// SWITCH CASE TAKES THE FIRST ELEMENT AS COMMAND AND REST ELEMENTS AS PARAMETER
//			switch (str[0].toLowerCase()) {
//
//			case "create_parking_lot":
//				// STORING SIZE IN N
//				n = Integer.parseInt(str[1]);
//
//				// CREATING Service TYPE OBJECT
//				service = ServiceImplementation.getInstance(n);
//				System.out.println("Created a parking lot with " + n + " slots");
//				break;
//
//			case "park":
//				// VALIDATORS FROM VALIDATOR CLASS
//				if (!Validator.registrationNumberValidator(str[1]) && !Validator.colourValidator(str[2])) {
//					System.out.println("incorrect statement type");
//					break;
//				}
//
//				// IF VAILD INPUT ENTERS TRY BLOCK ELSE GOES TO NEXT INPUT
//				try {
//					System.out.println("Allocated slot number:" + service.park(str[1], str[2]));
//				}
//				// IF PARKING IS FULL CATCHS ERROR
//				catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//				break;
//
//			case "leave":
//				slot = Integer.parseInt(str[1]);
//				// CHECKING VALID SLOT IN RANGE OF PARKING LOT
//				if (slot > n || slot < 1) {
//					System.out.println("incorrect statement type");
//					break;
//				}
//				// IF VAILD INPUT ENTERS TRY BLOCK ELSE GOES TO NEXT INPUT
//				try {
//					service.leave(slot);
//					System.out.println("Slot number " + slot + " is free");
//				}
//				// IF SLOT WAS EMPTY PREVIOUSLY THROWS ERROR
//				catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//				break;
//
//			case "status":
//				try {
//					Map<Integer, Car> map = service.getStatus();
//					System.out.println("Slot No.\tRegistration No\t\tColor");
//					for (Map.Entry<Integer, Car> entry : map.entrySet()) {
//						System.out.println(entry.getKey() + "\t\t" + entry.getValue().getRegistrationNumber() + "\t\t"
//								+ entry.getValue().getColour());
//					}
//				}
//				// IF NO CAR IN PARKING LOT CATCHS ERROR
//				catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//				break;
//
//			case "registration_numbers_for_cars_with_colour":
//				if (!Validator.colourValidator(str[1])) {
//					System.out.println("incorrect statement type");
//					break;
//				}
//				// IF VAILD INPUT ENTERS TRY BLOCK ELSE GOES TO NEXT INPUT
//				try {
//					String com = "";
//					for (String s : service.registrationNoOfCarColour(str[1])) {
//						System.out.print(com + s);
//						com = ", ";
//					}
//					System.out.println();
//				}
//				// IF NO CAR IN PARKING LOT MATCH THE CONDITION CATCHS ERROR
//				catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//				break;
//
//			case "slot_numbers_for_cars_with_colour":
//				if (!Validator.colourValidator(str[1])) {
//					System.out.println("incorrect statement type");
//					break;
//				}
//				// IF VAILD INPUT ENTERS TRY BLOCK ELSE GOES TO NEXT INPUT
//				try {
//					String com = "";
//					for (Integer s : service.slotNoOfCarColour(str[1])) {
//						System.out.print(com + s);
//						com = ", ";
//					}
//					System.out.println();
//				}
//				// IF NO CAR IN PARKING LOT MATCH THE CONDITION CATCHS ERROR
//				catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//				break;
//
//			case "slot_number_for_registration_number":
//				if (!Validator.registrationNumberValidator(str[1])) {
//					System.out.println("incorrect statement type");
//					break;
//				}
//				// IF VAILD INPUT ENTERS TRY BLOCK ELSE GOES TO NEXT INPUT
//				try {
//					System.out.println(service.getParkingSlot(str[1]));
//				}
//				// IF NO CAR IN PARKING LOT MATCH THE CONDITION CATCHS ERROR
//				catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//				break;
//
//			default:
//				System.out.println("Invalid Operation");
//
//			}// SWITCH CASE
//		} // WHILE

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//									UNCOMMENT THE BELOW CODE AND COMMENT THE ABOVE CODE FOR A DIFFERENT TYPE OF IMPLEMENTATION OF APPLICATION
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		

		System.out.println("Welcome to Parking Manager");
		boolean exit = false;

		String regNo, colour;

		System.out.println("Enter the number of spaces in Parking lot");
		n = Integer.parseInt(br.readLine().trim());

		service = ServiceImplementation.getInstance(n);
		System.out.println("Created a parking lot with " + n + " slots");

		while (!exit) {

			System.out.println("press 1: To park A Car");
			System.out.println("press 2: To make a car exit");
			System.out.println("press 3: To get status of parking");
			System.out.println("press 4: To get Registration numbers for cars with same colour");
			System.out.println("press 5: To get slot number for cars with same colour");
			System.out.println("press 6: To get parking slot of car using registration number");
			System.out.println("press 7: To Exit Application");
			try {
				Integer x = Integer.parseInt(br.readLine().trim());

				switch (x) {
				case 1:
					System.out.println("Enter Registration Number");
					regNo = br.readLine().trim();

					// Re-enter Until valid input is received (Validators for registration number
					// and colour used from Validator class)
					while (!Validator.registrationNumberValidator(regNo)) {
						System.out.println(
								"Re-Enter Registration Number XX-XX-XX-XXXX format '-' can be replaced with ' '");
						regNo = br.readLine().trim();
					}
					System.out.println("Enter Colour");
					colour = br.readLine().trim();
					while (!Validator.colourValidator(colour)) {
						System.out.println("Entered incorrect colour.Please re-enter");
						colour = br.readLine().trim();
					}
					try {
						System.out.println("Allocated slot number:" + service.park(regNo, colour));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;

				case 2:
					System.out.println("Enter slot number");
					slot = Integer.parseInt(br.readLine().trim());
					while (slot > n || slot < 1) {
						System.out.println("Incorrect slot number, Please re-enter");
						slot = Integer.parseInt(br.readLine().trim());
					}
					try {
						service.leave(slot);
						System.out.println("Slot number " + slot + " is free");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;

				case 3:
					try {
						Map<Integer, Car> map = service.getStatus();
						System.out.println("Slot No.\tRegistration No\t\tColor");
						for (Map.Entry<Integer, Car> entry : map.entrySet()) {
							System.out.println(entry.getKey() + "\t\t" + entry.getValue().getRegistrationNumber()
									+ "\t\t" + entry.getValue().getColour());
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;

				case 4:
					System.out.println("Enter the colour");
					colour = br.readLine().trim();
					while (!Validator.colourValidator(colour)) {
						System.out.println("Entered incorrect colour.Please re-enter");
						colour = br.readLine().trim();
					}
					try {
						String com = "";
						for (String s : service.registrationNoOfCarColour(colour)) {
							System.out.print(com + s);
							com = ", ";
						}
						System.out.println();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;

				case 5:
					System.out.println("Enter the colour");
					colour = br.readLine().trim();
					while (!Validator.colourValidator(colour)) {
						System.out.println("Entered incorrect colour.Please re-enter");
						colour = br.readLine().trim();
					}
					try {
						String com = "";
						for (Integer s : service.slotNoOfCarColour(colour)) {
							System.out.print(com + s);
							com = ", ";
						}
						System.out.println();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;

				case 6:
					System.out.println("Enter Registration Number");
					regNo = br.readLine().trim();
					while (!Validator.registrationNumberValidator(regNo)) {
						System.out.println(
								"Re-Enter Registration Number XX-XX-XX-XXXX format '-' can be replaced with ' '");
						regNo = br.readLine().trim();
					}
					try {
						System.out.println(service.getParkingSlot(regNo));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 7:
					exit = true;
					break;
				}// switch Case end
			} // try end
			catch (Exception e) {
				System.out.println("Incorrect Input Format");
			}
		} // while end

		System.out.println("Thank You for using Parking Manager, Have A Nice Day.");

		br.close();
	}// MAIN METHOD

}// CLASS
