package tw.noah.spring.boot.vue.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.spring.boot.vue.example.service.CacheDemoService;

@RestController
@RequestMapping("/apis/labs/")
public class LabsController {

  @Autowired
  private CacheDemoService cacheDemoService;

  private int cnt = 0;

  @GetMapping(value = "/int")
  public int getInt() {
    return cnt++;
  }

  @GetMapping(value = "/string")
  public String getString() {
    return "Hi,Books";
  }

  @GetMapping(value = "/bool")
  public boolean getBoolean() {
    return true;
  }

  @GetMapping(value = "/date")
  public Date getDate() {
    return new Date();
  }

  @GetMapping(value = "/date/str")
  public String getDateStr(){
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }


  @GetMapping(value = "/error")
  public Object getError() {
    if (true) {
      int c = 0 / 0;
    }
    return new Date();
  }

  @GetMapping(value = "/number/add/{x}/{y}")
  public long cacheAdd(@PathVariable(name="x") int x,@PathVariable(name="y") int y){
    return cacheDemoService.add(x,y);
  }

  @GetMapping(value = "/number/sub/{x}/{y}")
  public long cacheSub(@PathVariable(name="x") int x,@PathVariable(name="y") int y){
    return cacheDemoService.subtraction(x,y);
  }

  @GetMapping(value = "/jvm/opts")
  public String getJvmOpts(){
    return System.getProperty("user.timezone");
  }
}
