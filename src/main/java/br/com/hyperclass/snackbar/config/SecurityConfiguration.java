/*
 * snackbar 1.0 22 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import authentication.AuthenticationListener;
import authentication.PreAuthenticatedUserFilter;
import authentication.jwt.JwtSignatureVerifier;
import authentication.jwt.JwtVerifier;
import br.com.hyperclass.snackbar.infrastructure.persistence.UserPersistence;
import br.com.hyperclass.snackbar.infrastructure.security.UserSecurityRepository;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 22 de out de 2016
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = { "br.com.hyperclass.snackbar.infrastructure", "authentication", "authentication" })
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * A classe interface <code>AuthenticationManager</code> tenta autenticar um
	 * objeto passado por parametro retornando um object autenticacao preenchida
	 * com seus papeis em caso de sucesso.
	 * 
	 * Classe que implementa deve identificar o método de Autenthenthication de
	 * usuário e direciona-la para AuthenticationProvider apropriado.
	 * 
	 */
	@Autowired
	@Qualifier("jwtAuthenticationManager")
	private AuthenticationManager jwtAuthenticationManager;

	//@Autowired
	//private AuthenticationSuccessHandler successHandler;

	//@Autowired
	//private AuthenticationFailureHandler failureHandler;
	
	@Autowired
	@Qualifier("providerManager")
	private AuthenticationManager providerManager;


	/**
	 * Method reponsability for authentic and authority
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilter(preAuthenticationFilter());
		http.addFilter(loginFilter());
		http.addFilter(anonymousFilter());
		http.csrf().disable();
		http.authorizeRequests().regexMatchers(HttpMethod.GET, "/order").authenticated();
		http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/order").permitAll();
	}

	/**
	 * Esse método adiciona um novo usuário no contexto do Spring Security This
	 * method add one new user in contex Spring Security
	 */
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserService(userRepository)).passwordEncoder(new BCryptPasswordEncoder());
	}*/

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(5);
	}

	@Bean
	public Filter preAuthenticationFilter() {
		final PreAuthenticatedUserFilter filter = new PreAuthenticatedUserFilter();
		filter.setAuthenticationManager(jwtAuthenticationManager);
		return filter;
	}

	@Bean
	public AuthenticationManager providerManager(
			@Qualifier("defaultAuthenticationProvider") final AuthenticationProvider provider) {
		return new ProviderManager(Arrays.asList(provider));
	}

	@Bean
	public Filter loginFilter() {
		final UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(providerManager);
		//filter.setAuthenticationSuccessHandler(successHandler);
		//filter.setAuthenticationFailureHandler(failureHandler);
		return filter;
	}

	@Bean
	public Filter anonymousFilter() {
		return new AnonymousAuthenticationFilter("anonymousUser");
	}

/*	@Bean
	public List<AuthenticationListener> authenticationListeners(
			@Qualifier("responseHeaderAuthenticationListener") final AuthenticationListener responseHeaderListener) {
		final List<AuthenticationListener> list = new ArrayList<>(1);
		list.add(responseHeaderListener);
		return list;
	}*/

/*	@Bean
	public List<JwtVerifier> verifiersList(@Qualifier("issuerReferenceClaimsVerifier") final JwtVerifier issuerVerifier,
			@Qualifier("notBeforeTimeClaimsVerifier") final JwtVerifier notBeforeTimeVerifier,
			@Qualifier("referenceDateClaimsVerifier") final JwtVerifier referenceDateVerifier,
			@Qualifier("jwtSignatureVerifier") final JwtVerifier jwtSignatureVerifier) {
		final List<JwtVerifier> verifiersList = new ArrayList<>(4);
		verifiersList.add(jwtSignatureVerifier);
		verifiersList.add(issuerVerifier);
		verifiersList.add(notBeforeTimeVerifier);
		verifiersList.add(referenceDateVerifier);
		return verifiersList;
	}*/

/*	@Bean
	@Qualifier("jwtSignatureVerifier") 
	public JwtVerifier jwtSignatureVerifier(@Value("${jwt.secret}") final String secret) throws Exception {
		return new JwtSignatureVerifier(secret);
	}*/

/*	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
		final MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
		methodInvokingFactoryBean.setTargetMethod("setStrategyName");
		methodInvokingFactoryBean.setArguments(new Object[] { SecurityContextHolder.MODE_INHERITABLETHREADLOCAL });
		return methodInvokingFactoryBean;
	}*/
	
	@Bean
	public UserSecurityRepository getUserSecurityRepository(){
		final UserPersistence userPersistence = new UserPersistence();
		return userPersistence;
	}

}
