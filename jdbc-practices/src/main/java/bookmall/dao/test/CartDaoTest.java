package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		// insertTest();
		
		System.out.println("======================= 카트 목록 ============================");
		findAllTest();
	}
	
	private static void findAllTest() {
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
		CartVo vo = new CartVo();
		CartDao dao = new CartDao();
		
		vo.setMemberNo(2L);
		vo.setBookNo(1L);
		vo.setAmount(2L);
		dao.insert(vo);
		
		vo.setMemberNo(1L);
		vo.setBookNo(2L);
		vo.setAmount(1L);
		dao.insert(vo);
		
	}

}
