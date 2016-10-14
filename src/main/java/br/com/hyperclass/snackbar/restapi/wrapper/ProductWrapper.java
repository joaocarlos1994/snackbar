/*
 * snackbar 1.0 14 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi.wrapper;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.hyperclass.snackbar.restapi.deserializer.ProductDeserialize;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 14 de out de 2016
 */
@JsonDeserialize(using = ProductDeserialize.class)
public class ProductWrapper {
	
	private final String name;
	private final double price;
	
	public ProductWrapper(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	
	
}
