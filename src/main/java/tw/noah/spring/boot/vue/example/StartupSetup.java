package tw.noah.spring.boot.vue.example;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StartupSetup implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    log.info("Setup the dns cache ttl : 60 seconds" );
    java.security.Security.setProperty("networkaddress.cache.ttl", "60");

  }
}
