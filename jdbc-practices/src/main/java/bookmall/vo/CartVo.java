package bookmall.vo;

public class CartVo {
	private Long memberNo;
	private String name;
	private Long bookNo;
	private String title;
	private Long amount;
	private Long price;
	
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	@Override
	public String toString() {
		if(getTitle().length() > 10) {
			return " " + name + "\t│\t" + title + "\t│\t" + amount + "개\t │\t" + price + "원";
		} else {
			return " " + name + "\t│\t\t" + title + "\t\t│\t" + amount + "개\t │\t" + price + "원";
		}
	}
	
}
