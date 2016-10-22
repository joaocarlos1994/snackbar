/*
 * snackbar 1.0 22 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.hyperclass.snackbar.infrastructure.repository.UserRepository;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 22 de out de 2016
 */
@EnableWebSecurity
@ComponentScan(basePackages = {"br.com.hyperclass.snackbar.infrastructure"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * Method reponsability for authentic and authority
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		.and().formLogin();
	}
	
	/**
	 * Esse método adiciona um novo usuário no contexto do Spring Security
	 * This method add one new user in contex Spring Security
	 * */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserRepository()).
		passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
