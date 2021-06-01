package finalTest.service;

import java.util.ArrayList;

import finalTest.DAO.ProfDAO;
import finalTest.vo.MemberVO;
import finalTest.vo.SubjectVO;

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

	public ArrayList<SubjectVO> showSubject(String prof) {
		return dao.showSubject(prof);
	}

	public ArrayList<MemberVO> showSubStu(String sId) {
		return dao.showSubStu(sId);
	}
}
