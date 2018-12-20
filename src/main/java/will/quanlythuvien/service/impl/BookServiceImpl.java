package will.quanlythuvien.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import will.quanlythuvien.model.Author;
import will.quanlythuvien.model.Book;
import will.quanlythuvien.repository.BookRepository;
import will.quanlythuvien.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void remove(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findAllByAuthor(Author author, Pageable pageable) {
        return bookRepository.findAllByAuthor(author, pageable);
    }

    @Override
    public Page<Book> findAllByNameContaining (String name, Pageable pageable) {
        return bookRepository.findAllByNameContaining(name, pageable);
    }
}
