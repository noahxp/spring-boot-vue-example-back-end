package tw.noah.spring.boot.vue.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.noah.spring.boot.vue.example.dao.books.BookDao;
import tw.noah.spring.boot.vue.example.entity.Book;
import tw.noah.spring.boot.vue.example.service.BookService;

@Service
@Transactional("globalTx")

public class BookServiceImpl implements BookService {

  @Autowired
  private BookDao bookDAO;

  @Value("${api.page-size}")
  private int pageSize;

  @Override
  public List<Book> findAllBooks() {
    return bookDAO.findAll();
  }

  @Override
  public Page<Book> findAllBooks(int currentPage) {

    Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Direction.ASC,"bookName"));

    return bookDAO.findAll(pageable);
  }

  @Override
  public void saveBook(Book book){
    bookDAO.save(book);
  }
}
