package bookmall.dao.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		// insertTest();
		
		System.out.println("======================= 멤버 목록 ============================");
		findAllTest();
	}

	private static void findAllTest() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
		MemberVo vo = new MemberVo();
		MemberDao dao = new MemberDao();
		vo.setName("파이리");
		vo.setPhone("01022222222");
		vo.setEmail("charmander@gmail.com");
		vo.setPassword("fire1234");
		dao.insert(vo);
		
		vo.setName("이상해씨");
		vo.setPhone("01033333333");
		vo.setEmail("bulbasaur@gmail.com");
		vo.setPassword("flower1234");
		dao.insert(vo);
		
		vo.setName("피카츄");
		vo.setPhone("01011111111");
		vo.setEmail("pikachu@gmail.com");
		vo.setPassword("pika11");
		dao.insert(vo);
		
	}
}
