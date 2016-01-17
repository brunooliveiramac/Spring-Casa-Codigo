package br.com.caelum.loja.conf;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.caelum.loja.controller.HomeController;
import br.com.caelum.loja.controller.ProdutosController;
import br.com.caelum.loja.dao.ProdutoDAO;
import br.com.caelum.loja.infra.FileSaver;
import br.com.caelum.loja.models.CarrinhoCompras;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProdutosController.class, ProdutoDAO.class, 
		FileSaver.class, CarrinhoCompras.class})
public class AppWebConfiguration {
   
				//Gerenciada pelo Spring
				@Bean
			    public InternalResourceViewResolver internalResourceViewResolver() {
			        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			        resolver.setPrefix("/WEB-INF/views/");
			        resolver.setSuffix(".jsp");
			        //Todos os atributos do spring diponíveis no JSP
			        resolver.setExposedContextBeanNames("carrinhoCompras");
			        return resolver;
			    } 
				
				
				@Bean
				public MessageSource messageSource(){	
					ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource 
					= new ReloadableResourceBundleMessageSource();
					reloadableResourceBundleMessageSource.setBasename("/WEB-INF/message");
					reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
					reloadableResourceBundleMessageSource.setCacheSeconds(1);
					
					return reloadableResourceBundleMessageSource;
				}
				
				@Bean
		        public FormattingConversionService mvcConversionService() {
		            DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		            DateFormatterRegistrar registra = new DateFormatterRegistrar();
		            registra.setFormatter(new DateFormatter("dd/MM/yyyy"));
		            registra.registerFormatters(conversionService);

		            return conversionService;
		        }
			
				
				@Bean
				public MultipartResolver multipartResolver() {
				    return new StandardServletMultipartResolver();
				}
				
				
				@Bean
				public RestTemplate restTemplate(){
					return new RestTemplate();
				}
			
	
}
