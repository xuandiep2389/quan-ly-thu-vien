package will.quanlythuvien.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import will.quanlythuvien.model.Author;
import will.quanlythuvien.repository.AuthorRepository;
import will.quanlythuvien.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author findById(int id){
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void remove(int id) {
        authorRepository.deleteById(id);
    }
}
