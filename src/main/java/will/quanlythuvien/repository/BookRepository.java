package will.quanlythuvien.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import will.quanlythuvien.model.Author;
import will.quanlythuvien.model.Book;
import will.quanlythuvien.model.Student;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

    Page<Book> findAllByAuthor(Author author, Pageable pageable);

    Page<Book> findAllByNameContaining(String name, Pageable pageable);

    Page<Book> findAllByStudents(Student student, Pageable pageable);
}
