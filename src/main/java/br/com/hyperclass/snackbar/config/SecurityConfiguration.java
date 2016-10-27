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
import org.springframework.context.annotation.Configuration;
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

import authentication.AuthenticationListener;
import authentication.PreAuthenticatedUserFilter;
import authentication.jwt.JwtSignatureVerifier;
import authentication.jwt.JwtVerifier;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 22 de out de 2016
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("jwtAuthenticationManager")
	private AuthenticationManager jwtAuthenticationManager;

	@Autowired
	@Qualifier("defaultAuthenticationSuccessHandler")
	private AuthenticationSuccessHandler successHandler;

	@Autowired
	private AuthenticationFailureHandler failureHandler;

	@Autowired
	@Qualifier("providerManager")
	private AuthenticationManager providerManager;

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.addFilter(preAuthenticationFilter());
		http.addFilter(loginFilter());
		http.addFilter(anonymousFilter());
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/menu/**").permitAll()
		.antMatchers("/cashier").authenticated()
		.and().formLogin();
	}

	@Bean
	public AuthenticationManager providerManager(
			@Qualifier("defaultAuthenticationProvider") final AuthenticationProvider provider) {
		return new ProviderManager(Arrays.asList(provider));
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(5);
	}

	/**
	 * A <code>PreAuthenticatedUserFilter</code> extende a
	 * <code>AbstractPreAuthenticatedProcessingFilter</code> onde está classe é
	 * reponsavel por intercepectar todas requisição e extrair o Token
	 * existente. Está classe também implementar o método doFilter que é
	 * reponsável por delegar para o filtro existente a resolucao da requisicao.
	 * 
	 * Assim criando um instância de PreAuthenticatedUserFilter (que é um
	 * Filter) podemos delegar para ele o bean criado jwtAuthenticationManager
	 * onde ele irá retornara se o usuario foi atenticado com sucesso.
	 */
	@Bean
	public Filter preAuthenticationFilter() {
		final PreAuthenticatedUserFilter filter = new PreAuthenticatedUserFilter();
		filter.setAuthenticationManager(jwtAuthenticationManager);
		return filter;
	}

	@Bean
	public Filter anonymousFilter() {
		return new AnonymousAuthenticationFilter("anonymousUser");
	}

	@Bean
	public Filter loginFilter() {
		final UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(providerManager);
		filter.setAuthenticationSuccessHandler(successHandler);
		filter.setAuthenticationFailureHandler(failureHandler);
		return filter;
	}

	@Bean
	@Qualifier("authenticationListeners")
	public List<AuthenticationListener> authenticationListeners(
			@Qualifier("responseHeaderAuthenticationListener") final AuthenticationListener responseHeaderListener) {
		final List<AuthenticationListener> list = new ArrayList<>(1);
		list.add(responseHeaderListener);
		return list;
	}

	@Bean
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
	}

	@Bean
	public JwtVerifier jwtSignatureVerifier(@Value("${jwt.secret}") final String secret) {
		return new JwtSignatureVerifier(secret);
	}

	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
		final MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
		methodInvokingFactoryBean.setTargetMethod("setStrategyName");
		methodInvokingFactoryBean.setArguments(new Object[] { SecurityContextHolder.MODE_INHERITABLETHREADLOCAL });
		return methodInvokingFactoryBean;
	}

}
