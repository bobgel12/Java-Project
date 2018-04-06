
/**
 * ATM class that container bank, its daily limit.
 * @author phucle
 *
 */
public class Atm {
	private Bank bank;
	private double limit;
/**
 * Constructor that accept bank, limit as its params
 * @param bank
 * @param limit
 */
	public Atm (Bank bank, double limit) {
		this.setBank(bank);
		this.setLimit(limit);
	}
/**
 * Getter for bank
 * @return bank
 */
	public Bank getBank() {
		return bank;
	}
/**
 * Setter for Bank
 * @param bank
 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}
/**
 * Getter for limits
 * @return daily limit
 */
	public double getLimit() {
		return limit;
	}
/**
 * Setter for limit
 * @param limit
 */
	public void setLimit(double limit) {
		this.limit = limit;
	}
/**
 * Method that validate if the credit card is valid for this ATM given creditcard
 * @param creditcard
 * @return
 */
	public boolean validateCard(CreditCard creditcard) {
		if (creditcard.getBankId().compareTo(this.bank.getBankId()) == 0) {
			return true;
		} else return false;
	}
/**
 * Method that play as a bridge for the bank to validate the given password for the given creditcard
 * @param cardnumber
 * @param password
 * @return
 */
	public boolean validatePassword(int cardnumber, String password) {
		return(this.bank.validatePassword(cardnumber, password));
	}
/**
 * Method that play as a bridge for the bank to validate if the withdraw action is valid
 * @param cardnumber
 * @param amount
 * @return
 */
	public boolean requestWithdraw(int cardnumber, double amount) {
		return(this.bank.actionRequest(cardnumber, amount));
	}
}
