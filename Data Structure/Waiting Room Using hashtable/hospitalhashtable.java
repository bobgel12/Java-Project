
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.LinkedList;

// Patient class

class Patient {
	private String name;
	private int condition;
//	Construction of Patient class.
	Patient(){
		this.setName("John Doe");
		this.setCondition(0);
	}
//	Construction of Patient class.
	Patient(String name, int condition){
		this.setName(name);
		this.setCondition(condition);
	}
//	Get name of a patient.
	public String getName() {
		return name;
	}
//	Set name of a patient.
	public void setName(String name) {
		this.name = name;
	}
//	Get condition of a patient.
	public int getCondition() {
		return condition;
	}
//	Set condition of a patient.
	public void setCondition(int condition) {
		this.condition = condition;
	}
//	Get full properties of a patient. 
	public String getInfo() {
		return("Name: "+ name + ", Condition: "+ condition);
	}
	public int compareTo(Patient y) {
		return this.condition - y.condition;
	}
}


// Hashtable class
class HashTable {
    //	HASH TABLE VARIABLE
        private LinkedList<Patient>[] Chainning = new LinkedList[11];
        
    //	CONSTRUCTOR
        public HashTable() {
            for(int i=0; i<11; i++) {
                Chainning[i] = null;
            }
        }
    //	FUNCTION TO GET PATIENT BASE ON CONDITION
        public Patient getPatient(int condition) {
            if(condition == 0)
                return null;
    
            int index = condition % 11;
            LinkedList<Patient> items = Chainning[index];
    
            if(items == null)
                return null;
    
            for(Patient item : items) {
                    if(item.getCondition() == condition )
                    return item;
            }
    
            return null;
        }
    //	FUNCTION TO NAME BASE ON CONDITION
        public String get(int condition) {
            Patient item = getPatient(condition);
    
            if(item == null)
                return null;
            else
                return item.getName();
        }
    //	FUNCTION TO PUT NEW PATIENT INTO THE TABLE
        public void put(Patient patient) {
            int index = patient.getCondition() % 11;
            LinkedList<Patient> items = Chainning[index];
    
            if(items == null) {
                items = new LinkedList<Patient>();
                Patient item = new Patient();
                item.setName(patient.getName());
                item.setCondition(patient.getCondition());;
                items.add(item);
                Chainning[index] = items;
            }
            else {
                for(Patient item : items) {
                        if(item.getCondition() == patient.getCondition() ) {
                        item.setName(patient.getName());;
                        return;
                    }
                }
                Patient item = new Patient();
                item.setName(patient.getName());
                item.setCondition(patient.getCondition());
                items.add(item);
            }
        }
    //	FUNCTION TO DELETE A PATIENT FROM HASH TABLE
        public void delete(int condition) {
            int index = condition % 11;
            LinkedList<Patient> items = Chainning[index];
            if(items == null)
                return;
            for(Patient item : items) {
                    if(item.getCondition() ==condition ) {
                    items.remove(item);
                    return;
                }
            }
        }
    
    }

public class hospitalhashtable {
//	Sample array of names.
	static String[] name = { "Tess Hagel", "Reatha Cassel", "Tim Clouse", "Salome Husted", "Jermaine Kozak", "Eryn Stacker", "Kyong Watanabe", "Sherrell Kamps","Mai Sills","Joni Rogan","Mattie Flannigan","Dane Parman","Mitchel Manney","Janna Mantyla", "Amos Pearce", "Janita Giroir", "Nery Closson","Adolfo Schram", "Julissa Renteria", "Mallory Rempe" ,"Vina Rumble","Greta Lannon","Booker Bynum", "Cassaundra Baranowski","Rodrigo Dunkley","Lenora Stancil","Carolee Nordby","Antonetta Berta", "Ela Thornburg", "Lorrine Lynn", "Phyliss Bolanos", "Tiny Dieguez", "Tona Monsour", "Brittanie Ventura" };
	static HashTable Hash = new HashTable();
	static Patient[] Patients = new Patient[20];
	static Integer[] A = new Integer[100];
	static int currentUsedIndex = 20;
	static int currentOccupiedSeat = 0;
	static Scanner scanner = new Scanner(System.in);
//	Random a new patient with distinct name and condition.
	public static Patient RandomAPatient() {
		currentUsedIndex++;
		return new Patient((name[currentUsedIndex]), A[currentUsedIndex]);
	}
//	Function random a array of patient with distinct condition and name
	public static void RandomPatientsArray() {
		currentOccupiedSeat = 0;
		for (int i = 0; i<A.length; i++) {
			A[i] = i+1;
		}
		Collections.shuffle(Arrays.asList(A));
		for (int i = 0; i < 20; i++) {
			Patient newPatient = new Patient(name[i], A[i]);
			Patients[i] = newPatient;
			currentOccupiedSeat ++;
		}
	}
//	Function printout the current Patient in the Hash table
	public static void PrintCurrentHashTable() {
		for (int i = 0; i<20; i++) {
			Patient foundPatient = Hash.getPatient(A[i]);
			if(foundPatient != null) {
				System.out.println(foundPatient.getInfo());
			}
		}
	}
//	Function to creating a new Hash Table
	public static void CreateHashTable(){
		for(Patient a: Patients) {
			Hash.put(a);
		}
	}
//	Function to get valid condition input
	public static int GetCondition(String a) {
		String b = "";
		System.out.println("Please enter the condition of patient that you want to "+ a +":");
		while(!scanner.hasNextInt()){
			System.out.println("Please enter the condition of patient that you want to "+ a +":");
			b = scanner.next();
		}
		b = scanner.next();
		return Integer.parseInt(b);
	}
	public static void main(String[]args) {
		RandomPatientsArray();
		System.out.println("List of random Patient:");
		System.out.println("Hashing all Patients and create HashTable...");
		CreateHashTable();
		PrintCurrentHashTable();
		int b = 0;
//		Search for a Patient
		b = GetCondition("find");
		String foundName = Hash.get(b);
		while(foundName == null) {
			System.out.println("Can't find the patient with the given condition!");
			b = GetCondition("find");
			foundName = Hash.get(b);
		}
		System.out.println("Name of patient with condition, "+ b + " is: "+foundName);
//		Delete a Patient
		b = GetCondition("delete");
		foundName = Hash.get(b);
		while(foundName == null) {
			System.out.println("Can't find the patient with the given condition!");
			b = GetCondition("delete");
			foundName = Hash.get(b);
		}
		Hash.delete(b);
		System.out.println("The patient has just been deleted is: "+foundName+ ", Condition: "+b);
		PrintCurrentHashTable();
//		Insert a new Patient
		Patient newPatient = RandomAPatient();
		Hash.put(newPatient);
		System.out.println("The new random patient has just ben insert is :"+ newPatient.getInfo());
		PrintCurrentHashTable();
		
	}
}
