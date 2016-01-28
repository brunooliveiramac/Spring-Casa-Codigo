package br.com.caelum.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMvc extends AbstractAnnotationConfigDispatcherServletInitializer{
 
    @Override
    protected Class<?>[] getRootConfigClasses() {//sobe a configuração assim q o sistema estiver subindo
        return new Class[] {SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class};
    }
 
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
    
    @Override
    protected Filter[] getServletFilters() {
    	CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    	characterEncodingFilter.setEncoding("UTF-8");
    	return new Filter[] {characterEncodingFilter};
    }
    
    //Upload
    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }
    
    //pega profile dev na subida do servidor
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	super.onStartup(servletContext);
    	servletContext.addListener(RequestContextListener.class); //ouve contextos da aplicação
    	servletContext.setInitParameter("spring.profiles.active", "dev");
    }

}