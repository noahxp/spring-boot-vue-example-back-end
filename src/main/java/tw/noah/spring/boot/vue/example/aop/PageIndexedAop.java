package tw.noah.spring.boot.vue.example.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class PageIndexedAop {

  @Pointcut("execution(* tw.noah.spring.boot.vue.example.dao.*.*.*(..))")
  public void pointcut() {
  }

  /**
   * 因 JPA 的 Pageable 是 zero base indexed ，但前端是 one base indexed , 故由 aop 作差異處理
   * @param pjp
   * @return
   * @throws Throwable
   */
  @Around("pointcut()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable{

    boolean hasChangePageIndex = false;

    Object[] args = pjp.getArgs();
    int idx = 0;
    for (Object arg:args) {

      if (log.isDebugEnabled())
        log.debug("arg:" + arg);
      if (arg instanceof PageRequest){
        if (log.isDebugEnabled())
          log.debug("Page indexed -1 ");
        PageRequest pr = (PageRequest) arg;
        PageRequest newPr = PageRequest.of(pr.getPageNumber()-1, pr.getPageSize() ,pr.getSort());
        args[idx] = newPr;
        hasChangePageIndex = true;
      }
      idx++;
    }

    // 執行原程式
    Object obj = pjp.proceed(args);

    // 回傳 page 物件時，需把 -1 加回去給前端
    if (obj instanceof PageImpl && hasChangePageIndex){
      if (log.isDebugEnabled())
        log.debug("Page indexed +1");

      PageImpl page = (PageImpl) obj;

      Class c = ((PageRequest)page.getPageable()).getClass();
      Field[] f = c.getDeclaredFields();
      Arrays.stream(f).forEach(log::info);

      Field field = ((PageRequest)page.getPageable()).getClass().getSuperclass().getDeclaredField("page");
      Field modifiersField = Field.class.getDeclaredField("modifiers");
      modifiersField.setAccessible(true);
      modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

      field.setAccessible(true);
      field.setInt((page.getPageable()),page.getNumber()+1);


    }


    return obj;
  }


}
