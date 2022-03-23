package servlet.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.UserDto;

@WebServlet("/SimplePostTestServlet")
public class SimplePostTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
//	    throws ServletException, IOException {
//	    System.out.println("OPTIONS");
//	    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//	    response.setHeader("Access-Control-Allow-Origin", "*");
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		System.out.println("userId : " + userId);
		System.out.println("userPwd : " + userPwd);
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		
		if( userId.equals("ssafy") && userPwd.equals("1234") ) {			
			jsonObject.addProperty("result", "success");
		}else {
			jsonObject.addProperty("result", "fail");
		}
		
		String jsonStr = gson.toJson(jsonObject);
		
		response.addHeader("Access-Control-Allow-Origin", "*"); // VSCode Live Server Request
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(jsonStr);		
		System.out.println("SimpleJsonListServlet jsonStr : " + jsonStr);
	}
}
