/*
 * Data Structures Advanced (INFT 2042)
 * University of South Australia
 * Assignment 2, 2023
 * Author: Brandon Matthews 
 * Author: Gun Lee
 */


import java.time.LocalDate;

/**
 * Contains details about a person.
 * Name is assumed to be unique among all instances of Person in this system.
 * @author <your name and email id here>
 *
 */
public class Person {
	private String name; // The unique name of the person

	private LocalDate activeStartDate; // Date the person became an active case
	private LocalDate activeEndDate; // Date the person was no longer an active case
	
	public Person(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDate getActiveStartDate() {
		return activeStartDate;
	}

	/**
	 * Sets the date the person was detected as being an active case. 
	 * If the start date is after a current end date, the start date is not updated.
	 * If the start date is after the current date the start date is not updated.
	 * @param activeStartDate The date the person was detected as being an active case.
	 */
	public void setActiveStartDate(LocalDate activeStartDate) {
		// Active start date is not updated if it is after the current date
		if (DateHelpers.CURRENT_DATE.isBefore(activeStartDate)) return;
		// Active start date is not updated if it is after the end date
		if (this.activeEndDate != null && this.activeEndDate.isBefore(activeStartDate)) return;
		
		this.activeStartDate = activeStartDate;			

	}
	
	/**
	 * @return the date the person stopped being an active case.
	 */
	public LocalDate getActiveEndDate() {
		return activeEndDate;
	}
	
	/**
	 * Sets the Active End Date. 
	 * If the end date is before a current start date, the end date is not updated.
	 * If the end date is after the current date the end date is not updated.
	 * @param activeStartDate The date the person stopped being an active case.
	 */
	public void setActiveEndDate(LocalDate activeEndDate) {
		// Active end date is not updated if it is after the current date
		if (DateHelpers.CURRENT_DATE.isBefore(activeEndDate)) return;
		// Active end date is not updated if it is before the start date
		if (this.activeStartDate != null && activeEndDate.isBefore(this.activeStartDate)) return;

		this.activeEndDate = activeEndDate;			
	}
	
	/**
	 * Returns true if this person was an active case on the provided date, otherwise returns false.
	 * @param date
	 * @return true if date is between activeStartDate and activeEndDate
	 */
	public boolean activeOnDate(LocalDate date) {
		return ( !date.isBefore( activeStartDate ) ) && ( date.isBefore( activeEndDate ) ) ;
	}
	
	/**
	 * @return true if the person was ever an active case (including currently), otherwise false.
	 */
	public boolean previouslyActive() {
		return activeStartDate != null && activeEndDate != null;
	}
	
	
	/**
	 * @return true if the person is currently an active case, otherwise false.
	 */
	public boolean currentlyActive() {
		return activeEndDate == null && activeOnDate(DateHelpers.CURRENT_DATE);
	}
	
	@Override
	public String toString() {
		String s = "Name: " + name;
		
		if (previouslyActive()) {
			s += " - Active Start: " + activeStartDate.toString() + ", Active End: " + activeEndDate.toString();
		} else if (currentlyActive()) {
			s += " - Active Start: " + activeStartDate.toString() + ", Active End: N/A";
		} else {
			s += " - Never Active";
		}
		
		return s;
	}
}
