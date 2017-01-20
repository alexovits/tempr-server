package edu.endava.tempr.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by zsoltszabo on 14/12/2016.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(12));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.httpBasic();
        http.authorizeRequests()
                .antMatchers("/user/register/").permitAll()
                .antMatchers("/thermostat/configure/").permitAll()
                .antMatchers("/thermostat/log/").permitAll()
                .antMatchers("/user/").permitAll()
                .antMatchers("/version").permitAll()
                .antMatchers("/thermostat/desiredTemp/").permitAll()
                .anyRequest().authenticated();
        http.csrf().disable();
        // @formatter:on
    }

    //For ignoring the OPTIONS preflights
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}