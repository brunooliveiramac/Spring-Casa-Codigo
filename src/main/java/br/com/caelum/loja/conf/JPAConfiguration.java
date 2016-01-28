package br.com.caelum.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/*
 * JPA cuida da Transação
 */
@EnableTransactionManagement
public class JPAConfiguration {
   
	@Bean //criado fabrica de entity manager para ser injetada nos DAO'S
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
      
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();


		factoryBean.setDataSource(dataSource); //n pode  chamar metodo direto, necessario parametro
		
        factoryBean.setPackagesToScan("br.com.caelum.loja.models");


        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter );
        factoryBean.setJpaProperties(addctionalProperties());





        return factoryBean;

    }


	private Properties addctionalProperties() {
		Properties props = new Properties();
        props.setProperty("hibernate.dialect" , "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
		return props;
	}


	@Bean
	@Profile("dev")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}
    
    
    /*
     * Associar JPA com Spring. Spring cria as transações.
     */
    @Bean
    JpaTransactionManager jpaTransactionManager(EntityManagerFactory emf){
    	return new JpaTransactionManager(emf);
    }
}