package will.quanlythuvien.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String type;

    private int pageNumber;

    private String introduce;

    private int amount;

    public Book(String name, String type, int pageNumber, String introduce, int amount, Author author, Set<Student> students) {
        this.name = name;
        this.type = type;
        this.pageNumber = pageNumber;
        this.introduce = introduce;
        this.amount = amount;
        this.author = author;
        this.students = students;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pageNumber=" + pageNumber +
                ", introduce='" + introduce + '\'' +
                ", amount=" + amount +
                ", author=" + author +
                ", students=" + students +
                '}';
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(mappedBy = "books")
    private Set<Student> students = new HashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }

    public Book(String name, String type, int pageNumber, String introduce, int amount) {
        this.name = name;
        this.type = type;
        this.pageNumber = pageNumber;
        this.introduce = introduce;
        this.amount = amount;
    }

}
