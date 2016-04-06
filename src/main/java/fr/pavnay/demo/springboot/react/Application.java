package fr.pavnay.demo.springboot.react;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

//@EnableOAuth2Sso
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public ServletContextTemplateResolver templateResolver() {
	    ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
	    resolver.setPrefix("/templates/");
	    resolver.setSuffix(".html");
	    resolver.setTemplateMode("LEGACYHTML5");
	    resolver.setCacheable(false);
	    return resolver;
	}
}
