package tw.noah.spring.boot.vue.example.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tw.noah.spring.boot.vue.example.service.CacheDemoService;

@Service
@Log4j2
public class CacheDemoServiceImpl implements CacheDemoService {

  @Cacheable(value="add")
  @Override
  public long add(int x, int y) {
    log.info(x + "+" + y + "=" + (x+y));
    return x+y;
  }

  @Cacheable(value="sub")
  @Override
  public int subtraction(int x, int y) {
    log.info(x + "-" + y + "=" + (x-y));
    return x-y;
  }
}
