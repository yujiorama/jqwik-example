package jqwik.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.jdbc.DataSourceInitializationMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@EnableJdbcRepositories
public class JdbcConfig extends AbstractJdbcConfiguration {

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcOperations jdbcOperations) {

        return new NamedParameterJdbcTemplate(jdbcOperations);
    }

    @Bean
    DataSourceInitializer dataSourceInitializer(DataSource dataSource) {

        var populator = new ResourceDatabasePopulator();
        populator.addScripts( //
                new ClassPathResource("schema.sql"), //
                new ClassPathResource("data.sql") //
        );

        var initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
}
