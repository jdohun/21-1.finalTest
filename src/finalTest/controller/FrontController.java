package finalTest.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	HashMap<String, Controller> List = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		List = new HashMap<String, Controller>();
		List.put("/login.do", new LoginController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		Controller subController = List.get(path);
		subController.execute(req, resp);
	}
}
