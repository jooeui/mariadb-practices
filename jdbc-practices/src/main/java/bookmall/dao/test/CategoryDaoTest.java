package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		// insertTest();
		
		System.out.println("─ 카테고리 목록 ──────");
		System.out.println(" 번호 │   카테고리");
		System.out.println("──────────────────");
		findAllTest();
	}

	private static void findAllTest() {
		List<CategoryVo> list = new CategoryDao().findAll();
		
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
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

}
