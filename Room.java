package Coursework;

public class Room {
	
	// Creates a construct for a Room containing all the different variables needed for a room 
	public Room(String roomNo, String roomType, double roomPrice, boolean hasBalcony, boolean hasLounge, String guestEmail) {
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.hasBalcony = hasBalcony;
		this.hasLounge = hasLounge;
		this.guestEmail = guestEmail;
	}
	
	// Method to get the guest email and feed it to the construct so that it can be later used 
	public String getguestEmail() {
		return guestEmail;
	}
	
	// Method to set the guest email fetched from the program as the default
	public void setguestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}
	
	// Method to get the room number and feed it to the construct so that it can be later used 
	public String getroomNo() {
		return roomNo;
	}
	
	// Method to get the room type and feed it to the constructor so that it can be later used 
	public String getroomType() {
		return roomType;
		
	}
	
	// Method to get the room price and feed it to the constructor so that it can be later used 
	public double getroomPrice() {
		return roomPrice;
	}
	
	// Method to get the information on whether or not a room has a balcony so that it can be later used 
	public boolean hasBalcony() {
		return hasBalcony;
		
	}
	
	// Method to get the information on whether or not a room has a lounge so that it can be later used 
	public boolean hasLounge() {
		return hasLounge;
		
	}
	
	// Defines the different variables into their data types 
	String roomNo;
	String roomType;
	double roomPrice;
	boolean hasBalcony;
	boolean hasLounge;
	String guestEmail;
	
	// This method strings the results of all the different variables into a result that can be used through the program 
	public String toString() {
		String result = roomNo + " " + roomType + " " + roomPrice + " " + hasBalcony + " " + hasLounge + " " + guestEmail;
		return result;
		
	}
}
