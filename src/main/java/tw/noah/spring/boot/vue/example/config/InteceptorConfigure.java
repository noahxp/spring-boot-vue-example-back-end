package tw.noah.spring.boot.vue.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tw.noah.spring.boot.vue.example.interceptor.SampleInteceptor;

@Configuration
public class InteceptorConfigure implements WebMvcConfigurer {

  @Autowired
  private SampleInteceptor sampleInteceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(sampleInteceptor).addPathPatterns("/**");
  }

}
