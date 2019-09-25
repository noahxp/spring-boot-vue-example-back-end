package tw.noah.spring.boot.vue.example.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Log4j2
@EnableCaching
//@EntityScan("tw.noah.spring.boot.vue.example.entity")  // for embedded single jpa
public class GlobalConfigure {

  /**
   * Global Transaction
   * @param booksTx
   * @return
   */
  @Bean(name="globalTx")
  public PlatformTransactionManager transactionManager(@Qualifier("booksTx") JpaTransactionManager booksTx,@Qualifier("logsTx") JpaTransactionManager logsTx ){
    return new ChainedTransactionManager(booksTx,logsTx);  // put all tx-manager parameters
  }

  // no-effect https://stackoverflow.com/questions/52120070/spring-data-web-pageable-one-indexed-parameters-true-does-not-work
//  @Bean
//  PageableHandlerMethodArgumentResolverCustomizer pageableResolverCustomizer() {
//    return pageableResolver -> pageableResolver.setOneIndexedParameters(true);
//  }

}
