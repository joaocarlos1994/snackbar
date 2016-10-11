/*
 * snackbar 1.0 11 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.product;

/**
 * A classe <code>Products</code> representa uma produto da aplicação. Ela define
 * um construtor com dois parametro nome e preço do produto.
 * 
 * @author João Batista
 * @version 1.0 11 de out de 2016
 */
public class Products {
	
	private final String name;
	private final double price;
	
	public Products(String name, double price) {
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
