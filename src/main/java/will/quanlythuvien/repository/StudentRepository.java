package will.quanlythuvien.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import will.quanlythuvien.model.Book;
import will.quanlythuvien.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {
    Page<Student> findAllByNameContaining(String name, Pageable pageable);

    Page<Student> findAllByBooks(Book book, Pageable pageable);

}
