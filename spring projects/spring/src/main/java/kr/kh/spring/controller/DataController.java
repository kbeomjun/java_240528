package kr.kh.spring.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {
	
	@GetMapping("/data/sample")
	public String dataSample() {
		return "/data/sample";
	}
	
	
	@ResponseBody
    @PostMapping(value="/data/sample", produces="application/json; charset=UTF-8;")
    public String dataSamplePost(@RequestParam String location) throws Exception {
		String serviceKey = "pBhWBUkw5%2F4x7t6DQ1COIPpC4HQE%2FZQ7lM17jognwIbseXGONSy4EXK6O81K%2B%2Brce5XANzJELnFUFk240rMxoQ%3D%3D";
       
//		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"; 
//        url += "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
//        url += "&returnType=" + URLEncoder.encode("json", "UTF-8");
//        url += "&sidoName=" + URLEncoder.encode(location, "UTF-8");
        
        StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode(location, "UTF-8"));

        URL requestUrl = new URL(urlBuilder.toString());
        HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + urlConnection.getResponseCode());

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        
        StringBuilder responseText = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            responseText.append(line);
        }
        br.close();
        urlConnection.disconnect();
        
        return responseText.toString(); // JSON 형태로 반환
    }
}
