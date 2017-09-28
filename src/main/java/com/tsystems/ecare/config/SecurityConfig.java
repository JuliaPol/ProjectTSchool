package com.tsystems.ecare.config;

import com.tsystems.ecare.exception.LoginFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Import(DatabaseConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource ecare;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//        authenticationMgr.jdbcAuthentication().usersByUsernameQuery("select username, password, enabled from users where username=?")
//                .authoritiesByUsernameQuery("select u.username, ur.rolename from users u, user_roles ur where u.username = ur.username and u.username =?")
//                .dataSource(ecare);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/login").anonymous()
//                .antMatchers("/driver").anonymous()
//                .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginProcessingUrl("/login").loginPage("/login").failureHandler((httpServletRequest, httpServletResponse, e)  -> {
//            throw new LoginFailedException();
//        }).successForwardUrl("/").permitAll()
//                .and()
//                .logout().logoutSuccessUrl("/login")
//                .and()
//                .csrf().disable();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
}
