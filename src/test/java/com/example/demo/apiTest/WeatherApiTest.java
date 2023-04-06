package com.example.demo.apiTest;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
@SpringBootTest
public class WeatherApiTest {
	
			String serviceKey = "IQB7C38TSfgr5kAovrbwZXmc%2FcHfgBJ9GkvkTeOJ%2FjPnlxfBCO7DIfk%2BjXFzf9k3dZOrCj5ek%2FLHBCBKKpSvHw%3D%3D";
			String dataType = "JSON";
			String code = "11B20201";
	
	
	 	@Test
	 	void 테스트() throws IOException {
	 		 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst"); /*URL*/
	         urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
	         urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	         urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	         urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
	         urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode(code, "UTF-8")); /*11A00101(백령도), 11B10101 (서울), 11B20201(인천) 등... 별첨 엑셀자료 참조(‘육상’ 구분 값 참고)*/
	         URL url = new URL(urlBuilder.toString());
	         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         conn.setRequestProperty("Content-type", "application/json");
	         System.out.println("Response code: " + conn.getResponseCode());
	         BufferedReader rd;
	         if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	             rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         } else {
	             rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	         }
	         StringBuilder sb = new StringBuilder();
	         String line;
	         while ((line = rd.readLine()) != null) {
	             sb.append(line);
	         }
	         rd.close();
	         conn.disconnect();
	         System.out.println(sb.toString());
	     }
	 
}


