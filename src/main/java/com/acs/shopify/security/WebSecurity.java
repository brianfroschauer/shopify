package com.acs.shopify.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Author: brianfroschauer
 * Date: 2019-05-20
 */
@Configuration
public class WebSecurity {

    @Bean
    public BCryptPasswordEncoder configure() {
        return new BCryptPasswordEncoder();
    }
}
