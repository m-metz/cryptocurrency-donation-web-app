package com.group13.cryptocurrencywebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

/**
 * <pre>
 * Class Name: EndpointNoSecurity
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Class that disables oauth2 for endpoints definied in the Rest Controller.
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Configuration
public class EndpointNoSecurity {

    /**
     * Bean model used by Spring Boot to override security configuration in the
     * endpoints.
     * 
     * @return WebSecurityCustomizer object that is built with defined
     *         configurations and injected by Spring Boot during startup
     */
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/**");
    }
}
