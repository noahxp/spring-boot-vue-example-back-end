package tw.noah.spring.boot.vue.example.config;

import java.util.Properties;
import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.StringUtils;

/**
 * reference document : https://github.com/spring-projects/spring-data-examples/tree/master/jpa/multiple-datasources
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "logsFactoryBean" , transactionManagerRef = "logsTx",basePackages = {"tw.noah.spring.boot.vue.example.dao.logs"})
@Log4j2
public class LogsDataSourceConfig {

  @Autowired
  private Environment env;

  // 打包後，用 tomcat 起 Application 才能用 JNDI ，default Spring Application 只能用 spring data
  // 預設模式連結，https://blog.csdn.net/zhangshufei8001/article/details/53333501
  @Bean(name = "logsDatasource")
  @ConfigurationProperties(prefix = "logs.datasource")
  public DataSource dataSource() {

    DataSource dataSource = null;

    String jndiName = env.getProperty("logs.datasource.jndi-name", String.class);
    if (! StringUtils.isEmpty(jndiName)){ // jndi mode
      dataSource = new JndiDataSourceLookup().getDataSource(jndiName);
    }else{  // spring boot default mode
      dataSource = DataSourceBuilder.create().build();
    }

    log.info("datasource=" + dataSource);

    return dataSource;
  }


  @Bean(name = "logsFactoryBean")
  public LocalContainerEntityManagerFactoryBean factoryBean(@Qualifier("logsDatasource") DataSource dataSource) {

    log.info("env.getProperty(\"logs.datasource.properties.hibernate.show-sql\"=" + env.getProperty("logs.datasource.properties.hibernate.show-sql"));

    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setGenerateDdl(false);
    jpaVendorAdapter.setShowSql(env.getProperty("logs.datasource.properties.hibernate.show-sql", Boolean.class));
    jpaVendorAdapter.setGenerateDdl(env.getProperty("logs.datasource.properties.hibernate.ddl-auto", Boolean.class));

    log.info("env.getProperty(\"logs.datasource.properties.hibernate.dialect\")=" + env.getProperty("logs.datasource.properties.hibernate.dialect"));

    Properties props = new Properties();
    props.put("hibernate.dialect", env.getProperty("logs.datasource.properties.hibernate.dialect",String.class));
    props.put("hibernate.format_sql", env.getProperty("logs.datasource.properties.hibernate.format_sql", Boolean.class));
//    Properties props = new Properties();
//    log.info(jpaProperties.getProperties().size());
//    jpaProperties.getProperties().forEach((k,v)->{
//      log.info(k + "=" + v);
//      props.put(k, v);
//    });


    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setPackagesToScan("tw.noah.spring.boot.vue.example.entity");
    factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    factoryBean.setJpaProperties(props);

    log.info("factoryBean="+ factoryBean);

    return factoryBean;
  }


  @Bean(name = "logsTx")
  public JpaTransactionManager transactionManager(@Qualifier("logsFactoryBean") LocalContainerEntityManagerFactoryBean factoryBean) {
    return new JpaTransactionManager(factoryBean.getObject());
  }

}
