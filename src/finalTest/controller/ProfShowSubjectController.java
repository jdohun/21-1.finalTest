package finalTest.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalTest.service.ProfService;
import finalTest.vo.SubjectVO;

public class ProfShowSubjectController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<SubjectVO> List = null;
		String prof = (String)req.getSession().getAttribute("id");
		List = ProfService.getInstance().showSubject(prof);
		
		req.setAttribute("List", List);
		String path = "ProfShowSubject.jsp";
		HttpUtil.forward(req, resp, path);
	}

}
