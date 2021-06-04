package finalTest.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import finalTest.vo.SubjectVO;

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
	public SubjectVO searchSub(String subId) {
		SubjectVO subject = null;
		
		Connection con = connect();
		String sql = "select * from subject where id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				subject = new SubjectVO();
				subject.setId(rs.getString("id"));
				subject.setName(rs.getString("name"));
				subject.setCount(rs.getInt("count"));
				subject.setProf(rs.getString("prof"));
			}
			
		} catch (SQLException e) {
			System.out.println("searchSub error" + e);
		} finally {
			close(rs, pstmt, con);
		}
		
		return subject;
	}
	public void stuEnroll(String sId, String id) {
		Connection con = connect();
		String sql = "insert into enroll values(?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("stuEnroll error" + e);
		}finally {
			close(pstmt, con);
		}
	}
	public ArrayList<SubjectVO> stuShowSubject(String id) {
		ArrayList<SubjectVO> List = new ArrayList<SubjectVO>();
		Connection con = connect();
		String sql = "select a.id, a.name from subject a, enroll b where b.student=? and a.id = b.subject";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SubjectVO subject = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				subject = new SubjectVO();
				subject.setId(rs.getString("id"));
				subject.setName(rs.getString("name"));
				List.add(subject);
			}
			
		} catch (SQLException e) {
			System.out.println("stuShowSubject error" + e);
		} finally {
			close(rs, pstmt, con);
		}
		return List;
	}
	public void StuSubDelete(String id, String sId) {
		Connection con = connect();
		String sql = "delete from enroll where subject=? and student=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("StuSubDelete error" + e);
		} finally {
			close(pstmt, con);
		}
	}
}
