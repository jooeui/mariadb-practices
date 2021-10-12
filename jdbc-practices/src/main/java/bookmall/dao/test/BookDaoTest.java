package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {
	public static void main(String[] args) {
		// insertTest();
		
		System.out.println("─ 도서 목록 ─────────────────────────────────────────────────────────────────────");
		System.out.println("   번호\t│\t\t제목\t\t │\t  가격\t   │\t  카테고리");
		System.out.println("──────────────────────────────────────────────────────────────────────────────");
		findAllTest();
	}

	private static void findAllTest() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
		BookVo vo = null;
		BookDao dao = new BookDao();
		
		vo = new BookVo();
		vo.setTitle("뉴문");
		vo.setPrice(32000L);
		vo.setCategoryNo(1L);
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setTitle("html5 웹 프로그래밍 입문");
		vo.setPrice(25000L);
		vo.setCategoryNo(2L);
		dao.insert(vo);
		
		vo = new BookVo();
		vo.setTitle("넛지");
		vo.setPrice(17000L);
		vo.setCategoryNo(3L);
		dao.insert(vo);
	}
}
