package it.coderunner.spring.data;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("it.coderunner")
public class App {
	
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "org.h2.Driver";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:h2:~/wwsis";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "test";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "test";
    
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.H2Dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "it.coderunner.spring.data.*";
	private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "update";
    
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	/**
	 * Bootstraps an in-memory HSQL database.
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
         
        return dataSource;
	}

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactoryBean.setDataSource(dataSource());
            entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
            entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
            entityManagerFactoryBean.setJpaProperties(hibProperties());
             
            return entityManagerFactoryBean;
    }
     
    private Properties hibProperties() {
            Properties properties = new Properties();
            properties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
            properties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
            properties.put("hibernate.hbm2ddl.auto", PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO);
            return properties;        
    }
     
    @Bean
    public JpaTransactionManager transactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
            return transactionManager;
    }
	
}
