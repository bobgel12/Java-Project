

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * My Calendar.
 * @author PhucLe
 * @since March 19, 2018
 */
public class MyCalendarTester {
	private static LocalDateTime currentDate;
	private static ArrayList<Event> Events;
	private static MyCalendar calendar;
	private static Scanner sc = new Scanner(System.in);;
	
//	Array keep the name of month 
	private static String[] months = { "", "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	
//	Array keep the days in given month
	private static int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
//	Main method
	public static void main(String[] args) throws IOException {
		Running();
	}
	
//	Method to generate events
	public static void Initialize() {
		GregorianCalendar c1 = new GregorianCalendar(2017, 3, 2);
		GregorianCalendar c2 = new GregorianCalendar(2018, 6, 4);
		Event e1 = new Event("Dr. Kim's Office", LocalTime.of(9, 15), LocalTime.of(10, 15), c1);
		Event e2 = new Event("Dentist", LocalTime.of(13, 15), LocalTime.of(14, 0), c1);
		Event e3 = new Event("Job Interview", LocalTime.of(15, 0), LocalTime.of(16, 0), c2);
		calendar.addEvent(e1, c1);
		calendar.addEvent(e2, c1);
		calendar.addEvent(e3, c2);
	}
	
//	Menu management method
	public static void Running() throws IOException {
//		Create calendar field
		calendar = new MyCalendar();
		boolean shouldRun = true;
//		Begin the Menu loop
		while (shouldRun) {
			currentDate = LocalDateTime.now();
			
			System.out.println("Welcome to your calendar!");
			try {
				Events = calendar.extractEvent();
				displayCalendar(Events, currentDate.getYear(), currentDate.getMonthValue());
			} catch(NullPointerException a) {
				displayCalendar(null, currentDate.getYear(), currentDate.getMonthValue());
			}
			System.out.println("Select one of the following options: \n" + 
					"[L]oad   [V]iew by  [C]reate  [G]o to  [E]ventlist  [D]elete  [Q]uit");
			sc = new Scanner(System.in);
			String input = sc.nextLine();
			switch (input.toUpperCase()){
//			Load option
			case "L":
				getFile();
				break;
//			View option
			case "V":
				System.out.println("[D]ay view or [M]onth view ? ");
				String input2 = sc.nextLine();
				switch (input2.toUpperCase()) {
//				Day View option
				case "D":
					System.out.println("[D]ay view");
					calendar.getEvent(new GregorianCalendar());
					boolean run = true;
					while(run) {
						int year = currentDate.getYear();
						int month =  currentDate.getMonthValue();
						int day = currentDate.getDayOfMonth();
						while(run) {
							System.out.println("[P]revious or [N]ext or [M]ain menu ? ");
							String input4 = sc.nextLine();
							switch (input4.toUpperCase()){
							case "P":
								day --;
								if(day == 0) {
									month --;
									if (month == 0) {
										year --;
										month = 12;
									}
								}
								calendar.getEvent(new GregorianCalendar(year, month - 1, day));
								break;
							case "N":
								day ++;
								if(day > days[month]) {
									month ++;
									if (month > 12) {
										year --;
										month = 1;
									}
								}
								calendar.getEvent(new GregorianCalendar(year, month -1, day));
								break;
							case "M":
								run = false;
								break;
							}
						}
					}
					break;
//				Month View option
				case "M":
					System.out.println("[M]onth view");
					boolean run1 = true;
					displayCalendar(Events, currentDate.getYear(), currentDate.getMonthValue());
					while(run1) {
						int year = currentDate.getYear();
						int month =  currentDate.getMonthValue();
						while(run1) {
							System.out.println("[P]revious or [N]ext or [M]ain menu ? ");
							String input4 = sc.nextLine();
							switch (input4.toUpperCase()){
							case "P":
								month --;
								if (month == 0) {
									year --;
									month = 12;
								}
								displayCalendar(Events, year, month);
								break;
							case "N":
								month ++;
								if(month > 12) {
									year ++;
									month = 1;
								}
								displayCalendar(Events, year, month);
								break;
							case "M":
								run1 = false;
								break;
							}
						}
					}
					break;
				default: break;
				}
				break; 
//			Create option
			case "C":
				boolean again = true;
				GregorianCalendar inputDate = null;
				LocalTime[] inputTime;
				String inputName;
				Event inputEvent = null;
				while(again) {
					System.out.println("Please enter new event information!");
					again = false;
					inputDate = getInputDate();
					inputTime = getInputTime();
					inputName = getInputName();
					inputEvent = new Event(inputName, inputTime[0],inputTime[1],inputDate);
					for(Event event : Events) {
						if (event.getName().toUpperCase().equals(inputEvent.getName().toUpperCase()) && event.getDate().equals(inputEvent.getDate())) {
							again = true;
							System.out.println("Duplicated Event!");
						}
					}
				}
				DateFormat defaultDate = DateFormat.getDateInstance( DateFormat.FULL );
				System.out.println(defaultDate.format(inputDate.getTime()));
				calendar.addEvent(inputEvent, inputDate);
				break;
//			Go to option
			case "G":
				GregorianCalendar inputDate1 = getInputDate();
				calendar.getEvent(inputDate1);
				break;
//			Get all events option
			case "E":
				calendar.getAllEvent();
				break;
//			Delete option
			case "D":
				GregorianCalendar inputDate2 = getInputDate();
				calendar.getEvent(inputDate2);
				System.out.println("[S]elected or [A]ll");
				String input3 = sc.nextLine();
				switch (input3.toUpperCase()) {
//				Delete selected event in day only
				case "S":
					ArrayList<Event> todayEvent = calendar.getEventforGivenDay(inputDate2);
					if(todayEvent != null) {
						for (int i = 0; i < todayEvent.size(); i++) {
							System.out.println(i+1+ ": "+ todayEvent.get(i).toString());
						}
						System.out.println("Please select the desired Event to delete by number: ");
						String input5 = sc.nextLine();
						calendar.deleteEvent(todayEvent.get(Integer.parseInt(input5)-1), inputDate2);
						break;
					} else {
						System.out.println("There is no event at selected day");
					}
					break;
//				Delete all event in day
				case "A":
					calendar.deleteAllinDay(inputDate2);
					break;
				default: break;
				}
				break;
//			Quit and save events to file events.ser
			case "Q":
				createFile();
				shouldRun = false;
				break;
			default: break;
			}
		}
	}
	
//	Method to create .ser file to save all events
	public static void createFile() throws IOException {
		ArrayList<Event> array = calendar.extractEvent();
		try {
			FileOutputStream fileOut = new FileOutputStream("events.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(array);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in events.ser \n");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
//	Method to e04xtract the .ser file
	public static void getFile() {
		ArrayList<Event> array = new ArrayList<Event>();
		try {
			FileInputStream fileIn = new FileInputStream("events.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			array = (ArrayList<Event>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println("This is the first run");
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("This is the first run");
			return;
		}
		for (Event e : array) {
			calendar.addEvent(e, e.getDate());
		}
	}
	
//	Method to get the Name input for new Event
	public static String getInputName() {
		String rv = "";
		while (rv == "") {
			System.out.print("Name of Event: ");
			rv = sc.nextLine();
		}
		return rv;
	}
	
//	Method to get correct input time for Event
	public static LocalTime[] getInputTime() {
		LocalTime[] rv = new LocalTime[2];
		String[] arrOfStr = new String[10];
		boolean flag0 = true;
		while(flag0) {
			System.out.println("Does the event has ending time? Enter yes or no");
			String endtime = sc.nextLine();
			if(endtime.equals("yes")) {
				boolean run = true;
				while (run) {
					System.out.println("Enter the in format StartingTime-hh:StartingTime-mm:EndingTime-hh:EndingTime-mm (ex: 08:00:10:45)");
					String date = sc.nextLine();
					arrOfStr = date.split(":", 4);
					boolean flag = true;
					if ((arrOfStr.length != 4)) {
						flag = false;
					}
					for (String a : arrOfStr) {
						if (a.isEmpty()) {
							flag = false;
						}
						try {
							Integer.parseInt(a);
						} catch (NumberFormatException c) {
							flag = false;
						}
						if (a.length() != 2) {
							flag = false;
						}
					}
					if(flag) {
						try {
							rv[0] = LocalTime.of(Integer.parseInt(arrOfStr[0]),Integer.parseInt(arrOfStr[1]));
							rv[1] = LocalTime.of(Integer.parseInt(arrOfStr[2]),Integer.parseInt(arrOfStr[3]));
							if(rv[0].isBefore(rv[1])) {
								run = false;
							} else {
								System.out.print("Starting time has to be before Ending time!");
								run = true;
							}
						} catch (NumberFormatException n) {
							run = true;
						} catch (DateTimeException d) {
							run = true;
						}
					}
				}
				flag0 = false;
			} else if (endtime.equals("no")) {
				boolean run = true;
				while (run) {
					System.out.println("Enter the in format StartingTime-hh:StartingTime-mm(ex: 08:00)");
					String date = sc.nextLine();
					arrOfStr = date.split(":", 2);
					boolean flag = true;
					if ((arrOfStr.length != 2)) {
						flag = false;
					}
					for (String a : arrOfStr) {
						if (a.isEmpty()) {
							flag = false;
						}
						try {
							Integer.parseInt(a);
						} catch (NumberFormatException c) {
							flag = false;
						}
						if (a.length() != 2) {
							flag = false;
						}
					}
					if(flag) {
						try {
							rv[0] = LocalTime.of(Integer.parseInt(arrOfStr[0]),Integer.parseInt(arrOfStr[1]));
							run = false;
						} catch (NumberFormatException n) {
							run = true;
						} catch (DateTimeException d) {
							run = true;
						}
					}
				}
				flag0 = false;
			} else {
				System.out.println("Please enter yes or no!");
			}
		}
		return rv;
	}

//	Method for getting correct input date
	public static GregorianCalendar getInputDate() {
		String[] arrOfStr = new String[10];
		while (true) {
			System.out.println("Enter the date in format MM/DD/YYYY");
			String date = sc.nextLine();
			arrOfStr = date.split("/", 4);
			boolean flag = true;
			//	        Check for empry field
			for (String a : arrOfStr) {
				if (a.isEmpty())
					flag = false;
				try {
					Integer.parseInt(a);
				} catch (NumberFormatException c) {
					flag = false;
				}
			}
			//			Check for enough field
			if ((arrOfStr.length == 3)) {
				if ((arrOfStr[0].length() != 2) || (arrOfStr[1].length() != 2) || (arrOfStr[2].length() != 4)) {
					flag = false;
				} else
					flag = true;
			} else
				flag = false;
			if (flag)
				break;
			System.out.print("You entered wrong date format!");
		}
		GregorianCalendar rv = new GregorianCalendar(Integer.parseInt(arrOfStr[2]), Integer.parseInt(arrOfStr[0]) - 1,
				Integer.parseInt(arrOfStr[1]));
		return rv;
	}
	
//	Method checking if the year is leap year
	public static boolean checkLeapYear(int year) {
		if ((year % 4 == 0) && (year % 100 != 0))
			return true;
		if (year % 400 == 0)
			return true;
		return false;
	}
	
//	Method getting the first day of given month, year 
	public static int getday(int month, int day, int year) {
		int y = year - (14 - month) / 12;
		int x = y + y / 4 - y / 100 + y / 400;
		int m = month + 12 * ((14 - month) / 12) - 2;
		int d = (day + x + (31 * m) / 12) % 7;
		return d;
	}
	
//	Display calendar by month
	public static void displayCalendar(ArrayList<Event> Events, int year, int month) {
		if (month == 2 && checkLeapYear(year)) {
			days[month] = 29;
		}
		
		int[] temp = new int[days[month]+1];
		
		if(Events != null) {
			temp = getEventdayin(month , year);
		}
		
		System.out.println("      	" + months[month] + " " + year);
		System.out.println(" Sun  Mon  Tue  Wed  Thu  Fri  Sat");

		int firstDay = getday(month, 1, year);

		for (int i = 0; i < firstDay; i++)
			System.out.print("     ");
		for (int i = 1; i <= days[month]; i++) {
			if (temp[i] == 1) {
				System.out.printf("{%4d}", i);
			} else {
				System.out.printf("%4d ", i);
			}
			if (((i + firstDay) % 7 == 0) || (i == days[month]))
				System.out.println();
		}
	}
	
//	Method to get the days in month that has events
	public static int[] getEventdayin(int givenMonth, int givenYear) {
		ArrayList<Event> array = calendar.extractEvent();
		int[] rv = new int[days[givenMonth]+1];
		for(int i = 0; i < array.size(); i++) {
			if(array.get(i).getDate().get(Calendar.YEAR) == givenYear) {
				if(array.get(i).getDate().get(Calendar.MONTH) + 1 == (givenMonth)) {
					rv[array.get(i).getDate().get(Calendar.DAY_OF_MONTH)] = 1;
				}
			}
		}
		return rv;
	}
}