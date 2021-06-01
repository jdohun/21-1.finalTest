package finalTest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalTest.service.ProfService;
import finalTest.service.StudentService;

public class LoginController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginType = req.getParameter("loginType");
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		String path = null;
		boolean result = false;
		
		if(loginType.equals("prof")) {
			ProfService service = ProfService.getInstance();
			result = service.login(id, pwd);
			if(result) {
				HttpSession session = req.getSession();
				session.setAttribute("select", loginType);
				session.setAttribute("id", id);
				path = "MenuProf.jsp";
			}
			else {
				path = "index.jsp";
				req.setAttribute("result", "로그인 실패");
			}
		}
		else if(loginType.equals("stu")) {
			StudentService service = StudentService.getInstance();
			result = service.login(id, pwd);
			if(result) {
				HttpSession session = req.getSession();
				session.setAttribute("select", loginType);
				session.setAttribute("id", id);
				path = "MenuStudent.jsp";
			}
			else {
				path = "index.jsp";
				req.setAttribute("result", "로그인 실패");
			}
		}
		
		HttpUtil.forward(req, resp, path);
	}
}
