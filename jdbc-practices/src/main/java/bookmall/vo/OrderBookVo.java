package bookmall.vo;

public class OrderBookVo {
	private Long bookNo;
	private Long amount;
	private Long price;
	private String title;
	
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		String lResult = "\n\t\t" + bookNo + "\t\t";
		String rResult = "\t\t" + amount + "개\t      " + price + "원";
		String result = lResult + title + rResult;
		
		if(getTitle().length() < 10) {
			result = lResult + "\t" + title + "\t" + rResult;
		}
		return result;
	}
}
