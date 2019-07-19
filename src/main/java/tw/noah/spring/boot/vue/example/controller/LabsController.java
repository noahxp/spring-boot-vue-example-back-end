package tw.noah.spring.boot.vue.example.controller;

import java.util.Date;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis/labs/")
public class LabsController {

  private int cnt = 0;

  @GetMapping(value = "/int")
  public int getBooksCount() {
    return cnt++;
  }

  @GetMapping(value = "/string")
  public String getBooksString() {
    return "Hi,Books";
  }

  @GetMapping(value = "/bool")
  public boolean getBooksBoolean() {
    return true;
  }

  @GetMapping(value = "/date")
  public Date getBooksDate() {
    return new Date();
  }


  @GetMapping(value = "/error")
  public Object getBooksError() {
    if (true) {
      int c = 0 / 0;
    }
    return new Date();
  }
}
