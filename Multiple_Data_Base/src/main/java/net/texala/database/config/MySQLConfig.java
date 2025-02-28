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
    basePackages = "net.texala.database.repository.mysql",
    entityManagerFactoryRef = "mysqlEntityManager",
    transactionManagerRef = "mysqlTransactionManager"
)
public class MySQLConfig {
	
	@Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/mysqldb")
                .username("root")
                .password("root")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
	
    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManager(DataSource mysqlDataSource) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(mysqlDataSource);
        entityManager.setPackagesToScan("net.texala.database.model.mysql");
        entityManager.setPersistenceUnitName("mysql");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(
            @Qualifier("mysqlEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}