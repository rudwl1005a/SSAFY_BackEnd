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

@WebServlet("/SimpleJsonListServlet")
public class SimpleJsonListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
//	    throws ServletException, IOException {
//	    System.out.println("OPTIONS");
//	    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//	    response.setHeader("Access-Control-Allow-Origin", "*");
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String msg = request.getParameter("msg");
		System.out.println("userId : " + userId + " msg : " + msg);
		
		ArrayList<UserDto> userList = new ArrayList<UserDto>();
		userList.add(new UserDto(1111, "홍길동", "hong@mail.com"));
		userList.add(new UserDto(2222, "이길동", "lee@mail.com"));
		userList.add(new UserDto(3333, "박길동", "park@mail.com"));

		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(userList); // OK

		response.addHeader("Access-Control-Allow-Origin", "*"); // VSCode Live Server Request

//		response.setContentType("application/json; charset=utf-8");  
		response.setContentType("text/html; charset=utf-8"); // 
		response.getWriter().write(jsonStr);
		System.out.println("SimpleJsonListServlet JSON : " + jsonStr);
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
