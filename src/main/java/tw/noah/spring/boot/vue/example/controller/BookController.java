package tw.noah.spring.boot.vue.example.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.spring.boot.vue.example.model.JsonModel;
import tw.noah.spring.boot.vue.example.model.JsonMsg;
import tw.noah.spring.boot.vue.example.service.BookService;

@RestController
@Log4j2
@RequestMapping("/books/")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel getAllBooks() {
    return new JsonModel(bookService.findAllBooks(), JsonMsg.Success);
  }

}
