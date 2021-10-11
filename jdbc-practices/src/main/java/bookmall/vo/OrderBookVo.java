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
		return "(책 번호: " + bookNo + ", 제목: " + title + ", 수량: " + amount + ", 금액: " + price + ")";
	}
}
