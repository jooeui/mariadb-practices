package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryTest {

	public static void main(String[] args) {
		// insertTest();
		
		System.out.println("====== 카테고리 목록 ======");
		findAllTest();
	}

	private static void findAllTest() {
		List<CategoryVo> list = new CategoryDao().findAll();
		
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
		CategoryVo vo = new CategoryVo();
		CategoryDao dao = new CategoryDao();
		
		vo.setCategory("컴퓨터/IT");
		dao.insert(vo);

		vo.setCategory("소설");
		dao.insert(vo);
		
		vo.setCategory("경제");
		dao.insert(vo);
	}

}
