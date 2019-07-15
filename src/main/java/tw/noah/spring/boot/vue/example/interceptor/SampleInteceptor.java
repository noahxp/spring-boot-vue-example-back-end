package tw.noah.spring.boot.vue.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Log4j2
public class SampleInteceptor implements HandlerInterceptor {

  /**
   * execute time : before execute handler
   * @param request
   * @param response
   * @param handler
   * @return
   * @throws Exception
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.info("HandlerInterceptor.preHandle:[" + request.getRequestURL()  + "][" +  request.getMethod() + "]");
    return true;
  }

  /**
   * execture time : after response to frontend , 不能修改response body , 因為response body已被生成
   * @param request
   * @param response
   * @param handler caller
   * @param modelAndView
   * @throws Exception
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.info("HandlerInterceptor.postHandle:[" + request.getRequestURL()  + "][" +  request.getMethod() + "]");
  }


  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    log.info("HandlerInterceptor.afterCompletion");
  }



}
