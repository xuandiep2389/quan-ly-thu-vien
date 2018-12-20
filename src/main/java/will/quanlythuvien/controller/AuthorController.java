package will.quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import will.quanlythuvien.model.Author;
import will.quanlythuvien.service.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/create")
    public ModelAndView showCreateAuthorPage(){
        ModelAndView modelAndView = new ModelAndView("/author/create");
        modelAndView.addObject("author", new Author());
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView listAuthors(@PageableDefault(size = 3) Pageable pageable){
        Page<Author> authors = authorService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/author/list");
        modelAndView.addObject("authors", authors);
        return modelAndView;
    }


    @PostMapping("/create")
    public ModelAndView saveAuthor(@ModelAttribute("author") Author author){
        authorService.save(author);

        ModelAndView modelAndView = new ModelAndView("/author/create");
        modelAndView.addObject("author", new Author());
        modelAndView.addObject("message", "New author created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable int id){
        Author author = authorService.findById(id);
        if(author != null) {
            ModelAndView modelAndView = new ModelAndView("/author/edit");
            modelAndView.addObject("author", author);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateAuthor(@ModelAttribute("author") Author author){
        authorService.save(author);
        ModelAndView modelAndView = new ModelAndView("/author/edit");
        modelAndView.addObject("author", author);
        modelAndView.addObject("message", "Author updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id){
        Author author = authorService.findById(id);
        if(author != null) {
            ModelAndView modelAndView = new ModelAndView("/author/delete");
            modelAndView.addObject("author", author);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteProvince(@ModelAttribute("author") Author author){
        authorService.remove(author.getId());
        return "redirect:list";
    }
}
