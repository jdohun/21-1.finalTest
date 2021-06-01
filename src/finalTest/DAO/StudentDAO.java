package finalTest.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
	private static StudentDAO dao = new StudentDAO();
	private StudentDAO() {}
	public static StudentDAO getInstance() {
		return dao;
	}
	
	private Connection connect() {
		Connection con = null;
		String dbUrl = "jdbc:mysql://localhost/finalTest?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String passwd = "1234";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, user, passwd);
		} catch (ClassNotFoundException e) {
			System.out.print("StudentDAO-connect:ClassNotFound " + e);
		} catch (SQLException e) {
			System.out.print("StudentDAO-connect:SQL " + e);
		}
		
		return con;
	}
	
	private void close(PreparedStatement pstmt, Connection con) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.print("pstmt.close error " + e);
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.print("con.close error " + e);
			}
		}
	}
	
	private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.print("rs.close error " + e);
			}
		}
		
		close(pstmt, con);
	}
	
	public boolean login(String id, String pwd) {
		boolean result = false;
		
		Connection con = connect();
		String sql = "select * from student where id=? and pwd=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) result = true;
			
		} catch (SQLException e) {
			System.out.println("login error" + e);
		} finally {
			close(rs, pstmt, con);
		}
		
		return result;
	}
}
