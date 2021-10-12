package bookmall.dao.test;

import java.util.ArrayList;
import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.OrderDao;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;


public class OrderDaoTest {

	public static void main(String[] args) {
//		insertTest();
		
		System.out.println("─ 주문 목록 ─────────────────────────────────────────────────────────────────────────────────────────────────────\n");
		System.out.println(" 번호 │\t      주문번호\t   │\t       주문자(이름/이메일)\t\t│      결제금액\t  │\t    배송지");
		System.out.println(" ────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		findAllTest();
		
	}
	
	private static void findAllTest() {
		List<OrderVo> list = new OrderDao().findAll();
		for(OrderVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
		OrderVo vo = null;
		OrderBookVo obVO = null;
		OrderDao dao = new OrderDao();
		List<OrderBookVo> orderBookList = new ArrayList<>();
		
		obVO = new OrderBookVo();
		obVO.setBookNo(2L);
		obVO.setAmount(3L);
		obVO.setPrice(new BookDao().getOBPrice(obVO.getBookNo(), obVO.getAmount()));
		orderBookList.add(obVO);
		
		obVO = new OrderBookVo();
		obVO.setBookNo(3L);
		obVO.setAmount(5L);
		obVO.setPrice(new BookDao().getOBPrice(obVO.getBookNo(), obVO.getAmount()));
		orderBookList.add(obVO);
		
		vo = new OrderVo();
		vo.setAddress("서울");
		vo.setMemberNo(1L);
		vo.setOrderBookList(orderBookList);
		dao.insert(vo);
	}

}
