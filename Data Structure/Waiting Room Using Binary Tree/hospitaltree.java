
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Comparator;

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



//Node class
class Node<Patient> 
{
 public Patient data;
 public Node<Patient> left, right, p;

 public Node(Patient data, Node<Patient> l, Node<Patient> r)
 {
    setLeft(l); setRight(r);
    setP(null);
    this.data = data;
 }

 public Node(Patient data)
 {
    this(data, null, null);
 }

 public String toString()
 {
    return data.toString();
 }

	public Node<Patient> getRight() {
		return right;
	}

	public void setRight(Node<Patient> right) {
		this.right = right;
	}

	public Node<Patient> getLeft() {
		return left;
	}

	public void setLeft(Node<Patient> left) {
		this.left = left;
	}

	public Node<Patient> getP() {
		return p;
	}

	public void setP(Node<Patient> p) {
		this.p = p;
	}
} 

class MyComparator implements Comparator<Patient>
{
   public int compare(Patient x, Patient y)
   {
        return y.getCondition() - x.getCondition();
   }
}


class BstHospital {
//	PROPERTY OF THE TREE
	
	   private Node<Patient> root;
	   private Comparator<Patient> comparator;
	   
//	CONSTRUCTION
	   public BstHospital()
	   {
	      setRoot(null);
	      comparator = null;
	   }

	   public BstHospital(Comparator<Patient> comp)
	   {
	      setRoot(null);
	      comparator = comp;
	   }
	   
		public Node<Patient> getRoot() {
			return root;
		}

		public void setRoot(Node<Patient> root) {
			this.root = root;
		}
	   
//	DEFINE COMPARATOR TO COMPARE 2 PATIENT
	   private int compare(Patient x, Patient y)
	   {
		  if(comparator == null) return x.compareTo(y);
		  else return comparator.compare(x,y);
	   }

//	   Tree Minimum
	   public Node<Patient> TreeMinimum(Node<Patient> x){
		   while(x.left !=null) {
			   x = x.left;
		   }
		   return x;
	   }
//	   Tree Maximum
	   public Node<Patient> TreeMaximum(Node<Patient> x){
		   while(x.right !=null) {
			   x = x.right;
		   }
		   return x;
	   }
//	   Search
	   public Node<Patient> Search(Node<Patient> x, int condition){
		   while (x!= null && condition != x.data.getCondition()) {
			   if(condition < x.data.getCondition()) {
				   x = x.left;
			   } else x = x.right;
		   }
		   return x;
	   }
//	   In order tree Walk
	   public void InorderTreeWalk(Node<Patient> x) {
		   if (x != null) {
			   this.InorderTreeWalk(x.left);
			   System.out.println(x.data.getInfo());
			   this.InorderTreeWalk(x.right);
		   }
	   }
//	   INSERT NODE
	   public void Insert(Node<Patient> z) {
		   Node<Patient> y = null;
		   Node<Patient> x = this.root;
		   while(x!= null) {
			   y=x;
			   if (this.compare(z.data, x.data) < 0) {
				   x = x.left;
			   } else x = x.right;
		   }
		  z.setP(y);
		   if(y == null) {
			   this.root = z;
		   } else if(this.compare(z.data, y.data) < 0){
			   y.left = z;
		   } else y.right = z;   
	   }
//	   DELETE NODE
	   public void Transplant(Node<Patient> u, Node<Patient> v) {
		   if (u.p == null){
			   this.root = v;
		   } else if(u == u.p.left) {
			   u.p.left = v;
		   } else u.p.right = v;
		   if (v!= null) {
			   v.p = u.p;
		   }
	   }
	   public void Delete(Node<Patient> z) {
		   if (z.left == null) {
			   this.Transplant(z, z.right);
		   } else if (z.right == null) {
			   this.Transplant(z, z.left);
		   } else {
			   Node<Patient> y = this.TreeMinimum(z.right);
			   if (y.p != z) {
				   this.Transplant(y, y.right);
				   y.right = z.right;
				   y.right.p = y;
			   }
			   this.Transplant(z, y);
			   y.left = z.left;
			   y.left.p = y;
		   }
	   }
}

// MAIN FUNCTION

public class hospitaltree {
//	Sample array of names.
	static String[] name = { "Tess Hagel", "Reatha Cassel", "Tim Clouse", "Salome Husted", "Jermaine Kozak", "Eryn Stacker", "Kyong Watanabe", "Sherrell Kamps","Mai Sills","Joni Rogan","Mattie Flannigan","Dane Parman","Mitchel Manney","Janna Mantyla", "Amos Pearce", "Janita Giroir", "Nery Closson","Adolfo Schram", "Julissa Renteria", "Mallory Rempe" ,"Vina Rumble","Greta Lannon","Booker Bynum", "Cassaundra Baranowski","Rodrigo Dunkley","Lenora Stancil","Carolee Nordby","Antonetta Berta", "Ela Thornburg", "Lorrine Lynn", "Phyliss Bolanos", "Tiny Dieguez", "Tona Monsour", "Brittanie Ventura" };
	static Random random = new Random();
	static BstHospital BST = new BstHospital();
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
	public static void RandomPatientsTree() {
		currentOccupiedSeat = 0;
		for (int i = 0; i<A.length; i++) {
			A[i] = i;
		}
		Collections.shuffle(Arrays.asList(A));
		for (int i = 0; i < 20; i++) {
			Patient newPatient = new Patient(name[i], A[i]);
			BST.Insert(new Node<Patient>(newPatient));
			currentOccupiedSeat ++;
		}
	}
//	Get a input condition	
	public static int GetCondition(String a) {
		String b = "";
		System.out.println("Please enter the condition of patient that you want to "+ a +":");
		while(!scanner.hasNextInt()){
			System.out.println("Please enter the condition of patient that you want to"+ a +":");
			b = scanner.next();
		}
		b = scanner.next();
		return Integer.parseInt(b);
	}
//	Print the current Queue.
	public static void Print() {
		System.out.println("++++++++++++++Current Queue+++++++++++++++");
		BST.InorderTreeWalk(BST.getRoot());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
	}

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		System.out.println("Newly created Tree");
		RandomPatientsTree();
//		Sorted tree of patient ascending base on their condition
		Print();
//		Search for a Patient
		int b = GetCondition("find");
		Node<Patient> foundNode = BST.Search(BST.getRoot(), b);
		while(foundNode == null) {
			System.out.println("Can't find the patient with the given condition!");
			b = GetCondition("find");
			foundNode = BST.Search(BST.getRoot(), b);
		}
		System.out.println("The search Patient information are: "+ foundNode.data.getInfo());
//		Delete a Patient
		b = GetCondition("delete");
		foundNode = BST.Search(BST.getRoot(), b);
		while(foundNode == null) {
			System.out.println("Can't find the patient with the given condition!");
			b = GetCondition("delete");
			foundNode = BST.Search(BST.getRoot(), b);
		}
		System.out.println("The information of Patient to be deleted are: "+ foundNode.data.getInfo());
		BST.Delete(foundNode);
		Print();
//		Insert a random Patient
		Node<Patient> newNode = new Node<Patient>(RandomAPatient());
		System.out.println("The newly random Patient information are: "+ newNode.data.getInfo());
		BST.Insert(newNode);
		System.out.println("The queue after inserting the new random Patient");
		Print();
	}
	
}

