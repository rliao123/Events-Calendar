
package calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * MyCalendarTester prints out calendar with events and uses user input to carry
 * out specified task.
 */

public class MyCalendarTester implements Comparator<Event> {

	/**
	 * Prints out calendar and uses user input to carry out specified task
	 */
	public static void main(String[] args) {

		MyCalendar cal = new MyCalendar();

		LocalDate today = LocalDate.now();

		// *************************** DISPLAY CALENDAR ***************************//

		System.out.println("\n    " + today.getMonth() + " " + today.getYear());
		System.out.println("Su Mo Tu We Th Fr Sa");
		System.out.print("            ");

		for (int i = 1; i < today.lengthOfMonth() + 1; i++) {

			LocalDate first = today.withDayOfMonth(1);
			String day = first.getDayOfWeek().toString();

			int newLine = 0;

			if (day.equals("TUESDAY")) {

				newLine = 1;

			} else if (day.equals("WEDNESDAY")) {

				newLine = 2;

			} else if (day.equals("THURSDAY")) {

				newLine = 3;

			} else if (day.equals("FRIDAY")) {

				newLine = 4;

			} else if (day.equals("SATURDAY")) {

				newLine = 5;

			}

			// print new line in calendar
			if ((i + newLine) % 7 == 0) {

				System.out.println();
			}

			// highlight today
			if (today.getDayOfMonth() == i) {

				System.out.print("[" + i + "]");
				continue;
			}
			System.out.print(i + " ");
		}
		System.out.println();

		Scanner s = new Scanner(System.in);
		String letter = "";
		String name = "";
		String date = "";
		String start = "";
		String end = "";
		boolean done = false;

		while (!done) {

			System.out.println("\n[V]iew by,  [C]reate, [G]o to ,[E]vent list ,[D]elete  ,[Q]uit");

			System.out.println("\nEnter a Letter: ");
			letter = s.nextLine().toUpperCase();

			// *************************** VIEW ***************************//

			if (letter.equals("V")) {

				System.out.println("[D]ay view or [M]onth view ?");

				letter = s.nextLine().toUpperCase();

				// ***************** DAY VIEW *****************//

				if (letter.equals("D")) {

					String letter2 = "";

					// print today's date
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
					LocalDate c = LocalDate.now();
					System.out.println(formatter.format(c));

					// convert LocalDate into string
					DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					LocalDate d = LocalDate.now();
					String date1 = formatter1.format(d);

					ArrayList<Event> events = new ArrayList<Event>();

					// put today's events into new ArrayLsit
					for (int i = 0; i < cal.getEvents().size(); i++) {
						
						Event tempEvent = cal.getEvents().get(i);

						if ((tempEvent.getDate()).equals(date1)) {

							events.add(tempEvent);

						}
					}

					// order events based on starting time
					Collections.sort(events, new Comparator<Event>() {
						public int compare(Event o1, Event o2) {
							return o1.getStart().compareTo(o2.getStart());
						}
					});

					// print out today's events
					for (int i = 0; i < events.size(); i++) {
						
						Event tempEvent = cal.getEvents().get(i);

						System.out.println(tempEvent.getName() + " : " + tempEvent.getStart() + " - "
								+ tempEvent.getEnd());
					}

					System.out.println("\n[P]revious or [N]ext or [G]o back to main menu?");
					letter2 = s.nextLine().toUpperCase();

					while (letter2.equals("P") || letter2.equals("N")) {
						// PREVIOUS DAY
						if (letter2.equals("P")) {

							LocalDate tempDate = d;

							while (letter2.equals("P")) {

								LocalDate a = tempDate.plusDays(-1);
								System.out.println(formatter.format(a));
								String newDay = formatter1.format(a);
								tempDate = a;

								ArrayList<Event> prevEvents = new ArrayList<Event>();

								// put previous day's events into new ArrayLsit
								for (int i = 0; i < cal.getEvents().size(); i++) {
									
									Event tempEvent = cal.getEvents().get(i);

									if ((tempEvent.getDate()).equals(newDay)) {

										prevEvents.add(tempEvent);

									}
								}

								// order events based on starting time
								Collections.sort(prevEvents, new Comparator<Event>() {
									public int compare(Event o1, Event o2) {
										return o1.getStart().compareTo(o2.getStart());
									}
								});

								// print out previous day's events
								for (int i = 0; i < prevEvents.size(); i++) {

									System.out.println(prevEvents.get(i).getName() + " : "
											+ prevEvents.get(i).getStart() + " - " + prevEvents.get(i).getEnd());
								}

								System.out.println("\n[P]revious or [N]ext or [G]o back to main menu?");
								letter2 = s.nextLine().toUpperCase();
							}

							if (!tempDate.equals(d)) {
								d = tempDate;
							}

						}

						// NEXT DAY
						if (letter2.equals("N")) {

							LocalDate tempDate = d;

							while (letter2.equals("N")) {

								LocalDate a = tempDate.plusDays(1);
								System.out.println(formatter.format(a));
								String newDay = formatter1.format(a);
								tempDate = a;

								ArrayList<Event> nextEvents = new ArrayList<Event>();

								// put next day's events into new ArrayLsit
								for (int i = 0; i < cal.getEvents().size(); i++) {
									
									Event tempEvent = cal.getEvents().get(i);

									if ((tempEvent.getDate()).equals(newDay)) {

										nextEvents.add(tempEvent);

									}
								}

								// order events based on starting time
								Collections.sort(nextEvents, new Comparator<Event>() {
									public int compare(Event o1, Event o2) {
										return o1.getStart().compareTo(o2.getStart());
									}
								});

								// print out next day's events
								for (int i = 0; i < nextEvents.size(); i++) {

									System.out.println(nextEvents.get(i).getName() + " : "
											+ nextEvents.get(i).getStart() + " - " + nextEvents.get(i).getEnd());
								}

								System.out.println("\n[P]revious or [N]ext or [G]o back to main menu?");
								letter2 = s.nextLine().toUpperCase();
							}

							if (!tempDate.equals(d)) {
								d = tempDate;
							}

						}

					}
				}
				// ***************** MONTH VIEW *****************//

				if (letter.equals("M")) {

					String letter2 = "";

					// convert today's date into string
					DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					LocalDate d = LocalDate.now();
					String temp = formatter1.format(d);

					// contains days with events
					ArrayList<Integer> dayWithEvent = new ArrayList<Integer>();

					for (int i = 0; i < cal.getEvents().size(); i++) {

						// find events with today's month
						if ((cal.getEvents().get(i).getDate().substring(0, 2)).equals(temp.substring(0, 2))) {

							int date2 = Integer.parseInt(cal.getEvents().get(i).getDate().substring(3, 5));
							int date1 = Integer.parseInt(cal.getEvents().get(i).getDate().substring(4, 5));

							// date between 1 - 9 (one digit date)
							if (date2 > 0 && date2 < 10) {

								if (!dayWithEvent.contains(date1)) {

									dayWithEvent.add(date1);

								}
							}
							// date greater than 9 (two digit date)
							else {
								if (!dayWithEvent.contains(date2)) {

									dayWithEvent.add(date2);
								}
							}
						}
					}
					System.out.println("\n    " + today.getMonth() + " " + today.getYear());
					System.out.println("Su Mo Tu We Th Fr Sa");
					System.out.print("            ");

					Boolean next = false;

					// print out calendar
					for (int i = 1; i < today.lengthOfMonth() + 1; i++) {

						next = false;

						LocalDate first = today.withDayOfMonth(1);
						String day = first.getDayOfWeek().toString();

						int newLine = 0;

						if (day.equals("TUESDAY")) {

							newLine = 1;

						} else if (day.equals("WEDNESDAY")) {

							newLine = 2;

						} else if (day.equals("THURSDAY")) {

							newLine = 3;

						} else if (day.equals("FRIDAY")) {

							newLine = 4;

						} else if (day.equals("SATURDAY")) {

							newLine = 5;

						}

						if ((i + newLine) % 7 == 0) {

							System.out.println();
						}

						// highlight date if there is event
						for (int j = 0; j < dayWithEvent.size(); j++) {

							if (dayWithEvent.get(j) == i) {

								System.out.print("{" + i + "}");
								dayWithEvent.remove(dayWithEvent.get(j));
								next = true;
								break;
							}
						}

						if (!next) {

							System.out.print(i + " ");
							next = true;

						}
					}
					System.out.println();
					System.out.println("\n[P]revious or [N]ext or [G]o back to main menu?");
					letter2 = s.nextLine().toUpperCase();

					while (letter2.equals("P") || letter2.equals("N")) {
						// PREVIOUS MONTH
						if (letter2.equals("P")) {

							LocalDate tempMonth = d;

							while (letter2.equals("P")) {

								// convert LocalDate into String for previous month
								DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
								LocalDate prevMonth = tempMonth.plusMonths(-1);
								String st = formatter2.format(prevMonth);
								tempMonth = prevMonth;

								ArrayList<Integer> dayWithEvent2 = new ArrayList<Integer>();

								for (int i = 0; i < cal.getEvents().size(); i++) {
									
									String tempDate = cal.getEvents().get(i).getDate();

									// find events with this month
									if ((tempDate.substring(0, 2)).equals(st.substring(0, 2))) {

										int date2 = Integer.parseInt(tempDate.substring(3, 5));
										int date1 = Integer.parseInt(tempDate.substring(4, 5));

										// date between 1 - 9 (one digit date)
										if (date2 > 0 && date2 < 10) {

											if (!dayWithEvent2.contains(date1)) {

												dayWithEvent2.add(date1);

											}
										}
										// date greater than 9 (two digit date)
										else {
											if (!dayWithEvent2.contains(date2)) {

												dayWithEvent2.add(date2);
											}

										}
									}
								}

								// print out previous month
								System.out.println("\n    " + prevMonth.getMonth() + " " + prevMonth.getYear());
								System.out.println("Su Mo Tu We Th Fr Sa");
								System.out.print("            ");

								Boolean next2 = false;

								for (int i = 1; i < prevMonth.lengthOfMonth() + 1; i++) {

									next2 = false;

									LocalDate first = prevMonth.withDayOfMonth(1);
									String day2 = first.getDayOfWeek().toString();

									int newLine = 0;

									if (day2.equals("TUESDAY")) {

										newLine = 1;

									} else if (day2.equals("WEDNESDAY")) {

										newLine = 2;

									} else if (day2.equals("THURSDAY")) {

										newLine = 3;

									} else if (day2.equals("FRIDAY")) {

										newLine = 4;

									} else if (day2.equals("SATURDAY")) {

										newLine = 5;

									}

									if ((i + newLine) % 7 == 0) {

										System.out.println();
									}

									// highlight days with events
									for (int j = 0; j < dayWithEvent2.size(); j++) {

										if (dayWithEvent2.get(j) == i) {

											System.out.print("{" + i + "}");
											dayWithEvent2.remove(dayWithEvent2.get(j));
											next2 = true;
											break;
										}
									}
									if (!next2) {

										System.out.print(i + " ");
										next2 = true;
									}
								}
								System.out.println();

								System.out.println("\n[P]revious or [N]ext or [G]o back to main menu?");
								letter2 = s.nextLine().toUpperCase();
							}

							if (!tempMonth.equals(d)) {
								d = tempMonth;
							}

						}

						// NEXT MONTH
						if (letter2.equals("N")) {

							LocalDate tempMonth = d;

							while (letter2.equals("N")) {

								DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
								LocalDate nextMonth = tempMonth.plusMonths(1);
								String st2 = formatter3.format(nextMonth);
								tempMonth = nextMonth;

								ArrayList<Integer> dayWithEvent3 = new ArrayList<Integer>();

								for (int i = 0; i < cal.getEvents().size(); i++) {
									
									String tempDate = cal.getEvents().get(i).getDate();

									// find events with today's month
									if ((tempDate.substring(0, 2)).equals(st2.substring(0, 2))) {

										int date2 = Integer.parseInt(tempDate.substring(3, 5));
										int date1 = Integer.parseInt(tempDate.substring(4, 5));

										// date between 1 - 9 (one digit date)
										if (date2 > 0 && date2 < 10) {

											if (!dayWithEvent3.contains(date1)) {

												dayWithEvent3.add(date1);

											}
										}
										// date greater than 9 (two digit date)
										else {
											if (!dayWithEvent3.contains(date2)) {

												dayWithEvent3.add(date2);
											}
										}
									}
								}

								// print out next month
								System.out.println("\n    " + nextMonth.getMonth() + " " + nextMonth.getYear());
								System.out.println("Su Mo Tu We Th Fr Sa");
								System.out.print("            ");

								Boolean next3 = false;

								for (int i = 1; i < nextMonth.lengthOfMonth() + 1; i++) {

									next3 = false;

									LocalDate first = nextMonth.withDayOfMonth(1);
									String day2 = first.getDayOfWeek().toString();

									int newLine = 0;

									if (day2.equals("TUESDAY")) {

										newLine = 1;

									} else if (day2.equals("WEDNESDAY")) {

										newLine = 2;

									} else if (day2.equals("THURSDAY")) {

										newLine = 3;

									} else if (day2.equals("FRIDAY")) {

										newLine = 4;

									} else if (day2.equals("SATURDAY")) {

										newLine = 5;

									}

									if ((i + newLine) % 7 == 0) {

										System.out.println();
									}

									// highlight days withe events
									for (int j = 0; j < dayWithEvent3.size(); j++) {

										if (dayWithEvent3.get(j) == i) {

											System.out.print("{" + i + "}");
											dayWithEvent3.remove(dayWithEvent3.get(j));
											next3 = true;
											break;
										}

									}
									if (!next3) {

										System.out.print(i + " ");
										next3 = true;
									}
								}

								System.out.println();

								System.out.println("\n[P]revious or [N]ext or [G]o back to main menu?");
								letter2 = s.nextLine().toUpperCase();
							}
							if (!tempMonth.equals(d)) {
								d = tempMonth;
							}
						}
					}
				}
				letter = "";
			}

			// *************************** CREATE ***************************//

			if (letter.equals("C")) {

				Event e = new Event();

				System.out.println("Name: ");
				name = s.nextLine();
				e.setName(name);

				System.out.println("Date (MM/DD/YYYY): ");
				date = s.nextLine();
				e.setDate(date);

				System.out.println("Starting time (24 hour clock 00:00): ");
				start = s.nextLine();
				e.setStart(start);

				System.out.println("Ending time (24 hour clock 00:00): ");
				end = s.nextLine();
				e.setEnd(end);

				boolean overlap = false;

				// check if new event conflicts with existing events
				for (int i = 0; i < cal.getEvents().size(); i++) {
					
					Event tempEvent = cal.getEvents().get(i);

					if (e.getDate().equals(tempEvent.getDate())) {

						if ((e.getStart().isBefore(tempEvent.getEnd()))
								&& (e.getEnd().isAfter(tempEvent.getStart()))) {

							overlap = true;
						}
					}
				}

				// if not overlapping, add event
				if (!overlap) {

					cal.getEvents().add(e);
					System.out.println("successfully added!");

				} else {
					System.out.println("Event could not be added. There is a time conflict with an existing event.");
				}

				letter = "";
			}

			// *************************** GO ***************************//

			if (letter.equals("G")) {

				System.out.println("Enter Date (MM/DD/YYYY): ");
				date = s.nextLine();

				// print out formatted date
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
				LocalDate c = LocalDate.of(Integer.parseInt(date.substring(6, 10)),
						Integer.parseInt(date.substring(0, 2)), Integer.parseInt(date.substring(3, 5)));
				System.out.println();
				System.out.println(formatter.format(c));
				System.out.println();

				ArrayList<Event> temp = new ArrayList<Event>();

				// collect all events on specified date
				for (int i = 0; i < cal.getEvents().size(); i++) {
					
					Event tempEvent = cal.getEvents().get(i);

					if ((tempEvent.getDate()).equals(date)) {

						temp.add(tempEvent);
					}
				}

				// order events based on starting time
				Collections.sort(temp, new Comparator<Event>() {

					public int compare(Event o1, Event o2) {
						return o1.getStart().compareTo(o2.getStart());
					}
				});

				// print out events
				for (int i = 0; i < temp.size(); i++) {

					System.out.println(
							temp.get(i).getName() + " : " + temp.get(i).getStart() + " " + temp.get(i).getEnd());
				}
			}

			// *************************** EVENTS ***************************//

			if (letter.equals("E")) {

				System.out.println("ONE TIME EVENTS\n");

				ArrayList<Event> temp1 = new ArrayList<Event>();

				for (int i = 0; i < cal.getEvents().size(); i++) {
					
					Event tempEvent = cal.getEvents().get(i);

					if (tempEvent.getRecurr() == false) {

						temp1.add(tempEvent);
					}
				}

				// order events by date
				Collections.sort(temp1, new Comparator<Event>() {
					public int compare(Event o1, Event o2) {
						return o1.getDate().compareTo(o2.getDate());
					}
				});

				// order events by time if two events have same date
				Collections.sort(temp1, new Comparator<Event>() {
					public int compare(Event o1, Event o2) {
						int result = 0;
						if (o1.getDate().compareTo(o2.getDate()) == 0) {
							result = o1.getStart().compareTo(o2.getStart());
						}
						return result;
					}
				});

				// print out one time events
				for (int i = 0; i < temp1.size(); i++) {

					System.out.println(temp1.get(i).getFormattedDate() + " " + temp1.get(i).getStart() + " - "
							+ temp1.get(i).getEnd() + " " + temp1.get(i).getName());
				}

				System.out.println("\nRECURRING EVENTS\n");

				ArrayList<String> rEventName = new ArrayList<String>();
				ArrayList<Event> rEvents = new ArrayList<Event>();

				// get first event of recurring events
				for (int i = 0; i < cal.getEvents().size(); i++) {
					
					Event tempEvent = cal.getEvents().get(i);

					if (tempEvent.getRecurr() == true) {

						if (!rEventName.contains(tempEvent.getName())) {

							rEvents.add(tempEvent);
							rEventName.add(tempEvent.getName());
						}
					}
				}

				// sort events based on starting date
				Collections.sort(rEvents, new Comparator<Event>() {
					public int compare(Event o1, Event o2) {
						return o1.getDate().compareTo(o2.getDate());
					}
				});

				// print out recurring events
				for (int i = 0; i < rEvents.size(); i++) {

					System.out.println(rEvents.get(i).getName());

					System.out.println(rEvents.get(i).getStart() + " " + rEvents.get(i).getEnd() + " "
							+ rEvents.get(i).getrStartDate() + " " + rEvents.get(i).getrEndDate());
				}

				letter = "";
			}

			// *************************** DELETE ***************************//

			if (letter.equals("D")) {

				String deleteDate = "";
				String nameEvent = "";

				System.out.println("[S]elected [A]ll [DR]");
				letter = s.nextLine().toUpperCase();

				// delete specified event on selected day
				if (letter.equals("S")) {

					Boolean deleted = false;

					System.out.println("Enter the date (DD/MM/YYYY): ");
					deleteDate = s.nextLine();

					System.out.println("\nCurrent events:");

					// print out events on specified day
					for (int i = 0; i < cal.getEvents().size(); i++) {
						
						Event tempEvent = cal.getEvents().get(i);

						if (tempEvent.getDate().equals(deleteDate)) {

							System.out.println(tempEvent.getStart() + " - "
									+ tempEvent.getEnd() + " " + tempEvent.getName());

						}
					}

					System.out.println("\nEnter the name of event to delete: ");
					nameEvent = s.nextLine();

					// delete specified event
					for (int i = 0; i < cal.getEvents().size(); i++) {

						if ((cal.getEvents().get(i).getDate()).equals(deleteDate)) {

							if ((cal.getEvents().get(i).getName()).equals(nameEvent)) {

								cal.getEvents().remove(i);
								deleted = true;
								break;
							}
						}
					}
					if (deleted) {
						System.out.println("Successfully Deleted!");
					} else {
						System.out.println("Event could not be deleted because it was not found.");
					}
				}

				// delete all events on certain day
				if (letter.equals("A")) {

					System.out.println("Enter the date (DD/MM/YYYY): ");
					deleteDate = s.nextLine();

					System.out.println();

					// print out all events on specified day
					for (int i = 0; i < cal.getEvents().size(); i++) {
						
						Event tempEvent = cal.getEvents().get(i);
						
						if (tempEvent.getDate().equals(deleteDate)) {
							System.out.println(tempEvent.getStart() + " - "
									+ tempEvent.getEnd() + " " + tempEvent.getName());
						}
					}

					// delete all events on specified day
					for (int i = 0; i < cal.getEvents().size(); i++) {
						
						if (cal.getEvents().get(i).getDate().equals(deleteDate)) {
							cal.getEvents().remove(i);
						}
					}
					System.out.println("\nSuccessfully Deleted!");
				}

				// delete recurring event throughout calendar
				if (letter.equals("DR")) {

					System.out.println("Enter name of recurring event: ");
					nameEvent = s.nextLine();

					System.out.println();

					// delete specified recurring event
					for (int i = 0; i < cal.getEvents().size(); i++) {

						if ((cal.getEvents().get(i).getName()).equals(nameEvent)) {

							cal.getEvents().remove(i);
							i--;
						}
					}
					System.out.println("Successfully Deleted!");
				}
			
			}

			// *************************** QUIT ***************************//
			if (letter.equals("Q")) {
				done = true;
			}

			letter = "";
		}

		System.out.println("Good Bye!");
		s.close();

		try {
			// save current events into new file output.txt
			File output = new File("src/output.txt");
			output.createNewFile();
			PrintWriter pw = new PrintWriter(new FileWriter(output));

			// sort events based on date
			Collections.sort(cal.getEvents(), new Comparator<Event>() {

				public int compare(Event o1, Event o2) {
					return o1.getDate().compareTo(o2.getDate());
				}
			});

			// sort events by time if two events have same date
			Collections.sort(cal.getEvents(), new Comparator<Event>() {
				public int compare(Event o1, Event o2) {
					int result = 0;
					if (o1.getDate().compareTo(o2.getDate()) == 0) {
						result = o1.getStart().compareTo(o2.getStart());
					}
					return result;
				}
			});

			// print each event into file
			for (Event e : cal.getEvents()) {

				pw.println(e.getName());
				pw.println(e.getStart() + " - " + e.getEnd() + " " + e.getDate());

			}

			pw.close();

		} catch (

		FileNotFoundException x) {
			System.out.println(x.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Override compare method from Object class
	 * 
	 * @param o1 - the first event
	 * @param o2 - the second event
	 * @return negative integer, zero, or positive integer as Event is less than,
	 *         equal to, or greater than other Event
	 */
	public int compare(Event o1, Event o2) {
		return o1.getDate().compareTo(o2.getDate());
	}
}
