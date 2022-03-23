package servlet.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/maintain")
public class MaintainDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("attr1", "value1");
		getServletContext().setAttribute("attr2", "value2"); // servlet은 다른브라우저에서도 share됨
		request.getSession().setAttribute("attr3", "value3"); // session에 넣은건 forwarding 안해도 출력됨
		
		System.out.println("maintain");
		
		// forward - value1,2,3 다 넘어감
//		request.getRequestDispatcher("/maintain2").forward(request, response);
		
		// sendRedirect - value2,3만 넘어감
		response.sendRedirect("/WebBasic/maintain2");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}