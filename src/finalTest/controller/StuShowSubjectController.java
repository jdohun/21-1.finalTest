package finalTest.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalTest.service.StudentService;
import finalTest.vo.SubjectVO;

public class StuShowSubjectController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		ArrayList<SubjectVO> List = null; 
		List = StudentService.getInstance().stuShowSubject(id);
		req.setAttribute("List", List);
		
		String path = "StuShowSubject.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
