package Entities;

public class ReaderET extends UserET {
	
	private int subscription;
	private String card_num;
	private String valid_m;
	private String valid_y;
	private String cvv;
	private String rId;
	private int confirm;
	private int book_left;
	
	public ReaderET(String userName, String passWord, int subscription, String card_num, String valid_m, String valid_y,
			String cvv, String rId, int confirm, int book_left) {
		super(userName, passWord);
		this.subscription = subscription;
		this.card_num = card_num;
		this.valid_m = valid_m;
		this.valid_y = valid_y;
		this.cvv = cvv;
		this.rId = rId;
		this.confirm = confirm;
		this.book_left = book_left;
	}
	
}
