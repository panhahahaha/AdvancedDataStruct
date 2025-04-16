/*
 * Data Structures Advanced (INFT 2042)
 * University of South Australia
 * Assignment 2, 2023
 * Author: Brandon Matthews
 */

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Use this class to convert strings to dates, or strings to time.
 * @author <your name and email id here>
 *
 */
public class DateHelpers {
	
	public static LocalDate CURRENT_DATE = null;

	public static LocalDate ParseDateString(String date) {
		String[] splitDate = date.split("/");
		int dayOfMonth = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1]);
		int year = Integer.parseInt(splitDate[2]);
	
		return LocalDate.of(year, month, dayOfMonth);
	}
	
	public static LocalTime ParseTimeString(String time) {
		String[] splitTime = time.split(":");
		int hour = Integer.parseInt(splitTime[0]);
		int minute = Integer.parseInt(splitTime[1]);
		
		return LocalTime.of(hour, minute);
	}
}
