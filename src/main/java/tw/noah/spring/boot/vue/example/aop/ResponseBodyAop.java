package tw.noah.spring.boot.vue.example.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import tw.noah.spring.boot.vue.example.model.JsonResponse;
import tw.noah.spring.boot.vue.example.model.JsonStatus;

/**
 * rewrite response body aop
 */
@RestControllerAdvice
@Log4j2
public class ResponseBodyAop implements ResponseBodyAdvice {


  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {

    HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();


    // 統一處理 content type for utf-8 json
    resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);


    Object newBody = body;
    if (! (body instanceof JsonResponse)) {
      newBody = new JsonResponse(body, JsonStatus.Success,null);
    }

    if (body.getClass() == String.class) {
      try {
        newBody = new ObjectMapper().writeValueAsString(newBody);
      } catch (JsonProcessingException e) {
        log.error(e, e);
      }
    }

    return newBody;
  }
}
