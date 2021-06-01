package finalTest.service;

import finalTest.DAO.ProfDAO;

public class ProfService {
	private static ProfService service = new ProfService();
	private ProfDAO dao = ProfDAO.getInstance();
	
	private ProfService() {}
	
	public static ProfService getInstance() {
		return service;
	}
	
	public boolean login(String id, String pwd) {
		return dao.login(id,pwd);
	}
	
	public void enroll(String sId, String sTitle, String sMax, String prof) {
		dao.enroll(sId, sTitle, sMax, prof);
	}
}
