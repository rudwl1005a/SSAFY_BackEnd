package servlet.datagokr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/apttrade")
public class AptTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final String SERVICE_URL = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
	private final String SERVICE_KEY = "6vM39dXki1oCBdD9y9x224szS5pck9jjInXEMPVoXVMUiT3JH37DWatGsfB8lVuZOkZt3J9dlWSy1uFn%2Bl6RhA%3D%3D";
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNo = request.getParameter("pageNo");
		if(pageNo == null) {
			pageNo = "1";
		}
		
		String numOfRows = request.getParameter("numOfRows");
		if(numOfRows == null) {
			numOfRows = "10";
		}
		
		String LAWD_CD = request.getParameter("LAWD_CD");
		if(LAWD_CD == null) {
			LAWD_CD = "11110"; // 서울시 종로구
		}
		
		String DEAL_YMD = request.getParameter("DEAL_YMD");
		if(DEAL_YMD == null) {
			DEAL_YMD = "202112";
		}
		
		String clsf = request.getParameter("clsf");
		if(clsf == null) {
			clsf = "json";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(SERVICE_URL).append("?serviceKey=").append(SERVICE_KEY)
			.append("&pageNo=").append(pageNo)
			.append("&numOfRows=").append(numOfRows)
			.append("&DEAL_YMD=").append(DEAL_YMD)
			.append("&LAWD_CD=").append(LAWD_CD);
		
		System.out.println(sb.toString());
		
        // HTTP Request
        URL url = new URL(sb.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader br = null;
        if( responseCode == 200 ) {

            br = new BufferedReader( new InputStreamReader(con.getInputStream()));

            StringBuilder result = new StringBuilder();
            String line = null;
            while( (line = br.readLine()) != null ) {
                result.append(line);
            }
            br.close();
            con.disconnect();

            System.out.println(result.toString());

            response.setContentType("application/xml; charset=utf-8");
            response.getWriter().write(result.toString());
        }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
