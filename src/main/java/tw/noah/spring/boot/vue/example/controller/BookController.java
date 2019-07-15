package tw.noah.spring.boot.vue.example.controller;

import java.util.Date;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.spring.boot.vue.example.entity.Book;
import tw.noah.spring.boot.vue.example.model.JsonModel;
import tw.noah.spring.boot.vue.example.model.JsonMsg;
import tw.noah.spring.boot.vue.example.service.BookService;

@RestController
@Log4j2
@RequestMapping("/apis/books")
@Scope("prototype")
public class BookController {

  private int cnt = 0;

  @Autowired
  private BookService bookService;

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel getAllBooks() {
    return new JsonModel(bookService.findAllBooks(), JsonMsg.Success);
  }

  @GetMapping(value = "/page/{current-page}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel getAllBooks(@PathVariable(name = "current-page") int currentPage) {

    Assert.isTrue(currentPage >= 0, "current-page must grant 0");

    return new JsonModel(bookService.findAllBooks(currentPage), JsonMsg.Success);

  }

  @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel editoBook(@RequestBody Book book) {
    log.info("edit:" + book);

    Assert.notNull(book, "book can't been null");
    Assert.notNull(book.getBookId(), "book id can't been null");
    Assert.notNull(book.getBookName(), "book name can't been null");
    Assert.notNull(book.getIsdn(), "book isdn can't been null");

    bookService.saveBook(book);

    return new JsonModel(null, JsonMsg.Success);
  }


  @GetMapping(value = "/int", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public int getBooksCount() {
    return 999 + cnt;
  }

  @GetMapping(value = "/string", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String getBooksString() {
    return "Hi,Books";
  }


  @GetMapping(value = "/date", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Date getBooksDate() {
    return new Date();
  }


  @GetMapping(value = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Object getBooksError() {
    if (true) {
      int c = 0 / 0;
    }
    return new Date();
  }
}
