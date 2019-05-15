package tw.noah.spring.boot.vue.example.controller;

import javax.persistence.criteria.Order;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.spring.boot.vue.example.entity.Book;
import tw.noah.spring.boot.vue.example.model.JsonModel;
import tw.noah.spring.boot.vue.example.model.JsonMsg;
import tw.noah.spring.boot.vue.example.service.BookService;

@RestController
@Log4j2
@RequestMapping("/apis/books/")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel getAllBooks() {
    return new JsonModel(bookService.findAllBooks(), JsonMsg.Success);
  }

  @GetMapping(value = "/page/{current-page}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel getAllBooks(@PathVariable(name = "current-page") @NonNull int currentPage) {

    Assert.isTrue(currentPage >= 0, "current-page must grant 0");

    return new JsonModel(bookService.findAllBooks(currentPage), JsonMsg.Success);

//    Page<Book> books = bookService.findAllBooks(currentPage);
//    return new JsonModel(books, JsonMsg.Success);

  }

}
