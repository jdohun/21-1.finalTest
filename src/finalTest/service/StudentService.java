package finalTest.service;

import java.util.ArrayList;

import finalTest.DAO.StudentDAO;
import finalTest.vo.SubjectVO;

public class StudentService {
	private static StudentService service = new StudentService();
	private StudentDAO dao = StudentDAO.getInstance();
	private StudentService() {}
	
	public static StudentService getInstance() {
		return service;
	}
	
	public boolean login(String id, String pwd) {
		return dao.login(id, pwd);
	}

	public SubjectVO searchSub(String subId) {
		return dao.searchSub(subId); 
	}

	public void stuEnroll(String sId, String id) {
		dao.stuEnroll(sId, id);
	}

	public ArrayList<SubjectVO> stuShowSubject(String id) {
		return dao.stuShowSubject(id);
	}

	public void StuSubDelete(String id, String sId) {
		dao.StuSubDelete(id, sId);
	}
}
