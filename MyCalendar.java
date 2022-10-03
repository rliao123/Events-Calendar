
package calendar;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * MyCalendar is a collection to manage and store events.
 */

public class MyCalendar {

	private ArrayList<Event> events;
	private ArrayList<Event> events1;
	private Event e;

	/**
	 * Constructs a calendar with arraylists
	 */
	public MyCalendar() {
		events = new ArrayList<Event>();
		events1 = new ArrayList<Event>();

		try {

			File file = new File("src/events.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			boolean done = false;
			int counter = 0;

			while (!done) {

				line = br.readLine();

				if (line == null) {
					done = true;
					break;
				}

				String[] words = line.split(" ");

				String name = "";

				if (counter % 2 == 0) {
					for (int i = 0; i < words.length; i++) {

						if (words[i].equals(" ")) {
							break;
						} else if (i != 0) {
							name += " ";
						}
						name += words[i];

					}
					e = new Event();
					e.setName(name);

				}

				if (counter % 2 == 1) {

					if (Character.isLetter(words[0].charAt(0))) {
						e.setRecurring(true);
						e.setRecurr(true);
					} else {
						e.setRecurring(false);
					}

					if (e.getRecurring() == false) {

						e.setDate(words[0]);
						e.setStart(words[1]);
						e.setEnd(words[2]);
						e.setrStartDate(null);
						e.setrEndDate(null);
						e.setKey(null);

					} else {

						ArrayList<String> key = new ArrayList<String>();
						for (int i = 0; i < words[0].length(); i++) {
							key.add(words[0].substring(i, i + 1));
						}

						e.setKey(key);
						e.setDate(words[3]);
						e.setStart(words[1]);
						e.setEnd(words[2]);
						e.setrStartDate(words[3]);
						e.setrEndDate(words[4]);

					}

					events.add(e);

				}

				counter++;

			}

			for (int i = 0; i < events.size(); i++) {

				ArrayList<String> temp = events.get(i).getKey();

				if (events.get(i).getRecurring()) {

					String start = events.get(i).getrStartDate();
					String end = events.get(i).getrEndDate();

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					LocalDate startD = LocalDate.parse(start, formatter);
					LocalDate endD = LocalDate.parse(end, formatter);

					long weeks = ChronoUnit.WEEKS.between(startD, endD);

					LocalDate begin = LocalDate.of(startD.getYear(), startD.getMonth(), startD.getDayOfMonth());

					for (int j = 0; j < (weeks + 1); j++) {

						for (int k = 0; k < temp.size(); k++) {

							Event e = new Event();
							Boolean finish = false;

							while (!finish) {

								if (begin.getDayOfWeek().getValue() == 7 && temp.get(k).equals("S")) {

									e.setName(events.get(i).getName());
									e.setStart(events.get(i).getStart().toString());
									e.setEnd(events.get(i).getEnd().toString());
									e.setDate(formatter.format(begin));
									e.setrStartDate(events.get(i).getrStartDate());
									e.setrEndDate(events.get(i).getrEndDate());

									finish = true;

								}

								else if (begin.getDayOfWeek().getValue() == 1 && temp.get(k).equals("M")) {

									e.setName(events.get(i).getName());
									e.setStart(events.get(i).getStart().toString());
									e.setEnd(events.get(i).getEnd().toString());
									e.setDate(formatter.format(begin));
									e.setrStartDate(events.get(i).getrStartDate());
									e.setrEndDate(events.get(i).getrEndDate());
									finish = true;

								}

								else if (begin.getDayOfWeek().getValue() == 2 && temp.get(k).equals("T")) {

									e.setName(events.get(i).getName());
									e.setStart(events.get(i).getStart().toString());
									e.setEnd(events.get(i).getEnd().toString());
									e.setDate(formatter.format(begin));
									e.setrStartDate(events.get(i).getrStartDate());
									e.setrEndDate(events.get(i).getrEndDate());
									finish = true;

								}

								else if (begin.getDayOfWeek().getValue() == 3 && temp.get(k).equals("W")) {

									e.setName(events.get(i).getName());
									e.setStart(events.get(i).getStart().toString());
									e.setEnd(events.get(i).getEnd().toString());
									e.setDate(formatter.format(begin));
									e.setrStartDate(events.get(i).getrStartDate());
									e.setrEndDate(events.get(i).getrEndDate());
									finish = true;

								}

								else if (begin.getDayOfWeek().getValue() == 4 && temp.get(k).equals("R")) {

									e.setName(events.get(i).getName());
									e.setStart(events.get(i).getStart().toString());
									e.setEnd(events.get(i).getEnd().toString());
									e.setDate(formatter.format(begin));
									e.setrStartDate(events.get(i).getrStartDate());
									e.setrEndDate(events.get(i).getrEndDate());
									finish = true;

								}

								else if (begin.getDayOfWeek().getValue() == 5 && temp.get(k).equals("F")) {

									e.setName(events.get(i).getName());
									e.setStart(events.get(i).getStart().toString());
									e.setEnd(events.get(i).getEnd().toString());
									e.setDate(formatter.format(begin));
									e.setrStartDate(events.get(i).getrStartDate());
									e.setrEndDate(events.get(i).getrEndDate());
									finish = true;

								}

								else if (begin.getDayOfWeek().getValue() == 6 && temp.get(k).equals("A")) {

									e.setName(events.get(i).getName());
									e.setStart(events.get(i).getStart().toString());
									e.setEnd(events.get(i).getEnd().toString());
									e.setDate(formatter.format(begin));
									e.setrStartDate(events.get(i).getrStartDate());
									e.setrEndDate(events.get(i).getrEndDate());
									finish = true;

								}

								begin = begin.plusDays(1);

							}

							e.setRecurr(true);
							events1.add(e);

						}

					}

				} else {
					events1.add(events.get(i));
				}

			}

			System.out.println("Loading is done!");
			br.close();
			fr.close();

		} catch (IOException x) {
			System.out.println(x.getMessage());
		}

	}

	/**
	 * Returns the events of the calendar
	 * @return the events
	 */
	public ArrayList<Event> getEvents() {
		return events1;
	}

}
