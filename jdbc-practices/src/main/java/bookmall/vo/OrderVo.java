package bookmall.vo;

import java.util.List;

public class OrderVo {
	private Long no;
	private String orderNo;
	private Long memberNo;
	private String orderer;
	private Long orderPrice;
	private String address;
	private List<OrderBookVo> orderBookList;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public Long getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Long orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<OrderBookVo> getOrderBookList() {
		return orderBookList;
	}
	public void setOrderBookList(List<OrderBookVo> orderBookList) {
		this.orderBookList = orderBookList;
	}
	@Override
	public String toString() {
		String list = "";
		for(int i=0; i<orderBookList.size(); i++) {
			list += orderBookList.get(i);
		}
		return "  " + no + "  │\t  " + orderNo + "\t   │\t" + orderer + "\t│     " + orderPrice + "원\t  │\t" + address
				+ "\n     -----------------------------------------------------------------------------------------------\n"
				+ "\t      책번호\t\t \t제목\t\t \t수량\t \t금액"
				+ "\n     -----------------------------------------------------------------------------------------------"
				+ list
				+ "\n ─────────────────────────────────────────────────────────────────────────────────────────────────────────────";
	}
	
}
