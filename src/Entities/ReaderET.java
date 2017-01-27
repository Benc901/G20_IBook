package Entities;
/**
 * class of reader entity with all the reader details by the database reader table
 * extends user entity
 *
 */
public class ReaderET extends UserET {
	
	public int subscription;
	private String card_num;
	private String valid_m;
	private String valid_y;
	private String cvv;
	private String rId;
	private int confirm;
	private int book_left;
	
	public ReaderET(UserET user, int subscription, String card_num, String valid_m, String valid_y,
			String cvv, String rId, int confirm, int book_left) {
		super(user);
		this.subscription = subscription;
		this.card_num = card_num;
		this.valid_m = valid_m;
		this.valid_y = valid_y;
		this.cvv = cvv;
		this.rId = rId;
		this.confirm = confirm;
		this.book_left = book_left;
	}
	public ReaderET(int id, int subscription, String card_num, String valid_m, String valid_y,
			String cvv, String rId, int confirm, int book_left) {
		super(id);
		this.subscription = subscription;
		this.card_num = card_num;
		this.valid_m = valid_m;
		this.valid_y = valid_y;
		this.cvv = cvv;
		this.rId = rId;
		this.confirm = confirm;
		this.book_left = book_left;
	}

	public int getSubscription() {
		return subscription;
	}

	public void setSubscription(int subscription) {
		this.subscription = subscription;
	}

	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}

	public String getValid_m() {
		return valid_m;
	}

	public void setValid_m(String valid_m) {
		this.valid_m = valid_m;
	}

	public String getValid_y() {
		return valid_y;
	}

	public void setValid_y(String valid_y) {
		this.valid_y = valid_y;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public int getConfirm() {
		return confirm;
	}

	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}

	public int getBook_left() {
		return book_left;
	}

	public void setBook_left(int book_left) {
		this.book_left = book_left;
	}
	
}
