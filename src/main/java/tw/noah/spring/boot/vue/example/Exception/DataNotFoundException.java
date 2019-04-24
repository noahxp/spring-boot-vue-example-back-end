package tw.noah.spring.boot.vue.example.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 客製資料找不到錯誤，
 * modify by noah : 因應 HttpURLConnection 等傳統 consumer 不好識別此種異常，資料找不到調整為 HttpStatus.OK
 */
//@ResponseStatus(HttpStatus.NOT_FOUND)
@ResponseStatus(HttpStatus.OK)
public class DataNotFoundException extends RuntimeException {
  public DataNotFoundException(String msg){
    super(msg);
  }

  public DataNotFoundException(String msg,Throwable cause){
    super(msg,cause);
  }
}
