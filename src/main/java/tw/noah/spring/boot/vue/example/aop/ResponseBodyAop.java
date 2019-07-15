package tw.noah.spring.boot.vue.example.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import tw.noah.spring.boot.vue.example.model.JsonModel;
import tw.noah.spring.boot.vue.example.model.JsonMsg;

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

    JsonModel newBody = new JsonModel(body, JsonMsg.Success);

    if (body.getClass() == String.class) {
      try {
        return new ObjectMapper().writeValueAsString(newBody);
      } catch (JsonProcessingException e) {
        log.error(e, e);
      }
    }

    return newBody;
  }
}
