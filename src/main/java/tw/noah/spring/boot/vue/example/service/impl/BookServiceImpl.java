package tw.noah.spring.boot.vue.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.noah.spring.boot.vue.example.dao.books.BookDao;
import tw.noah.spring.boot.vue.example.entity.Book;
import tw.noah.spring.boot.vue.example.service.BookService;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookDao bookDAO;


  @Override
  public List<Book> findAllBooks() {
    return bookDAO.findAll();
  }
}
