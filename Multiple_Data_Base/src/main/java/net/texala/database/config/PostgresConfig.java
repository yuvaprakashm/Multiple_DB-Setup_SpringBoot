package net.texala.database.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "net.texala.database.repository.postgres",
    entityManagerFactoryRef = "postgresEntityManager",
    transactionManagerRef = "postgresTransactionManager"
)
public class PostgresConfig {
	
	@Bean(name = "postgresDataSource")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/database")
                .username("postgres")
                .password("root")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
	
    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManager(DataSource postgresDataSource) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(postgresDataSource);
        entityManager.setPackagesToScan("net.texala.database.model.postgres");
        entityManager.setPersistenceUnitName("postgres");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManager") EntityManagerFactory postgresEntityManager) {
        return new JpaTransactionManager(postgresEntityManager);
    }

}