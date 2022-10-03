
package calendar;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Event contains name, time interval, and recurrence
 */

public class Event {

	private String name;
	private TimeInterval ti;
	private boolean recurr;

	/**
	 * Creates new events with time interval with name and no recurrence
	 */
	public Event() {
		ti = new TimeInterval(name);
		setRecurr(false);
	}

	/**
	 * Return name of event
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of event
	 * @param n - name
	 * precondition: n must be non-null
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * Return start time of event
	 * @return TimeInterval st
	 */
	public LocalTime getStart() {
		return ti.getStart();
	}

	/**
	 * Set start time of event
	 * @param st - start time
	 * precondition: st must be non-null
	 */
	public void setStart(String st) {
		ti.setStart(st);
	}

	/**
	 * Return end time of event
	 * @return TimeInterval et
	 */
	public LocalTime getEnd() {
		return ti.getEnd();
	}

	/**
	 * Set end time of event
	 * @param et - end time
	 * precondition: et must be non-null
	 */
	public void setEnd(String et) {
		ti.setEnd(et);

	}

	/**
	 * Return date of event
	 * @return TimeInterval date
	 */
	public String getDate() {
		return ti.getDate();
	}

	/**
	 * Set date of event
	 * @param d - date
	 * precondition: d must be non-null
	 */
	public void setDate(String d) {
		ti.setDate(d);
	}

	/**
	 * Return starting date for recurring event
	 * @return TimeInterval rStartDate
	 */
	public String getrStartDate() {
		return ti.getrStartDate();
	}

	/**
	 * Set start date of recurring event
	 * @param d - start date
	 * precondition: d must be non-null
	 */
	public void setrStartDate(String d) {
		ti.setrStartDate(d);
	}

	/**
	 * Return ending date for recurring event
	 * @return TimeInterval eStartDate
	 */
	public String getrEndDate() {
		return ti.getrEndDate();
	}

	/**
	 * Set end date for recurring event
	 * @param d - end date
	 * precondition: d must be non-null
	 */
	public void setrEndDate(String d) {
		ti.setrEndDate(d);
	}

	/**
	 * Return if event has recurrence
	 * @return TimeInterval recurring
	 */
	public Boolean getRecurring() {
		return ti.getRecurring();
	}

	/**
	 * Set recurring true if event has recurrence, false if event is one time event
	 * @param r - recurrence
	 * precondition: r must be true/false
	 */
	public void setRecurring(Boolean r) {
		ti.setRecurring(r);
	}

	/**
	 * Return ArrayList of days for events with recurrence
	 * @return TimeInterval key
	 */
	public ArrayList<String> getKey() {
		return ti.getKey();
	}

	/**
	 * Set ArrayList of days for events with recurrence
	 * @param ArrayLsit key
	 */
	public void setKey(ArrayList<String> key) {
		ti.setKey(key);
	}

	/**
	 * Return if specific event is recurring event
	 * @return recurr - true if event is recurring event, false if event is one time event
	 */
	public boolean getRecurr() {
		return recurr;
	}

	/**
	 * Set if specific event is recurring event
	 * @param r - true if event is recurring event, false if event is one time event
	 * precondition: r - must be true/false
	 */
	public void setRecurr(boolean r) {
		this.recurr = r;
	}

	/**
	 * Return formatted date based on TimeInterval format method
	 * @return TimeInterval formatted date
	 */
	public String getFormattedDate() {
		return ti.format(ti.getDate());
	}

	/**
	 * Return month as integer
	 * @return int TimeInterval month
	 */
	public int getMonth() {
		return Integer.parseInt(ti.getMonth());
	}

}
