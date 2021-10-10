package com.example.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	

	 @Autowired
	 private CustomAuthenticationProvider authenticationProvider;
	 
	 @Autowired
	 private JWTTokenFilter jwtTokenFilter;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) {
	        auth.authenticationProvider(authenticationProvider);
	    }
	    
	    
	   

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.addFilterAfter(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	        http.authorizeRequests().antMatchers("/login").permitAll();
	        http.authorizeRequests().antMatchers("/deny").denyAll();
	        http.authorizeRequests()
	                .anyRequest().authenticated();
	        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        http.csrf().disable();
	    }



        @Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			// TODO Auto-generated method stub
			return super.authenticationManagerBean();
		}
}