

import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * Bank class is to contain its bankId and its accounts.
 * Bank can create new account and add it to its accounts array.
 * Bank also can  return balance of given account.
 * Bank can validate if the password enter by user match the password in the card of the account
 * Bank can approve or disapprove the withdraw request from the ATM.
 * Bank has its own toString method to report its account information.
 * @author PhucLe
 * @version 1.0.0
 *
 *
 */
public class Bank {
	private String bankId;
	private ArrayList<Account> accounts = new ArrayList<Account>();
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
/**
 * Bank constructor that accept bandId as its only params.
 * @param bankId
 */
	public Bank(String bankId) {
		this.setBankId(bankId);
	}
/**
 * Method to add new account to the bank's accounts array
 * @param account
 */
	public void newAccount (Account account) {
		this.accounts.add(account);
	}
/**
 * Getter for bankId
 * @return bankId
 */
	public String getBankId() {
		return bankId;
	}
/**
 * Setter for bankId
 * @param bankId
 */
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
/**
 * Method to determine if the withdraw action is approved or not
 * @param cardnumber
 * @param amount
 * @return true or false
 */
	boolean actionRequest(int cardnumber, double amount){
		Account foundAccount = null;
		for (Account account: this.accounts) {
			if (account.getCreditcard().getcardNumber() == cardnumber) foundAccount = account;
		}

		double check = foundAccount.getBalance() - amount;
		if (check >= 0) {
			foundAccount.setBalance(check);
			return true;
		} else
		return false;
	}
/**
 * Method to validate if a password is correct
 * @param cardnumber
 * @param password
 * @return
 */
	boolean validatePassword(int cardnumber, String password) {
		Account foundAccount = null;
		for (Account account: this.accounts) {
			if (account.getCreditcard().getcardNumber() == cardnumber) foundAccount = account;
		}
		if (foundAccount.getCreditcard().getPassword().compareTo(password) == 0) {
			return true;
		} else return false;
	}
/**
 * Method that return the current information of its accounts.
 *
 */
	public String toString() {
		String value = this.getBankId() +" has " + this.accounts.size() +" accounts: \n";
		for (int i = 0; i<this.accounts.size(); i++) {
			value = value + "Customer with card (" +this.bankId+ ", card number: " + this.accounts.get(i).getCreditcard().getcardNumber() +
			", expire on "+ sdf.format(this.accounts.get(i).getCreditcard().getExpirationDate().getTime())+ ", password: "+ this.accounts.get(i).getCreditcard().getPassword()+ "\n";
		}
		return value;
	}
/**
 * Method that return balance of given account
 * @param cardnumber
 * @param password
 * @return balance
 */
	public double getBalance(int cardnumber, String password) {
		Account foundAccount = null;
		for (Account account: this.accounts) {
			if (account.getCreditcard().getcardNumber() == cardnumber) foundAccount = account;
		}
		return(foundAccount.getBalance());
	}

}
