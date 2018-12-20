package will.quanlythuvien.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import will.quanlythuvien.model.Author;

public interface AuthorService {

    Page<Author> findAll(Pageable pageable);

    Author findById(int id);

    void save(Author author);

    void remove(int id);
}
