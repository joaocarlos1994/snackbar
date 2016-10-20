/*
 * snackbar 1.0 19 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi.wrapper;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.hyperclass.snackbar.restapi.deserializer.SalesDateDeserialize;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 19 de out de 2016
 */
@JsonDeserialize(using = SalesDateDeserialize.class)
public class SalesDateWrapper {
	
	private final long dateInitial;
	private final long dateFinal;
	
	public SalesDateWrapper(long dateInitial, long dateFinal) {
		super();
		this.dateInitial = dateInitial;
		this.dateFinal = dateFinal;
	}

	public long getDateInitial() {
		return dateInitial;
	}

	public long getDateFinal() {
		return dateFinal;
	}
	
	
	
}
