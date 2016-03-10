package com.opengroupe.cloud.saas.config;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@org.springframework.context.annotation.Configuration
public class WebMvcConfiguration {

//    @Bean
//    public TemplateResolver templateResolver() {
//        TemplateResolver templateResolver = new ThymeleafViewResolver().;
//        templateResolver.setPrefix("/resources/templates");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//        templateResolver.setCacheable(false);
//        return templateResolver;
//    }

//    @Bean
//    public UrlTemplateResolver urlTemplateResolver() {
//        return new UrlTemplateResolver();
//    }
    
//	@Bean
//	public SpringTemplateEngine templateEngine() {
//		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
////		templateEngine.addTemplateResolver(templateResolver());
//		templateEngine.addTemplateResolver(urlTemplateResolver());
//		templateEngine.addDialect(new SpringSecurityDialect());
//		return templateEngine;
//	}
}
