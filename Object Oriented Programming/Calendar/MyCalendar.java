
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Class MyCalendar.
 */
public class MyCalendar {
	
	private Map<GregorianCalendar, ArrayList<Event>> map = new TreeMap<>();
	
	/**
	 * Instantiates a new TreeMap<>() my calendar.
	 */
	public MyCalendar() {
		this.map = new TreeMap<>();
	}
	
	/**
	 * Adds the event.
	 *
	 * @param Event object
	 * @param GregorianCalendar c of the given day
	 */
	void addEvent(Event event, GregorianCalendar c) {
		if (map.containsKey(c)) {
			map.get(c).add(event);
		} else {
			ArrayList<Event> array = new ArrayList<>();
			array.add(event);
			map.put(c, array);
		}
	}

	/**
	 * Delete specific event on given day.
	 *
	 * @param Event object
	 * @param GregorianCalendar c of the given day
	 */
	void deleteEvent(Event event, GregorianCalendar c) {
		ArrayList<Event> e = map.get(c);
			for (Iterator<Event> iterator = e.iterator(); iterator.hasNext(); ) {
			    Event value = iterator.next();
			    if (value.getName().equals(event.getName()) && value.getEndingTime().equals(event.getEndingTime())
						&& value.getStartingTime().equals(event.getStartingTime())) {
			    		iterator.remove();
			    }
			}
	}

	/**
	 * Delete all events.
	 */
	void deleteAll() {
		map.clear();
	}
	
	/**
	 * Delete all events in given day.
	 *
	 * @param GregorianCalendar c
	 */
	void deleteAllinDay(GregorianCalendar c) {
		map.remove(c);
	}

	/**
	 * 
	 * Print all Events in calendar
	 * 
	 */
	void getAllEvent() {
		System.out.println("All events in your calendar sorted by date and starting time:");
		DateFormat defaultDate = DateFormat.getDateInstance( DateFormat.FULL );
		for (Map.Entry<GregorianCalendar, ArrayList<Event>> entry : map.entrySet()) {
			System.out.println(defaultDate.format(entry.getKey().getTime()));
			Collections.sort((List<Event>) entry.getValue());
			for (Event e : entry.getValue()) {
				System.out.println(e.toString());
				
			}
		}
	}
	
	
	/**
	 * Print the events for given day.
	 *
	 * @param c the GregorianCalendar
	 * 
	 */
	void getEvent(GregorianCalendar c) {
		DateFormat defaultDate = DateFormat.getDateInstance( DateFormat.FULL );
			ArrayList<Event> e = map.get(c);
			System.out.println(defaultDate.format(c.getTime()));
			if(e != null) {
				for (Iterator<Event> iterator = e.iterator(); iterator.hasNext(); ) {
				    Event value = iterator.next();
				    	System.out.println(value.toString());
				}
			} else {
				System.out.println("There are no events today!");
			}
	}
	
	/**
	 * Gets the event for given day.
	 *
	 * @param c the GregorianCalendar
	 * @return the events for given day
	 */
	ArrayList<Event> getEventforGivenDay(GregorianCalendar c) {
			return map.get(c);
	}
	
	/**
	 * Extract Events ArrayList.
	 *
	 * @return ArrayList of all Events
	 */
	ArrayList<Event> extractEvent() {
		ArrayList<Event> rv = new ArrayList<Event>();
		for (Map.Entry<GregorianCalendar, ArrayList<Event>> entry : map.entrySet()) {
			Collections.sort((List<Event>) entry.getValue());
			for (Event e : entry.getValue()) {
				rv.add(e);
			}
		}
		return rv;
	}

}

