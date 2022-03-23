package servlet.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/removeCookie")
public class CookieRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie c1 = new Cookie("c1", "v1");
		c1.setPath("/");
		c1.setMaxAge(0); // remove
		// setMaxAge(60*60*24*7); -> 일주일동안 저장 (파라미터는 초단위로 저장)
		// setMaxAge(음수); -> delete after browser closed, not saved 
		response.addCookie(c1);
		
		System.out.println("removeCookie");
		response.getWriter().append("remove Cookie!!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}