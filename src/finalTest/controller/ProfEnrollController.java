package finalTest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import finalTest.service.ProfService;

public class ProfEnrollController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sId = req.getParameter("sId");
		String sTitle = req.getParameter("sTitle");
		String sMax = req.getParameter("sMax");
		HttpSession session = req.getSession();
		String prof = (String)session.getAttribute("id");
		
		ProfService.getInstance().enroll(sId, sTitle, sMax, prof);
		
		String path = "MenuProf.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
