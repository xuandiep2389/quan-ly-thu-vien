package will.quanlythuvien.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import will.quanlythuvien.model.Author;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {
}
