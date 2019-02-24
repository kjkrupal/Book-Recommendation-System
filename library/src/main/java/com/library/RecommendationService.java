package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


@Service
public class RecommendationService {
	
	public List<Book> getRecommendationTitle(String title) {
		List<Book> list = new ArrayList<Book>();
		List<Recommendation> recoList = new ArrayList<>();
		    	
    	ResultSet r1,r2,r3;
		try {

			DriverManager.deregisterDriver(new org.postgresql.Driver());
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "qwer");		
			Statement s = conn.createStatement();
			
			r1 = s.executeQuery("Select * from book where title like '%"+title+"%'");
			
			String asins = "";
			while(r1.next()) {
				if(r1.getString(8)!=null && r1.getString(8)!="null") {
					asins = asins+"'"+r1.getString(8).trim()+"',";
				}
	    	}
			
			asins = asins.substring(0, asins.length()-1);
			
			r2 = s.executeQuery("Select * from Recommendation where asin IN ("+asins+")");
			
			while(r2.next()) {
				Recommendation b = new Recommendation();
	    		b.setAsin(r2.getString(1));
	    		b.setConfidence(r2.getString(3));
	    		b.setPrediction(r2.getString(2));
	    		recoList.add(b);
	    	}
			
			List<String> recommendedAsins = new ArrayList<String>();
			for(Recommendation r: recoList) {
				recommendedAsins.addAll(Arrays.asList(r.getPrediction().split(",")));
			}
			
			String resultAsin = "";
			for(String st: recommendedAsins) {
				resultAsin = resultAsin + "'"+st.trim()+"',";
			}
			
			resultAsin = resultAsin.substring(0, resultAsin.length()-1);
			
			r3 = s.executeQuery("Select * from book where asin IN ("+resultAsin+")");
			
			while(r3.next()) {
				Book b = new Book();
	    		b.setTitle(r3.getString(1));
	    		b.setAuthor(r3.getString(2));
	    		b.setGenre(r3.getString(3));
	    		b.setPublicationYear(r3.getString(4));
	    		b.setLink(r3.getString(5));
	    		b.setImgURL(r3.getString(6));
	    		b.setRatings(r3.getString(7));
	    		b.setAsin(r3.getString(8));
	    		list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return list;
	}
	
	public List<Book> getRecommendationByAsin(String asin) {
		List<Book> list = new ArrayList<Book>();
		List<Recommendation> recoList = new ArrayList<>();
		    	
    	ResultSet r2,r3;
		try {

			DriverManager.deregisterDriver(new org.postgresql.Driver());
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "qwer");		
			Statement s = conn.createStatement();
			
			r2 = s.executeQuery("Select * from Recommendation where asin = '"+asin+"'");
			
			while(r2.next()) {
				Recommendation b = new Recommendation();
	    		b.setAsin(r2.getString(1));
	    		b.setConfidence(r2.getString(3));
	    		b.setPrediction(r2.getString(2));
	    		recoList.add(b);
	    	}
			
			Map<String,String> map = new HashMap<>();
			
			
			List<String> recommendedAsins = new ArrayList<String>();
			List<String> recommendedConfidence = new ArrayList<String>();
			
			for(Recommendation r: recoList) {
				recommendedAsins.addAll(Arrays.asList(r.getPrediction().split(",")));
				recommendedConfidence.addAll(Arrays.asList(r.getConfidence().split(",")));
			}
			
			String resultAsin = "";
			
			for(String st: recommendedAsins) {
				resultAsin = resultAsin + "'"+st.trim()+"',";	
			}
			
			for(int i=0;i<recommendedAsins.size();i++) {
				map.put(recommendedAsins.get(i).trim(),recommendedConfidence.get(i).trim());
			}
			
			resultAsin = resultAsin.substring(0, resultAsin.length()-1);
			
			r3 = s.executeQuery("Select * from book where asin IN ("+resultAsin+")");
			
			while(r3.next()) {
				Book b = new Book();
	    		b.setTitle(r3.getString(1));
	    		b.setAuthor(r3.getString(2));
	    		b.setGenre(r3.getString(3));
	    		b.setPublicationYear(r3.getString(4));
	    		b.setLink(r3.getString(5));
	    		b.setImgURL(r3.getString(6));
	    		b.setRatings(r3.getString(7));
	    		b.setAsin(r3.getString(8));
	    		b.setConfidence(map.get(b.getAsin()));
	    		if(Double.parseDouble(b.getConfidence())>0.20) {
	    			b.setConfidence((Double.parseDouble(b.getConfidence())*100)+"");
	    			list.add(b);
	    		}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return list;
	}
}

