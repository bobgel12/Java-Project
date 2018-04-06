
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Credit Card class that contain bankId, password, cardNumber, and expiration date of it.
 * Credit Card also has its own toString method to report its information
 * @author PhucLe
 *
 */

public class CreditCard {
	private String bankId;
	private String password;
	private int cardNumber;
	private GregorianCalendar expirationDate;

/**
 * Constructor that create Credit Card take in bankId, password, cardNumber, expire as params.
 * @param bankId
 * @param password
 * @param cardNumber
 * @param expire
 */
	public CreditCard(String bankId, String password, int cardNumber, GregorianCalendar expire) {
		this.setPassword(password);
		this.setcardNumber(cardNumber);
		this.setExpirationDate(expire);
		this.setBankId(bankId);
	}
/**
 * Getter for password
 * @return password
 */
	public String getPassword() {
		return password;
	}
/**
 * Setter for password
 * @param password
 */
	public void setPassword(String password) {
		this.password = password;
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
 * Getter for cardNumber
 * @return cardNumber
 */
	public Integer getcardNumber() {
		return cardNumber;
	}
/**
 * Setter for cardNumber
 * @param cardNumber
 */
	public void setcardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
/**
 * Getter for Expiration date
 * @return expirationDate
 */
	public GregorianCalendar getExpirationDate() {
		return expirationDate;
	}
/**
 * Setter for expirationDate
 * @param expirationDate
 */
	public void setExpirationDate(GregorianCalendar expirationDate) {
		this.expirationDate = expirationDate;
	}
/**
 * toString method that report card's information
 */
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String returnValue = this.getBankId() +", Cardnumber: "+ this.getcardNumber() + ", Password: " + this.getPassword() + ", Expiration Date: " + sdf.format(this.getExpirationDate().getTime());
		return returnValue;
	}
}
