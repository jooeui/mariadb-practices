package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {
	public static void main(String[] args) {
		// insertTest();
		
		System.out.println("======================= 도서 목록 ============================");
		findAllTest();
	}

	private static void findAllTest() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
		BookVo vo = new BookVo();
		BookDao dao = new BookDao();
		
		vo.setTitle("뉴문");
		vo.setPrice(32000L);
		vo.setCategoryNo(2L);
		dao.insert(vo);
		
		vo.setTitle("html5 웹 프로그래밍 입문");
		vo.setPrice(25000L);
		vo.setCategoryNo(1L);
		dao.insert(vo);
		
		vo.setTitle("넛지");
		vo.setPrice(17000L);
		vo.setCategoryNo(3L);
		dao.insert(vo);
	}
}
