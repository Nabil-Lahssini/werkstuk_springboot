package com.example.werkstuk;

import com.example.werkstuk.user.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    Auth auth;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/cart","/addProduct", "/removeProduct", "success")
                .authenticated()
                .and()
                .oauth2Login()
                .successHandler(auth);

                //Ik declareer voor welke routes de gebruikers zich moeten inloggen. Daarna zeg ik dat oauth de authenticatie systeem word.
    }
}

