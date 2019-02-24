package com.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;

public class NetworkClient {

	public List<Map<String,String>> getData(String asin) {

		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		  try {

			
			URL url = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:0827229534&jscmd=data&format=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());	
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				// parsing file "JSONExample.json" 
		        Object obj;
				try {
					obj = new JSONParser().parse(output);
					JSONObject jo = (JSONObject) obj; 
					System.out.println(jo);
					jo = (JSONObject) jo.get("ISBN:"+asin);
					
					if(jo!=null && jo.get("by_statement")!=null && jo.get("publish_date")!=null) {
						Map<String, String> map = new HashMap<String, String>();
						String author= (String) jo.get("by_statement");
						String pubyear = (String) jo.get("publish_date");
						
						map.put("author", author);
						map.put("pubyear", pubyear);
						list.add(map);
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		          
		        // typecasting obj to JSONObject 
		        
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return list;

		}
}
