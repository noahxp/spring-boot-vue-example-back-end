package tw.noah.spring.boot.vue.example.controller;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.spring.boot.vue.example.entity.Book;
import tw.noah.spring.boot.vue.example.service.BookService;

@RestController
@Log4j2
@RequestMapping("/apis/books")
@Scope("prototype")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<Book> getAllBooks() {
    return bookService.findAllBooks();
  }

  @GetMapping(value = "/page/{current-page}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Page<Book> getAllBooks(@PathVariable(name = "current-page") int currentPage) {

    Assert.isTrue(currentPage >= 0, "current-page must grant 0");

    return bookService.findAllBooks(currentPage);

  }

  @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void editoBook(@RequestBody Book book) {
    log.info("edit:" + book);

    Assert.notNull(book, "book can't been null");
    Assert.notNull(book.getBookId(), "book id can't been null");
    Assert.notNull(book.getBookName(), "book name can't been null");
    Assert.notNull(book.getIsdn(), "book isdn can't been null");

    bookService.saveBook(book);

  }

}
