package tw.noah.spring.boot.vue.example.aop;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import tw.noah.spring.boot.vue.example.model.JsonModel;
import tw.noah.spring.boot.vue.example.model.SimplePage;

@Component
@Aspect
@Log4j2
public class SimplePageAop {

  @Pointcut("execution(* tw.noah.spring.boot.vue.example.controller.*.*(..))")
  public void pointcut() {
  }

  //
//  @Before("pointcut()")
//  public void before(JoinPoint joinPoint){
//    // an aop before example
//    CodeSignature cs = (CodeSignature) joinPoint.getSignature();
//    String[] ps = cs.getParameterNames();
//  }
//
//  @After("pointcut()")
//  public void after(JoinPoint joinPoint) {
//    // an aop after example
//    if (log.getLevel() == Level.TRACE) {
//      log.trace("JoinPoint.after..");
//    }
//  }
//
//
//
  @AfterReturning(value = "pointcut()", returning = "ret")
  public Object afterReturning(JoinPoint joinPoint, Object ret) {

    if (ret instanceof JsonModel) {
      JsonModel m = (JsonModel) ret;
      Object obj = m.getResult();
      if (obj instanceof Page) {
        Page page = (Page) obj;

        if (log.getLevel() == Level.TRACE) {
          log.trace(page.getTotalElements());
          log.trace(page.getTotalPages());
          log.trace(page.getNumber());
          log.trace(page.getSize());
        }

        SimplePage sPage = new SimplePage();
        sPage.setContent(page.getContent());

        sPage.setPageSize(page.getSize());
        sPage.setCurrentPage(page.getNumber());
        sPage.setTotalPages(page.getTotalPages());
        sPage.setTotalRows(page.getTotalElements());

        m.setResult(sPage); // rewrite Page to SimplePage

      }

    }

    if (log.isDebugEnabled()) {
      log.debug(ret);
    }

    return ret;
  }

}
