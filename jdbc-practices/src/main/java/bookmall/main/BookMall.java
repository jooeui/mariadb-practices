package bookmall.main;

import java.util.ArrayList;
import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		System.out.println("온라인 서점에 오신 것을 환영합니다!\n");
		
		insert();
		findAll();
	}

	private static void findAll() {
		try {
			System.out.println("\n결과를 출력합니다!");
			findByMember();
			Thread.sleep(500);
			
			findByCategory();
			Thread.sleep(500);
			
			findByBook();
			Thread.sleep(500);
			
			findByCart();
			Thread.sleep(500);
			
			findByOrder();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void findByMember() {
		System.out.println("\n─ 멤버 목록 ──────────────────────────────────────────────────────────────────────────\n");
		System.out.println("   번호\t│\t이름\t   │\t  전화번호\t\t│\t\t이메일");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────");
		
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("\n");
	}

	private static void findByCategory() {
		System.out.println("\n─ 카테고리 목록 ──────\n");
		System.out.println(" 번호 │   카테고리");
		System.out.println("──────────────────");
		
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}	
		System.out.println("\n");	
	}

	private static void findByBook() {
		System.out.println("\n─ 도서 목록 ─────────────────────────────────────────────────────────────────────\n");
		System.out.println("   번호\t│\t\t제목\t\t │\t  가격\t   │\t  카테고리");
		System.out.println("──────────────────────────────────────────────────────────────────────────────");
		
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("\n");
	}

	private static void findByCart() {
		System.out.println("\n─ 카트 목록 ─────────────────────────────────────────────────────────────────────\n");
		System.out.println("  이름\t│\t\t제목\t\t│\t수량\t │\t 금액");
		System.out.println("──────────────────────────────────────────────────────────────────────────────");
		
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("\n");
	}

	private static void findByOrder() {
		System.out.println("\n─ 주문 목록 ─────────────────────────────────────────────────────────────────────────────────────────────────────\n");
		System.out.println(" 번호 │\t      주문번호\t   │\t       주문자(이름/이메일)\t\t│      결제금액\t  │\t    배송지");
		System.out.println(" ────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		
		List<OrderVo> list = new OrderDao().findAll();
		for(OrderVo vo : list) {
			System.out.println(vo);
		}
		System.out.println("\n");
	}

	private static void insert() {
		try {
			System.out.println("데이터를 추가합니다!");
			System.out.println("회원 추가...");
			Thread.sleep(500);
			insertMember();
			
			System.out.println("카테고리 추가...");
			Thread.sleep(500);
			insertCategory();
			
			System.out.println("상품 추가...");
			Thread.sleep(500);
			insertBook();
			
			System.out.println("카트 추가...");
			Thread.sleep(500);
			insertCart();
			
			System.out.println("주문 리스트 추가...");
			Thread.sleep(500);
			insertOrder();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void insertMember() {
		MemberVo vo = null;
		MemberDao dao = new MemberDao();
		
		vo = new MemberVo();
		vo.setName("피카츄");
		vo.setPhone("01011111111");
		vo.setEmail("pikachu@gmail.com");
		vo.setPassword("pika11");
		dao.insert(vo);
		
		vo = new MemberVo();
		vo.setName("파이리");
		vo.setPhone("01022222222");
		vo.setEmail("charmander@gmail.com");
		vo.setPassword("fire1234");
		dao.insert(vo);
	}

	private static void insertCategory() {
		CategoryVo vo = null;
		CategoryDao dao = new CategoryDao();
		
		vo = new CategoryVo();
		vo.setCategory("소설");
		dao.insert(vo);

		vo = new CategoryVo();
		vo.setCategory("컴퓨터/IT");
		dao.insert(vo);
		
		vo = new CategoryVo();
		vo.setCategory("경제");
		dao.insert(vo);		
	}

	private static void insertBook() {
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

	private static void insertCart() {
		CartVo vo = null;
		CartDao dao = new CartDao();
		
		vo = new CartVo();
		vo.setMemberNo(2L);
		vo.setBookNo(1L);
		vo.setAmount(2L);
		dao.insert(vo);
		
		vo = new CartVo();
		vo.setMemberNo(1L);
		vo.setBookNo(2L);
		vo.setAmount(1L);
		dao.insert(vo);
	}

	private static void insertOrder() {
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
