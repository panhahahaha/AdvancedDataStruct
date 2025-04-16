/*
 * Data Structures Advanced (INFT 2042)
 * University of South Australia
 * Assignment 2, 2023
 * Author: Brandon Matthews
 * Author: Gun Lee
 */


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Contains details about a visit.
 * @author <your name and email id here>
 *
 */
public class Visit {
	/* TODO: Implement the Visit class.
	 * NOTE: You are free to implement and modify this class however best suits the data structures you are using.
	 * The provided constructor takes in just the location and person name,
	 * however based on the data structure and algorithms you implement you might want to 
	 * hold references to the person and locations themselves.
	 */
	
	private String personName;
	private String locationName;
	private LocalDate date; // Date of the visit
	private LocalTime entryTime; // Entry time
	private LocalTime exitTime; // Exit time
	
	public Visit(String personName, String locationName, LocalDate date, LocalTime entryTime, LocalTime exitTime) {
		this.personName = personName;
		this.locationName = locationName;
		this.date = date;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
	}
	
	public int OverlapMinutes(Visit other) {
		LocalTime latestEntryTime = entryTime.isBefore(other.entryTime) ? other.entryTime : entryTime;
		LocalTime earliestExitTime = exitTime.isBefore(other.exitTime) ? exitTime : other.exitTime;
		int minutesBetween = (int) latestEntryTime.until(earliestExitTime, ChronoUnit.MINUTES);
		return minutesBetween;
	}
	
	@Override
	public String toString() {
		String s = personName + " visited " + locationName + " on " + date + " between " + entryTime + " and " + exitTime;
		return s;
	}
}
