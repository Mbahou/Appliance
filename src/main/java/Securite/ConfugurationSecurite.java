//package Securite;
//
//import org.keycloak.adapters.KeycloakDeployment;
//import org.keycloak.adapters.KeycloakDeploymentBuilder;
//import org.keycloak.adapters.spi.HttpFacade;
//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
//import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticatedActionsFilter;
//import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
//import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter;
//import org.keycloak.adapters.springsecurity.filter.KeycloakSecurityContextRequestFilter;
//import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
//import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//@Configuration 
//@EnableWebSecurity 
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
//public class ConfugurationSecurite  extends KeycloakWebSecurityConfigurerAdapter  {
//	
//	  @KeycloakConfiguration
//	  @ConditionalOnProperty(name = "keycloak.enabled", havingValue = "true", matchIfMissing = true)
//	public class CustomKeycloakSpringBootConfigResolver extends KeycloakSpringBootConfigResolver {
//	    private final KeycloakDeployment keycloakDeployment;
//	    public CustomKeycloakSpringBootConfigResolver(KeycloakSpringBootProperties properties) {
//	        keycloakDeployment = KeycloakDeploymentBuilder.build(properties);
//	    }
//	    @Override
//	    public KeycloakDeployment resolve(HttpFacade.Request facade) {
//	        return keycloakDeployment;
//	    }
//	}
//	@Autowired 
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
//        KeycloakAuthenticationProvider keycloakAuthenticationProvider 
//                = keycloakAuthenticationProvider(); 
//        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper( 
//                new SimpleAuthorityMapper()); 
//        auth.authenticationProvider(keycloakAuthenticationProvider); 
//        
//    } 
//	@Bean 
//    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() { 
//        return new KeycloakSpringBootConfigResolver(); 
//    } 
//	
//
//
//	  @Bean 
//
//	    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() { 
////	        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//		  return new NullAuthenticatedSessionStrategy();
//
//	    } 
//	  @Bean
//	    public FilterRegistrationBean<?> keycloakAuthenticationProcessingFilterRegistrationBean(
//	            KeycloakAuthenticationProcessingFilter filter) {
//
//	        FilterRegistrationBean<?> registrationBean = new FilterRegistrationBean<>(filter);
//
//	        registrationBean.setEnabled(false);
//	        return registrationBean;
//	    }
//
//	    @Bean
//	    public FilterRegistrationBean<?> keycloakPreAuthActionsFilterRegistrationBean(
//	            KeycloakPreAuthActionsFilter filter) {
//
//	        FilterRegistrationBean<?> registrationBean = new FilterRegistrationBean<>(filter);
//	        registrationBean.setEnabled(false);
//	        return registrationBean;
//	    }
//
//	    @Bean
//	    public FilterRegistrationBean<?> keycloakAuthenticatedActionsFilterBean(
//	            KeycloakAuthenticatedActionsFilter filter) {
//
//	        FilterRegistrationBean<?> registrationBean = new FilterRegistrationBean<>(filter);
//
//	        registrationBean.setEnabled(false);
//	        return registrationBean;
//	    }
//
//	    @Bean
//	    public FilterRegistrationBean<?> keycloakSecurityContextRequestFilterBean(
//	            KeycloakSecurityContextRequestFilter filter) {
//
//	        FilterRegistrationBean<?> registrationBean = new FilterRegistrationBean<>(filter);
//
//	        registrationBean.setEnabled(false);
//
//	        return registrationBean;
//	    }
//  
//	    @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	        super.configure(http);
//	        http.cors().and().csrf().disable()
//	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//	        .authorizeRequests();
////	        .antMatchers(HttpMethod.POST).hasAnyRole("ADMIN","USER","MANAGER")
////	        .antMatchers(HttpMethod.GET).hasAnyRole("USER","ADMIN","MANAGER")
////	        .antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN","MANAGER")
////	        .antMatchers(HttpMethod.PUT).hasAnyRole("USER", "ADMIN","MANAGER")
////	        .antMatchers("/appliance*").hasAnyRole("ADMIN");  // hasAnyRole("ADMIN")
////	        .antMatchers("POV*").hasAnyRole("USER", "MANAGER", "ADMIN")
////	        .antMatchers("/Seance*").hasAnyRole("USER", "MANAGER", "ADMIN")
////	        .anyRequest().permitAll();
////	        .antMatchers("/appliance/**").hasAnyAuthority("ADMIN");
////	        .anyRequest().authenticated();
//	      
//	        
//	        
//	}
//	    @Bean
//	    @Override
//	    @ConditionalOnMissingBean(HttpSessionManager.class)
//	    protected HttpSessionManager httpSessionManager() {
//	        return new HttpSessionManager();
//	    }
//	
//
//}
