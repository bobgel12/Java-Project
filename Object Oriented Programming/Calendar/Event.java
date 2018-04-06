
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

/**
 * The Class Event.
 */
public class Event implements Comparable<Event>, java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private	String name;
	private LocalTime startingTime;
	private LocalTime endingTime;
	private GregorianCalendar date;
	
	/**
	 * Instantiates a new event.
	 *
	 * @param name the name
	 * @param startingTime the starting time
	 * @param endingTime the ending time
	 * @param date the date
	 */
	public Event(String name, LocalTime startingTime, LocalTime endingTime, GregorianCalendar date) {
		this.name = name;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.date = date;
	}
	/**
	 * @return the date
	 */
	public GregorianCalendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the startingTime
	 */
	public LocalTime getStartingTime() {
		return startingTime;
	}
	/**
	 * @param startingTime the startingTime to set
	 */
	public void setStartingTime(LocalTime startingTime) {
		this.startingTime = startingTime;
	}
	/**
	 * @return the endingTime
	 */
	public LocalTime getEndingTime() {
		return endingTime;
	}
	/**
	 * @param endingTime the endingTime to set
	 */
	public void setEndingTime(LocalTime endingTime) {
		this.endingTime = endingTime;
	}
	/**
	 * Compare method
	 * @param e2: the event to compareTo
	 */
	public int compareTo(Event e2) {
		return (this.getStartingTime().compareTo(e2.getStartingTime()));
	}
	/**
	 * To string method to print each event
	 */
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		if(this.endingTime == null) {
			return(this.startingTime.format(dtf) + " " + this.name );
		} else {
			return(this.startingTime.format(dtf) +" - "+ this.endingTime.format(dtf) + " " + this.name );
		}
	}
}
