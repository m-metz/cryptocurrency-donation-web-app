package com.group13.cryptocurrencywebapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * Class Name: CorsConfiguration
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Class that configures bean model for cors security definitions.
 * For this POC Cors is enabling all trafic.
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    /**
     * Method that overrides addCoraMappings from WebMvcConfigurer used by Spring
     * Boot uses to configure Cors configuration mapping
     * 
     * @param registry object of type CorsRegistry that will receive configuration
     *                 parameters.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*");
    }

}