package org.example.myecommerceapp.security;

import org.example.myecommerceapp.rest.oauth2Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private oauth2Controller oauth2SuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) //disabling csrf
                .httpBasic(Customizer.withDefaults()) //login form for postman
                .formLogin(Customizer.withDefaults()) //login form for an app
                .authorizeHttpRequests(cust->cust.requestMatchers(
                                "/api/sign-new/sign_UP","/api/account/{id}",
                                "/api/sign-new/","/api/products/product/{id}",
                                "/api/products/product/","/api/products/add-product",
                                "/api/products/all-products","/api/products/delete-product",
                                "/api/sign-new/update-user{userId}",
                                "/api/products/get-oneProduct",
                                "/api/log/login").permitAll()
                        .anyRequest().authenticated()) //authenticates every request coming to server
                .oauth2Login(Customizer.withDefaults())
                //.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //makes session stateless
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
