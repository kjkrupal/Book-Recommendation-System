package com.library;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {

    public List<Book> getAllBooks() {
    	List<Book> list = new ArrayList<Book>();
    	
    	ResultSet r;
		try {

			DriverManager.deregisterDriver(new org.postgresql.Driver());
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "qwer");		
			Statement s = conn.createStatement();
			
			r = s.executeQuery("Select * from book");
			
			while(r.next()) {
				Book b = new Book();
	    		b.setTitle(r.getString(1));
	    		b.setAuthor(r.getString(2));
	    		b.setGenre(r.getString(3));
	    		b.setPublicationYear(r.getString(4));
	    		b.setLink(r.getString(5));
	    		b.setImgURL(r.getString(6));
	    		list.add(b);
	    	}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    }
    
    public List<Book> getBooksByAsin(String asin) {
    	List<Book> list = new ArrayList<Book>();
    	
    	ResultSet r;
		try {

			DriverManager.deregisterDriver(new org.postgresql.Driver());
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "qwer");
			Statement s = conn.createStatement();
			
			if(asin!=null) {
				r = s.executeQuery("Select * from book where asin = '"+asin+"'");
				
				while(r.next()) {
					Book b = new Book();
		    		b.setTitle(r.getString(1));
		    		b.setGenre(r.getString(3));
		    		b.setImgURL(r.getString(6));
		    		b.setRatings(r.getString(7));
		    		b.setAsin(r.getString(8));
		    		b.setLink("http://asin.info/a/"+asin);
		    		b.setImgURL("http://images.amazon.com/images/P/"+asin);
		    		
		    		
		    			System.out.println("///");
			    		NetworkClient c = new NetworkClient();
			        	List<Map<String, String>> data = c.getData(asin);
			        	if(data!=null && !data.isEmpty() && data.get(0)!=null && data.get(0).get("author")!=null && data.get(0).get("pubyear")!=null) {
			        		b.setAuthor(data.get(0).get("author"));
			        		b.setPublicationYear(data.get(0).get("pubyear"));
			        	}
		    		list.add(b);
		    	}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
    }
    
    public List<Book> getBooksByTitle(String title) {
    	List<Book> list = new ArrayList<Book>();
    	
    	ResultSet r;
		try {

			DriverManager.deregisterDriver(new org.postgresql.Driver());
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "qwer");
			Statement s = conn.createStatement();
			
			r = s.executeQuery("Select * from book where title like '%"+title+"%'");
			
			while(r.next()) {
				Book b = new Book();
	    		b.setTitle(r.getString(1));
	    		b.setGenre(r.getString(3));
	    		b.setImgURL(r.getString(6));
	    		b.setRatings(r.getString(7));
	    		b.setAsin(r.getString(8));
	    		b.setLink("http://asin.info/a/"+r.getString(8));
	    		b.setImgURL("http://images.amazon.com/images/P/"+r.getString(8));
	    		
	    		if(r.getString(8)!=null) {
	    			System.out.println("///");
		    		NetworkClient c = new NetworkClient();
		        	List<Map<String, String>> data = c.getData(r.getString(8));
		        	if(data!=null && !data.isEmpty() && data.get(0)!=null && data.get(0).get("author")!=null && data.get(0).get("pubyear")!=null) {
		        		b.setAuthor(data.get(0).get("author"));
		        		b.setPublicationYear(data.get(0).get("pubyear"));
		        	}
	    		}
	    		list.add(b);
	    	}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    	
    }
    
    public List<Book> getBooksByAuthor(String author) {
    	List<Book> list = new ArrayList<Book>();
    	
    	ResultSet r;
		try {

			DriverManager.deregisterDriver(new org.postgresql.Driver());
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "qwer");
			Statement s = conn.createStatement();
			
			r = s.executeQuery("Select * from book where author like '%"+author+"%'");
			
			while(r.next()) {
				Book b = new Book();
				b.setAsin(r.getString(1));
	    		b.setTitle(r.getString(2));
	    		b.setAuthor(r.getString(3));
	    		b.setGenre(r.getString(4));
	    		b.setPublicationYear(r.getString(5));
	    		b.setLink("http://asin.info/a/"+b.getAsin());
	    		b.setImgURL("http://images.amazon.com/images/P/"+b.getAsin());
	    		b.setRatings(r.getString(7));
	    		list.add(b);
	    	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return list;
    }
    
    public List<Book> getBooksByGenre(String genre) {
    	List<Book> list = new ArrayList<Book>();
    	
    	ResultSet r;
		try {

			DriverManager.deregisterDriver(new org.postgresql.Driver());
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "qwer");
			Statement s = conn.createStatement();
			
			r = s.executeQuery("Select * from book where genre like '%"+genre+"%'");
			
			while(r.next()) {
				Book b = new Book();
	    		b.setTitle(r.getString(1));
	    		b.setAuthor(r.getString(2));
	    		b.setGenre(r.getString(3));
	    		b.setPublicationYear(r.getString(4));
	    		b.setLink(r.getString(5));
	    		b.setImgURL(r.getString(6));
	    		b.setRatings(r.getString(7));
	    		list.add(b);
	    	}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    }
    
    /*public Data callService() {
    	
    }*/
    
}
