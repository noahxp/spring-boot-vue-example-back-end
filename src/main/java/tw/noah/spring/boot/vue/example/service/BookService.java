package tw.noah.spring.boot.vue.example.service;

import java.util.List;
import org.springframework.data.domain.Page;
import tw.noah.spring.boot.vue.example.entity.Book;

public interface BookService {

  List<Book> findAllBooks();

  Page<Book> findAllBooks(int currentPage);
}

