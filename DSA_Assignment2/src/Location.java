/*
 * Data Structures Advanced (INFT 2042)
 * University of South Australia
 * Assignment 2, 2023
 * Author: Brandon Matthews
 */

/**
 * Contains details about a location.
 * Name is assumed to be unique among all instances of Location in this system.
 * @author <your name and email id here>
 */
public class Location {

	private String name; // The unique name of the location

	public Location(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		String s = "Location Name: " + name;

		return s;
	}
	
}
