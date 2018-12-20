package will.quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import will.quanlythuvien.model.Author;
import will.quanlythuvien.model.Book;
import will.quanlythuvien.service.AuthorService;
import will.quanlythuvien.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @ModelAttribute("authors")
    public Page<Author> provinces(Pageable pageable) {
        return authorService.findAll(pageable);
    }

    @GetMapping("/list")
    public ModelAndView listBook(@PageableDefault(size = 3) Pageable pageable){
        Page<Book> books = bookService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/book/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveBook(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/book/create");
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("message", "New book created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable int id){
        Book book = bookService.findById(id);
        if(book != null) {
            ModelAndView modelAndView = new ModelAndView("/book/edit");
            modelAndView.addObject("book", book);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateBook(@ModelAttribute("book") Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/book/edit");
        modelAndView.addObject("book", book);
        modelAndView.addObject("message", "Book updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id){
        Book book = bookService.findById(id);
        if(book != null) {
            ModelAndView modelAndView = new ModelAndView("/book/delete");
            modelAndView.addObject("book", book);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("book") Book book){
        bookService.remove(book.getId());
        return "redirect:list";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewBookDetail(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/book/view");
        modelAndView.addObject("book", bookService.findById(id));
        Author author = bookService.findById(id).getAuthor();
        modelAndView.addObject("author",author.getName());
        return modelAndView;
    }
}
