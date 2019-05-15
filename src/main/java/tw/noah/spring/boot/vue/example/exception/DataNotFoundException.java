package tw.noah.spring.boot.vue.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * customize data can't find exception，
 * modify by noah : HttpStatus.OK for old client scene ,
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
//@ResponseStatus(HttpStatus.OK)
public class DataNotFoundException extends RuntimeException {

}
