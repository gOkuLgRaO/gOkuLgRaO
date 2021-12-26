package Coursework;

// Imports all the needed Java utilities into the program
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BookingSystem {
	
	// Creates a blank arraylist which can be filled with the data provided via "Rooms.txt"
	private static Room[] roomArray = null;
	// This scanner scans input as a keyword for entities being entered into the system 
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		
		// Loads the inital file of "Rooms.txt" into the program so that it is ready to be displayed to the user
		loadRooms();
		String userInput = " ";
		
		do {
			
			// Prints the main menu to the user when running the program and presents a number of options to navigate through the system 
			System.out.println("\n-- MAIN MENU --");
			System.out.println("1. Display Bookings ");
			System.out.println("2. Reserve Room ");
			System.out.println("3. Cancel Room ");
			System.out.println("Q. Exit System ");
			System.out.println("Pick: ");
			
			// Scans the next entity as a user input so that they can be redirected to the neccessary part of the program 
			userInput = input.next().toUpperCase();
			
			// Switches between the different options presented to the user awaiting user input 
			switch (userInput) {
			case "1": {
				DisplayBookings();
				break;
			}
			case "2": {
				ReserveRoom();
				break;
			}
			case "3": {
				CancelRoom();
				break;
			}
		}	
	}
	// Quits the program and making no changes, terminating the program
	while (!userInput.equalsIgnoreCase("Q"));
		System.out.println("-- PROGRAMME QUIT --");
		System.exit(0);
		
	}
	
	// Creates a method to scan "Rooms.txt" and then implements the file into an arraylist, creating a new list for each room within the text file
	private static void loadRooms() throws FileNotFoundException {
		Scanner file = new Scanner (new FileReader("Rooms.txt"));
		
		ArrayList<Room> list = new ArrayList<Room>();
		while(file.hasNext()) {
			String roomNo = file.next();
			String roomType = file.next();
			double roomPrice = file.nextDouble();
			boolean hasBalcony = file.nextBoolean();
			boolean hasLounge = file.nextBoolean();
			String guestEmail = file.next();
			
			list.add(new Room(roomNo, roomType, roomPrice, hasBalcony, hasLounge, guestEmail));
		}
		
		// Gathers the data from the text file creating an arraylist with the data, it then returns the number of elements implemented with the list
		roomArray = new Room[list.size()];
		list.toArray(roomArray);
		file.close();
		}
	
		// Creates a method for reserving a room initially displaying the menu for finding the user a room
		public static void ReserveRoom() {
			
			// Sets the search parameters for finding a room for the user by setting the balcony and lounge options to false
			String userInput = " ";
			String room = "";
			boolean hasBalcony = false;
			boolean hasLounge = false;
			
			System.out.println("-- RESERVE ROOM --");
			System.out.println("What room type would you like? ");
			System.out.println("1. Single");
			System.out.println("2. Double");
			System.out.println("3. Suite");
			System.out.println("Pick: ");
			
			userInput = input.next().toUpperCase();
			
			// Presents the user with different parameters in order to find their ideal room (roomType)
			switch(userInput) {
			case "1":
				room = "Single";
				break;
			
			case "2":
				room = "Double";
				break;
			
			case "3":
				room = "Suite";
				break;
				
			
			}
			
			System.out.println("Would you prefer a balcony?");
			System.out.println("1. I want balcony");
			System.out.println("2. No, nevermind");
			System.out.println("Pick: ");
			
			// presents the user with different parameters in order to find their ideal room (hasBalcony)
			switch(restrictionInput(1,2)) {
			case 1:
				hasBalcony = true;
				break;
				
			case 2:
				hasBalcony = false;
				break;
				
			default:
				assert(false);
			}
			
			System.out.println("Would you prefer a lounge?");
			System.out.println("1. I want a lounge");
			System.out.println("2. No, nevermind");
			System.out.println("Pick: ");
			
			// presents the user with different parameters in order to find their ideal room (hasLounge)
			switch(restrictionInput(1,2)) {
			case 1:
				hasLounge = true;
				break;
				
			case 2:
				hasLounge = false;
				break;
				
			default:
				assert(false);
			}
			// With the search parameters from the user entered, the program will seach through the arraylist to find a suitable room
		for (Room entry:roomArray) {
			boolean result = true;
			String choice;
			
			// 	Gets the various types of data from the Room class and sends it to the BookingSystem class
			result = result&&(entry.getroomType().equalsIgnoreCase(room));
			result = result&&(entry.hasBalcony()==hasBalcony);
			result = result&&(entry.hasLounge()==hasLounge);
			result = result&&(entry.getguestEmail().equalsIgnoreCase("free"));
			if(result) {
				System.out.println("We have found you a room");
				
				// Prints the room information based on the user parameters 
				System.out.println("-- ROOM INFO --");
				System.out.printf("Your room Number : %s%n", entry.getroomNo());
				System.out.printf("Your room type : %s%n", entry.getroomType());
				System.out.printf("Your room price : £%.2f%n", entry.getroomPrice());
				System.out.printf("Your room balcony : %s%n", entry.hasBalcony());
				System.out.printf("Your room lounge : %s%n", entry.hasLounge());
				
				// Asks the user if they want to book the specified room and if so then requests the users email address to save the room
				System.out.println("Do you want to reserve this room? Y/N");
				choice = input.next();
				if(choice.matches("Y") || choice.matches("y")) {
					System.out.println("Please enter your E-mail: ");
					String emailInput = input.next();
					if(emailInput.length()==0 || !isValidEmail(emailInput)) {
						System.out.println("There is an error in your E-maiil, Please try again.");
						return;
						}
					else {
						// Sets the guest email and updates the text file with user email information
						entry.setguestEmail(emailInput);
						update();
						System.out.println("Your room has been booked!");
					}
				}
				// If the user does not want to book the room then the program will end 
				else if(choice.matches("N") || choice.matches("n")) {
					System.out.println("Sorry to see you go!");
				}
				return;
				
			}
		}
		// If the room is not available (it is booked) then this message will prompt to the user	
		System.out.printf("Unfortunately, there is no rooms available. We are deeply sorry,");
		}
		
		// Creates a method for cancelling rooms by identifying if the room number is not yet booked 
		public static void CancelRoom() {
			int get = 0;
			Room found = null;
			System.out.println("Please enter your room number: ");
			String userResponse = input.next();
			for (Room c:roomArray) {
				if(c.getroomNo().equalsIgnoreCase(userResponse)) {
					found = c;
					break;
				}
				}
			// If the room information comes back null (meaning it has not been booked yet) then the user will be prompted with that message
			if(found==null) {
				System.out.println("The room has not been reserved yet!");
				return;
				}
			
			System.out.println("Cancel the room? ");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println("Pick: ");
			
			// If the user selects option 1 then the program will update the text file and remove the set guest email address
			// If the user selects option 2 then the program will alert the user that they have not updated the guest email address
			while(true) {
				get = Integer.parseInt(input.next());
				
				if(get==1) {
					found.setguestEmail("free");
					update();
					System.out.println("The room has been cancelled!");
					break;
				}
				else if (get==2) {
					System.out.println("You didn't cancel the room");
					break;
				}
				else {
					assert false;
					System.out.println("Please enter either number 1 or 2");
				}
			}
		}
		
		// This method will print the arraylist onto the console for the user gathering the information from "Rooms.txt" and Room class
		public static void DisplayBookings() throws FileNotFoundException {
			for(Room entry:roomArray) {
				System.out.print("-- ROOM INFORMATION --");
				System.out.printf("\nRoom Number : %s%n", entry.getroomNo());
				System.out.printf("Room Type : %s%n", entry.getroomType());
				System.out.printf("Room Price : £%.2f%n", entry.getroomPrice());
				System.out.printf("Room Balcony : %s%n", entry.hasBalcony());
				System.out.printf("Room Lounge : %s%n", entry.hasLounge());
				System.out.printf("Room Reserved by : %s%n", entry.getguestEmail());
			}	
		}
		
		// This method ensures that the users email is valid by checking if the email contains an "@"
		private static boolean isValidEmail (String emailInput) {
			String[] dtct =emailInput.split("@");
			boolean result = dtct.length==2&&dtct[1].length()!=0&&!(emailInput.contains(" "));
			return result;
		}
		
		// This method updates "Rooms.txt" using text writer through the entry of arraylists
		private static void update() {
			try {
				FileWriter overwrite = new FileWriter("Rooms.txt");
				for(Room entry:roomArray) {
					overwrite.write(entry.toString());
					overwrite.write("\n");
				}
				// Removes any final exceptions made within the program before closing it and saving it back to the file 
				overwrite.flush();
				overwrite.close();
			}
			// cathches the print writer stack so that the error can be traced back to its source 
			catch(Throwable thrw) {
				thrw.printStackTrace();
			}
		}
		
		// This method retrict's the user input to 2 different int variables with the program not allowing the user to exceed or regress below the set integer
		private static int restrictionInput(int min, int max) {
			while (true) {
				try {
					System.out.print("");
					int com = Integer.parseUnsignedInt(input.next());
					if (com>=min&&com<=max) {
						return com;
					}
				}
					// This catches the exceptions found within the program and prints a message promting the user that their input is invalid
					catch(Exception ex) {	
					}
					System.out.println("The number you entered is invalid, please try again!");
			}
		}
		
		// This method saves the data that has been collected through the program running and saves it to the text file
		public static void saveToFile() throws IOException {
			
			PrintWriter file = new PrintWriter("Rooms.txt");
			for (Room entry : roomArray) {
				
			// Saves the guest email to the text file replacing the room availability
			String output = entry.getguestEmail();
			if (entry.guestEmail !=null)
			output += roomArray;
			
			// Prints the result 
			file.println(output);
			}	
			
			// Closes the file 
			file.close();
	
	}
}
