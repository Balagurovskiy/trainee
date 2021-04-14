package com.shop;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * After some investigation, it turned out that antMatcher was 
		 * working as expected & allowing all URLs as intended, but the 
		 * reason for the forbidden response that I was getting for the 
		 * POST APIs was that Spring security was waiting for csrf token 
		 * for these POST requests because CSRF protection is enabled by 
		 * default in spring security.
		 * 
		 * So in order to make it work like this, you must provide the csrf 
		 * token in POST request OR you can temporarily turn CSRF protection 
		 * off (but you should enable it again before going to production as 
		 * this is a serious attack)
		 */
	    http
	    		.csrf().disable()
	            .authorizeRequests()
	            .antMatchers("/**").permitAll();
	}
}
