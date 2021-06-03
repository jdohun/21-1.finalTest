package finalTest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalTest.service.StudentService;

public class StuSubDeleteController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		String sId = req.getParameter("sId");
		StudentService.getInstance().StuSubDelete(id, sId);
		
		String path = "StuShowSubject.stu";
		HttpUtil.forward(req, resp, path);
	}
}
