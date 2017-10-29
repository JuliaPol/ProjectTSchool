package com.tsystems.ecare.config;

import com.tsystems.ecare.controller.main.MainController;
import com.tsystems.ecare.exception.LoginFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Import(DatabaseConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource ecare;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    public void configureGlobalAuthentication(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").successForwardUrl("/")
                .failureUrl("/login-error").permitAll().usernameParameter("username")
                .passwordParameter("password").loginProcessingUrl("/login")
                .failureHandler((request, response, authException)
                        -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
