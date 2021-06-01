package finalTest.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
	public static void forward(HttpServletRequest req, HttpServletResponse resp, String path) {
		RequestDispatcher rd = req.getRequestDispatcher(path);
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			System.out.print("forward 오류:" + e);
		}
	}
}
