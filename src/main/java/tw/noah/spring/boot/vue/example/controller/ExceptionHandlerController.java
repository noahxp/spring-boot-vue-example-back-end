package tw.noah.spring.boot.vue.example.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tw.noah.spring.boot.vue.example.exception.DataNotFoundException;
import tw.noah.spring.boot.vue.example.model.JsonResponse;
import tw.noah.spring.boot.vue.example.model.JsonStatus;


/**
 * global error handler
 *
 */
@RestController
@Log4j2
@Scope("prototype")
public class ExceptionHandlerController {

  @RestControllerAdvice
  class GeneralErrorException {

    // if environment eq "dev" or "staging" , get detail exception.
    @Value("${debug.mode}")
    boolean debug104Mode = false;

    /**
     * 使用一般錯誤物件的處理方式
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {ServletRequestBindingException.class, MethodArgumentTypeMismatchException.class,IllegalArgumentException.class, IllegalStateException.class, HttpMessageNotReadableException.class })
    public final ResponseEntity<JsonResponse> handleBadRequest(Exception ex) {
      log.error(ex,ex);
      JsonResponse jsonResponse = getJsonModel(ex);
      return new ResponseEntity<>(jsonResponse ,HttpStatus.BAD_REQUEST);
    }

    /**
     * 使用一般錯誤物件的處理方式
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<JsonResponse> handleInternalServerError(Exception ex) {
      log.error(ex,ex);
      JsonResponse jsonResponse = getJsonModel(ex);
      return new ResponseEntity<>(jsonResponse ,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * 使用自定化錯誤物件的處理方式，http status 會從自定義的 annotation 裡捉的
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {DataNotFoundException.class})
    public final ResponseEntity<JsonResponse> handleDataNotFoundException(DataNotFoundException ex) {
      log.warn(ex, ex);
      ResponseStatus responseStatus = DataNotFoundException.class.getAnnotation(ResponseStatus.class);
      HttpStatus httpStatus = responseStatus.value();
      return new ResponseEntity<>(new JsonResponse(ex.getMessage(), JsonStatus.Failure,null) ,httpStatus);
    }


    private JsonResponse getJsonModel(Throwable th){
      if (debug104Mode) {
        return new JsonResponse(null, JsonStatus.Error, ExceptionUtils.getStackTrace(th));
      } else {
        return new JsonResponse(null, JsonStatus.Error, JsonStatus.Error.getDesc());
      }
    }


  }


  // default - 404 not found.
  @RestController
  class NotFoundException implements ErrorController {
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public JsonResponse error() {
      return new JsonResponse("Api 404 Not Found!", JsonStatus.Error ,JsonStatus.Error.getDesc());
    }

    @Override
    public String getErrorPath() {
      return PATH;
    }
  }
}
