package will.quanlythuvien.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import will.quanlythuvien.model.Author;
import will.quanlythuvien.model.Book;
import will.quanlythuvien.model.Student;

public interface BookService {
    Page<Book> findAll(Pageable pageable);

    Book findById(int id);

    void save(Book book);

    void remove(int id);

    Page<Book> findAllByAuthor(Author author, Pageable pageable);

    Page<Book> findAllByNameContaining(String name, Pageable pageable);

    Page<Book> findAllByStudents(Student student, Pageable pageable);
}
