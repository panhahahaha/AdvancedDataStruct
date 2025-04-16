/*
 * Data Structures Advanced (INFT 2042)
 * University of South Australia
 * Assignment 2, 2023
 * Author: Brandon Matthews
 * Author: Daniel Ablett
 * Author: Gun Lee
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is were most of your code would go.
 * @author <your name and email ID here>
 *
 */
public class Main {

	public static void main(String[] args) {
		/* Check if a command file is provided as an input argument and quit if not */
		if (args.length < 1) {
			System.out.println("No Test Command File Provided");
			return;
		}

		/* Tools to read command line and text file instructions */
		BufferedReader reader = null;
		Scanner scanner;
		String command;
		String trimmedLine;
		boolean initialized = false;

		/* Open command file and read line */
		try {
			reader = new BufferedReader(new FileReader(args[0]));

			String line = reader.readLine();

			/* Process and read every line */
			while (line != null) {
				/* Clean up any extra spaces etc. */
				trimmedLine = line;
				while (trimmedLine != null && trimmedLine.trim().equals("")) {
					line = reader.readLine();
					trimmedLine = line;
				}

				/* Ignore any empty lines */
				if (line == null) {
					break;
				}

				/* Filter out comments */
				if (line.contains("#")) {
					String noComments;
					noComments = line.substring(0, line.indexOf("#"));
					/*
					 * If there is text before comment, process it, otherwise ignore the line and
					 * move on.
					 */
					if (noComments.length() > 0) {
						line = noComments;
					} else {
						line = reader.readLine();
						continue;
					}
				}

				/* Store the command component */
				command = line.substring(0, line.indexOf(" "));

				/* Read all the arguments into a scanner */
				scanner = new Scanner(line.substring(line.indexOf(" "), line.length()));
				
				if (!command.equals("INIT") && !initialized) {
					System.out.println("Program has not been initialized, skipping commands until first INIT");
					line = reader.readLine();
					continue;
				}

				switch (command) {
				case "INIT": {
					String currentDate = scanner.next();
					System.out.println("Initializing with 'Current Date' = " + currentDate);
					DateHelpers.CURRENT_DATE = DateHelpers.ParseDateString(currentDate);
					initialized = true;
					break;
				}
				case "LOAD_DATA": {
					String peoplePath = scanner.next();
					System.out.println("Loading People Data from " + peoplePath);
					Person[] people = LoadPeople(peoplePath);
					System.out.println("Found " + people.length + " people.");
					
					String locationPath = scanner.next();
					System.out.println("Loading Location Data from " + locationPath);
					Location[] locations = LoadLocations(locationPath);
					System.out.println("Found " + locations.length + " locations.");
					
					String visitPath = scanner.next();
					System.out.println("Loading Visit Data from " + visitPath);
					Visit[] visits = LoadVisits(visitPath);
					System.out.println("Found " + visits.length + " visits.");
					
					
					/* 
					 * TODO: Store people, locations and visits in more efficient data structures
					 * 	 		such that they may be used by other commands
					 */		 
					break;
				}
				case "ADD_PERSON": {
					String addPersonName = scanner.next();
					System.out.println("Adding new Person: " + addPersonName);
					String activeStartDate = scanner.hasNext() ? scanner.next() : null;
					String activeEndDate = scanner.hasNext() ? scanner.next() : null;
					
					/*
					 *  TODO: Create person and add to data structure
					 */
					break;
				}
				case "ADD_LOCATION": {
					String addLocationName = scanner.next();
					System.out.println("Adding new Location: " + addLocationName);

					/*
					 *  TODO: Create location and add to data structure
					 */
					break;
				}
				case "ADD_VISIT": {
					String visitPerson = scanner.next();
					String visitLocation = scanner.next();
					
					System.out.println("Adding Visit by: " + visitPerson + ", to: " + visitLocation);
					
					String visitDate = scanner.next();
					String visitStartTime = scanner.next();
					String visitEndTime = scanner.next();
					
					/*
					 *  TODO: Create Visit and add to data structure
					 */
					break;
				}
				case "GET_PERSON": {
					String getPersonName = scanner.next();
					System.out.println("Get Person: " + getPersonName);
					
					String startDate = scanner.hasNext() ? scanner.next() : null;
					String endDate = scanner.hasNext() ? scanner.next() : null;
					
					/*
					 *  TODO: Get Person and their Visits (optionally between startDate and endDate)
					 */
					break;
				}
				case "GET_LOCATION": {
					String getLocationName = scanner.next();
					System.out.println("Get Location: " + getLocationName);

					String startDate = scanner.hasNext() ? scanner.next() : null;
					String endDate = scanner.hasNext() ? scanner.next() : null;

					/*
					 *  TODO: Get Location and its Visits (optionally between startDate and endDate)
					 */
					break;
				}
				case "LIST_CONTACTS": {
					String contactPersonName = scanner.next();
					int n = Integer.parseInt(scanner.next());
					
					System.out.println("Listing all contacts with: " + contactPersonName + ", within " + n + " levels.");
					
					/*
					 *  TODO: List all contacts up to n.
					 */
					break;
				}
				case "NEW_CASE": {
					String newCasePersonName = scanner.next();

					System.out.println("Adding new case to: " + newCasePersonName);
					
					/*
					 *  TODO: Set new active case with estimated active start date and
					 *  		print most likely source
					 */
					
					break;
				}
				case "TRACE_PATH": {
					String pathTargetName = scanner.next();
					System.out.println("Tracing infection path for: " + pathTargetName);
					
					/*
					 *  TODO: List path the virus has taken from person to person
					 *  		until it reaches the target
					 */
					
					break;
				}
				case "CURRENTLY_INFECTED": {
					System.out.println("Listing currently infected people...");
					/*
					 *  TODO: List all current active cases.
					 */
					
					break;
				}
				default:
					System.out.println("Invalid Command " + command);
				}
				
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: File could not be found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error: Unexpected IO exception encountered");
		}
	}

	private static Person[] LoadPeople(String peoplePath) {
		BufferedReader csvReader;

		Person[] personArray = new Person[0];

		try {
			ArrayList<Person> people = new ArrayList<Person>();
			csvReader = new BufferedReader(new FileReader(peoplePath));

			String row = csvReader.readLine();

			while (row != null) {
				String[] data = row.split(",");

				if (data == null || data.length < 1) {
					row = csvReader.readLine();
					continue;
				}

				String name = data[0];

				Person person = new Person(name);

				String[] startDate = null;
				String[] endDate = null;

				if (data.length > 1) {
					person.setActiveStartDate(DateHelpers.ParseDateString(data[1]));
				}

				if (data.length > 2) {
					person.setActiveEndDate(DateHelpers.ParseDateString(data[2]));
				}

				people.add(person);

				row = csvReader.readLine();
			}

			csvReader.close();

			personArray = new Person[people.size()];
			people.toArray(personArray);
			return personArray;
		} catch (FileNotFoundException e) {
			System.out.println("Error: File could not be found: " + e.getMessage());
			return personArray;
		} catch (IOException e) {
			System.out.println("Error: Unexpected IO exception encountered");
			return personArray;
		}
	}

	private static Location[] LoadLocations(String locationPath) {
		BufferedReader csvReader;
		Location[] locationArray = new Location[0];

		try {
			ArrayList<Location> locations = new ArrayList<Location>();
			csvReader = new BufferedReader(new FileReader(locationPath));

			String row = csvReader.readLine();

			while (row != null) {
				String[] data = row.split(",");

				if (data == null || data.length < 1) {
					row = csvReader.readLine();
					continue;
				}

				Location location = new Location(data[0]);
				locations.add(location);

				row = csvReader.readLine();
			}

			csvReader.close();

			locationArray = new Location[locations.size()];
			locations.toArray(locationArray);
			return locationArray;
		} catch (FileNotFoundException e) {
			System.out.println("Error: File could not be found: " + e.getMessage());
			return locationArray;
		} catch (IOException e) {
			System.out.println("Error: Unexpected IO exception encountered");
			return locationArray;
		}
	}

	private static Visit[] LoadVisits(String visitPath) {
		BufferedReader csvReader;

		Visit[] visitArray = new Visit[0];

		try {
			ArrayList<Visit> visits = new ArrayList<Visit>();
			csvReader = new BufferedReader(new FileReader(visitPath));

			String row = csvReader.readLine();

			while (row != null) {
				String[] data = row.split(",");

				if (data == null || data.length < 1) {
					row = csvReader.readLine();
					continue;
				}

				String personName = data[0];
				String locationName = data[1];

				LocalDate date = DateHelpers.ParseDateString(data[2]);
				LocalTime entryTime = DateHelpers.ParseTimeString(data[3]);
				LocalTime exitTime = DateHelpers.ParseTimeString(data[4]);

				Visit visit = new Visit(personName, locationName, date, entryTime, exitTime);
				visits.add(visit);

				row = csvReader.readLine();
			}

			csvReader.close();

			visitArray = new Visit[visits.size()];
			visits.toArray(visitArray);
			return visitArray;
		} catch (FileNotFoundException e) {
			System.out.println("Error: File could not be found: " + e.getMessage());
			return visitArray;
		} catch (IOException e) {
			System.out.println("Error: Unexpected IO exception encountered");
			return visitArray;
		}
	}
}
