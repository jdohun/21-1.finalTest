package finalTest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalTest.service.StudentService;

public class StuEnrollController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sId = (String)req.getParameter("sId");
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		StudentService.getInstance().stuEnroll(sId, id);
		String path = "StuEnrollSubject.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
