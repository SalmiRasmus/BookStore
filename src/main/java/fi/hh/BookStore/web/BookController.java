package fi.hh.BookStore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.BookStore.domain.Book;
import fi.hh.BookStore.domain.BookRepository;

@Controller
public class BookController {
	
    @Autowired
    private BookRepository repository;
    
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
    
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> studentListRest() {	
        return (List<Book>) repository.findAll();
    }    

    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findStudentRest(@PathVariable("id") Long bookId) {	
    	return repository.findOne(bookId);
    }   
    
    @RequestMapping(value="booklist", method=RequestMethod.GET)
    public String books(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        repository.delete(id);
        return "redirect:/booklist";
    }
    
    @RequestMapping(value = "/addbook")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addbook";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }
}