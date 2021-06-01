package finalTest.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalTest.service.ProfService;
import finalTest.vo.MemberVO;

public class ProfShowSubStuController implements Controller {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sub = (String)req.getParameter("sub");
		req.setAttribute("sub", sub);
		String sId = (String)req.getAttribute("profSubId");
		
		ArrayList<MemberVO> List = 	ProfService.getInstance().showSubStu(sId);
		req.setAttribute("List", List);
		
		String path = "ProfSubStuList.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
