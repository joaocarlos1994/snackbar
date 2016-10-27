/*
 * snackbar 1.0 26 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.infrastructure.persist;

import java.util.Map;
import java.util.WeakHashMap;

import org.springframework.stereotype.Component;

import br.com.hyperclass.snackbar.domain.user.PerfilAuthority;
import br.com.hyperclass.snackbar.domain.user.UserRepository;
import br.com.hyperclass.snackbar.domain.user.UserSnack;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 26 de out de 2016
 */
@Component
public class UserSnackPersist implements UserRepository {

	private final Map<String, UserSnack> user;
	
	public UserSnackPersist() {
		super();
		this.user = new WeakHashMap<>();
		setUserSnac();
	}

	@Override
	public UserSnack getByUsername(String name) {
		return user.get(name);
	}
	
	private void setUserSnac() {
		
		final  String joao = "joao";
		final  String augusto = "augusto";
		
		final UserSnack admin = new UserSnack("joao", "$2a$05$BplCFfrzw088NMi1tmuwKuSqu/gZwa8MomN2xE/cwuynaaTVnRX.W", PerfilAuthority.ADMIN);
		final UserSnack counter = new UserSnack("augusto", "$2a$05$Li77Ai/eA7l60B/Zj.qsk.d6r1vKXp6..eHtbpDsgEb/TiEh/SPnu", PerfilAuthority.COUNTER);
		
		user.put(joao, admin);
		user.put(augusto, counter);
		
	}

}
