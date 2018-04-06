
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;



//  	Patient Class
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

//	Node Class for the Red Black Tree
class Node<Patient> 
{
	 public Patient data;
	 public String color = "";
	 public Node<Patient> left, right, p;
	 
//	Constructor for Node class
	 public Node(Patient data, Node<Patient> l, Node<Patient> r, String color)
	 {
		setColor(color);
	    setLeft(l); setRight(r);
	    setP(null);
	    this.data = data;
	 }
//	Constructor for Node class
	public Node(Patient data)
	 {
	    this(data, null, null, "black");
	 }
//	Constructor for Node class
	public Node() {
		this(null, null, null, "black");
	}
//	To String Function to get information of the node 
	public String toString()
	 {
	    return (data.toString() + "Color: " + this.color);
	 }	
//	Getter and setter for Node element.
	public Node<Patient> getRight() {
		return right;
	}

	public void setRight(Node<Patient> right) {
		this.right = right;
	}
	
	public void setColor(String color) {
		this.color = color;
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
//	Comparator class
class MyComparator implements Comparator<Patient>
{
	public int compare(Patient x, Patient y)
	{
	     return y.getCondition() - x.getCondition();
	}
}

//Red Black Tree class
class RBT {
//	Property of the tree
//	Sentinel represent nil has black color.
	   private Node<Patient> nil = new Node<Patient>(null, null, null, "black");
	   private Node<Patient> root;
	   private Comparator<Patient> comparator;
	   
//	Construction
	   public RBT()
	   {
	      setRoot(nil);
	      this.root.p = nil;
	      comparator = null;
	   }
//		Construction
	   public RBT(Comparator<Patient> comp)
	   {
	      setRoot(nil);
	      comparator = comp;
	   }
//	   Get the root of the current Tree
	   public Node<Patient> getRoot() {
			return root;
	   }
//	   Set the root for the current Tree	
	   public void setRoot(Node<Patient> root) {
			this.root = root;
			this.root.p = nil;
			this.root.p.left = nil;
			this.root.p.right = nil;
	   }  
//	Define comparator to compare 2 patient
	   private int compare(Patient x, Patient y)
	   {
		  if(comparator == null) return x.compareTo(y);
		  else return comparator.compare(x,y);
	   }

//	   Tree Minimum
	   public Node<Patient> TreeMinimum(Node<Patient> x){
		   while(x.left != nil) {
			   x = x.left;
		   }
		   return x;
	   }
//	   Tree Maximum
	   public Node<Patient> TreeMaximum(Node<Patient> x){
		   while(x.right != nil) {
			   x = x.right;
		   }
		   return x;
	   }
//	   Search
	   public Node<Patient> Search(Node<Patient> x, int condition){
		   while (x!= nil && condition != x.data.getCondition()) {
			   if(condition < x.data.getCondition()) {
				   x = x.left;
			   } else x = x.right;
		   }
		   return x;
	   }
//	   In order tree Walk
	   public void InorderTreeWalk(Node<Patient> x) {
		   if (x != nil) {
			   this.InorderTreeWalk(x.left);
			   System.out.println(x.data.getInfo() + ", Color: " + x.color);
			   this.InorderTreeWalk(x.right);
		   }
	   }
//	   Insert new node
	   public void RBInsert(Node<Patient> z) {
		   Node<Patient> y = nil;
		   Node<Patient> x = this.root;
		   while(x!= nil) {
			   y = x;
			   if (this.compare(z.data, x.data) < 0) {
				   x = x.left;
			   } else x = x.right;
		   }
		  z.setP(y);
		   if(y == nil) {
			   this.root = z;
		   } else if(this.compare(z.data, y.data) < 0){
			   y.left = z;
		   } else y.right = z; 
		   z.left = nil;
		   z.right = nil;
		   z.color = "red";
		   RBInsertFixup(z);
	   }
//	   Left Rotate
	   public void LeftRotate(Node<Patient> x) {
		   Node<Patient> y = x.right;
		   x.right = y.left;
		   if(y.left != nil) {
			   y.left.p = x;
		   }
		   y.p = x.p;
		   if(x.p == nil) {
			   this.root = y;
		   } else if( x == x.p.left) {
			   x.p.left = y;
		   } else {
			   x.p.right = y;
		   }
		   y.left = x;
		   x.p = y;
	   }
//	   Right Rotate
	   public void RightRotate(Node<Patient> x) {
		   Node<Patient> y = x.left;
		   x.left = y.right;
		   if(y.right != nil) {
			   y.right.p = x;
		   }
		   y.p = x.p;
		   if(x.p == nil) {
			   this.root = y;
		   } else if( x == x.p.right) {
			   x.p.right = y;
		   } else {
			   x.p.left = y;
		   }
		   y.right = x;
		   x.p = y;
	   }
	   
//	   Insertion fix-up to maintain the property of the red black tree
	   public void RBInsertFixup(Node<Patient> z) {
		   Node<Patient> y = nil;
		   while (z.p.color == "red") {
			   if(z.p == z.p.p.left) {
				   y = z.p.p.right;
//				   Case 1
				   if(y.color == "red") {
					   z.p.color ="black";
					   y.color = "black";
					   z.p.p.color = "red";
					   z = z.p.p;
				   } else {
//				   Case 2
					   if(z==z.p.right) {
						   z = z.p;
					       LeftRotate(z);
					   }
//				   Case 3
					   z.p.color = "black";
					   z.p.p.color = "red";
					   RightRotate(z.p.p);
				   }   
			   } else {
				   y = z.p.p.left;
//				   Case 1
				   if(y.color == "red") {
					   z.p.color ="black";
					   y.color = "black";
					   z.p.p.color = "red";
					   z = z.p.p;
				   } else {
//				   Case 2
					   if(z==z.p.left) {
						   z = z.p;
						   RightRotate(z);
					   } 
//				   Case 3
					   z.p.color = "black";
					   z.p.p.color = "red";
					   LeftRotate(z.p.p);
				   }   
			   }
		   }
		   this.root.color = "black";
	   }
	   
//	   Transplant method for Red Black Tree
	   public void Transplant(Node<Patient> u, Node<Patient> v) {
		   if (u.p == nil){
			   this.root = v;
		   } else if(u == u.p.left) {
			   u.p.left = v;
		   } else u.p.right = v;
		   v.p = u.p;
	   }
//	   Delete node
	   public void RBDelete(Node<Patient> z) {
		   Node<Patient> y = z;
		   Node<Patient> x;
		   String y_OriginalColor = y.color; 
		   if (z.left == nil) {
			   x = z.right;
			   Transplant(z, z.right);
		   } else if (z.right == nil) {
			   x = z.left;
			   Transplant(z, z.left);
		   } else {
			   y = this.TreeMinimum(z.right);
			   y_OriginalColor = y.color;
			   x = y.right;
			   if (y.p == z) {
				 x.p = y;  
			   } else {
				   Transplant(y, y.right);
				   y.right = z.right;
				   y.right.p = y;
			   }
			   Transplant(z, y);
			   y.left = z.left;
			   y.left.p = y;
			   y.color = z.color;
		   }
		   if (y_OriginalColor == "black") {
			  RBDeleteFixup(x); 
		   }
	   }
//	   Delete Fix-up to maintain the Red Black Tree property
	   public void RBDeleteFixup(Node<Patient> x) {
		   while (x!= this.root && x.color == "black") {
			   Node<Patient> w;
			   if (x == x.p.left) {
				   w = x.p.right;
//				   Case 1
				   if (w.color =="red") {
					   w.color = "black";
					   x.p.color = "red";
					   LeftRotate(x.p);
					   w = x.p.right;
				   }
//				   Case 2
				   if (w.left.color =="black" && w.right.color == "black") {
					   w.color = "red";
					   x = x.p;
//				   Case 3
				   } else {
					   if(w.right.color =="black") {
						   w.left.color = "black";
						   w.color = "red";
						   RightRotate(w);
						   w = x.p.right;
					   }
//					Case 4
					   w.color = x.p.color;
					   x.p.color = "black";
					   w.right.color  ="black";
					   LeftRotate(x.p);
					   x = this.root;
				   }
			   } else {
				   w = x.p.left;
//				   Case 1
				   if (w.color =="red") {
				       w.color = "black";
				       x.p.color = "red";
				       RightRotate(x.p);
				       w = x.p.left;
				   }
//				   Case 2
				   if (w.right.color =="black" && w.left.color == "black") {
				       w.color = "red";
				       x = x.p;
//				   Case 3
				   } else {
					   if(w.left.color =="black") {
					       w.right.color = "black";
					       w.color = "red";
					       LeftRotate(w);
					       w = x.p.left;	     
					   }
//				   Case 4 
				       w.color = x.p.color;
				       x.p.color = "black";
				       w.left.color  ="black";
				       RightRotate(x.p);
				       x = this.root;
				   }
			   }
		   }
		   x.color = "black";
	   }
}

//	Main Class
public class HW3 {
//	Sample array of names.
	static String[] name = { "Tess Hagel", "Reatha Cassel", "Tim Clouse", "Salome Husted", "Jermaine Kozak", "Eryn Stacker", "Kyong Watanabe", "Sherrell Kamps","Mai Sills","Joni Rogan","Mattie Flannigan","Dane Parman","Mitchel Manney","Janna Mantyla", "Amos Pearce", "Janita Giroir", "Nery Closson","Adolfo Schram", "Julissa Renteria", "Mallory Rempe" ,"Vina Rumble","Greta Lannon","Booker Bynum", "Cassaundra Baranowski","Rodrigo Dunkley","Lenora Stancil","Carolee Nordby","Antonetta Berta", "Ela Thornburg", "Lorrine Lynn", "Phyliss Bolanos", "Tiny Dieguez", "Tona Monsour", "Brittanie Ventura" };
	static Random random = new Random();
	static RBT RBT = new RBT();
	static Integer[] A = new Integer[100];
//	Demo Array
//	static Integer[] A = {25,24,80,10,46,45,30,54,57,4,2,70,39,23,11,66,41,9,49,6,15,14,21,97,32,68,57,74,25,70,76};
	static int currentUsedIndex = 19;
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
			RBT.RBInsert(new Node<Patient>(newPatient));
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
		System.out.println("++++++++++++++Current Tree+++++++++++++++");
		RBT.InorderTreeWalk(RBT.getRoot());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
	}
// Main class to run the programe. 
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		System.out.println("Newly created Tree");
		RandomPatientsTree();
//		Sorted tree of patient ascending base on their condition
		Print();	
		int input = 1;
		do {
			System.out.println("+++++++++++++++Hospital Waiting Room+++++++++++++++++"
					+ "\n\nSimulation of Hospital Waiting Room Impliment Reb Black Tree.(Enter 0 to exit) "
					+ "\n Please choose the Following options:"
					+ "\n 1.Search for a patient with the given Priority number."
					+ "\n 2.Delete a patient from the tree."
					+ "\n 3.Add a patient into the current tree."
					+ "\n 4.Sort the Tree in ascending order base on their condition");
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
//			Search for a Patient
			case 1:	
					int b = GetCondition("find");
					Node<Patient> foundNode = RBT.Search(RBT.getRoot(), b);
					while(foundNode.data == null) {
						System.out.println("Can't find the patient with the given condition!");
						b = GetCondition("find");
						foundNode = RBT.Search(RBT.getRoot(), b);
					}
					System.out.println("The search Patient information are: "+ foundNode.data.getInfo() +", Color: " +foundNode.color);
					break;	
//			Delete a patient from the tree.
			case 2:	if (currentOccupiedSeat < 1) {
						System.out.println("There is no more patient to be deleted!");
						break;
					} else {
						b = GetCondition("delete");
						foundNode = RBT.Search(RBT.getRoot(), b);
						while(foundNode.data == null) {
							System.out.println("Can't find the patient with the given condition!");
							b = GetCondition("delete");
							foundNode = RBT.Search(RBT.getRoot(), b);
						}
						System.out.println("The information of Patient to be deleted are: "+ foundNode.data.getInfo() +", Color: " +foundNode.color);
						RBT.RBDelete(foundNode);
						Print();
						currentOccupiedSeat --;
						break;
					}
//			Insert a new random patient into the current tree, then print the current tree.
			case 3: if (currentOccupiedSeat >= 20) {
						System.out.println("Please delete a patient before insert new patient!");
						break;
					} else {
						Node<Patient> newNode = new Node<Patient>(RandomAPatient());
						System.out.println("The newly random Patient information is: "+ newNode.data.getInfo());
						RBT.RBInsert(newNode);
						System.out.println("The queue after inserting the new random Patient");
						Print();
						System.out.println("The newly insert node's color: "+ RBT.Search(RBT.getRoot(), newNode.data.getCondition()).color);
						currentOccupiedSeat ++;
						break;
					}
//			Print out the tree in the ascending order base on their condition. 
			case 4: Print();
					break;
			}
// 			Enter 0 to exit the programe.
		} while (input != 0);
	}
}