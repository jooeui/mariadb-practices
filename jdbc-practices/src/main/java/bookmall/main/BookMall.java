package bookmall.main;

public class BookMall {

	public static void main(String[] args) {
		new CategoryDao().insert(vo);
		new CategoryDao().insert(vo);
		new CategoryDao().insert(vo);
		
		new CategoryDao().findAll();
		
		System.out.println("========== 회원 리스트 ==========");
	}

}
