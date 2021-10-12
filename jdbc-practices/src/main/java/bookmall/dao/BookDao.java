package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;

public class BookDao {
	public boolean insert(BookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "insert into book values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩(binding)
			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getCategoryNo());
			
			
			// 5. SQL 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			// clean up
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "select b.no, b.title, b.price, c.category " + 
						" from book b, category c " + 
						" where b.category_no=c.no " + 
						" order by no asc ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩(binding)
			
			// 5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long price = rs.getLong(3);
				String category = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategory(category);
				
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			// clean up
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public Long getOBPrice(Long no, Long amount) {
		Connection conn = null;
		Long price = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select price*? as OBPrice from book where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, amount);
			pstmt.setLong(2, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				price = rs.getLong(1);
			}
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			// clean up
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return price;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. JDBC Driver 로딩
			// ClassNotFoundException은 던지면 받는 쪽에서 이상하기 때문에 여기서 처리
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/bookmall?charset=utf8?";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 " + e);
		}

		return conn;
	}
}
