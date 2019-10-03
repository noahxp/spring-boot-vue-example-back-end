package tw.noah.spring.boot.vue.example;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class StartupNotify implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    NumberFormat format = new DecimalFormat("###,###,###");
    log.info("Max memory=" + format.format(Runtime.getRuntime().maxMemory()));
    log.info("now is :" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    log.info("Application startup success...");
  }
}
