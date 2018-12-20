package will.quanlythuvien.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import will.quanlythuvien.model.Author;
import will.quanlythuvien.service.AuthorService;

import java.text.ParseException;
import java.util.Locale;

@Component
public class AuthorFormatter implements Formatter<Author> {

    private AuthorService authorService;

    @Autowired
    public AuthorFormatter(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Author parse(String text, Locale locale) throws ParseException {
        return authorService.findById(Integer.parseInt(text));
    }

    @Override
    public String print(Author object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}