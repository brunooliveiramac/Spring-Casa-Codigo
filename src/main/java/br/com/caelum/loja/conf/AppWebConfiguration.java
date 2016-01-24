package br.com.caelum.loja.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.caelum.loja.controller.HomeController;
import br.com.caelum.loja.controller.ProdutosController;
import br.com.caelum.loja.dao.ProdutoDAO;
import br.com.caelum.loja.infra.FileSaver;
import br.com.caelum.loja.models.CarrinhoCompras;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProdutosController.class, ProdutoDAO.class, 
		FileSaver.class, CarrinhoCompras.class})
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
   
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
				
				@Bean
				public CacheManager cacheManager(){ //Gerenciador de cache
				 	
					CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
					.maximumSize(100)
					.expireAfterAccess(5, TimeUnit.MINUTES);
					GuavaCacheManager guavaCacheManager = new GuavaCacheManager();
					
					guavaCacheManager.setCacheBuilder(builder);
					
					return guavaCacheManager;
				}
				
				@Bean //json ou jsp
				public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager){
					List<ViewResolver> viewResolver = new ArrayList<>();
					
					viewResolver.add(internalResourceViewResolver());
					viewResolver.add(new JsonViewResolver());
					
					ContentNegotiatingViewResolver contentNegotiatingViewResolver = new	ContentNegotiatingViewResolver();
					contentNegotiatingViewResolver.setViewResolvers(viewResolver);
					contentNegotiatingViewResolver.setContentNegotiationManager(manager);
					
					return contentNegotiatingViewResolver;
				}
			
				@Override
				public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
					//Servlets responsaveis por pegar resources -->tomcat
					configurer.enable();
				}
}
