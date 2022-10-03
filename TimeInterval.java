/**
* 2022 CS151 Programming Assignment 1 Solution
* @author Rachel Liao
* @version 1.0 09/05/2022
*/
package calendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * TimeInterval contains the attributes for an event
 */

public class TimeInterval {

	private LocalTime st;
	private LocalTime et;
	private String date;
	private boolean recurring;
	private String rStartDate;
	private String rEndDate;
	private ArrayList<String> key;
	
	private static String month;
	private static String day;
	private static String year;

	/**
	 * Constructs a time interval with date and no recurrence
	 * @param d - date
	 */
	public TimeInterval(String d) {
		date = d;
		recurring = false;
	}

	/**
	 * Return start time of time interval
	 * @return start time
	 */
	public LocalTime getStart() {
		return st;
	}
	
	/**
	 * Set start time into LocalTime of String sTime 
	 * @param sTime - start time
	 * precondition: sTime - must be non-null
	 */
	public void setStart(String sTime) {
		st = LocalTime.parse(sTime);

	}

	/**
	 * Return end time of time interval
	 * @return end time
	 */
	public LocalTime getEnd() {
		return et;
	}
	
	/**
	 * Set end time into LocalTime of String eTime 
	 * @param eTime - end time
	 * precondition: eTime - must be non-null
	 */
	public void setEnd(String eTime) {
		et = LocalTime.parse(eTime);
	}
	

	/**
	 * Return date of time interval
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Set date
	 * @param date
	 * precondition: date - must be non-null
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Return if time interval has recurrence
	 * @return recurrence
	 */
	public boolean getRecurring() {
		return recurring;
	}

	/**
	 * Set recurrence of event
	 * @param false if event does not have recurrence, true if event has recurrence
	 * precondition: recurring - must be true/false
	 */
	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	/**
	 * Return start date of recurring event
	 * @return recurring start date
	 */
	public String getrStartDate() {
		return rStartDate;
	}

	/**
	 * Set start date for recurring event
	 * @param rStartDate - start date
	 * precondition: rStartDate - must be non-null
	 */
	public void setrStartDate(String rStartDate) {
		this.rStartDate = rStartDate;
	}
	
	/**
	 * Return end date of recurring event
	 * @return recurring end date
	 */
	public String getrEndDate() {
		return rEndDate;
	}

	/**
	 * Set end date for recurring event
	 * @param rEndDate - end date
	 *  precondition: rEndDate - must be non-null
	 */
	public void setrEndDate(String rEndDate) {
		this.rEndDate = rEndDate;
	}
	
	/**
	 * Return the days the event has recurrence
	 * @return arraylist of days
	 */
	public ArrayList<String> getKey() {
		return key;
	}

	/**
	 * Set ArrayList of days of recurrence
	 * @param key - array list of day letters
	 */
	public void setKey(ArrayList<String> key) {
		this.key = key;
	}
	
	/**
	 * Format date into "Day, Month day year" format
	 * @param date
	 * @return formatted String for date
	 *  precondition: date - must be mm/dd/yyyy format
	 */
	public String format(String date) {

		month = date.substring(0, 2);
		
		day = date.substring(3, 5);

		year = date.substring(6, 10);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
		LocalDate c = LocalDate.of(formatYear(year), formatMonth(month), formatDay(day));
		return " " + formatter.format(c);
	}

	/**
	 * Convert String month into integer
	 * @param month
	 * @return month integer
	 */
	public static int formatMonth(String month) {
		int m = Integer.parseInt(month);
		return m;
	}

	/**
	 * Convert String day into integer
	 * @param day
	 * @return day integer
	 */
	public static int formatDay(String day) {
		int d = Integer.parseInt(day);
		return d;
	}

	/**
	 * Convert String year into integer
	 * @param year
	 * @return year integer
	 */
	public static int formatYear(String year) {
		int y = Integer.parseInt(year);
		return y;
	}
	
	/**
	 * Return String month
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	
}
