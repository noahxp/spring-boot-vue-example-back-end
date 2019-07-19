package tw.noah.spring.boot.vue.example.model;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@ApiModel(value= "Response Format" ,description = "回傳固定結構")
public class JsonResponse {
  /**
   * result set
   */
  //@ApiModelProperty(value="執行結果集",required = false)
  private Object result;

  /**
   * JsonStatus message
   */
  //@ApiModelProperty(value="執行結果訊息",required = true)
  private JsonStatus status;

  /**
   * debug message , or error stack.
   */
  private String message;

}
