package finalTest.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import finalTest.vo.MemberVO;
import finalTest.vo.SubjectVO;

public class ProfDAO {
	private static ProfDAO dao = new ProfDAO();
	private ProfDAO() {}
	public static ProfDAO getInstance() {
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
			System.out.print("ProfDAO-connect:ClassNotFound " + e);
		} catch (SQLException e) {
			System.out.print("ProfDAO-connect:SQL " + e);
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
		String sql = "select * from prof where id=? and pwd=?";
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
			if(rs != null) {
				close(rs, pstmt, con);
			}
			else close(pstmt, con);
		}
		
		return result;
	}
	public void enroll(String sId, String sTitle, String sMax, String prof) {
		Connection con = connect();
		String sql = "insert into subject values(?,?,?,?)";
		PreparedStatement pstmt = null;	
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, sTitle);
			pstmt.setString(3, sMax);
			pstmt.setString(4, prof);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("enroll error" + e);
		} finally {
			close(pstmt, con);
		}
	}
	public ArrayList<SubjectVO> showSubject(String prof) {
		ArrayList<SubjectVO> List = new ArrayList<SubjectVO>();
		ArrayList<SubjectVO> List2 = new ArrayList<SubjectVO>();
		Connection con = connect();
		String sql = "select * from subject where prof=?";
		String sql2 = "select subject, count(*) as enrolled from enroll group by subject";
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		SubjectVO subject = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prof);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				subject = new SubjectVO();
				subject.setId(rs.getString("id"));
				subject.setName(rs.getString("name"));
				subject.setCount(rs.getInt("count"));
				List.add(subject);
			}
			
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				subject = new SubjectVO();
				subject.setId(rs.getString("subject"));
				subject.setEnrolled(rs.getInt("enrolled"));
				List2.add(subject);
			}
			
			for(int i = 0; i< List.size(); ++i) {
				for(int j = 0; j < List2.size(); ++j) {
					String sId = List.get(i).getId();
					if(sId.equals(List2.get(j).getId())) {
						List.get(i).setEnrolled(List2.get(j).getEnrolled());
						break;
					}
				}
			}
			
		} catch (SQLException e) {
			System.out.println("showSubject error" + e);
		} finally {
			if(rs != null) {
				close(rs, pstmt, con);
			}
			else close(pstmt, con);
		}
		return List;
	}
	
	public ArrayList<MemberVO> showSubStu(String sId) {
		ArrayList<MemberVO> List = new ArrayList<MemberVO>();
		Connection con = connect();
		String sql = "select student.id, student.name from student, enroll where enroll.subject = ? and enroll.student = student.id";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member  = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				List.add(member);
			}
			
		} catch (SQLException e) {
			System.out.println("showSubStu error" + e);
		} finally {
			if(rs != null) {
				close(rs, pstmt, con);
			}
			else close(pstmt, con);
		}
		return List;
	}
}
