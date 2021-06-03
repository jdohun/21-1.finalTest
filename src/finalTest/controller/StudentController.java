package finalTest.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentController extends HttpServlet {
	HashMap<String, Controller> List = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		List = new HashMap<String, Controller>();
		List.put("/searchSub.stu", new StuSearchSubController());
		List.put("/StuEnroll.stu", new StuEnrollController());
		List.put("/StuShowSubject.stu", new StuShowSubjectController());
		List.put("/StuSubDelete.stu", new StuSubDeleteController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		Controller subController = List.get(path);
		subController.execute(req, resp);
	}
}
