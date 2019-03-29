package com.example.demo.configuration;

import com.example.demo.filter.AuthFilter;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Configuration for web security
 */
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {
    private ConfigurableJWTProcessor<SecurityContext> processor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter will ignore the below paths
        web
                .ignoring()
                .antMatchers(HttpMethod.GET, "/auth/**");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .antMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(new AuthFilter(processor, authenticationManager()))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }

    public ConfigurableJWTProcessor<SecurityContext> getProcessor() {
        return this.processor;
    }

    public AuthConfig(ConfigurableJWTProcessor<SecurityContext> processor) {
        super();
        this.processor = processor;
    }

}
