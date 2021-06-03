package finalTest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalTest.service.StudentService;
import finalTest.vo.SubjectVO;

public class StuSearchSubController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String subId = (String)req.getParameter("sId");
		
		SubjectVO subject = StudentService.getInstance().searchSub(subId);
		req.setAttribute("subject", subject);
		
		String path = "/StuEnrollSubject.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
