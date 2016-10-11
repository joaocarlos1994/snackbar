/*
 * snackbar 1.0 11 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cart;

import br.com.hyperclass.snackbar.domain.product.Products;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 11 de out de 2016
 */
public class ProductsCart {
	
	private final Products products;
	private final int quantity;
	
	public Products getProducts() {
		return products;
	}

	public int getQuantity() {
		return quantity;
	}

	public ProductsCart(Products products, int quantity) {
		super();
		this.products = products;
		this.quantity = quantity;
	}
	
	
	
}
