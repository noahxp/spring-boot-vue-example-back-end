package tw.noah.spring.boot.vue.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.spring.boot.vue.example.model.JsonResponse;
import tw.noah.spring.boot.vue.example.model.JsonStatus;
import tw.noah.spring.boot.vue.example.service.EmailExample;

@RestController
@RequestMapping("/apis/email")
public class EmailController {

  @Autowired
  private EmailExample emailExample;


  @GetMapping(value = "/html" )
  public JsonResponse htmlMail(){

    emailExample.demoSpringMail();

    return new JsonResponse("ok", JsonStatus.Success ,null);
  }
}
