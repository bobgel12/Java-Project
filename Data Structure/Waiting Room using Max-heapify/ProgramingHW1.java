import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class ProgramingHW1 {
//	Patient Class
	static class Patient {
		private String name;
		private int condition;
//		Construction of Patient class.
		Patient(){
			this.setName("John Doe");
			this.setCondition(0);
		}
//		Construction of Patient class.
		Patient(String name, int condition){
			this.setName(name);
			this.setCondition(condition);
		}
//		Get name of a patient.
		public String getName() {
			return name;
		}
//		Set name of a patient.
		public void setName(String name) {
			this.name = name;
		}
//		Get condition of a patient.
		public int getCondition() {
			return condition;
		}
//		Set condition of a patient.
		public void setCondition(int condition) {
			this.condition = condition;
		}
//		Get full properties of a patient. 
		public String getInfo() {
			return("Name: "+ name + ", Condition: "+ condition);
		}
	}
// Heapsort Class
	static class HeapsortArraylist {
//		Private variable for Heapsort.
		private int heap_size;
		private int largest;
		
		public int parent(int i) {
			return i/2;
		}
		public int left(int i) {
			return 2*i;
		}
		public int right(int i) {
			return (2*i+1);
		}
//		Method for swapping Patient 
		public void swaps(ArrayList<Patient> Patients, int a, int b) {
			Patient temp = (Patient) Patients.get(a);
			Patients.set(a, Patients.get(b)) ;
			Patients.set(b, temp) ;
		}
//		Method Max-Heapify from the textbook, used for maintaining the max-heap property.
		public void Max_Heapify(ArrayList<Patient> Patients, int i){
			int l = this.left(i);
			int r = this.right(i);
			if	((l < this.heap_size) && ( Patients.get(l).getCondition() > Patients.get(i).getCondition())) 
				{
					this.largest = l;
				} else this.largest = i;
			if	((r < this.heap_size) && (Patients.get(r).getCondition() > Patients.get(this.largest).getCondition())) 
				{
					this.largest = r;
				}
			if	(this.largest != i) {
				this.swaps(Patients, i, this.largest);
				this.Max_Heapify(Patients, this.largest);
			}
		}
//		Method Build-Max-Heap from textbook, used for building max-heap tree.
		public void Build_Max_Heap(ArrayList<Patient> Patients) {
			this.heap_size = Patients.size();
			for (int i = (Patients.size()/2); i>=0; i--){
				Max_Heapify(Patients,i);
			}
		}
//		Method Heapsort from textbook, used for sorting the array.
		public void heapsort(ArrayList<Patient> Patients) {
			Build_Max_Heap(Patients);
			for(int i = Patients.size() -1 ; i>=1; i--) {
				this.swaps(Patients, 0, i);
				this.heap_size --;
				Max_Heapify(Patients,0);
			}
		}
//		Method Max-Heap-Insert from textbook, used for inserting a new element but keeping the max-heap tree property.
		public void Max_Heap_Insert(ArrayList<Patient> Patients, Patient X) {
			this.heap_size ++;
			Patients.add(X);
//			Patients.set((this.heap_size - 2), X);
			Heap_Increase_Key(Patients, Patients.size()-1, X.getCondition());
		}
//	 	Method Heap-Maximum from text book, used to get the current largest key value element.
		public Patient Heap_Maximum(ArrayList<Patient> Patients) {
			return Patients.get(0);
		}
//		Method Heap-Increase-Key from textbook, used for increasing any element's key.
		public void Heap_Increase_Key(ArrayList<Patient> Patients, int i, int key) {
			if	(key < Patients.get(i).getCondition()) {
				System.out.print("Error, New condition is smaller than current condition");
			}
			Patients.get(i).setCondition(key);
			while (i>0 && (Patients.get(parent(i)).getCondition() < Patients.get(i).getCondition() )) {
				swaps(Patients, i, parent(i));
				i = parent(i);
			}
		}
//		Method Heap-Extract-Max from textbook, used for getting and removing the current largest key value ele
		public Patient Heap_Extract_Max(ArrayList<Patient> Patients) {
			if (Patients.size() < 1){
				return new Patient("",0);
			}
			Patient max = Patients.get(0);
			Patients.set(0, Patients.get(this.heap_size-1));
			Patients.remove(this.heap_size -1);
			this.heap_size --;
			Max_Heapify(Patients, 0);
			return max;
		}
	}
	
//	Sample array of names.
	static String[] name = { "Tess Hagel", "Reatha Cassel", "Tim Clouse", "Salome Husted", "Jermaine Kozak", "Eryn Stacker", "Kyong Watanabe", "Sherrell Kamps","Mai Sills","Joni Rogan","Mattie Flannigan","Dane Parman","Mitchel Manney","Janna Mantyla", "Amos Pearce", "Janita Giroir", "Nery Closson","Adolfo Schram", "Julissa Renteria", "Mallory Rempe" ,"Vina Rumble","Greta Lannon","Booker Bynum", "Cassaundra Baranowski","Rodrigo Dunkley","Lenora Stancil","Carolee Nordby","Antonetta Berta", "Ela Thornburg", "Lorrine Lynn", "Phyliss Bolanos", "Tiny Dieguez", "Tona Monsour", "Brittanie Ventura" };
	static ArrayList<Patient> Patients = new ArrayList<>();
	static Random random = new Random();
	static HeapsortArraylist Heapsort = new HeapsortArraylist();
	static Integer[] A = new Integer[100];
	static int currentUsedIndex = 20;
	static int currentOccupiedSeat = 0;
	private static Scanner scanner;
//	Random a new patient with distinct name and condition.
	public static Patient RandomAPatient() {
		currentUsedIndex++;
		return new Patient((name[currentUsedIndex]), A[currentUsedIndex]);
	}
//	Random an Array of Patients with distinct name and condition.
	public static void RandomPatients() {
		Patients.clear();
		currentOccupiedSeat = 0;
		for (int i = 0; i < 20; i++) {
			Patients.add(new Patient(name[i], A[i]));
			currentOccupiedSeat ++;
		}
	}
//	Print the current Queue.
	public static void Print() {
		System.out.println("++++++++++++++Current Queue+++++++++++++++");
		for(int i = 0; i<Patients.size(); i++) {
			System.out.println(Patients.get(i).getInfo());
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
	}
//	Check if the current Queue has any items.
	public static boolean ValidateCurrentQueue(ArrayList<Patient> Patients) {
		if (Patients.get(0) != null && Patients.get(1) != null) {
			if (Patients.get(0).getCondition() > Patients.get(1).getCondition()) {
				return true;
			} else return false;
		} else return false;
	}
//	Main function
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		for (int i = 0; i<A.length; i++) {
			A[i] = i;
		}
		Collections.shuffle(Arrays.asList(A));
		int input = 1;
		do {
			System.out.println("+++++++++++++++Hospital Waiting Room ArrayList Final+++++++++++++++++"
					+ "\n\nSimulation of Hospital Queue Impliment Priority Queue base on Heapsort.(Enter 0 to exit) "
					+ "\n Please choose the Following options:"
					+ "\n 1.Random a new Queue. "
					+ "\n 2.Get the info of the next patient in the Queue. "
					+ "\n 3.Call the next patient in the Queue."
					+ "\n 4.Change condition of any patient."
					+ "\n 5.Add a patient into the current queue."
					+ "\n 6.Sort the Queue in ascending order base on their condition");
			System.out.println("===================================================");
			do {
				System.out.println("Please enter your choose of 1-6:");
				while(!scanner.hasNextInt()) {
					System.out.println("Please enter your choose of 1-6:");
					scanner.next();
				}
				input = scanner.nextInt();
			} while (input<1 && input >6);
			switch (input) {
//			Random a new array of patients then print, then run Build-Max-Heap to turn the array into a Max-Heap tree, then Print.
			case 1: RandomPatients();
					Print();
					Heapsort.Build_Max_Heap(Patients);
					System.out.println("Queue affer running Build_Max_Heap");
					Print();
					break;
//			Call Heap-Maximum function to get the current largest values.
			case 2:	if (ValidateCurrentQueue(Patients)) {
						Heapsort.Build_Max_Heap(Patients);
						System.out.println("The next patient is: "+ Heapsort.Heap_Maximum(Patients).getInfo());
						Print();
					} else {
						System.out.println("Please create a list of patients first.");
					}
					break;	
//			Call the Heap-Extract-Max function to remove the current largest values element. 
//			The currentOccupiedSeat variable keep track of the current patient in the queue.
			case 3: if(ValidateCurrentQueue(Patients)) {
						System.out.println("Patient has just been called and removed is: " + Heapsort.Heap_Extract_Max(Patients).getInfo() );
						currentOccupiedSeat --;
						Print();
					} else {
						System.out.println("Please create a list of patients first.");
					}
					break;
//			Call the Heap-Increase-Key function to change the key value of an element in the max-heap tree.
			case 4: if(ValidateCurrentQueue(Patients)) {
						Heapsort.Build_Max_Heap(Patients);
						System.out.println("Please enter the Patient index that you want to change Condition(from 0 to "+(Patients.size()-1)+"):");
						int possition = scanner.nextInt();
						while(possition<0 || possition>Patients.size()-1) {
							System.out.println("Please enter the Patient index that between 0 and "+(Patients.size()-1));
							possition = scanner.nextInt();
						}
						System.out.println("Please enter the new condition of Patient " + possition +" :");
						int newCondition = scanner.nextInt();
						while(newCondition<Patients.get(possition).getCondition() || newCondition>99) {
							System.out.println("Please enter the new condition of Patient " + possition +"(Greater than "+ Patients.get(possition).getCondition() +" and less than 99)  :");
							newCondition = scanner.nextInt();
						}
						Heapsort.Heap_Increase_Key(Patients, possition, newCondition);
						Print();
					} else {
						System.out.println("Please create a list of patients first.");
					}
					break;
//			Call the Max-Heap-Insert function to insert new element into the max-heap tree. 
			case 5: if(ValidateCurrentQueue(Patients)) {
						if	(currentOccupiedSeat < 20) {
							Patient newPatient = RandomAPatient(); 
							System.out.println("The new random Patient is "+ newPatient.getInfo());
							Heapsort.Max_Heap_Insert(Patients, newPatient);
							currentOccupiedSeat ++;
							Print();
						} else {
							System.out.println("No available seat! Please Call and Remove a patient first.");
						}
					} else {
						System.out.println("Please create a list of patients first.");
					}
					break;
//			Call the heapsort method to sort the array by ascending order base on their condition. 
			case 6: if(ValidateCurrentQueue(Patients)) {
					Heapsort.heapsort(Patients);
					Print();
				} else {
					System.out.println("Please create a list of patients first.");
				}
				break;
			}
		} while (input != 0);
	}
}


