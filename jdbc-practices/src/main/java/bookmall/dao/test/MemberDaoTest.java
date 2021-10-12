package bookmall.dao.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		// insertTest();
		
		System.out.println("─ 멤버 목록 ──────────────────────────────────────────────────────────────────────────");
		System.out.println("   번호\t│\t이름\t   │\t  전화번호\t\t│\t\t이메일");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────");
		findAllTest();
	}

	private static void findAllTest() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest() {
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
}
