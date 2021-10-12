package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDao {
	public void insert(OrderVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql1 = "insert into `order` " + 
						 " values(null, concat(date_format(now(), '%y%m%d%s%i%h'), ?), 0, ?, ?)";
			pstmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

			pstmt.setLong(1, vo.getMemberNo());
			pstmt.setString(2, vo.getAddress());
			pstmt.setLong(3, vo.getMemberNo());
			
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			
			if(!rs.next()) {
				throw new SQLException("주문 에러");
			}
			Long orderNoKey = rs.getLong(1);
			
			String sql2 = "insert into order_book values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql2);
			Long totalPrice = 0L;
			for(OrderBookVo obVO : vo.getOrderBookList()) {
				pstmt.setLong(1, orderNoKey);
				pstmt.setLong(2, obVO.getBookNo());
				pstmt.setLong(3, obVO.getAmount());
				pstmt.setLong(4, obVO.getPrice());
				totalPrice += obVO.getPrice();
				pstmt.addBatch();
				pstmt.clearParameters();
			}
			pstmt.executeBatch();
			
			
			
			vo.setOrderPrice(totalPrice);
			
			String sql3 = "update `order` set order_price=? where no=?";
			pstmt = conn.prepareStatement(sql3);
			
			pstmt.setLong(1, vo.getOrderPrice());
			pstmt.setLong(2, orderNoKey);
			
			pstmt.executeUpdate();
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
	}


	public List<OrderVo> findAll() {
		List<OrderVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet orderRS = null;
		ResultSet orderBookRS = null;

		try {
			conn = getConnection();

			String sql = "select o.no, o.order_no, concat(m.name, ' / ', m.email) as orderer, o.order_price, o.addr " + 
						" from `order` o, member m " + 
						" where o.member_no = m.no " +
						" order by o.no asc";
			String sql2 = "select ob.book_no, b.title, ob.amount, ob.price " + 
						 " from order_book ob, `order` o, book b " + " where ob.book_no = b.no " + 
						 "	and ob.order_no = o.no" + 
						 "   and ob.order_no = ?" +
						 " order by book_no asc";
			pstmt = conn.prepareStatement(sql);
			orderRS = pstmt.executeQuery();

			while (orderRS.next()) {
				Long no = orderRS.getLong(1);
				String orderNo = orderRS.getString(2);
				String orderer = orderRS.getString(3);
				Long orderPrice = orderRS.getLong(4);
				String address = orderRS.getString(5);

				OrderVo vo = new OrderVo();

				vo.setNo(no);
				vo.setOrderNo(orderNo);
				vo.setOrderer(orderer);
				vo.setOrderPrice(orderPrice);
				vo.setAddress(address);
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setLong(1, vo.getNo());
				orderBookRS = pstmt.executeQuery();
				
				List<OrderBookVo> obList = new ArrayList<>();
				while(orderBookRS.next()) {
					Long bookNo = orderBookRS.getLong(1);
					String title = orderBookRS.getString(2);
					Long amount = orderBookRS.getLong(3);
					Long price = orderBookRS.getLong(4);

					OrderBookVo obVO = new OrderBookVo();

					obVO.setBookNo(bookNo);
					obVO.setTitle(title);
					obVO.setAmount(amount);
					obVO.setPrice(price);
					
					obList.add(obVO);
				}
				vo.setOrderBookList(obList);
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
