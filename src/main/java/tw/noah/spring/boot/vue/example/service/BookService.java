package tw.noah.spring.boot.vue.example.service;

import java.util.List;
import tw.noah.spring.boot.vue.example.entity.Book;

public interface BookService {

  List<Book> findAllBooks();
}
