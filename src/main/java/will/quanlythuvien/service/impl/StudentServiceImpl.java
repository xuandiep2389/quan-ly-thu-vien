package will.quanlythuvien.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import will.quanlythuvien.model.Book;
import will.quanlythuvien.model.Student;
import will.quanlythuvien.repository.StudentRepository;
import will.quanlythuvien.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findAllByBook(Book book, Pageable pageable) {
        return studentRepository.findAllByBook(book, pageable);
    }

    @Override
    public Page<Student> findAllByNameContaining(String name, Pageable pageable) {
        return findAllByNameContaining(name, pageable);
    }
}
