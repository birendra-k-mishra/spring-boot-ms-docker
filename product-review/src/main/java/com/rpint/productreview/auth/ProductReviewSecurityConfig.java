package com.rpint.productreview.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ProductReviewSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicAuthEntry authEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(getUserDetails());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/review/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/review/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/review/**").hasRole("USER").
                and().httpBasic().authenticationEntryPoint(authEntryPoint);
    }

    private UserDetails getUserDetails() {
        return User.withUsername("birendra").password(passwordEncoder().encode("mishra")).roles("USER").build();
    }

}
