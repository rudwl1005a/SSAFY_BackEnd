package servlet.datagokr;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.google.gson.Gson;

@WebServlet("/apttrade")
public class AptTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String SERVICE_URL = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
	private final String SERVICE_KEY = "6vM39dXki1oCBdD9y9x224szS5pck9jjInXEMPVoXVMUiT3JH37DWatGsfB8lVuZOkZt3J9dlWSy1uFn%2Bl6RhA%3D%3D";
//	private final String SERVICE_KEY = "e2Yh4Ejq7VHkBwgR7A8VoAwQFEoTGYyqwzrcpjhxlPmeafQXdpYyLESEa2mqfQfBrL8CxIa7RWFMvCp3zrKG1A%3D%3D"; // 교수님 키
//	private final String SERVICE_KEY = "6vM39dXki1oCBdD9y9x224szS5pck9jjInXEMPVoXVMUiT3JH37DWatGsfB8lVuZOkZt3J9dlWSy1uFn+l6RhA=="; // decoding

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        if( pageNo == null ) pageNo = "1";

        String numOfRows = request.getParameter("numOfRows");
        if( numOfRows == null ) numOfRows = "10";

        String DEAL_YMD = request.getParameter("DEAL_YMD");
        if( DEAL_YMD == null ) DEAL_YMD = "202112";

        String LAWD_CD = request.getParameter("LAWD_CD");
        if( LAWD_CD == null ) LAWD_CD = "11110"; // 종로구

        String clsf = request.getParameter("clsf");
        if( clsf == null ) clsf = "json";

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

            System.out.println(result.toString());

            if( "raw".equals(clsf) ) {
                sendRaw(response, result.toString() );
            }else if( "json".equals(clsf)) {
                sendJson(response, result.toString() );
            }
        }else {
            System.out.println("error : " + responseCode);
        }
        
        con.disconnect();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void sendRaw(HttpServletResponse response, String result) throws ServletException, IOException  {
        response.setContentType("application/xml; charset=utf-8");
        response.getWriter().write(result.toString());
    }

    protected void sendJson(HttpServletResponse response, String result) throws ServletException, IOException  {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            HouseSAXHandler handler = new HouseSAXHandler();
            InputStream is = new ByteArrayInputStream( result.getBytes(StandardCharsets.UTF_8));
            parser.parse(is, handler);
            List<HouseDeal> houseDealList = handler.getHouseDealList();

            Gson gson = new Gson();
            String jsonStr = gson.toJson(houseDealList);

            System.out.println("JSON -----------------");
            System.out.println(jsonStr);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(jsonStr);

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

}
