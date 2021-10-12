package bookmall.vo;

public class BookVo {
	private Long no;
	private String title;
	private Long price;
	private Long categoryNo;
	private String category;
	
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategory(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		String lResult = "    " + no + "\t│\t";
		String rResult = "\t │\t" + price + "원\t   │\t" + category;
		String result = lResult + title + rResult;
		
		if(getTitle().length() < 10) {
			result = lResult + "\t" + title + "\t" + rResult;
		}
		return result;
	}
	
}
