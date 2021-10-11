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
		return "[" + no + "] 주문번호: " + orderNo + ", 주문자: " + orderer + ", 결제금액: " + orderPrice + ", 주소: " + address + "\n"
				+ "    상세내역 " + orderBookList;
	}
	
}
