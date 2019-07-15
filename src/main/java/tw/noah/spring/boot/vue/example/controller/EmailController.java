package tw.noah.spring.boot.vue.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.spring.boot.vue.example.model.JsonModel;
import tw.noah.spring.boot.vue.example.model.JsonMsg;
import tw.noah.spring.boot.vue.example.service.EmailExample;

@RestController
@RequestMapping("/apis/email")
public class EmailController {

  @Autowired
  private EmailExample emailExample;


  @GetMapping(value = "/html" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JsonModel htmlMail(){

    emailExample.demoSpringMail();

    return new JsonModel("ok", JsonMsg.Success);
  }
}
