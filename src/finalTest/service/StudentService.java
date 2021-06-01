package finalTest.service;

import finalTest.DAO.StudentDAO;

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
}
