
import java.util.GregorianCalendar;
import java.util.Scanner;
/**
 * ATMSystem class that control the program.
 * @author PhucLe
 * @since February 23, 2018
 */
public class ATMSystem {
	private static Scanner scanner;
	private static Atm chosenATM;
	private static CreditCard chosenCard;
/*
 * 	Initialize BANK and its ATM
 */

	static Bank bank1 = new Bank("WellsFargo");
	static Bank bank2 = new Bank("Bank of America");
	static Atm Atm1 = new Atm(bank1,200);
	static Atm Atm2 = new Atm(bank2,300);

//		Initialize accounts and correspond credit cards.
	static Account account10 = new Account(500);
	static Account account11 = new Account(500);
	static Account account12 = new Account(500);
	static CreditCard creditcard10 = new CreditCard("WellsFargo", "secret", 10, new GregorianCalendar(2017,04,12));
	static CreditCard creditcard11 = new CreditCard("WellsFargo", "secret", 11, new GregorianCalendar(2018,04,15));
	static CreditCard creditcard12 = new CreditCard("WellsFargo", "secret", 12, new GregorianCalendar(2019,03,16));

	static Account account20 = new Account(500);
	static Account account21 = new Account(500);
	static Account account22 = new Account(500);
	static CreditCard creditcard20 = new CreditCard("Bank of America", "secret", 20, new GregorianCalendar(2016,5,26));
	static CreditCard creditcard21 = new CreditCard("Bank of America", "secret", 21, new GregorianCalendar(2018,8,16));
	static CreditCard creditcard22 = new CreditCard("Bank of America", "secret", 22, new GregorianCalendar(2019,5,11));

/*
 * Method for Displaying its ATMs, Banks as well as its credit card information.
 */
	public static void displayBank() {
		System.out.println("Wellcome to Our ATM System");
		System.out.println(
				"Assume all accounts have $500 preloaded. \n" +
				bank1.toString()+
				bank2.toString()
		);
		System.out.println("There are 2 ATM for these 2 banks:\n"+
				"ATM_1: "+ bank1.getBankId()+ ", ATM's limit: "+ Atm1.getLimit()+"\n"+
				"ATM_2: "+ bank2.getBankId()+", ATM's limit: "+ Atm2.getLimit()
				);

}
/**
 * Main method for running
 * @param args
 */
	public static void main(String[] args) {
		scanner = new Scanner(System.in);

/*
 * 		Connect credit card with its account.
 * 		Connect the bank with its credit card and account.
 */
		account10.setCreditcard(creditcard10);
		account11.setCreditcard(creditcard11);
		account12.setCreditcard(creditcard12);
		bank1.newAccount(account10);
		bank1.newAccount(account11);
		bank1.newAccount(account12);

		account20.setCreditcard(creditcard20);
		account21.setCreditcard(creditcard21);
		account22.setCreditcard(creditcard22);
		bank2.newAccount(account20);
		bank2.newAccount(account21);
		bank2.newAccount(account22);

/*
 * 		Authorization Dialog
 */
	    displayBank();

	    boolean validATM = false;

		while (validATM == false) {
		    System.out.println("Please enter your choice of ATM");
		    String in = scanner.nextLine();
		    if(in.compareTo("ATM_1") == 0) {
		    		chosenATM = Atm1;
		    		System.out.println("ATM_1 is selected");
		    		validATM = true;
		    } else if(in.compareTo("ATM_2") == 0) {
	    			chosenATM = Atm2;
	    			System.out.println("ATM_2 is selected");
	    			validATM = true;
		    }
	    }

		boolean validCard = false;
		boolean notExpired = false;
		String in1 = "";
		while (validCard == false || notExpired == false) {
		    System.out.println("Please enter your card number");
		    in1 = scanner.nextLine();
		    switch (in1) {
		    case "10":	chosenCard = creditcard10;
		    	break;
		    case "11":	chosenCard = creditcard11;
			break;
		    case "12":	chosenCard = creditcard12;
			break;
		    case "20":	chosenCard = creditcard20;
			break;
		    case "21":	chosenCard = creditcard21;
			break;
		    case "22":	chosenCard = creditcard22;
			break;
			default: System.out.println("Card not supported and has been returned.");
		    }
		    	if (chosenCard != null){
		    	    if (chosenCard.getExpirationDate().after(new GregorianCalendar(2018,03,19))) {
		    	    		notExpired = true;
			    } else {
			    		System.out.println("This Card has expired and has been returned");
			    		notExpired = false;
			    }

			    if (chosenATM.validateCard(chosenCard)) {
			    		validCard = true;
			    } else {
			    		System.out.println("This card is not supported by this ATM and has been returned");
			    		validCard = false;
			    }
		    	}
		}

		System.out.println("Card Accepted!");

/*
 * 		Transaction Dialog
 */
	    	boolean validPassword = false;
	    	String in2 = "";
	    	while(!validPassword) {
		    	System.out.println("Please enter password");
		    	in2 = scanner.nextLine();
		    	if (chosenATM.validatePassword(Integer.parseInt(in1), in2)) {
		    		validPassword = true;
		    	} else System.out.println("Wrong password!");
	    	}

	    	System.out.println("Authorization is accepted.");

	    	boolean validAmountATM = false;
	    	boolean validAmountBank = false;
	    	boolean stopTransaction = false;
	    	String in3 = "0";

	    	while (!stopTransaction) {
		    	while(!validAmountATM || !validAmountBank) {
		    		System.out.println("Start your transaction by entering the amount to withdraw.");
		    		in3 = scanner.nextLine();
		    		if (Double.parseDouble(in3) > chosenATM.getLimit()) {
		    			System.out.println("This amount exceeds the maximum amount you can withdraw per transaction: " + chosenATM.getLimit());
		    		} else {
		    			validAmountATM = true;
			    		if (!chosenATM.requestWithdraw(Integer.parseInt(in1), Double.parseDouble(in3))) {
			    			System.out.println("The amount exceeds the current balance.");
			    			System.out.println("Enter quit to exit, no to continue");
			    			in3 = scanner.nextLine();
			    			if (in3.compareTo("quit")==0) {
			    				stopTransaction = true;
			    				validAmountATM = true;
			    				validAmountBank = true;
			    			} else {
			    				stopTransaction = false;
			    				validAmountATM = false;
			    				validAmountBank = false;
			    			}
			    		} else {
			    			validAmountBank = true;
			    	    		System.out.println("$" + in3 +" is withdrawn from  your account. The remaining balance of this account is $" + chosenATM.getBank().getBalance(Integer.parseInt(in1), in2));
				    			System.out.println("Enter quit to exit, no to continue");
				    			in3 = scanner.nextLine();
				    			if (in3.compareTo("quit")==0) {
				    				stopTransaction = true;
				    				validAmountATM = true;
				    				validAmountBank = true;
				    			} else {
				    				stopTransaction = false;
				    				validAmountATM = false;
				    				validAmountBank = false;
				    			}
			    		}
		    		}
	    	}
    	}
    	System.out.println("Exited");
	}
}
