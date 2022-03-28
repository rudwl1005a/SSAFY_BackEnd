package servlet.datagokr;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class HouseSAXHandler extends DefaultHandler {
	private List<HouseDeal> houseDealList = new ArrayList<HouseDeal>();

	private String temp;
	private HouseDeal houseDeal;
	
	public void startElement(String uri, String localName
			                           , String qName, Attributes att ){
		if(qName.equals("item")){
			houseDeal = new HouseDeal();
		}
	}
	public void endElement(String uri, String localName, String qName){
		if(qName.equals("지역코드")) { 
			houseDeal.setCode(Integer.parseInt(temp));
		}else if(qName.equals("아파트")) { 
			houseDeal.setAptName(temp.trim());
		}else if(qName.equals("법정동")) { 
			houseDeal.setDong(temp.trim());
		}else if(qName.equals("img")) { 
			houseDeal.setImg(temp);
		}else if(qName.equals("건축년도")) { 
			houseDeal.setBuildYear(Integer.parseInt(temp));
		}else if(qName.equals("지번")) { 
			houseDeal.setJibun(temp);
		}else if(qName.equals("item")) {
			houseDealList.add(houseDeal);
		}
	}
	public void characters(char[]ch, int start, int length){
		temp = new String(ch, start, length);
	}
	public List<HouseDeal> getHouseDealList() {
		return houseDealList;
	}
}





