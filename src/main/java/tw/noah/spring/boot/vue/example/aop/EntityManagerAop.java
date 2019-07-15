package tw.noah.spring.boot.vue.example.aop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Solve JpaRepository without detach method, so it's process with aop
 */
@Component
@Aspect
@Log4j2
public class EntityManagerAop {

  @PersistenceContext(unitName = "booksFactoryBean")
  private EntityManager entityManagerBooks;

  @PersistenceContext(unitName = "logsFactoryBean")
  private EntityManager entityManagerLogs;


  @After("execution(* tw.noah.spring.boot.vue.example.service.impl.*.*(..))")
  public void after(JoinPoint joinPoint) {
    if (log.getLevel() == Level.DEBUG) {
      log.debug("entityManagerBooks.clear() on AOP ");
      log.debug("entityManagerLogs.clear() on AOP ");
    }

    entityManagerBooks.clear();
    entityManagerLogs.clear();
  }


}
