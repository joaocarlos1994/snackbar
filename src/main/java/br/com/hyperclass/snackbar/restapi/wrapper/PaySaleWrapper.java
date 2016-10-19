/*
 * snackbar 1.0 19 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi.wrapper;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.hyperclass.snackbar.restapi.deserializer.PaySaleDeserialize;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 19 de out de 2016
 */
@JsonDeserialize(using = PaySaleDeserialize.class)
public class PaySaleWrapper {
	
	private final double money;
	private final String typeSale;
	
	public PaySaleWrapper(double money, String typeSale) {
		super();
		this.money = money;
		this.typeSale = typeSale;
	}

	public double getMoney() {
		return money;
	}

	public String getTypeSale() {
		return typeSale;
	}
	
	
	
}
