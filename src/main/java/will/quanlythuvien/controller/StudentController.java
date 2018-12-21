package will.quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import will.quanlythuvien.model.Book;
import will.quanlythuvien.model.Student;
import will.quanlythuvien.service.BookService;
import will.quanlythuvien.service.StudentService;

import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private BookService bookService;

    @ModelAttribute("books")
    public Page<Book> books(Pageable pageable){
        return bookService.findAll(pageable);
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveStudent(@ModelAttribute("student") Student student){
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Student());
        modelAndView.addObject("message", "New student created successfully");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView listStudent(@PageableDefault(size = 3) Pageable pageable){
        Page<Student> students = studentService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/student/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable int id){
        Student student = studentService.findById(id);
        if(student != null) {
            ModelAndView modelAndView = new ModelAndView("/student/edit");
            modelAndView.addObject("student", student);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateStudent(@ModelAttribute("student") Student student){
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("/student/edit");
        modelAndView.addObject("student", student);
        modelAndView.addObject("message", "Student updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id){
        Student student = studentService.findById(id);
        if(student != null) {
            ModelAndView modelAndView = new ModelAndView("/student/delete");
            modelAndView.addObject("student", student);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteStudent(@ModelAttribute("student") Student student){
        studentService.remove(student.getId());
        return "redirect:list";
    }

    @GetMapping("/search-by-name")
    public ModelAndView searchByName(@RequestParam("searchByName") Optional<String> searchByName, @PageableDefault(size = 3) Pageable pageable) {
        Page<Student> students;
        ModelAndView modelAndView = new ModelAndView("/student/search/searchByName");
        if (searchByName.isPresent()) {
            students = studentService.findAllByNameContaining(searchByName.get(), pageable);
        } else {
            students = Page.empty();
            modelAndView.addObject("nosuchname","There is no such name");
        }

        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewStudent(@PathVariable("id") int id, Pageable pageable){
        Student student = studentService.findById(id);
        if(student == null){
            return new ModelAndView("/error.404");
        }

        Page<Book> books = bookService.findAllByStudents(student, pageable);
        ModelAndView modelAndView = new ModelAndView("/student/view");
        modelAndView.addObject("student", student);
        modelAndView.addObject("books", books);
        return modelAndView;
    }
}
