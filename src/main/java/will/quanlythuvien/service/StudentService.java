package will.quanlythuvien.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import will.quanlythuvien.model.Book;
import will.quanlythuvien.model.Student;

public interface StudentService {
    Page<Student> findAll(Pageable pageable);

    Student findById(int id);

    void save(Student student);

    void remove(int id);

    Page<Student> findAllByNameContaining(String name, Pageable pageable);

    Page<Student> findAllByBooks(Book book, Pageable pageable);
}
