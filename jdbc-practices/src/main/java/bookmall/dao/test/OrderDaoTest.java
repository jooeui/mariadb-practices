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
		
		System.out.println("======================= 주문 목록 ============================");
		findAllTest();
		
	}
	
	private static void findAllTest() {
		List<OrderVo> list = new OrderDao().findAll();
		for(OrderVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
		List<OrderBookVo> orderBookList = new ArrayList<>();
		
		OrderBookVo obVO = new OrderBookVo();
		obVO.setBookNo(1L);
		obVO.setAmount(1L);
		obVO.setPrice(new BookDao().getOBPrice(obVO.getBookNo(), obVO.getAmount()));
		orderBookList.add(obVO);
		
		obVO = new OrderBookVo();
		obVO.setBookNo(3L);
		obVO.setAmount(2L);
		obVO.setPrice(new BookDao().getOBPrice(obVO.getBookNo(), obVO.getAmount()));
		orderBookList.add(obVO);
		
		OrderVo vo = new OrderVo();
		OrderDao dao = new OrderDao();

		vo.setAddress("부산");
		vo.setMemberNo(3L);
		vo.setOrderBookList(orderBookList);
		dao.insert(vo);
	}

}
