package com.example.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}pass")
                .roles("USER");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/auth").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)// 1
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)); // 1
    }

    @Bean
    public AuthFilter authenticationFilter() throws Exception {
        AuthFilter filter = new AuthFilter();
        filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler()); // 1
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler()); // 2
        filter.setAuthenticationManager(authenticationManager()); // 3
        return filter;
    }
}
