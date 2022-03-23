package servlet.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/param")
public class ParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		System.out.println("username : " + username);
		System.out.println("email : " + email);

		response.getWriter().append("username : " + username);
		response.getWriter().append(" email : " + email);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8"); // 한글 깨지지 않게
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String[] hobby = request.getParameterValues("hobby");

		System.out.println("username : " + username);
		System.out.println("password : " + password);

		for (String h : hobby) {
			System.out.println(h);
		}

		response.setContentType("text/html; charset=utf-8"); // 한글 깨지지 않게
		response.getWriter().append("username : " + username);
		response.getWriter().append(" password : " + password);

		for (String h : hobby) {
			response.getWriter().append(" hobby : " + h);
		}
	}

}