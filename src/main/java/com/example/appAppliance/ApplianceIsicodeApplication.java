package com.example.appAppliance;

import java.util.Arrays;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ApplianceIsicodeApplication {
 

	public static void main(String[] args) {
		SpringApplication.run(ApplianceIsicodeApplication.class, args);
		
	}
	   @Bean
	    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
	        return new KeycloakSpringBootConfigResolver();
	        
	    }
	   @Bean
	    public CorsFilter corsFilter() {
	        CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.setAllowCredentials(true);
	        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:8080/**"));
	        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
	                "Accept", "Authorization", "Origin, Accept", "X-Requested-With","Access-Control-Allow-Origin",
	                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
	        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
	                "Access-Control-Allow-Origin","Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
	        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
	        return new CorsFilter(urlBasedCorsConfigurationSource);
	    }
	

	

}

