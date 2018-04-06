
/**
 * Account class that contain balance and its creditcard.
 * @author PhucLe
 *
 */
public class Account {
	private double balance;
	private CreditCard creditcard;
/**
 * Constructor that accept balance as it param
 * @param balance
 */
	public Account(double balance) {
		this.balance = balance;
	}
/**
 * Getter for balance
 * @return balance
 */
	public double getBalance() {
		return balance;
	}
/**
 * Setter for balance
 * @param balance
 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
/**
 * Getter for CreditCard
 * @return creditcard
 */
	public CreditCard getCreditcard() {
		return creditcard;
	}
/**
 * Setter for Credit Card
 * @param creditcard
 */
	public void setCreditcard(CreditCard creditcard) {
		this.creditcard = creditcard;
	}

}
