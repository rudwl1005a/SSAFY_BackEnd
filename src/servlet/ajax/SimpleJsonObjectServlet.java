package servlet.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.UserDto;


@WebServlet("/SimpleJsonObjectServlet")
public class SimpleJsonObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDto userDto = new UserDto(1111, "홍길동", "hong@mail.com");

		Gson gson = new Gson();
		String jsonStr = gson.toJson(userDto); // OK

		response.addHeader("Access-Control-Allow-Origin", "*"); // VSCode Live Server Request
		// text/html 도 가능하지만, content-type을 명료하게 하는 게 더 좋다.
		// 한글 처리를 위해 charset 필요
		response.setContentType("application/json; charset=utf-8"); 
		// response.setContentType("text/html; charset=utf-8"); // 
		response.getWriter().write(jsonStr);
		System.out.println("SimpleJsonObjectServlet JSON : " + jsonStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
