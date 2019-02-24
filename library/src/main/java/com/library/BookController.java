package com.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private RecommendationService recService;
    
    @RequestMapping(method = RequestMethod.GET, value="/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @CrossOrigin(origins="*")
    @RequestMapping(method = RequestMethod.GET, value="/books/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
    	System.out.println("getBooksByTitle: controller");
        return bookService.getBooksByTitle(title);
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping(method = RequestMethod.GET, value="/books/asin/{asin}")
    public List<Book> getBooksByAsin(@PathVariable String asin) {
        return bookService.getBooksByAsin(asin);
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/books/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/books/genre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre) {
        return bookService.getBooksByGenre(genre);
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping(method = RequestMethod.GET, value="/reco/title/{title}")
    public List<Book> getRecommendationByTitle(@PathVariable String title) {
        return recService.getRecommendationTitle(title);
    }
    
    @CrossOrigin(origins="*")
    @RequestMapping(method = RequestMethod.GET, value="/reco/asin/{asin}")
    public List<Book> getRecommendationByAsin(@PathVariable String asin) {
        return recService.getRecommendationByAsin(asin);
    }
}
