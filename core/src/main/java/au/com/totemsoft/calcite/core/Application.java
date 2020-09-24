package au.com.totemsoft.calcite.core;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean("mysqlDatasource")
    @ConfigurationProperties(prefix = "spring.datasource-mysql")
    public DataSource mysqlDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("postgresDatasource")
    @ConfigurationProperties(prefix = "spring.datasource-postgres")
    public DataSource postgresDatasource() {
        return DataSourceBuilder.create().build();
    }

}
